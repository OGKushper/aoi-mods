package me.mrdaniel.ageofittgard.util;

import me.mrdaniel.ageofittgard.AgeOfIttgard;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.EventContext;
import org.spongepowered.api.event.cause.EventContextKeys;

public class CauseUtils {

    public static Cause getPluginCause() {
        return Cause.builder().append(AgeOfIttgard.getInstance()).build(EventContext.builder().add(EventContextKeys.PLUGIN, AgeOfIttgard.getInstance().getContainer()).build());
    }
}
