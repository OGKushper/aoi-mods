package me.mrdaniel.ageofittgard.quest.requirement;

import me.mrdaniel.ageofittgard.catalogtypes.requirementtype.RequirementTypes;
import me.mrdaniel.ageofittgard.quest.Requirement;
import org.spongepowered.api.entity.living.player.Player;

public class RequirementTime extends Requirement {

    private int fromTicks;
    private int toTicks;

    public RequirementTime() {
        super(RequirementTypes.TIME);
    }

    public RequirementTime(int fromTicks, int toTicks) {
        this();
        this.fromTicks = fromTicks;
        this.toTicks = toTicks;
    }

    @Override
    public boolean evaluate(Player player) {
        long dayTime = player.getWorld().getProperties().getWorldTime() % 24000;

        return dayTime > this.fromTicks && dayTime < this.toTicks;
    }

    @Override
    public boolean apply(Player player) {
        return this.evaluate(player);
    }

    public int getFromTicks() {
        return fromTicks;
    }

    public void setFromTicks(int fromTicks) {
        this.fromTicks = fromTicks;
    }

    public int getToTicks() {
        return toTicks;
    }

    public void setToTicks(int toTicks) {
        this.toTicks = toTicks;
    }
}
