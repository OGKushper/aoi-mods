package me.mrdaniel.ageofittgard.io.hocon.typeserializer.quest;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import me.mrdaniel.ageofittgard.catalogtypes.queststatus.QuestStatus;
import me.mrdaniel.ageofittgard.catalogtypes.requirementtype.RequirementType;
import me.mrdaniel.ageofittgard.catalogtypes.requirementtype.RequirementTypes;
import me.mrdaniel.ageofittgard.quest.Requirement;
import me.mrdaniel.ageofittgard.quest.requirement.*;
import me.mrdaniel.npcs.utils.Position;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.Map;

public class QuestRequirementTypeSerializer implements TypeSerializer<Requirement> {

    private final Map<RequirementType, TypeSerializer> serializers;

    public QuestRequirementTypeSerializer() {
        this.serializers = Maps.newHashMap();
        this.serializers.put(RequirementTypes.ITEM, new Item());
        this.serializers.put(RequirementTypes.LOCATION, new Location());
        this.serializers.put(RequirementTypes.MONEY, new Money());
        this.serializers.put(RequirementTypes.TIME, new Time());
        this.serializers.put(RequirementTypes.QUEST_STATUS, new Quest());
        this.serializers.put(RequirementTypes.STAGE_ACTIVE, new Stage());
    }

    @Nullable
    @Override
    public Requirement deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
        return (Requirement) this.serializers.get(value.getNode("requirementType").getValue(TypeToken.of(RequirementType.class))).deserialize(type, value);
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable Requirement obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        if (obj != null) {
            this.serializers.get(obj.getRequirementType()).serialize(type, obj, value);

            value.getNode("requirementId").setValue(obj.getRequirementId());
            value.getNode("requirementType").setValue(TypeToken.of(RequirementType.class), obj.getRequirementType());
        }
    }

    private static class Item implements TypeSerializer<RequirementItem> {

        @Nullable
        @Override
        public RequirementItem deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            return new RequirementItem(
                    value.getNode("requirementId").getInt(),
                    value.getNode("item").getValue(TypeToken.of(ItemStack.class)),
                    value.getNode("itemAmount").getInt(),
                    value.getNode("take").getBoolean()
            );
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable RequirementItem obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("item").setValue(TypeToken.of(ItemStack.class), obj.getItem());
                value.getNode("itemAmount").setValue(obj.getItemAmount());
                value.getNode("take").setValue(obj.isTake());
            }
        }
    }

    private static class Location implements TypeSerializer<RequirementLocation> {

        @Nullable
        @Override
        public RequirementLocation deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            return new RequirementLocation(value.getNode("requirementId").getInt(), value.getNode("target").getValue(TypeToken.of(Position.class)), value.getNode("distance").getDouble());
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable RequirementLocation obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("target").setValue(TypeToken.of(Position.class), obj.getTarget());
                value.getNode("distance").setValue(obj.getDistance());
            }
        }
    }

    private static class Money implements TypeSerializer<RequirementMoney> {

        @Nullable
        @Override
        public RequirementMoney deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            return new RequirementMoney(value.getNode("requirementId").getInt(), value.getNode("money").getDouble(), value.getNode("take").getBoolean());
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable RequirementMoney obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("money").setValue(obj.getMoney());
                value.getNode("take").setValue(obj.isTake());
            }
        }
    }

    private static class Time implements TypeSerializer<RequirementTime> {

        @Nullable
        @Override
        public RequirementTime deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            return new RequirementTime(value.getNode("requirementId").getInt(), value.getNode("fromTicks").getInt(), value.getNode("toTicks").getInt());
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable RequirementTime obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("fromTicks").setValue(obj.getFromTicks());
                value.getNode("toTicks").setValue(obj.getToTicks());
            }
        }
    }

    private static class Quest implements TypeSerializer<RequirementQuestStatus> {

        @Nullable
        @Override
        public RequirementQuestStatus deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            return new RequirementQuestStatus(value.getNode("requirementId").getInt(), value.getNode("questId").getInt(), value.getNode("status").getValue(TypeToken.of(QuestStatus.class)));
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable RequirementQuestStatus obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("questId").setValue(obj.getQuestId());
                value.getNode("status").setValue(TypeToken.of(QuestStatus.class), obj.getStatus());
            }
        }
    }

    private static class Stage implements TypeSerializer<RequirementStageActive> {

        @Nullable
        @Override
        public RequirementStageActive deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            return new RequirementStageActive(value.getNode("requirementId").getInt(), value.getNode("questId").getInt(), value.getNode("stageId").getInt());
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable RequirementStageActive obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("questId").setValue(obj.getQuestId());
                value.getNode("stageId").setValue(obj.getStageId());
            }
        }
    }
}
