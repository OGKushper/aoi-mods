package me.mrdaniel.ageofittgard.command;

import me.mrdaniel.ageofittgard.AoIQuests;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class CommandReload implements CommandExecutor {

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        src.sendMessage(Text.of(TextColors.GOLD, "Reloading AgeOfIttgard Quests..."));
        AoIQuests.getInstance().onReload(null);
        src.sendMessage(Text.of(TextColors.GOLD, "Reloaded AgeOfIttgard Quests successfully!"));

        return CommandResult.success();
    }

    public CommandSpec build() {
        return CommandSpec.builder()
                .description(Text.of(TextColors.GOLD, "AoI Quests | Reload"))
                .permission("aoi.quests.admin.reload")
                .executor(this)
                .build();
    }
}
