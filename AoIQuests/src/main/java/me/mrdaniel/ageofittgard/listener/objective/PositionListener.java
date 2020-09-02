package me.mrdaniel.ageofittgard.listener.objective;

import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.MoveEntityEvent;

public class PositionListener extends AbstractObjectiveListener {

    public PositionListener() {
        super(ObjectiveTypes.LOCATION);
    }

    // TODO: Implement more efficiently
    @Listener
    public void onMoveEvent(MoveEntityEvent e) {
        if (e.getTargetEntity() instanceof Player) {
            super.checkObjectives(((Player) e.getTargetEntity()), e);
        }
    }
}
