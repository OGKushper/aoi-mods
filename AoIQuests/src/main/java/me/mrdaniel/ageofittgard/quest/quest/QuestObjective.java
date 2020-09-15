package me.mrdaniel.ageofittgard.quest.quest;

import com.google.common.collect.Lists;
import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveType;
import me.mrdaniel.ageofittgard.quest.Requirement;
import me.mrdaniel.ageofittgard.quest.player.ActiveQuest;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Event;

import java.util.Collection;
import java.util.List;

public abstract class QuestObjective {

    protected final int objectiveId;
    protected final ObjectiveType type;

    protected final List<Requirement> requirements;

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
     * @param event The event
     * @return whether the player has met the objective
     */
    public boolean evaluate(Player player, Event event) {
        return this.evaluateRequirements(player) && this.evaluateObjective(player, event);
    }

    protected boolean evaluateRequirements(Player player) {
        for (Requirement req : this.requirements) {
            if (!req.evaluate(player)) {
                return false;
            }
        }

        return true;
    }

    protected abstract boolean evaluateObjective(Player player, Event event);

    public int getObjectiveId() {
        return this.objectiveId;
    }

    public ObjectiveType getType() {
        return this.type;
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }

    public void addRequirements(Collection<Requirement> requirements) {
        this.requirements.addAll(requirements);
    }

    public QuestObjective addRequirement(Requirement requirement) {
        this.requirements.add(requirement);
        return this;
    }
}
