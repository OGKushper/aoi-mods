package me.mrdaniel.ageofittgard.event;

import me.mrdaniel.ageofittgard.quest.player.ActiveQuest;
import me.mrdaniel.ageofittgard.util.CauseUtils;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.impl.AbstractEvent;

public abstract class AbstractQuestEvent extends AbstractEvent implements Cancellable {

    private final Player player;
    private final ActiveQuest activeQuest;
    private final Cause cause;

    private boolean cancel;

    public AbstractQuestEvent(Player player, ActiveQuest quest) {
        this.player = player;
        this.activeQuest = quest;
        this.cause = CauseUtils.getPluginCause();
        this.cancel = false;
    }

    public Player getPlayer() {
        return this.player;
    }

    public ActiveQuest getActiveQuest() {
        return this.activeQuest;
    }

    @Override
    public Cause getCause() {
        return this.cause;
    }

    @Override
    public boolean isCancelled() {
        return this.cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
