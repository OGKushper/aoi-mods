package me.mrdaniel.ageofittgard.listener.objective;

import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveTypes;
import me.mrdaniel.ageofittgard.quest.quest.objective.ObjectiveKill;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;
import org.spongepowered.api.event.entity.DestructEntityEvent;
import org.spongepowered.api.event.filter.cause.First;

public class EntityKillListener extends AbstractObjectiveListener<ObjectiveKill> {

    public EntityKillListener() {
        super(ObjectiveTypes.KILL);
    }

    @Listener
    public void onEntityDeath(DestructEntityEvent.Death e, @First EntityDamageSource source) {
        if (source.getSource() instanceof Player) {
            super.checkObjectives(((Player) source.getSource()), e);
        }
    }
}
