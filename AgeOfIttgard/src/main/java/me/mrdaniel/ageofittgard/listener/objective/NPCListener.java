package me.mrdaniel.ageofittgard.listener.objective;

import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveTypes;
import me.mrdaniel.ageofittgard.event.CompleteDialogueEvent;
import me.mrdaniel.npcs.events.NPCInteractEvent;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;

public class NPCListener extends AbstractObjectiveListener {

    public NPCListener() {
        super(ObjectiveTypes.NPC_TALK);
    }

    @Listener
    public void onNPCInteract(NPCInteractEvent e) {
        if (e.getSource() instanceof Player) {
            super.checkObjectives(((Player) e.getSource()), e);
        }
    }

    @Listener
    public void onDialogueComplete(CompleteDialogueEvent e) {
        super.checkObjectives(e.getPlayer(), e);
    }
}
