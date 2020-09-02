package me.mrdaniel.ageofittgard.io.hocon.typeserializer.player;

import com.google.common.reflect.TypeToken;
import me.mrdaniel.ageofittgard.io.hocon.HoconPersistStrategy;
import me.mrdaniel.ageofittgard.quest.player.ActiveQuest;
import me.mrdaniel.ageofittgard.quest.player.PlayerData;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;
import java.util.Objects;

public class PlayerDataTypeSerializer implements TypeSerializer<PlayerData> {

    @Nullable
    @Override
    public PlayerData deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
        PlayerData data = new PlayerData(new HoconPersistStrategy());
        data.getStarted().addAll(value.getNode("started").getList(TypeToken.of(Integer.class)));
        data.getActive().addAll(value.getNode("active").getList(TypeToken.of(ActiveQuest.class)));
        data.getCompleted().addAll(value.getNode("completed").getList(TypeToken.of(Integer.class)));

        // Required to delete active quest data for deleted quests
        data.getActive().removeIf(Objects::isNull);

        return data;
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable PlayerData obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        if (obj == null) {
            return;
        }

        value.getNode("started").setValue(obj.getStarted());
        value.getNode("active").setValue(new TypeToken<List<ActiveQuest>>(){}, obj.getActive());
        value.getNode("completed").setValue(obj.getCompleted());
    }
}
