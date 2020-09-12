package me.mrdaniel.ageofittgard.quest.quest.requirement;

import me.mrdaniel.ageofittgard.catalogtypes.requirementtype.RequirementTypes;
import me.mrdaniel.ageofittgard.quest.quest.QuestRequirement;
import me.mrdaniel.npcs.utils.Position;
import org.spongepowered.api.entity.living.player.Player;

public class QuestRequirementLocation extends QuestRequirement {

    private Position target;
    private double distanceSquared;

    public QuestRequirementLocation(int requirementId) {
        super(RequirementTypes.LOCATION, requirementId);
    }

    public QuestRequirementLocation(int requirementId, Position target, double distanceSquared) {
        this(requirementId);

        this.target = target;
        this.distanceSquared = distanceSquared;
    }

    @Override
    public boolean evaluate(Player player) {
        return player.getWorld().getName().equalsIgnoreCase(this.target.getWorldName())
                && player.getPosition().distanceSquared(this.target.getX(), this.target.getY(), this.target.getZ()) < this.distanceSquared;
    }

    @Override
    public boolean apply(Player player) {
        return this.evaluate(player);
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
}
