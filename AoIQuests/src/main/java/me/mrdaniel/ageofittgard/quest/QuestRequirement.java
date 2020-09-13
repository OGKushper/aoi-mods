package me.mrdaniel.ageofittgard.quest;

import me.mrdaniel.ageofittgard.catalogtypes.requirementtype.RequirementType;
import org.spongepowered.api.entity.living.player.Player;

public abstract class QuestRequirement {

    protected int requirementId;
    protected RequirementType requirementType;

    public QuestRequirement(RequirementType requirementType, int requirementId) {
        this.requirementType = requirementType;
        this.requirementId = requirementId;
    }

    public int getRequirementId() {
        return this.requirementId;
    }

    public RequirementType getRequirementType() {
        return this.requirementType;
    }

    public abstract boolean evaluate(Player player);
    public abstract boolean apply(Player player);
}
