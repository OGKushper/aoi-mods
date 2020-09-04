package io.github.OGKushper.aoimods.regions.command;

import java.util.Optional;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextStyles;
import org.spongepowered.api.world.Location;

import io.github.OGKushper.aoimods.regions.RegionManager;

public class RegionOriginCommand implements CommandExecutor{
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		Optional<Integer> ox = args.getOne("x");
		Optional<Integer> oz = args.getOne("z");
		int x = 0;
		int z = 0;
		if(ox.isPresent()&&oz.isPresent()) {
			x = ox.get();
			z = oz.get();
		}else {
			if(!oz.isPresent()&&!ox.isPresent()) {
				if(src instanceof Player) {
					Player p = (Player) src;
					x = p.getLocation().getBlockX();
					z = p.getLocation().getBlockZ();
				}else {
					src.sendMessage(Text.of("Cannot set region point at your location, as you are not a player."));
					return CommandResult.empty();
				}
			}else {
				src.sendMessage(Text.of("please format as following: /region origin [X] [Z]"));
				return CommandResult.empty();
			}
			
		}
		RegionManager.originX = x;
		RegionManager.originZ = z;
		Text msg = Text.builder("Region origin point set to location at X = ")
				.append(Text.builder(Integer.toString(x)).style(TextStyles.BOLD).build())
				.append(Text.builder(", Z = ").build())
				.append(Text.builder(Integer.toString(z)).style(TextStyles.BOLD).build()).build();
		src.sendMessage(msg);
		return CommandResult.success();
	}
}
