package me.mrdaniel.ageofittgard.io.hocon.typeserializer.quest;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveType;
import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveTypes;
import me.mrdaniel.ageofittgard.quest.quest.QuestObjective;
import me.mrdaniel.ageofittgard.quest.quest.QuestRequirement;
import me.mrdaniel.ageofittgard.quest.quest.objective.*;
import me.mrdaniel.npcs.utils.Position;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public class QuestObjectiveTypeSerializer implements TypeSerializer<QuestObjective> {

    private Map<ObjectiveType, TypeSerializer> serializers;

    public QuestObjectiveTypeSerializer() {
        this.serializers = Maps.newHashMap();
        this.serializers.put(ObjectiveTypes.COLLECT, new Collect());
        this.serializers.put(ObjectiveTypes.KILL, new Kill());
        this.serializers.put(ObjectiveTypes.LOCATION, new Location());
        this.serializers.put(ObjectiveTypes.NPC_TALK, new NPCTalk());
        this.serializers.put(ObjectiveTypes.MONEY, new Money());
        this.serializers.put(ObjectiveTypes.QUEST, new Quest());
    }

    @Nullable
    @Override
    public QuestObjective deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
        QuestObjective obj = (QuestObjective) this.serializers.get(value.getNode("type").getValue(TypeToken.of(ObjectiveType.class))).deserialize(type, value);

        if (obj != null) {
            obj.addRequirements(value.getNode("requirements").getList(TypeToken.of(QuestRequirement.class)));
        }

        return obj;
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable QuestObjective obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        if (obj != null) {
            this.serializers.get(obj.getType()).serialize(type, obj, value);

            value.getNode("type").setValue(TypeToken.of(ObjectiveType.class), obj.getType());
            value.getNode("objectiveId").setValue(obj.getObjectiveId());

            if (!obj.getRequirements().isEmpty()) {
                value.getNode("requirements").setValue(new TypeToken<List<QuestRequirement>>(){}, obj.getRequirements());
            }
        }
    }

    public static class Collect implements TypeSerializer<ObjectiveCollect> {

        @Nullable
        @Override
        public ObjectiveCollect deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            ObjectiveCollect obj = new ObjectiveCollect(value.getNode("objectiveId").getInt());
            obj.setItem(value.getNode("item").getValue(TypeToken.of(ItemStack.class)));
            obj.setItemAmount(value.getNode("itemAmount").getInt());

            return obj;
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable ObjectiveCollect obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("item").setValue(TypeToken.of(ItemStack.class), obj.getItem());
                value.getNode("itemAmount").setValue(obj.getItemAmount());
            }
        }
    }

    public static class Kill implements TypeSerializer<ObjectiveKill> {

        @Nullable
        @Override
        public ObjectiveKill deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            ObjectiveKill obj = new ObjectiveKill(value.getNode("objectiveId").getInt());
            obj.setAmount(value.getNode("amount").getInt());
            obj.setEntityType(value.getNode("entityType").getValue(TypeToken.of(EntityType.class)));

            return obj;
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable ObjectiveKill obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("amount").setValue(obj.getAmount());
                value.getNode("entityType").setValue(TypeToken.of(EntityType.class), obj.getEntityType());
            }
        }
    }

    public static class Location implements TypeSerializer<ObjectiveLocation> {

        @Nullable
        @Override
        public ObjectiveLocation deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            ObjectiveLocation obj = new ObjectiveLocation(value.getNode("objectiveId").getInt());
            obj.setTarget(value.getNode("target").getValue(TypeToken.of(Position.class)));
            obj.setDistanceSquared(value.getNode("distanceSquared").getDouble());

            return obj;
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable ObjectiveLocation obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("target").setValue(TypeToken.of(Position.class), obj.getTarget());
                value.getNode("distanceSquared").setValue(obj.getDistanceSquared());
            }
        }
    }

    public static class NPCTalk implements TypeSerializer<ObjectiveNPCTalk> {

        @Nullable
        @Override
        public ObjectiveNPCTalk deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            ObjectiveNPCTalk obj = new ObjectiveNPCTalk(value.getNode("objectiveId").getInt());
            obj.setNpcId(value.getNode("npcId").getInt());
            obj.setDialogueId(value.getNode("dialogueId").getInt());

            return obj;
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable ObjectiveNPCTalk obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("npcId").setValue(obj.getNpcId());
                value.getNode("dialogueId").setValue(obj.getDialogueId());
            }
        }
    }

    public static class Money implements TypeSerializer<ObjectiveMoney> {

        @Nullable
        @Override
        public ObjectiveMoney deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            ObjectiveMoney obj = new ObjectiveMoney(value.getNode("objectiveId").getInt());
            obj.setMoney(value.getNode("money").getDouble());

            return obj;
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable ObjectiveMoney obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("money").setValue(obj.getMoney());
            }
        }
    }

    public static class Quest implements TypeSerializer<ObjectiveQuest> {

        @Nullable
        @Override
        public ObjectiveQuest deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            ObjectiveQuest obj = new ObjectiveQuest(value.getNode("objectiveId").getInt());
            obj.setQuestId(value.getNode("questId").getInt());

            return obj;
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable ObjectiveQuest obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("questId").setValue(obj.getQuestId());
            }
        }
    }
}
