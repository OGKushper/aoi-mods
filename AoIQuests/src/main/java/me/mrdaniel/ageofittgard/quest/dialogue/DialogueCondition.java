package me.mrdaniel.ageofittgard.quest.dialogue;

import me.mrdaniel.ageofittgard.catalogtypes.conditiontype.ConditionType;
import org.spongepowered.api.entity.living.player.Player;

public abstract class DialogueCondition {

    protected int conditionId;
    protected ConditionType conditionType;

    public DialogueCondition(ConditionType conditionType, int conditionId) {
        this.conditionType = conditionType;
        this.conditionId = conditionId;
    }

    public int getConditionId() {
        return this.conditionId;
    }

    public ConditionType getConditionType() {
        return this.conditionType;
    }

    public abstract boolean evaluate(Player player);
    public abstract boolean apply(Player player);
}
