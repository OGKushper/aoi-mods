package me.mrdaniel.ageofittgard.command;

import me.mrdaniel.ageofittgard.gui.inventory.QuestListInventoryMenu;
import me.mrdaniel.npcs.commands.PlayerCommand;
import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class CommandLogbook extends PlayerCommand {

    @Override
    public void execute(Player player, CommandContext commandContext) throws CommandException {
        new QuestListInventoryMenu(player).open();
    }

    public CommandCallable build() {
        return CommandSpec.builder()
                .description(Text.of(TextColors.GOLD, "AoI | Logbook Command"))
                .permission("aoi.logbook")
                .executor(this)
                .build();
    }
}
