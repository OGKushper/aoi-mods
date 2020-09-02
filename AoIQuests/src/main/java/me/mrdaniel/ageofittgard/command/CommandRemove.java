package me.mrdaniel.ageofittgard.command;

import me.mrdaniel.ageofittgard.AoIQuests;
import me.mrdaniel.ageofittgard.quest.player.ActiveQuest;
import me.mrdaniel.ageofittgard.quest.player.PlayerData;
import me.mrdaniel.ageofittgard.quest.quest.Quest;
import me.mrdaniel.npcs.commands.PlayerCommand;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class CommandRemove extends PlayerCommand {

    @Override
    public void execute(Player player, CommandContext args) throws CommandException {
        Integer questId = args.<Integer>getOne("questId").get();
        Quest quest = AoIQuests.getInstance().getQuestManager().getQuest(questId).orElseThrow(() -> new CommandException(Text.of(TextColors.RED, "No quest with that id exists.")));
        PlayerData data = AoIQuests.getInstance().getPlayerDataManager().getPlayerData(player.getUniqueId());

        data.getStarted().remove(questId);
        data.getActive().removeIf(a -> a.getQuest().getQuestId() == questId);
        data.getCompleted().remove(questId);
        data.save();

        AoIQuests.getInstance().getQuestProgressManager().load(player.getUniqueId(), data, new ActiveQuest(quest, null));
    }

    public CommandSpec build() {
        return CommandSpec.builder()
                .description(Text.of(TextColors.GOLD, "AoI | Remove Quest"))
                .permission("aoi.admin.remove")
                .arguments(GenericArguments.integer(Text.of("questId")))
                .executor(this)
                .build();
    }
}
