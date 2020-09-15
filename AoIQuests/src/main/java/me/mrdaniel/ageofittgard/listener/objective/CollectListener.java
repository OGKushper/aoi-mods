package me.mrdaniel.ageofittgard.listener.objective;

import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveTypes;
import me.mrdaniel.ageofittgard.quest.quest.objective.ObjectiveCollect;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.item.inventory.ChangeInventoryEvent;

public class CollectListener extends AbstractObjectiveListener<ObjectiveCollect> {

    public CollectListener() {
        super(ObjectiveTypes.COLLECT);
    }

    @Listener
    public void onPickup(ChangeInventoryEvent.Pickup e, @First Player player) {
        super.checkObjectives(player, e);
    }
}
