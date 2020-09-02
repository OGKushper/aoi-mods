package me.mrdaniel.ageofittgard.quest.quest.objective;

import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveTypes;
import me.mrdaniel.ageofittgard.quest.quest.QuestObjective;
import me.mrdaniel.npcs.utils.Position;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Event;

public class ObjectiveLocation extends QuestObjective {

    private Position target;
    private double distanceSquared;

    public ObjectiveLocation(int objectiveId) {
        this(objectiveId, null, 0);
    }

    public ObjectiveLocation(int objectiveId, Position target, double distanceSquared) {
        super(objectiveId, ObjectiveTypes.LOCATION);

        this.target = target;
        this.distanceSquared = distanceSquared;
    }

    public Position getTarget() {
        return target;
    }

    public void setTarget(Position target) {
        this.target = target;
    }

    public double getDistanceSquared() {
        return distanceSquared;
    }

    public void setDistanceSquared(double distanceSquared) {
        this.distanceSquared = distanceSquared;
    }

    @Override
    public boolean evaluate(Player player, Event e) {
        return player.getWorld().getName().equalsIgnoreCase(this.target.getWorldName())
                && player.getPosition().distanceSquared(this.target.getX(), this.target.getY(), this.target.getZ()) < this.distanceSquared;
    }
}
