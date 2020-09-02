package me.mrdaniel.ageofittgard.io.hocon.typeserializer.dialogue;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import me.mrdaniel.ageofittgard.catalogtypes.conditiontype.ConditionType;
import me.mrdaniel.ageofittgard.catalogtypes.conditiontype.ConditionTypes;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueCondition;
import me.mrdaniel.ageofittgard.quest.dialogue.condition.ItemDialogueCondition;
import me.mrdaniel.ageofittgard.quest.dialogue.condition.MoneyDialogueCondition;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.Map;

public class DialogueConditionTypeSerializer implements TypeSerializer<DialogueCondition> {

    private final Map<ConditionType, TypeSerializer> serializers;

    public DialogueConditionTypeSerializer() {
        this.serializers = Maps.newHashMap();
        this.serializers.put(ConditionTypes.ITEM, new Item());
        this.serializers.put(ConditionTypes.MONEY, new Money());
    }

    @Nullable
    @Override
    public DialogueCondition deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
        return (DialogueCondition) this.serializers.get(value.getNode("conditionType").getValue(TypeToken.of(ConditionType.class))).deserialize(type, value);
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable DialogueCondition obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        if (obj != null) {
            this.serializers.get(obj.getConditionType()).serialize(type, obj, value);

            value.getNode("conditionId").setValue(obj.getConditionId());
            value.getNode("conditionType").setValue(TypeToken.of(ConditionType.class), obj.getConditionType());
        }
    }

    public static class Item implements TypeSerializer<ItemDialogueCondition> {

        @Nullable
        @Override
        public ItemDialogueCondition deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            ItemDialogueCondition data = new ItemDialogueCondition(value.getNode("conditionId").getInt());

            data.setItem(value.getNode("item").getValue(TypeToken.of(ItemStack.class)));
            data.setItemAmount(value.getNode("itemAmount").getInt());
            data.setTake(value.getNode("take").getBoolean());

            return data;
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable ItemDialogueCondition obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("item").setValue(TypeToken.of(ItemStack.class), obj.getItem());
                value.getNode("itemAmount").setValue(obj.getItemAmount());
                value.getNode("take").setValue(obj.isTake());
            }
        }
    }

    public static class Money implements TypeSerializer<MoneyDialogueCondition> {

        @Nullable
        @Override
        public MoneyDialogueCondition deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
            MoneyDialogueCondition data = new MoneyDialogueCondition(value.getNode("conditionId").getInt());

            data.setMoney(value.getNode("money").getDouble());
            data.setTake(value.getNode("take").getBoolean());

            return data;
        }

        @Override
        public void serialize(@NonNull TypeToken<?> type, @Nullable MoneyDialogueCondition obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
            if (obj != null) {
                value.getNode("money").setValue(obj.getMoney());
                value.getNode("take").setValue(obj.isTake());
            }
        }
    }
}
