package me.mrdaniel.ageofittgard.quest.dialogue.event;

import me.mrdaniel.ageofittgard.catalogtypes.eventtype.EventTypes;
import me.mrdaniel.ageofittgard.event.CompleteDialogueEvent;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueEvent;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueRunner;
import org.spongepowered.api.Sponge;

public class DialogueEventCompleteObjective extends DialogueEvent {

    public DialogueEventCompleteObjective(int eventId) {
        super(eventId, EventTypes.OBJECTIVE);
    }

    @Override
    public void run(DialogueRunner runner) {
        Sponge.getEventManager().post(new CompleteDialogueEvent(runner.getPlayer(), runner.getDialogue().getNpcId()));
    }
}
