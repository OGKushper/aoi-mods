package me.mrdaniel.ageofittgard.io.hocon.typeserializer.dialogue;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import me.mrdaniel.ageofittgard.io.hocon.HoconPersistStrategy;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueLink;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueNode;
import me.mrdaniel.ageofittgard.quest.dialogue.NPCDialogue;
import me.mrdaniel.ageofittgard.quest.quest.QuestRequirement;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

public class NPCDialogueTypeSerializer implements TypeSerializer<NPCDialogue> {

    @Nullable
    @Override
    public NPCDialogue deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
        NPCDialogue data = new NPCDialogue(new HoconPersistStrategy(), value.getNode("dialogueId").getInt(0));

        data.setFirstNode(value.getNode("firstNode").getInt(0));
        value.getNode("nodes").getList(TypeToken.of(DialogueNode.class)).forEach(data::addNode);
        value.getNode("links").getList(TypeToken.of(DialogueLink.class)).forEach(data::addLink);
        value.getNode("requirements").getList(TypeToken.of(QuestRequirement.class)).forEach(data::addRequirement);

        return data;
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable NPCDialogue obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        if (obj != null) {
            value.getNode("dialogueId").setValue(obj.getDialogueId());
            value.getNode("firstNode").setValue(obj.getFirstNode());
            value.getNode("nodes").setValue(new TypeToken<List<DialogueNode>>(){}, Lists.newArrayList(obj.getNodes().values()));
            value.getNode("links").setValue(new TypeToken<List<DialogueLink>>(){}, Lists.newArrayList(obj.getLinks().values()));
            if (!obj.getRequirements().isEmpty()) {
                value.getNode("requirements").setValue(new TypeToken<List<QuestRequirement>>(){}, Lists.newArrayList(obj.getRequirements().values()));
            }
        }
    }
}
