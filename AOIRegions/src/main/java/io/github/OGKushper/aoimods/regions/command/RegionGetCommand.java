package io.github.OGKushper.aoimods.regions.command;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import io.github.OGKushper.aoimods.regions.RegionManager;

public class RegionGetCommand implements CommandExecutor{
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if (src instanceof Player) {
			Player p = (Player) src;
			Integer region = RegionManager.playerRegions.get(p.getUniqueId());
			if(region == null) {src.sendMessage(Text.of("No region data found for player")); return CommandResult.empty();}
			src.sendMessage(Text.of(region));
			return CommandResult.success();
		}
		return CommandResult.empty();
		
	}
}
