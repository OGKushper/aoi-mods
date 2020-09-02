package me.mrdaniel.ageofittgard.quest.quest.objective;

import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveTypes;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.entity.DestructEntityEvent;

public class ObjectiveKill extends QuestAmountObjective {

    // TODO: Add additional variables to allow custom mobs
    private EntityType entityType;

    public ObjectiveKill(int objectiveId) {
        this(objectiveId, null, 0);
   }

    public ObjectiveKill(int objectiveId, EntityType entityType, int amount) {
        super(objectiveId, ObjectiveTypes.KILL);

        this.entityType = entityType;
        this.setAmount(amount);
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    @Override
    public boolean evaluate(Player player, Event e) {
        if (!(e instanceof DestructEntityEvent.Death)) {
            return false;
        }

        return this.entityType == ((DestructEntityEvent.Death) e).getTargetEntity().getType();
    }
}