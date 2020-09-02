package me.mrdaniel.ageofittgard.event;

import me.mrdaniel.ageofittgard.quest.player.ActiveObjective;
import me.mrdaniel.ageofittgard.quest.quest.QuestObjective;
import me.mrdaniel.ageofittgard.quest.player.ActiveQuest;
import org.spongepowered.api.entity.living.player.Player;

public abstract class AbstractQuestObjectiveEvent extends AbstractQuestEvent {

    private final QuestObjective objective;

    public AbstractQuestObjectiveEvent(Player player, ActiveObjective objective) {
        this(player, objective.getActiveQuest(), objective.getObjective());
    }

    public AbstractQuestObjectiveEvent(Player player, ActiveQuest quest, QuestObjective objective) {
        super(player, quest);

        this.objective = objective;
    }

    public QuestObjective getObjective() {
        return this.objective;
    }
}
