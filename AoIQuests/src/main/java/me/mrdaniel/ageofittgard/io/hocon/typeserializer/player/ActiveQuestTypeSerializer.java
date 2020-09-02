package me.mrdaniel.ageofittgard.io.hocon.typeserializer.player;

import com.google.common.reflect.TypeToken;
import me.mrdaniel.ageofittgard.AgeOfIttgard;
import me.mrdaniel.ageofittgard.exception.InvalidQuestException;
import me.mrdaniel.ageofittgard.quest.player.ActiveQuest;
import me.mrdaniel.ageofittgard.quest.quest.Quest;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Map;

public class ActiveQuestTypeSerializer implements TypeSerializer<ActiveQuest> {

    @Nullable
    @Override
    public ActiveQuest deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
        try {
            Quest quest = AgeOfIttgard.getInstance().getQuestManager().getQuest(value.getNode("questId").getInt(0)).orElseThrow(InvalidQuestException::new);
            ActiveQuest active = new ActiveQuest(quest);
            active.setStage(quest.getStage(value.getNode("stageId").getInt(0)).orElse(null));

            value.getNode("progress").getValue(new TypeToken<Map<Integer, Integer>>(){}).forEach(active::setProgress);

            return active;
        } catch (InvalidQuestException exc) {
            return null;
        }
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable ActiveQuest obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        if (obj == null) {
            return;
        }

        value.getNode("questId").setValue(obj.getQuest().getQuestId());
        value.getNode("progress").setValue(new TypeToken<Map<Integer, Integer>>(){}, obj.getProgress());
        if (obj.getStage() != null) {
            value.getNode("stageId").setValue(obj.getStage().getStageId());
        }
    }
}
