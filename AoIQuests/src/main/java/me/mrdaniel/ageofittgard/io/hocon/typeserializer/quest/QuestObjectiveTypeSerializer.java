package me.mrdaniel.ageofittgard.io.hocon.typeserializer.quest;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveType;
import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveTypes;
import me.mrdaniel.ageofittgard.quest.Requirement;
import me.mrdaniel.ageofittgard.quest.quest.QuestObjective;
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
        this.serializers.put(ObjectiveTypes.NPC_DIALOGUE, new NPCDialogue());
        this.serializers.put(ObjectiveTypes.MONEY, new Money());
        this.serializers.put(ObjectiveTypes.QUEST, new Quest());
    }

    @Nullable
    @Override
    public QuestObjective deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
        QuestObjective obj = (QuestObjective) this.serializers.get(value.getNode("type").getValue(TypeToken.of(ObjectiveType.class))).deserialize(type, value);

        if (obj != null) {
            obj.addRequirements(value.getNode("requirements").getList(TypeToken.of(Requirement.class)));
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
                value.getNode("requirements").setValue(new TypeToken<List<Requirement>>(){}, obj.getRequirements());
            }
        }
    }

    private static class Collect implements TypeSerializer<ObjectiveCollect> {

        @Nullable
        @Override
        public ObjectiveCollect deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            return new ObjectiveCollect(value.getNode("objectiveId").getInt(), value.getNode("item").getValue(TypeToken.of(ItemStack.class)), value.getNode("itemAmount").getInt());
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable ObjectiveCollect obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("item").setValue(TypeToken.of(ItemStack.class), obj.getItem());
                value.getNode("itemAmount").setValue(obj.getItemAmount());
            }
        }
    }

    private static class Kill implements TypeSerializer<ObjectiveKill> {

        @Nullable
        @Override
        public ObjectiveKill deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            return new ObjectiveKill(value.getNode("objectiveId").getInt(), value.getNode("entityType").getValue(TypeToken.of(EntityType.class)), value.getNode("amount").getInt());
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable ObjectiveKill obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("entityType").setValue(TypeToken.of(EntityType.class), obj.getEntityType());
                value.getNode("amount").setValue(obj.getAmount());
            }
        }
    }

    private static class Location implements TypeSerializer<ObjectiveLocation> {

        @Nullable
        @Override
        public ObjectiveLocation deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            return new ObjectiveLocation(value.getNode("objectiveId").getInt(), value.getNode("target").getValue(TypeToken.of(Position.class)), value.getNode("distance").getDouble());
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable ObjectiveLocation obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("target").setValue(TypeToken.of(Position.class), obj.getTarget());
                value.getNode("distance").setValue(obj.getDistance());
            }
        }
    }

    private static class NPCDialogue implements TypeSerializer<ObjectiveNPCDialogue> {

        @Nullable
        @Override
        public ObjectiveNPCDialogue deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            return new ObjectiveNPCDialogue(value.getNode("objectiveId").getInt(), value.getNode("npcId").getInt());
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable ObjectiveNPCDialogue obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("npcId").setValue(obj.getNpcId());
            }
        }
    }

    private static class Money implements TypeSerializer<ObjectiveMoney> {

        @Nullable
        @Override
        public ObjectiveMoney deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            return new ObjectiveMoney(value.getNode("objectiveId").getInt(), value.getNode("money").getDouble());
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable ObjectiveMoney obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("money").setValue(obj.getMoney());
            }
        }
    }

    private static class Quest implements TypeSerializer<ObjectiveQuest> {

        @Nullable
        @Override
        public ObjectiveQuest deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            return new ObjectiveQuest(value.getNode("objectiveId").getInt(), value.getNode("questId").getInt());
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable ObjectiveQuest obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("questId").setValue(obj.getQuestId());
            }
        }
    }
}
