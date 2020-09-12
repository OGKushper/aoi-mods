package me.mrdaniel.ageofittgard.quest.quest.objective;

import me.mrdaniel.ageofittgard.AoIQuests;
import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveTypes;
import me.mrdaniel.ageofittgard.event.CompleteDialogueEvent;
import me.mrdaniel.ageofittgard.quest.quest.QuestObjective;
import me.mrdaniel.npcs.events.NPCInteractEvent;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Event;

public class ObjectiveNPCTalk extends QuestObjective {

    private int npcId;
    private int dialogueId;

    public ObjectiveNPCTalk(int objectiveId) {
        this(objectiveId, 0, 0);
    }

    public ObjectiveNPCTalk(int objectiveId, int npcId, int dialogueId) {
        super(objectiveId, ObjectiveTypes.NPC_TALK);

        this.npcId = npcId;
        this.dialogueId = dialogueId;
    }

    public int getNpcId() {
        return this.npcId;
    }

    public void setNpcId(int npcId) {
        this.npcId = npcId;
    }

    public int getDialogueId() {
        return this.dialogueId;
    }

    public void setDialogueId(int dialogueId) {
        this.dialogueId = dialogueId;
    }

    @Override
    protected boolean evaluateObjective(Player player, Event e) {
        if (e instanceof NPCInteractEvent) {       // Always returns false. This only kicks off the NPC dialogue.
            if (((NPCInteractEvent) e).getData().getId() == this.npcId) {
                AoIQuests.getInstance().getDialogueManager().startDialogue(this.dialogueId, player);
            }
        } else if (e instanceof CompleteDialogueEvent) {
            return ((CompleteDialogueEvent) e).getDialogueId() == this.dialogueId;
        }

        return false;
    }
}
