package me.mrdaniel.ageofittgard.io.hocon.typeserializer.dialogue;

import com.google.common.reflect.TypeToken;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueEvent;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueLink;
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
        DialogueNode node = new DialogueNode(value.getNode("nodeId").getInt(), value.getNode("choiceMenu").getBoolean(false));

        node.getLinks().addAll(value.getNode("links").getList(TypeToken.of(DialogueLink.class)));
        node.getEvents().addAll(value.getNode("events").getList(TypeToken.of(DialogueEvent.class)));

        return node;
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable DialogueNode obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        if (obj != null) {
            value.getNode("nodeId").setValue(obj.getNodeId());
            if (obj.isChoiceMenu()) value.getNode("choiceMenu").setValue(true);
            if (!obj.getLinks().isEmpty()) value.getNode("links").setValue(new TypeToken<List<DialogueLink>>(){}, obj.getLinks());
            if (!obj.getEvents().isEmpty()) value.getNode("events").setValue(new TypeToken<List<DialogueEvent>>(){}, obj.getEvents());
        }
    }
}
