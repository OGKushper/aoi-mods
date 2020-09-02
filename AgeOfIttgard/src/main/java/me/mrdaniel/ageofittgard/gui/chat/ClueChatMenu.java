package me.mrdaniel.ageofittgard.gui.chat;

import com.google.common.collect.Lists;
import me.mrdaniel.ageofittgard.AgeOfIttgard;
import me.mrdaniel.ageofittgard.exception.InvalidQuestException;
import me.mrdaniel.ageofittgard.gui.inventory.QuestListInventoryMenu;
import me.mrdaniel.ageofittgard.quest.quest.Quest;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import java.util.List;
import java.util.function.Consumer;

public class ClueChatMenu extends AbstractChatMenu {

    protected Quest quest;
    private boolean completed;

    public ClueChatMenu(Player player, Quest quest) {
        this.setPlayer(player);
        this.setTitle(Text.of(TextColors.AQUA, TextStyles.BOLD, "Write down clue?"));
        this.setHeader(null);
        this.setFooter(null);

        this.quest = quest;
        this.completed = false;
    }

    @Override
    public List<Text> getContents() {
        List<Text> lines = Lists.newArrayList();

        Text yes = Text.builder().append(Text.of(TextColors.GREEN, "Write down clue"))
                .onHover(TextActions.showText(Text.of(TextColors.GREEN, "Click to write down the clue.")))
                .onClick(TextActions.executeCallback(this.getYesAction()))
                .build();

        Text no = Text.builder().append(Text.of(TextColors.RED, "Ignore clue."))
                .onHover(TextActions.showText(Text.of(TextColors.RED, "Click to ignore the clue.")))
                .onClick(TextActions.executeCallback(src -> this.completed = true))
                .build();

        lines.addAll(this.quest.getStartClues());
        lines.add(Text.of(TextColors.AQUA, "Do you want to write this clue down in your logbook?"));
        lines.add(Text.of(yes, "   ", no));

        return lines;
    }

    private Consumer<CommandSource> getYesAction() {
        return src -> {
            if (!this.completed) {
                this.completed = true;
                try {
                    Player player = (Player) src;
                    AgeOfIttgard.getInstance().getQuestProgressManager().startQuest(player, this.quest);
                    new QuestListInventoryMenu(player).setNewQuest(this.quest).open();
                } catch (InvalidQuestException exc) {
                    AgeOfIttgard.getInstance().getLogger().error("Failed to start quest", exc);
                }
            }
        };
    }
}
