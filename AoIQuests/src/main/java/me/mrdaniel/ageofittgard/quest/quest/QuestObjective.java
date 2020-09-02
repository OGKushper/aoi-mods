package me.mrdaniel.ageofittgard.quest.quest;

import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveType;
import me.mrdaniel.ageofittgard.quest.player.ActiveQuest;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Event;

public abstract class QuestObjective {

    protected QuestStage stage;

    protected final int objectiveId;
    protected final ObjectiveType type;

    public QuestObjective(ObjectiveType type) {
        this(0, type);
    }

    protected QuestObjective(int objectiveId, ObjectiveType type) {
        this.objectiveId = objectiveId;
        this.type = type;
    }

    public int getObjectiveId() {
        return objectiveId;
    }

    public ObjectiveType getType() {
        return type;
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
     * Checks if the player has met the objective. Also applies any changes to the player (e.g. takes items away if appropriate)
     *
     * @param player The player
     * @param e The event
     * @return whether the player has met the objective
     */
    public abstract boolean evaluate(Player player, Event e);

    public void load(QuestStage stage) {
        this.stage = stage;
    }

    public void unload() {
        this.stage = null;
    }

    public void save() {
        this.stage.save();
    }

}
