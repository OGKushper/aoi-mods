package me.mrdaniel.ageofittgard.listener.objective;

import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveTypes;
import me.mrdaniel.ageofittgard.event.CompleteQuestEvent;
import me.mrdaniel.ageofittgard.quest.quest.objective.ObjectiveQuest;
import org.spongepowered.api.event.Listener;

public class QuestListener extends AbstractObjectiveListener<ObjectiveQuest> {

    public QuestListener() {
        super(ObjectiveTypes.QUEST);
    }

    @Listener
    public void onQuestCompletion(CompleteQuestEvent e) {
        super.checkObjectives(e.getPlayer(), e);
    }
}
