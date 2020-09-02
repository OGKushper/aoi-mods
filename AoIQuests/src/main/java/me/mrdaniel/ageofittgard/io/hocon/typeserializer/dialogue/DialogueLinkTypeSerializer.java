package me.mrdaniel.ageofittgard.io.hocon.typeserializer.dialogue;

import com.google.common.reflect.TypeToken;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueLink;
import me.mrdaniel.ageofittgard.util.TextUtils;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.api.util.TypeTokens;

import java.util.List;
import java.util.stream.Collectors;

public class DialogueLinkTypeSerializer implements TypeSerializer<DialogueLink> {

    @Nullable
    @Override
    public DialogueLink deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
        DialogueLink data = new DialogueLink(value.getNode("linkId").getInt(), value.getNode("nextNodeId").getInt());
        data.getNpcLines().addAll(value.getNode("npcLines").getList(TypeTokens.STRING_TOKEN).stream().map(TextUtils::toText).collect(Collectors.toList()));
        data.getConditions().addAll(value.getNode("conditions").getList(TypeToken.of(Integer.class)));
        data.setChoiceLine(TextUtils.toText(value.getNode("choiceLine").getString(null)));

        return data;
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable DialogueLink obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        if (obj == null) {
            return;
        }

        value.getNode("linkId").setValue(obj.getLinkId());
        value.getNode("nextNodeId").setValue(obj.getNextNodeId());
        value.getNode("choiceLine").setValue(TextUtils.toString(obj.getChoiceLine()));
        value.getNode("npcLines").setValue(new TypeToken<List<String>>(){}, obj.getNpcLines().stream().map(TextUtils::toString).collect(Collectors.toList()));
        value.getNode("conditions").setValue(new TypeToken<List<Integer>>(){}, obj.getConditions());
    }
}
