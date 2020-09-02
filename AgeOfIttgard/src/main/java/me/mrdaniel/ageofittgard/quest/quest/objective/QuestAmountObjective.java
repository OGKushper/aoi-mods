package me.mrdaniel.ageofittgard.quest.quest.objective;

import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveType;
import me.mrdaniel.ageofittgard.quest.player.ActiveQuest;
import me.mrdaniel.ageofittgard.quest.quest.QuestObjective;

public abstract class QuestAmountObjective extends QuestObjective {

    private int amount;

    public QuestAmountObjective(ObjectiveType type) {
        super(type);
    }

    public QuestAmountObjective(int objectiveId, ObjectiveType type) {
        super(objectiveId, type);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean isCompleted(ActiveQuest active) {
        return active.getProgress(this.objectiveId) >= this.amount;
    }
}
