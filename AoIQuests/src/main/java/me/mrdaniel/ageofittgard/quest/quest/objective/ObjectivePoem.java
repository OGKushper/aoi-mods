package me.mrdaniel.ageofittgard.quest.quest.objective;

import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveTypes;
import me.mrdaniel.ageofittgard.quest.quest.QuestObjective;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Event;

public class ObjectivePoem extends QuestObjective {

    public ObjectivePoem(int objectiveId) {
        super(objectiveId, ObjectiveTypes.POEM);
    }

    @Override
    public boolean evaluate(Player player, Event e) {
        

        return false;
    }
}