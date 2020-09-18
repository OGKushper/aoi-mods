package me.mrdaniel.ageofittgard.io.hocon.typeserializer.dialogue;

import com.google.common.reflect.TypeToken;
import me.mrdaniel.ageofittgard.quest.Requirement;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueLink;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueNode;
import me.mrdaniel.ageofittgard.util.TextUtils;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

public class DialogueLinkTypeSerializer implements TypeSerializer<DialogueLink> {

    @Nullable
    @Override
    public DialogueLink deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
        DialogueLink data = new DialogueLink();
        data.setNextNode(value.getNode("nextNode").getValue(TypeToken.of(DialogueNode.class)));
        data.setChoiceLine(TextUtils.toText(value.getNode("choiceLine").getString(null)));
        data.getNpcLines().addAll(TextUtils.deserialize(value.getNode("npcLines")));
        data.getRequirements().addAll(value.getNode("requirements").getList(TypeToken.of(Requirement.class)));

        return data;
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable DialogueLink obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        if (obj != null) {
            value.getNode("nextNode").setValue(TypeToken.of(DialogueNode.class), obj.getNextNode());
            value.getNode("choiceLine").setValue(TextUtils.toString(obj.getChoiceLine()));
            TextUtils.serialize(value.getNode("npcLines"), obj.getNpcLines());
            if (!obj.getRequirements().isEmpty()) value.getNode("requirements").setValue(new TypeToken<List<Requirement>>(){}, obj.getRequirements());
        }
    }
}
