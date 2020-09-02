package me.mrdaniel.ageofittgard.gui.book;

import com.google.common.collect.Lists;
import me.mrdaniel.ageofittgard.AoIQuests;
import me.mrdaniel.ageofittgard.quest.player.ActiveQuest;
import me.mrdaniel.npcs.gui.book.AbstractBookMenu;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import java.util.List;

public class QuestListBookMenu extends AbstractBookMenu {

    public QuestListBookMenu(Player player) {
        super.setPlayer(player);
        super.setTitle(Text.of(TextColors.DARK_GRAY, TextStyles.BOLD, "Logbook"));
        super.setAuthor(Text.of(TextColors.DARK_GRAY, TextStyles.BOLD, player.getName()));
    }

    @Override
    protected List<Text> getPages() {
        List<Text> pages = Lists.newArrayList();

        AoIQuests.getInstance().getPlayerDataManager().getPlayerData(super.player.getUniqueId()).getActive().forEach(a -> pages.add(this.getPage(a)));

        return pages;
    }

    private Text getPage(ActiveQuest active) {
        Text.Builder page = Text.builder().append(Text.of(active.getQuest().getName()), Text.NEW_LINE);

        for (int i = 1; i < active.getStage().getStageId(); i++) {
            page.append(active.getQuest().getStage(i).get().getPostDesc()).append(Text.NEW_LINE);
        }

        return page.append(active.getStage().getPreDesc()).build();
    }

//    private Text getPage(IQuest quest) {
//        Text.Builder page = Text.builder().append(Text.of(quest.getName(), Text.NEW_LINE));
//
//        for (IQuestStage stage : quest.getStages()) {
//
//            for (Text hint : stage.getHints()) {
//                page.append(hint).append(Text.NEW_LINE);
//            }
//            page.append(Text.NEW_LINE);
//        }
//
//        return page.build();
//    }
}
