package me.mrdaniel.ageofittgard.listener.objective;

import me.mrdaniel.ageofittgard.AoIQuests;
import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveTypes;
import me.mrdaniel.ageofittgard.event.CompleteDialogueEvent;
import me.mrdaniel.ageofittgard.quest.player.ActiveObjective;
import me.mrdaniel.ageofittgard.quest.quest.objective.ObjectiveNPCDialogue;
import me.mrdaniel.npcs.events.NPCInteractEvent;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;

import java.util.List;

public class NPCListener extends AbstractObjectiveListener<ObjectiveNPCDialogue> {

    public NPCListener() {
        super(ObjectiveTypes.NPC_DIALOGUE);
    }

    @Listener
    public void onNPCInteract(NPCInteractEvent event) {
        if (event.getSource() instanceof Player) {
            Player player = ((Player) event.getSource());

            List<ActiveObjective<ObjectiveNPCDialogue>> objectives = this.objectives.get(player.getUniqueId());
            if (objectives == null || objectives.isEmpty()) {
                return;
            }

            ActiveObjective<ObjectiveNPCDialogue> active = objectives.stream().filter(a -> a.getObjective().evaluateDialogue(player, event)).findFirst().orElse(null);

            if (active != null) {
                AoIQuests.getInstance().getDialogueManager().startDialogue(active.getObjective().getDialogueId(), player);
            } else {
                // TODO: Implement pre and post quest dialogue.
            }
        }
    }

    @Listener
    public void onDialogueComplete(CompleteDialogueEvent event) {
        super.checkObjectives(event.getPlayer(), event);
    }
}
