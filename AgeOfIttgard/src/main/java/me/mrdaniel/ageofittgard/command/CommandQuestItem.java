package me.mrdaniel.ageofittgard.command;

import me.mrdaniel.ageofittgard.catalogtypes.questitem.QuestItem;
import me.mrdaniel.npcs.commands.PlayerCommand;
import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class CommandQuestItem extends PlayerCommand {

    @Override
    public void execute(Player player, CommandContext args) throws CommandException {
        player.getInventory().offer(args.<QuestItem>getOne("item").get().build());
    }

    public CommandCallable build() {
        return CommandSpec.builder()
                .description(Text.of(TextColors.GOLD, "AoI | QuestItem Command"))
                .permission("aoi.questitem")
                .executor(this)
                .arguments(GenericArguments.catalogedElement(Text.of("item"), QuestItem.class))
                .build();
    }
}
