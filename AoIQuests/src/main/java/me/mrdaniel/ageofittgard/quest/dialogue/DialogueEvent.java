package me.mrdaniel.ageofittgard.quest.dialogue;

import me.mrdaniel.ageofittgard.catalogtypes.eventtype.EventType;

public abstract class DialogueEvent {

    private final int eventId;
    private final EventType eventType;

    public DialogueEvent(int eventId, EventType eventType) {
        this.eventId = eventId;
        this.eventType = eventType;
    }

    public int getEventId() {
        return this.eventId;
    }

    public EventType getEventType() {
        return this.eventType;
    }

    public abstract void run(DialogueRunner runner);
}
