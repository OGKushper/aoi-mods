package me.mrdaniel.ageofittgard.quest.quest.objective;

import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveTypes;
import me.mrdaniel.ageofittgard.event.CompleteQuestEvent;
import me.mrdaniel.ageofittgard.quest.quest.QuestObjective;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Event;

public class ObjectiveQuest extends QuestObjective {

    private int questId;

    public ObjectiveQuest(int objectiveId) {
        this(objectiveId, 0);
    }

    public ObjectiveQuest(int objectiveId, int questId) {
        super(objectiveId, ObjectiveTypes.QUEST);

        this.questId = questId;
    }

    public int getQuestId() {
        return questId;
    }

    public void setQuestId(int questId) {
        this.questId = questId;
    }

    @Override
    protected boolean evaluateObjective(Player player, Event e) {
        return e instanceof CompleteQuestEvent && ((CompleteQuestEvent) e).getActiveQuest().getQuest().getQuestId() == this.questId;
    }
}
