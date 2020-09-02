package me.mrdaniel.ageofittgard.manager;

import com.google.common.collect.Lists;
import me.mrdaniel.ageofittgard.AoIQuests;
import me.mrdaniel.ageofittgard.event.CompleteQuestEvent;
import me.mrdaniel.ageofittgard.event.CompleteQuestObjectiveEvent;
import me.mrdaniel.ageofittgard.event.CompleteQuestStageEvent;
import me.mrdaniel.ageofittgard.exception.InvalidQuestException;
import me.mrdaniel.ageofittgard.gui.chat.ClueChatMenu;
import me.mrdaniel.ageofittgard.listener.objective.*;
import me.mrdaniel.ageofittgard.quest.player.ActiveObjective;
import me.mrdaniel.ageofittgard.quest.player.ActiveQuest;
import me.mrdaniel.ageofittgard.quest.player.PlayerData;
import me.mrdaniel.ageofittgard.quest.quest.Quest;
import me.mrdaniel.ageofittgard.quest.quest.QuestObjective;
import me.mrdaniel.ageofittgard.quest.quest.QuestStage;
import me.mrdaniel.ageofittgard.util.CauseUtils;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.type.CarriedInventory;
import org.spongepowered.api.service.economy.EconomyService;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.chat.ChatTypes;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class QuestProgressManager {

    private List<AbstractObjectiveListener> objectiveListeners;

    public QuestProgressManager() {
        this.objectiveListeners = Lists.newArrayList();
    }

    /**
     * Loads all the objective listeners.
     */
    public void load() {
        this.objectiveListeners.clear();
        this.objectiveListeners.add(new NPCListener());
        this.objectiveListeners.add(new PositionListener());
        this.objectiveListeners.add(new EntityKillListener());
        this.objectiveListeners.add(new QuestListener());
        this.objectiveListeners.add(new CollectListener());

        this.objectiveListeners.forEach(l -> Sponge.getEventManager().registerListeners(AoIQuests.getInstance(), l));
    }

    /**
     * Loads all active objectives into the objective listeners.
     * Also loads quest triggers if the quest hasnt been started yet.
     * Should be called when a player joins the game.
     *
     * @param uuid The uuid of a Player
     * @param data The player's PlayerData
     */
    public void load(UUID uuid, PlayerData data) {
        for (Quest quest : AoIQuests.getInstance().getQuestManager().getAllQuests()) {
            if (!data.isCompleted(quest)) {
                this.load(uuid, data, data.getActive(quest).orElseGet(() -> data.setup(quest)));
            }
        }
    }

    /**
     * Loads the active objectives of a specific quest into the objective listeners.
     * Also loads the quest's trigger if the quest hasn't been started yet.
     * Should be called when a player starts a new quest, or started a new stage of a quest.
     *
     * @param uuid The uuid of a Player
     * @param data The player's PlayerData
     * @param active The quest to load
     */
    public void load(UUID uuid, PlayerData data, ActiveQuest active) {
        active.load(data);

        if (active.getStage() == null) {
            this.objectiveListeners.forEach(l -> l.add(uuid, new ActiveObjective(active, active.getQuest().getTrigger())));
        } else {
            active.getStage().getObjectives().forEach(obj -> this.objectiveListeners.forEach(l -> l.add(uuid, new ActiveObjective(active, obj))));
        }
    }

    /**
     * Removes all active objectives from the objective listeners.
     * Should be called when a player leaves the game.
     *
     * @param uuid The uuid of a player
     */
    public void unload(UUID uuid) {
        this.objectiveListeners.forEach(l -> l.unload(uuid));
    }

    /**
     * Removes a specific objective from the objective listeners.
     *
     * @param uuid The uuid of a player
     * @param objective The objective
     */
    public void unload(UUID uuid, QuestObjective objective) {
        this.objectiveListeners.forEach(l -> l.unload(uuid, objective));
    }

    /**
     * Starts a quest for a player.
     * Usually called by the Objective Listeners after completing a quest trigger.
     *
     * @param player The player
     * @param quest The quest
     * @throws InvalidQuestException
     */
    public void startQuest(Player player, Quest quest) throws InvalidQuestException {
        PlayerData data = AoIQuests.getInstance().getPlayerDataManager().getPlayerData(player.getUniqueId());
        if (data.isStarted(quest)) {
            return;
        }

        ActiveQuest active = data.getActive(quest).orElseGet(() -> data.setup(quest));
        active.load(data);
        active.setStage(quest.getStage(1).orElseThrow(InvalidQuestException::new));
        active.getProgress().clear();

        data.start(active);
        data.save();

        this.load(player.getUniqueId(), data, active);
    }

    /**
     * Only supposed to be called by the objective listeners.
     * Increases the objective amounts. Only used for objective types that support amounts.
     * Also handles stage progression and quest completion.
     *
     * @param player The player
     * @param active The active quest
     * @param objective The objective
     * @return boolean value whether to remove the current ActiveObjective
     */
    public boolean increaseObjective(Player player, ActiveQuest active, QuestObjective objective) {
        active.setProgress(objective.getObjectiveId(), active.getProgress(objective.getObjectiveId()) + 1);

        boolean completed = objective.isCompleted(active);
        if (completed) {
            this.completeObjective(player, active, objective);
        } else {
            active.getPlayerData().save();
        }

        return completed;
    }

    private void completeObjective(Player player, ActiveQuest active, QuestObjective objective) {
        if (Sponge.getEventManager().post(new CompleteQuestObjectiveEvent(player, active, objective))) {
            return;
        }

        if (active.getStage() == null) {
            new ClueChatMenu(player, active.getQuest()).open();
        } else if (active.getStage().isCompleted(active)) {
            this.completeStage(player, active);
        } else {
            active.getPlayerData().save();
        }
    }

    private void completeStage(Player player, ActiveQuest active) {
        if (Sponge.getEventManager().post(new CompleteQuestStageEvent(player, active))) {
            return;
        }

        QuestStage nextStage = active.getQuest().getStage(active.getStage() == null ? 1 : active.getStage().getStageId() + 1).orElse(null);
        if (nextStage == null) {
            this.completeQuest(player, active);
        } else {
            player.sendMessage(Text.of(" "));
            player.sendMessage(Text.of(TextColors.GRAY, TextStyles.ITALIC, "You found a new clue to write in your logbook.")); // TODO: Improve messaging

            active.setStage(nextStage);
            active.getPlayerData().save();

            // Loads the next stage's objectives into the objective listeners
            this.load(player.getUniqueId(), active.getPlayerData(), active);
        }
    }

    private void completeQuest(Player player, ActiveQuest active) {
        if (Sponge.getEventManager().post(new CompleteQuestEvent(player, active))) {
            return;
        }

        player.sendMessage(Text.of(" "));
        player.sendMessage(Text.of(TextColors.RED, TextStyles.BOLD, "You completed the quest ", active.getQuest().getName(), "!")); // TODO: Improve messaging

        this.offerRewards(player, active);
    }

    private void offerRewards(Player player, ActiveQuest active) {

        // Add reward items to inventory TODO: Discuss how to implement reward items
        PlayerData playerData = active.getPlayerData();
        CarriedInventory<?> inv = player.getInventory();

        boolean inventoryFull = false;
        for (ItemStack item : active.getQuest().getRewards()) {
            ItemStack copy = item.copy();

            if (!inventoryFull && inv.canFit(copy)) {
                inv.offer(copy);
            } else {
                playerData.addUnclaimed(copy);
                if (!inventoryFull) {
                    inventoryFull = true;
                    player.sendMessage(ChatTypes.ACTION_BAR, Text.of(TextColors.RED, TextStyles.BOLD, "No space in inventory. Open logbook to claim rewards!"));
                }
            }
        }

        if (inventoryFull) {
            playerData.save();
        }

        // Add reward money to player's balance
        Sponge.getServiceManager().provide(EconomyService.class).ifPresent(econ -> {
            econ.getOrCreateAccount(player.getUniqueId()).get().deposit(econ.getDefaultCurrency(), new BigDecimal(active.getQuest().getRewardMoney()), CauseUtils.getPluginCause());
        });

        active.getPlayerData().complete(active);
        active.getPlayerData().save();
        active.unload();
    }
}
