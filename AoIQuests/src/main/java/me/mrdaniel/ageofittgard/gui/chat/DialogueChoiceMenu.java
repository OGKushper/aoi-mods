package me.mrdaniel.ageofittgard.gui.chat;

import com.google.common.collect.Lists;
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
        List<Text> lines = Lists.newArrayList();

        this.node.getLinks().forEach(l -> lines.add(this.getLineText(l)));

        return lines;
    }

    private Text getLineText(DialogueLink link) {
         return Text.builder()
                .append(link.getChoiceLine())
                .onHover(this.getHoverAction(link))
                .onClick(this.getClickAction(link))
                .build();
    }

    private HoverAction.ShowText getHoverAction(DialogueLink link) {
        return TextActions.showText(link.metRequirements(super.player, false) ? Text.of(TextColors.DARK_GREEN, "Click to choose this option.") : Text.of(TextColors.RED, "Conditions not met!"));
    }

    private ClickAction.ExecuteCallback getClickAction(DialogueLink link) {
        return TextActions.executeCallback(src -> {
            if (!this.completed) {
                if (link.metRequirements(super.player, true)) {
                    this.completed = true;
                    this.runner.runLink(link);
                } else {
                    super.player.sendMessage(ChatTypes.ACTION_BAR, Text.of(TextColors.RED, TextStyles.BOLD, "Conditions not met!"));
                }
            }
        });
    }
}
