package me.mrdaniel.ageofittgard.event;

import me.mrdaniel.ageofittgard.quest.player.ActiveObjective;
import me.mrdaniel.ageofittgard.quest.quest.QuestObjective;
import me.mrdaniel.ageofittgard.quest.player.ActiveQuest;
import org.spongepowered.api.entity.living.player.Player;

public class CompleteQuestObjectiveEvent extends AbstractQuestObjectiveEvent {

    public CompleteQuestObjectiveEvent(Player player, ActiveObjective objective) {
        super(player, objective);
    }

    public CompleteQuestObjectiveEvent(Player player, ActiveQuest quest, QuestObjective objective) {
        super(player, quest, objective);
    }
}
