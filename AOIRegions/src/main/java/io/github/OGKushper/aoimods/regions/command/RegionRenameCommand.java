package io.github.OGKushper.aoimods.regions.command;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextStyles;

import io.github.OGKushper.aoimods.regions.RegionManager;

public class RegionRenameCommand implements CommandExecutor{
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		String a = (String) args.getOne("name1").get();
		String b = "";
		if(args.getOne("name2").isPresent()) {
			b = (String) args.getOne("name2").get();
			
		}
		if(src instanceof Player) {
			Player p = (Player) src;
			b = RegionManager.getPlayerRegionName(p.getUniqueId());
		}else {
			src.sendMessage(Text.of("Please format command as follows: /region rename [old name] [new name]"));
		}
		boolean succesful = RegionManager.regionMap.renameRegion(a, b);
		if(!succesful) {
			src.sendMessage(Text.of("No region found to rename"));
			return CommandResult.empty();
		}
		Text msg = Text.builder("Succesfully renamed region ")
				.append(Text.builder(a).style(TextStyles.BOLD).build())
				.append(Text.builder(" to ").build())
				.append(Text.builder(b).style(TextStyles.BOLD).build()).build();
		src.sendMessage(msg);
		return CommandResult.success();
	}
}
