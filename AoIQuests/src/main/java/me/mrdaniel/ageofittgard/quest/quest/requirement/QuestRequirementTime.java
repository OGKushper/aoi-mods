package me.mrdaniel.ageofittgard.quest.quest.requirement;

import me.mrdaniel.ageofittgard.catalogtypes.requirementtype.RequirementTypes;
import me.mrdaniel.ageofittgard.quest.quest.QuestRequirement;
import org.spongepowered.api.entity.living.player.Player;

public class QuestRequirementTime extends QuestRequirement {

    private int fromTicks;
    private int toTicks;

    public QuestRequirementTime(int requirementId) {
        super(RequirementTypes.TIME, requirementId);
    }

    public QuestRequirementTime(int requirementId, int fromTicks, int toTicks) {
        this(requirementId);

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
