package me.mrdaniel.ageofittgard.quest.player;

import me.mrdaniel.ageofittgard.quest.quest.QuestObjective;

public class ActiveObjective<T extends QuestObjective> {

    private ActiveQuest activeQuest;
    private T objective;

    public ActiveObjective(ActiveQuest activeQuest, T objective) {
        this.activeQuest = activeQuest;
        this.objective = objective;
    }

    public ActiveQuest getActiveQuest() {
        return this.activeQuest;
    }

    public T getObjective() {
        return this.objective;
    }
}
