package me.mrdaniel.ageofittgard.gui.inventory;

import com.google.common.collect.Lists;
import me.mrdaniel.ageofittgard.AoIQuests;
import me.mrdaniel.ageofittgard.catalogtypes.queststatus.QuestStatus;
import me.mrdaniel.ageofittgard.catalogtypes.queststatus.QuestStatusus;
import me.mrdaniel.ageofittgard.quest.player.ActiveQuest;
import me.mrdaniel.ageofittgard.quest.player.PlayerData;
import me.mrdaniel.ageofittgard.quest.quest.Quest;
import me.mrdaniel.ageofittgard.quest.quest.QuestStage;
import me.mrdaniel.npcs.gui.inventory.AbstractInventoryListMenu;
import me.mrdaniel.npcs.gui.inventory.Button;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.enchantment.Enchantment;
import org.spongepowered.api.item.enchantment.EnchantmentTypes;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class QuestListInventoryMenu extends AbstractInventoryListMenu {

    private QuestStatus filter;
    private Quest newQuest;

    public QuestListInventoryMenu(Player player) {
        this.filter = null;
        this.newQuest = null;

        super.setPlayer(player);
        super.setArchetype(InventoryArchetypes.CHEST);
        super.setTitle(Text.of(TextColors.DARK_GRAY, TextStyles.BOLD, "Logbook"));
        super.setSize(9, 6);
        super.setModifierInterval(10);
    }

    public QuestListInventoryMenu setNewQuest(Quest quest) {
        this.newQuest = quest;
        return this;
    }

    @Override
    protected List<Button> getListButtons() {
        List<Button> buttons = Lists.newArrayList();

        PlayerData data = AoIQuests.getInstance().getPlayerDataManager().getPlayerData(super.player.getUniqueId());
        List<Integer> quests = data.getStarted();
        ListIterator<Integer> iter = quests.listIterator(quests.size());

        while (iter.hasPrevious()) {
            Quest quest = AoIQuests.getInstance().getQuestManager().getQuest(iter.previous()).orElse(null);
            if (quest == null) {
                continue;
            }

            boolean blinking = this.newQuest != null && this.newQuest.getQuestId() == quest.getQuestId();
            boolean started = data.isStarted(quest);
            boolean completed = data.isCompleted(quest);
            QuestStatus questStatus = completed ? QuestStatusus.COMPLETED : started ? QuestStatusus.ACTIVE : QuestStatusus.AVAILABLE;

            // TODO: Decide whether to include available quests or not
            if (questStatus == QuestStatusus.AVAILABLE) {
                continue;
            }

            if (this.filter != null && this.filter != questStatus) {
                continue;
            }

            Button button = new Button()
                    .setItemStack(ItemStack.builder()
                            .itemType(questStatus.getItemType())
                            .quantity(1)
                            .add(Keys.DISPLAY_NAME, quest.getName())
                            .add(Keys.ITEM_LORE, completed ? this.getCompletedLore(quest) : started ? this.getActiveLore(data.getActive(quest).get()) : Lists.newArrayList())
                            .add(Keys.HIDE_ENCHANTMENTS, true)
                            .build());
            if (blinking) {
                button.setModifier(item -> {
                    if (item.get(Keys.ITEM_ENCHANTMENTS).orElse(Lists.newArrayList()).isEmpty()) {
                        item.offer(Keys.ITEM_ENCHANTMENTS, Lists.newArrayList(Enchantment.builder().type(EnchantmentTypes.MENDING).level(1).build()));
                    } else {
                        item.offer(Keys.ITEM_ENCHANTMENTS, Lists.newArrayList());
                    }
                });
            }

            buttons.add(button);
        }

        return buttons;
    }

    private List<Text> getActiveLore(ActiveQuest active) {
        List<Text> lore = Lists.newArrayList();

        for (int i = 1; i < active.getStage().getStageId(); i++) {
            lore.add(active.getQuest().getStage(i).get().getPostDesc());
        }
        lore.add(active.getStage().getPreDesc());

        return lore;
    }

    private List<Text> getCompletedLore(Quest quest) {
        return quest.getStages().stream().map(QuestStage::getPostDesc).collect(Collectors.toList());
    }

    @Override
    protected List<Button> getMenuButtons() {
        List<Button> buttons = Lists.newArrayList();

        buttons.add(new Button()
                .setItemStack(ItemStack.builder()
                        .itemType(ItemTypes.BARRIER)
                        .quantity(1)
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.GRAY, "No Filter"))
                        .build())
                .setLeftAction((p, s) -> { this.filter = null; this.changePage(1); }));

        buttons.add(Button.spacer());

        buttons.add(new Button()
                .setItemStack(ItemStack.builder()
                        .itemType(ItemTypes.BOOK)
                        .quantity(1)
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.GRAY, "Filter Available Quests"))
                        .build())
                .setLeftAction((p, s) -> { this.filter = QuestStatusus.AVAILABLE; this.changePage(1); }));

        buttons.add(new Button()
                .setItemStack(ItemStack.builder()
                        .itemType(ItemTypes.WRITABLE_BOOK)
                        .quantity(1)
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.GRAY, "Filter Active Quests"))
                        .build())
                .setLeftAction((p, s) -> { this.filter = QuestStatusus.ACTIVE; this.changePage(1); }));

        buttons.add(new Button()
                .setItemStack(ItemStack.builder()
                        .itemType(ItemTypes.ENCHANTED_BOOK)
                        .quantity(1)
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.GRAY, "Filter Completed Quests"))
                        .build())
                .setLeftAction((p, s) -> { this.filter = QuestStatusus.COMPLETED; this.changePage(1); }));

        return buttons;
    }
}
