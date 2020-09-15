package me.mrdaniel.ageofittgard.listener.objective;

import me.mrdaniel.ageofittgard.AoIQuests;
import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveTypes;
import me.mrdaniel.ageofittgard.event.CompleteDialogueEvent;
import me.mrdaniel.ageofittgard.quest.quest.objective.ObjectiveNPCDialogue;
import me.mrdaniel.npcs.events.NPCInteractEvent;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;

public class NPCListener extends AbstractObjectiveListener<ObjectiveNPCDialogue> {

    public NPCListener() {
        super(ObjectiveTypes.NPC_DIALOGUE);
    }

    @Listener
    public void onNPCInteract(NPCInteractEvent event) {
        if (event.getSource() instanceof Player) {
            AoIQuests.getInstance().getDialogueManager().startDialogue(((Player) event.getSource()), event.getData().getId());
        }
    }

    @Listener
    public void onDialogueComplete(CompleteDialogueEvent event) {
        super.checkObjectives(event.getPlayer(), event);
    }
}
