package me.mrdaniel.ageofittgard.gui.chat;

import me.mrdaniel.ageofittgard.quest.dialogue.DialogueLink;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueNode;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueRunner;
import me.mrdaniel.npcs.gui.chat.AbstractChatMenu;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.ClickAction;
import org.spongepowered.api.text.action.HoverAction;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.chat.ChatTypes;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import java.util.List;
import java.util.stream.Collectors;

public class DialogueChoiceMenu extends AbstractChatMenu {

    private DialogueRunner runner;
    private DialogueNode node;
    private boolean completed;

    public DialogueChoiceMenu(DialogueRunner runner, DialogueNode node) {
        this.setPlayer(runner.getPlayer());
        this.setTitle(Text.of(TextColors.GOLD, TextStyles.BOLD, "Make a choice."));

        this.runner = runner;
        this.node = node;
        this.completed = false;
    }

    @Override
    protected List<Text> getContents() {
        return this.node.getLinks()
                .stream()
                .map(this.runner.getDialogue().getLinks()::get)
                .map(this::getLineText).collect(Collectors.toList());
    }

    private Text getLineText(DialogueLink link) {
         return Text.builder()
                .append(link.getChoiceLine())
                .onHover(this.getHoverAction(link))
                .onClick(this.getClickAction(link))
                .build();
    }

    private HoverAction.ShowText getHoverAction(DialogueLink link) {
        return TextActions.showText(this.runner.getDialogue().metRequirements(super.player, link, false) ? Text.of(TextColors.DARK_GREEN, "Click to choose this option.") : Text.of(TextColors.RED, "Conditions not met!"));
    }

    private ClickAction.ExecuteCallback getClickAction(DialogueLink link) {
        return TextActions.executeCallback(src -> {
            if (!this.completed) {
                if (this.runner.getDialogue().metRequirements(super.player, link, true)) {
                    this.completed = true;
                    this.runner.runLink(link);
                } else {
                    super.player.sendMessage(ChatTypes.ACTION_BAR, Text.of(TextColors.RED, TextStyles.BOLD, "Conditions not met!"));
                }
            }
        });
    }
}
