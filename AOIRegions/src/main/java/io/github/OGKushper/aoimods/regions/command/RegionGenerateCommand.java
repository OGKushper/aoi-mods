package io.github.OGKushper.aoimods.regions.command;

import java.util.Optional;
import java.util.function.Consumer;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextStyles;

import io.github.OGKushper.aoimods.regions.RegionManager;
import io.github.OGKushper.aoimods.regions.region.PlayerRegionMonitor;
import io.github.OGKushper.aoimods.regions.region.RegionGenerator;

public class RegionGenerateCommand implements CommandExecutor{
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if(RegionManager.originX == null || RegionManager.originZ == null) {
			src.sendMessage(Text.of("Please define origin point before commencing region generation. Use /region origin [X] [Z]"));
			return CommandResult.empty();
		}
		PlayerRegionMonitor monitor = new PlayerRegionMonitor((Player) src, RegionManager.regionMap);
		Text msg = Text.builder("Are you sure you want to commence region generation? \n"
				+ " this will reset all current regions. \n"
				+ "click ").append(Text.builder("HERE").style(TextStyles.BOLD).onClick(TextActions.executeCallback(new Consumer<CommandSource>() {
					public void accept(CommandSource t) {
						Task.builder().execute(new RegionGenerator(RegionManager.originX, RegionManager.originZ)).async().submit(RegionManager.plugin);
					}})).build())
				.append(Text.builder("to commence region generation").build()).build();
		src.sendMessage(msg);
		return CommandResult.success();
	}
}
