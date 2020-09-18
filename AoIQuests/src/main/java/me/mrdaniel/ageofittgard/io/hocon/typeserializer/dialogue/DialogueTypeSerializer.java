package me.mrdaniel.ageofittgard.io.hocon.typeserializer.dialogue;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import me.mrdaniel.ageofittgard.io.hocon.HoconPersistStrategy;
import me.mrdaniel.ageofittgard.quest.Requirement;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueLink;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueNode;
import me.mrdaniel.ageofittgard.quest.dialogue.Dialogue;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

public class DialogueTypeSerializer implements TypeSerializer<Dialogue> {

    @Nullable
    @Override
    public Dialogue deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
        Dialogue data = new Dialogue(new HoconPersistStrategy(), value.getNode("npcId").getInt(0));

        data.setFirstNode(value.getNode("firstNodeId").getInt(0));
        value.getNode("nodes").getList(TypeToken.of(DialogueNode.class)).forEach(data::addNode);
        value.getNode("links").getList(TypeToken.of(DialogueLink.class)).forEach(data::addLink);
        value.getNode("requirements").getList(TypeToken.of(Requirement.class)).forEach(data::addRequirement);

        return data;
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable Dialogue obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        if (obj != null) {
            value.getNode("npcId").setValue(obj.getNpcId());
            value.getNode("firstNodeId").setValue(obj.getFirstNode());
            value.getNode("nodes").setValue(new TypeToken<List<DialogueNode>>(){}, Lists.newArrayList(obj.getNodes().values()));
            value.getNode("links").setValue(new TypeToken<List<DialogueLink>>(){}, Lists.newArrayList(obj.getLinks().values()));
            if (!obj.getRequirements().isEmpty()) {
                value.getNode("requirements").setValue(new TypeToken<List<Requirement>>(){}, Lists.newArrayList(obj.getRequirements().values()));
            }
        }
    }
}
