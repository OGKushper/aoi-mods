package me.mrdaniel.ageofittgard.quest.requirement;

import me.mrdaniel.ageofittgard.catalogtypes.requirementtype.RequirementTypes;
import me.mrdaniel.ageofittgard.quest.Requirement;
import me.mrdaniel.npcs.utils.Position;
import org.spongepowered.api.entity.living.player.Player;

public class RequirementLocation extends Requirement {

    private Position target;
    private double distanceSquared;
    private double distance;

    public RequirementLocation(int requirementId) {
        super(RequirementTypes.LOCATION, requirementId);
    }

    public RequirementLocation(int requirementId, Position target, double distance) {
        this(requirementId);

        this.target = target;
        this.distanceSquared = distance * distance;
        this.distance = distance;
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
        return this.target;
    }

    public void setTarget(Position target) {
        this.target = target;
    }

    public double getDistanceSquared() {
        return this.distanceSquared;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distanceSquared = distance * distance;
        this.distance = distance;
    }
}
