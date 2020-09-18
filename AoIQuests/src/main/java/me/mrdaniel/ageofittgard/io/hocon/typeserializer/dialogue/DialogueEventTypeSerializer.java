package me.mrdaniel.ageofittgard.io.hocon.typeserializer.dialogue;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import me.mrdaniel.ageofittgard.catalogtypes.eventtype.EventType;
import me.mrdaniel.ageofittgard.catalogtypes.eventtype.EventTypes;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueEvent;
import me.mrdaniel.ageofittgard.quest.dialogue.event.DialogueEventCompleteObjective;
import me.mrdaniel.ageofittgard.quest.dialogue.event.DialogueEventGift;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.Map;

public class DialogueEventTypeSerializer implements TypeSerializer<DialogueEvent> {

    private final Map<EventType, TypeSerializer> serializers;

    public DialogueEventTypeSerializer() {
        this.serializers = Maps.newHashMap();
        this.serializers.put(EventTypes.OBJECTIVE, new CompleteObjective());
        this.serializers.put(EventTypes.GIFT, new Gift());
    }

    @Nullable
    @Override
    public DialogueEvent deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
        return (DialogueEvent) this.serializers.get(value.getNode("eventType").getValue(TypeToken.of(EventType.class))).deserialize(type, value);
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable DialogueEvent obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        if (obj != null) {
            this.serializers.get(obj.getEventType()).serialize(type, obj, value);

            value.getNode("eventId").setValue(obj.getEventId());
            value.getNode("eventType").setValue(TypeToken.of(EventType.class), obj.getEventType());
        }
    }

    private static class CompleteObjective implements TypeSerializer<DialogueEventCompleteObjective> {

        @Nullable
        @Override
        public DialogueEventCompleteObjective deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) {
            return new DialogueEventCompleteObjective(value.getNode("eventId").getInt());
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable DialogueEventCompleteObjective obj, @NonNull ConfigurationNode value) { }
    }

    private static class Gift implements TypeSerializer<DialogueEventGift> {

        @Nullable
        @Override
        public DialogueEventGift deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            return new DialogueEventGift(value.getNode("eventId").getInt(), value.getNode("item").getValue(TypeToken.of(ItemStack.class)));
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable DialogueEventGift obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("item").setValue(TypeToken.of(ItemStack.class), obj.getItem());
            }
        }
    }
}
