package me.mrdaniel.ageofittgard.io.hocon.typeserializer.quest;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import me.mrdaniel.ageofittgard.catalogtypes.requirementtype.RequirementType;
import me.mrdaniel.ageofittgard.catalogtypes.requirementtype.RequirementTypes;
import me.mrdaniel.ageofittgard.quest.quest.QuestRequirement;
import me.mrdaniel.ageofittgard.quest.quest.requirement.*;
import me.mrdaniel.npcs.utils.Position;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.Map;

public class QuestRequirementTypeSerializer implements TypeSerializer<QuestRequirement> {

    private final Map<RequirementType, TypeSerializer> serializers;

    public QuestRequirementTypeSerializer() {
        this.serializers = Maps.newHashMap();
        this.serializers.put(RequirementTypes.ITEM, new Item());
        this.serializers.put(RequirementTypes.LOCATION, new Location());
        this.serializers.put(RequirementTypes.MONEY, new Money());
        this.serializers.put(RequirementTypes.QUEST, new Quest());
        this.serializers.put(RequirementTypes.TIME, new Time());
    }

    @Nullable
    @Override
    public QuestRequirement deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
        return (QuestRequirement) this.serializers.get(value.getNode("requirementType").getValue(TypeToken.of(RequirementType.class))).deserialize(type, value);
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable QuestRequirement obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        if (obj != null) {
            this.serializers.get(obj.getRequirementType()).serialize(type, obj, value);

            value.getNode("requirementId").setValue(obj.getRequirementId());
            value.getNode("requirementType").setValue(TypeToken.of(RequirementType.class), obj.getRequirementType());
        }
    }

    public static class Item implements TypeSerializer<QuestRequirementItem> {

        @Nullable
        @Override
        public QuestRequirementItem deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            QuestRequirementItem data = new QuestRequirementItem(value.getNode("requirementId").getInt());

            data.setItem(value.getNode("item").getValue(TypeToken.of(ItemStack.class)));
            data.setItemAmount(value.getNode("itemAmount").getInt());
            data.setTake(value.getNode("take").getBoolean());

            return data;
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable QuestRequirementItem obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("item").setValue(TypeToken.of(ItemStack.class), obj.getItem());
                value.getNode("itemAmount").setValue(obj.getItemAmount());
                value.getNode("take").setValue(obj.isTake());
            }
        }
    }

    public static class Location implements TypeSerializer<QuestRequirementLocation> {

        @Nullable
        @Override
        public QuestRequirementLocation deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            QuestRequirementLocation data = new QuestRequirementLocation(value.getNode("requirementId").getInt());

            data.setTarget(value.getNode("target").getValue(TypeToken.of(Position.class)));
            data.setDistanceSquared(value.getNode("distanceSquared").getDouble());

            return data;
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable QuestRequirementLocation obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("target").setValue(TypeToken.of(Position.class), obj.getTarget());
                value.getNode("distanceSquared").setValue(obj.getDistanceSquared());
            }
        }
    }

    public static class Money implements TypeSerializer<QuestRequirementMoney> {

        @Nullable
        @Override
        public QuestRequirementMoney deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            QuestRequirementMoney data = new QuestRequirementMoney(value.getNode("requirementId").getInt());

            data.setMoney(value.getNode("money").getDouble());
            data.setTake(value.getNode("take").getBoolean());

            return data;
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable QuestRequirementMoney obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("money").setValue(obj.getMoney());
                value.getNode("take").setValue(obj.isTake());
            }
        }
    }

    public static class Quest implements TypeSerializer<QuestRequirementQuest> {

        @Nullable
        @Override
        public QuestRequirementQuest deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            QuestRequirementQuest data = new QuestRequirementQuest(value.getNode("requirementId").getInt());

            data.setQuestId(value.getNode("questId").getInt());

            return data;
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable QuestRequirementQuest obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("questId").setValue(obj.getQuestId());
            }
        }
    }

    public static class Time implements TypeSerializer<QuestRequirementTime> {

        @Nullable
        @Override
        public QuestRequirementTime deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            QuestRequirementTime data = new QuestRequirementTime(value.getNode("requirementId").getInt());

            data.setFromTicks(value.getNode("fromTicks").getInt());
            data.setToTicks(value.getNode("toTicks").getInt());

            return data;
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable QuestRequirementTime obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("fromTicks").setValue(obj.getFromTicks());
                value.getNode("toTicks").setValue(obj.getToTicks());
            }
        }
    }
}
