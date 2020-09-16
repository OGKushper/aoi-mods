package me.mrdaniel.ageofittgard.io.hocon.typeserializer.dialogue;

import com.google.common.reflect.TypeToken;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueNode;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

public class DialogueNodeTypeSerializer implements TypeSerializer<DialogueNode> {

    @Nullable
    @Override
    public DialogueNode deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
        DialogueNode node = new DialogueNode(value.getNode("nodeId").getInt(), value.getNode("choiceMenu").getBoolean());

        node.getLinks().addAll(value.getNode("linkIds").getList(TypeToken.of(Integer.class)));
        node.getEvents().addAll(value.getNode("eventIds").getList(TypeToken.of(Integer.class)));

        return node;
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable DialogueNode obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        if (obj != null) {
            value.getNode("nodeId").setValue(obj.getNodeId());
            value.getNode("choiceMenu").setValue(obj.isChoiceMenu());
            if (!obj.getLinks().isEmpty()) value.getNode("linkIds").setValue(new TypeToken<List<Integer>>(){}, obj.getLinks());
            if (!obj.getEvents().isEmpty()) value.getNode("eventIds").setValue(new TypeToken<List<Integer>>(){}, obj.getEvents());
        }
    }
}
