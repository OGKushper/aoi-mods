package me.mrdaniel.ageofittgard.io.hocon.typeserializer.dialogue;

import com.google.common.reflect.TypeToken;
import me.mrdaniel.ageofittgard.io.hocon.HoconPersistStrategy;
import me.mrdaniel.ageofittgard.quest.dialogue.Dialogue;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueNode;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public class DialogueTypeSerializer implements TypeSerializer<Dialogue> {

    @Nullable
    @Override
    public Dialogue deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
        Dialogue data = new Dialogue(new HoconPersistStrategy(), value.getNode("npcId").getInt(0));

        data.setRoot(value.getNode("root").getValue(TypeToken.of(DialogueNode.class)));

        return data;
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable Dialogue obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        if (obj != null) {
            value.getNode("npcId").setValue(obj.getNpcId());
            value.getNode("root").setValue(TypeToken.of(DialogueNode.class), obj.getRoot());
        }
    }
}
