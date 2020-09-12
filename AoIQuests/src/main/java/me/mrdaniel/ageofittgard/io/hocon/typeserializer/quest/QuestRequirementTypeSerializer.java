package me.mrdaniel.ageofittgard.io.hocon.typeserializer.quest;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import me.mrdaniel.ageofittgard.catalogtypes.requirementtype.RequirementType;
import me.mrdaniel.ageofittgard.catalogtypes.requirementtype.RequirementTypes;
import me.mrdaniel.ageofittgard.quest.quest.QuestRequirement;
import me.mrdaniel.ageofittgard.quest.quest.requirement.ItemQuestRequirement;
import me.mrdaniel.ageofittgard.quest.quest.requirement.MoneyQuestRequirement;
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
        this.serializers.put(RequirementTypes.MONEY, new Money());
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

    public static class Item implements TypeSerializer<ItemQuestRequirement> {

        @Nullable
        @Override
        public ItemQuestRequirement deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            ItemQuestRequirement data = new ItemQuestRequirement(value.getNode("requirementId").getInt());

            data.setItem(value.getNode("item").getValue(TypeToken.of(ItemStack.class)));
            data.setItemAmount(value.getNode("itemAmount").getInt());
            data.setTake(value.getNode("take").getBoolean());

            return data;
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable ItemQuestRequirement obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("item").setValue(TypeToken.of(ItemStack.class), obj.getItem());
                value.getNode("itemAmount").setValue(obj.getItemAmount());
                value.getNode("take").setValue(obj.isTake());
            }
        }
    }

    public static class Money implements TypeSerializer<MoneyQuestRequirement> {

        @Nullable
        @Override
        public MoneyQuestRequirement deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            MoneyQuestRequirement data = new MoneyQuestRequirement(value.getNode("requirementId").getInt());

            data.setMoney(value.getNode("money").getDouble());
            data.setTake(value.getNode("take").getBoolean());

            return data;
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable MoneyQuestRequirement obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("money").setValue(obj.getMoney());
                value.getNode("take").setValue(obj.isTake());
            }
        }
    }
}
