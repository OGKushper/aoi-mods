package me.mrdaniel.ageofittgard.quest.dialogue;

import me.mrdaniel.ageofittgard.catalogtypes.eventtype.EventType;

public abstract class DialogueEvent {

    private final EventType eventType;

    public DialogueEvent(EventType eventType) {
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return this.eventType;
    }

    public abstract void run(DialogueRunner runner);
}
