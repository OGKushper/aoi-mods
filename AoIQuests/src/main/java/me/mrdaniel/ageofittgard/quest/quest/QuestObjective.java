package me.mrdaniel.ageofittgard.quest.quest;

import com.google.common.collect.Lists;
import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveType;
import me.mrdaniel.ageofittgard.quest.player.ActiveQuest;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Event;

import java.util.Collection;
import java.util.List;

public abstract class QuestObjective {

    protected final int objectiveId;
    protected final ObjectiveType type;

    protected final List<QuestRequirement> requirements;

    protected QuestObjective(int objectiveId, ObjectiveType type) {
        this.objectiveId = objectiveId;
        this.type = type;
        this.requirements = Lists.newArrayList();
    }

    /**
     * Used to check whether the objective has been completed.
     *
     * @param active The active quest
     * @return true if the objective has been completed, else false
     */
    public boolean isCompleted(ActiveQuest active) {
        return active.getProgress(this.objectiveId) > 0;
    }

    /**
     * Checks if the event has met the criteria for the objective. Also kicks off NPC dialogue when appropriate.
     *
     * @param player The player
     * @param e The event
     * @return whether the player has met the objective
     */
    public boolean evaluate(Player player, Event e) {
        return this.evaluateRequirements(player) && this.evaluateObjective(player, e);
    }

    protected boolean evaluateRequirements(Player player) {
        for (QuestRequirement req : this.requirements) {
            if (!req.evaluate(player)) {
                return false;
            }
        }

        return true;
    }

    protected abstract boolean evaluateObjective(Player player, Event e);

    public int getObjectiveId() {
        return this.objectiveId;
    }

    public ObjectiveType getType() {
        return this.type;
    }

    public List<QuestRequirement> getRequirements() {
        return requirements;
    }

    public void addRequirements(Collection<QuestRequirement> requirements) {
        this.requirements.addAll(requirements);
    }

    public QuestObjective addRequirement(QuestRequirement requirement) {
        this.requirements.add(requirement);
        return this;
    }
}
