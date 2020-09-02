package me.mrdaniel.ageofittgard.event;

import me.mrdaniel.ageofittgard.util.CauseUtils;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.impl.AbstractEvent;

public class CompleteDialogueEvent extends AbstractEvent {

    private final Player player;
    private final int dialogueId;
    private final Cause cause;

    public CompleteDialogueEvent(Player player, int dialogueId) {
        this.player = player;
        this.dialogueId = dialogueId;
        this.cause = CauseUtils.getPluginCause();
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getDialogueId() {
        return this.dialogueId;
    }

    @Override
    public Cause getCause() {
        return this.cause;
    }
}
