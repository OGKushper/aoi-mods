package me.mrdaniel.ageofittgard.io.hocon.typeserializer.quest;

import com.google.common.reflect.TypeToken;
import me.mrdaniel.ageofittgard.io.hocon.HoconPersistStrategy;
import me.mrdaniel.ageofittgard.quest.quest.Quest;
import me.mrdaniel.ageofittgard.quest.quest.QuestStage;
import me.mrdaniel.ageofittgard.quest.quest.QuestTrigger;
import me.mrdaniel.ageofittgard.util.TextUtils;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.List;

public class QuestTypeSerializer implements TypeSerializer<Quest> {

    @Nullable
    @Override
    public Quest deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
        Quest quest = new Quest(new HoconPersistStrategy(), value.getNode("questId").getInt(0));
        quest.setName(TextUtils.toText(value.getNode("name").getString("")));
        quest.setTrigger(value.getNode("trigger").getValue(TypeToken.of(QuestTrigger.class)));
        quest.getStages().addAll(value.getNode("stages").getList(TypeToken.of(QuestStage.class)));
        quest.getRewards().addAll(value.getNode("rewards").getList(TypeToken.of(ItemStack.class)));
        quest.setRewardMoney(value.getNode("rewardMoney").getDouble(0.0D));

        return quest;
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable Quest obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        if (obj != null) {
            value.getNode("questId").setValue(obj.getQuestId());
            value.getNode("name").setValue(TextUtils.toString(obj.getName()));
            value.getNode("trigger").setValue(TypeToken.of(QuestTrigger.class), obj.getTrigger());
            value.getNode("stages").setValue(new TypeToken<List<QuestStage>>(){}, obj.getStages());
            value.getNode("rewards").setValue(new TypeToken<List<ItemStack>>(){}, obj.getRewards());
            value.getNode("rewardMoney").setValue(obj.getRewardMoney());
        }
    }
}
