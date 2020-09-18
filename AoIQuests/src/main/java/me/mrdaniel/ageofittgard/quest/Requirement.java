package me.mrdaniel.ageofittgard.quest;

import me.mrdaniel.ageofittgard.catalogtypes.requirementtype.RequirementType;
import org.spongepowered.api.entity.living.player.Player;

public abstract class Requirement {

    protected RequirementType requirementType;

    public Requirement(RequirementType requirementType) {
        this.requirementType = requirementType;
    }

    public RequirementType getRequirementType() {
        return this.requirementType;
    }

    public abstract boolean evaluate(Player player);
    public abstract boolean apply(Player player);
}
