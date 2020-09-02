package me.mrdaniel.ageofittgard.data;

import com.google.common.reflect.TypeToken;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.value.mutable.MapValue;

public class AoIKeys {

    public static final Key<MapValue<Integer, Integer>> DIALOGUE_PROGRESS = Key.builder()
            .type(new TypeToken<MapValue<Integer, Integer>>(){})
            .id("dialogue-progress")
            .name("Dialogue Progress")
            .query(DataQuery.of("dialogue-progress"))
            .build();

    public static void init() {
        DIALOGUE_PROGRESS.getName();
    }
}
