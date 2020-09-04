package io.github.OGKushper.aoimods.regions.command;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class RegionCommandRegisterer {

	public static void Register(Object plugin) {

		CommandSpec regionGetCommand = CommandSpec.builder()
				.description(Text.of("gets the current region"))
				.permission("aoiregions.command.region.get")
				.executor(new RegionGetCommand())
				.build();
		CommandSpec regionRenameCommand = CommandSpec.builder()
				.description(Text.of("renames the current region. usage: /region rename [new name]"))
				.permission("aoiregions.command.region.rename")
				.arguments(GenericArguments.onlyOne(GenericArguments.string(Text.of("name1"))), GenericArguments.optional(GenericArguments.string(Text.of("name2"))))
				.executor(new RegionRenameCommand())
				.build();
		CommandSpec regionOriginCommand = CommandSpec.builder()
				.description(Text.of("sets the point of origin for region generation. usage: '/region origin [x] [y]' OR '/region origin' (uses player location)"))
				.permission("aoiregions.command.region.generate")
				.arguments(GenericArguments.optional(GenericArguments.integer(Text.of("x"))), GenericArguments.optional(GenericArguments.integer(Text.of("z"))))
				.executor(new RegionOriginCommand())
				.build();
		CommandSpec regionGenerateCommand = CommandSpec.builder()
				.description(Text.of("Generates a full region map from point of origin"))
				.permission("aoiregions.command.region.generate")
				.executor(new RegionGenerateCommand())
				.build();
		
		CommandSpec regionCommand = CommandSpec.builder()
				.description(Text.of("command used for getting, renaming and generating Regions."))
				.permission("aoiregions.command.region")
				.child(regionGetCommand, "get", "g")
				.child(regionRenameCommand, "rename", "set", "name")
				.child(regionOriginCommand, "origin", "source", "o")
				.child(regionGenerateCommand, "generate")
				.executor(new RegionCommand())
				.build();
		
		Sponge.getCommandManager().register(plugin, regionCommand, "region");
		
	}
}
