package me.mrdaniel.ageofittgard.quest.player;

import me.mrdaniel.ageofittgard.quest.quest.QuestObjective;

public class ActiveObjective {

    private ActiveQuest activeQuest;
    private QuestObjective objective;

    public ActiveObjective(ActiveQuest activeQuest, QuestObjective objective) {
        this.activeQuest = activeQuest;
        this.objective = objective;
    }

    public ActiveQuest getActiveQuest() {
        return this.activeQuest;
    }

    public QuestObjective getObjective() {
        return this.objective;
    }
}
