package me.mrdaniel.ageofittgard.command;

import me.mrdaniel.ageofittgard.AgeOfIttgard;
import me.mrdaniel.ageofittgard.quest.player.PlayerData;
import me.mrdaniel.npcs.commands.PlayerCommand;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class CommandComplete extends PlayerCommand {

    @Override
    public void execute(Player player, CommandContext args) throws CommandException {
        Integer questId = args.<Integer>getOne("questId").get();
        PlayerData data = AgeOfIttgard.getInstance().getPlayerDataManager().getPlayerData(player.getUniqueId());

        if (!data.getStarted().contains(questId)) {
            data.getStarted().add(questId);
        }
        data.getActive().removeIf(active -> active.getQuest().getQuestId() == questId);
        if (!data.getCompleted().contains(questId)) {
            data.getCompleted().add(questId);
        }

        data.save();
    }

    public CommandSpec build() {
        return CommandSpec.builder()
                .description(Text.of(TextColors.GOLD, "AoI | Complete Quest"))
                .permission("aoi.admin.complete")
                .arguments(GenericArguments.integer(Text.of("questId")))
                .executor(this)
                .build();
    }
}
