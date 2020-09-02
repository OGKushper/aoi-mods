package io.github.OGKushper.aoimods.regions.command;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;

public class RegionGenerateCommand implements CommandExecutor{
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		Text msg = Text.of("To use the region command:\n "
				+ "use '/region get' to get the current region name"
				+ "use '/region rename [new name]' to rename the current region"
				+ "use '/region origin [X] [Y]' to set the point of origin for region generation"
				+ "use '/region generate' to start region generation (will override any previous region generations)");
		src.sendMessage(msg);
		return CommandResult.success();
	}
}
