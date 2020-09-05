package me.mrdaniel.ageofittgard.command;

import me.mrdaniel.npcs.commands.PlayerCommand;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class CommandAoI extends PlayerCommand {

    @Override
    public void execute(Player player, CommandContext args) throws CommandException {
        player.sendMessage(Text.of(TextColors.GOLD, "This is the AoI main command.")); // TODO: Implement
    }

    public CommandSpec build() {
        return CommandSpec.builder()
                .description(Text.of(TextColors.GOLD, "AoI Quests | Main Command"))
                .permission("aoi.quests.main")
                .executor(this)
                .child(new CommandReload().build(), "reload")
                .child(new CommandQuestItem().build(), "questitem")
                .child(new CommandRemove().build(), "remove")
                .child(new CommandActivate().build(), "activate")
                .child(new CommandComplete().build(), "complete")
                .build();
    }
}
