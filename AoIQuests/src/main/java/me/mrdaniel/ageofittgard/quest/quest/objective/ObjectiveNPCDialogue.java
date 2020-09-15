package me.mrdaniel.ageofittgard.quest.quest.objective;

import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveTypes;
import me.mrdaniel.ageofittgard.event.CompleteDialogueEvent;
import me.mrdaniel.ageofittgard.quest.quest.QuestObjective;
import me.mrdaniel.npcs.events.NPCInteractEvent;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Event;

public class ObjectiveNPCDialogue extends QuestObjective {

    private int npcId;

    public ObjectiveNPCDialogue(int objectiveId) {
        this(objectiveId, 0);
    }

    public ObjectiveNPCDialogue(int objectiveId, int npcId) {
        super(objectiveId, ObjectiveTypes.NPC_DIALOGUE);

        this.npcId = npcId;
    }

    @Override
    protected boolean evaluateObjective(Player player, Event event) {
        if (event instanceof CompleteDialogueEvent) {
            return ((CompleteDialogueEvent) event).getNpcId() == this.npcId;
        }

        return false;
    }

    /**
     * Used by the NPCListener to check whether the dialogue needs to be started.
     * Separated from evaluateObjective because this should never complete an objective.
     *
     * @param event The event
     * @return whether or not the dialogue needs to be started.
     */
    public boolean evaluateDialogue(Player player, NPCInteractEvent event) {
        return super.evaluateRequirements(player) && event.getData().getId() == this.npcId;
    }

    public int getNpcId() {
        return this.npcId;
    }

    public void setNpcId(int npcId) {
        this.npcId = npcId;
    }
}
