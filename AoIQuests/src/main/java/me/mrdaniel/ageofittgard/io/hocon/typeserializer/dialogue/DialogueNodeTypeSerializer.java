package me.mrdaniel.ageofittgard.io.hocon.typeserializer.dialogue;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import me.mrdaniel.ageofittgard.catalogtypes.nodetype.NodeType;
import me.mrdaniel.ageofittgard.catalogtypes.nodetype.NodeTypes;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueNode;
import me.mrdaniel.ageofittgard.quest.dialogue.node.*;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public class DialogueNodeTypeSerializer implements TypeSerializer<DialogueNode> {

    private final Map<NodeType, TypeSerializer> serializers;

    public DialogueNodeTypeSerializer() {
        this.serializers = Maps.newHashMap();
        this.serializers.put(NodeTypes.LINK, new Link());
        this.serializers.put(NodeTypes.CHOOSE, new Choose());
//        this.serializers.put(NodeTypes.CONDITIONS, new Condition());
        this.serializers.put(NodeTypes.BREAK, new Break());
        this.serializers.put(NodeTypes.GIFT, new Gift());
        this.serializers.put(NodeTypes.END, new End());
    }

    @Nullable
    @Override
    public DialogueNode deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
        return (DialogueNode) this.serializers.get(value.getNode("nodeType").getValue(TypeToken.of(NodeType.class))).deserialize(type, value);
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable DialogueNode obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        if (obj != null) {
            this.serializers.get(obj.getNodeType()).serialize(type, obj, value);

            value.getNode("nodeId").setValue(obj.getNodeId());
            value.getNode("nodeType").setValue(TypeToken.of(NodeType.class), obj.getNodeType());
        }
    }

    public static class Link implements TypeSerializer<LinkDialogueNode> {

        @Nullable
        @Override
        public LinkDialogueNode deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            LinkDialogueNode data = new LinkDialogueNode(value.getNode("nodeId").getInt());

            data.setLinkId(value.getNode("link").getInt());

            return data;
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable LinkDialogueNode obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("link").setValue(obj.getLinkId());
            }
        }
    }

    public static class Choose implements TypeSerializer<ChooseDialogueNode> {

        @Nullable
        @Override
        public ChooseDialogueNode deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            ChooseDialogueNode data = new ChooseDialogueNode(value.getNode("nodeId").getInt());

            data.getLinks().addAll(value.getNode("links").getList(TypeToken.of(Integer.class)));

            return data;
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable ChooseDialogueNode obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("links").setValue(new TypeToken<List<Integer>>(){}, obj.getLinks());
            }
        }
    }

//    public static class Condition implements TypeSerializer<ConditionDialogueNode> {
//
//        @Nullable
//        @Override
//        public ConditionDialogueNode deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
//            ConditionDialogueNode data = new ConditionDialogueNode(value.getNode("nodeId").getInt());
//
//            data.setDefaultLink(value.getNode("defaultLink").getInt());
//            data.getLinks().addAll(value.getNode("links").getList(TypeToken.of(Integer.class)));
//
//            return data;
//        }
//
//        @Override
//        public void serialize(@NonNull TypeToken<?> type, @Nullable ConditionDialogueNode obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
//            if (obj != null) {
//                value.getNode("defaultLink").setValue(obj.getDefaultLink());
//                value.getNode("links").setValue(new TypeToken<List<Integer>>(){}, obj.getLinks());
//            }
//        }
//    }

    public static class Break implements TypeSerializer<BreakDialogueNode> {

        @Nullable
        @Override
        public BreakDialogueNode deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            BreakDialogueNode data = new BreakDialogueNode(value.getNode("nodeId").getInt());

            data.setNextNodeId(value.getNode("nextNodeId").getInt());

            return data;
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable BreakDialogueNode obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("nextNodeId").setValue(obj.getNextNodeId());
            }
        }
    }

    public static class Gift implements TypeSerializer<GiftDialogueNode> {

        @Nullable
        @Override
        public GiftDialogueNode deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            GiftDialogueNode data = new GiftDialogueNode(value.getNode("nodeId").getInt());

            data.setLinkId(value.getNode("link").getInt());
            data.setItem(value.getNode("item").getValue(TypeToken.of(ItemStack.class)));

            return data;
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable GiftDialogueNode obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("link").setValue(obj.getLinkId());
                value.getNode("item").setValue(TypeToken.of(ItemStack.class), obj.getItem());
            }
        }
    }

    public static class End implements TypeSerializer<EndDialogueNode> {

        @Nullable
        @Override
        public EndDialogueNode deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) {
            return new EndDialogueNode(value.getNode("nodeId").getInt());
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable EndDialogueNode obj, @NonNull ConfigurationNode value) {

        }
    }
}
