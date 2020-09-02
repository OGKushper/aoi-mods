package me.mrdaniel.ageofittgard.util;

import me.mrdaniel.ageofittgard.AoIQuests;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.EventContext;
import org.spongepowered.api.event.cause.EventContextKeys;

public class CauseUtils {

    public static Cause getPluginCause() {
        return Cause.builder().append(AoIQuests.getInstance()).build(EventContext.builder().add(EventContextKeys.PLUGIN, AoIQuests.getInstance().getContainer()).build());
    }
}
