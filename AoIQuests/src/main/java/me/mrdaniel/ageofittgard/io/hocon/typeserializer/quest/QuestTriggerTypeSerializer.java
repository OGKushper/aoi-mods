package me.mrdaniel.ageofittgard.io.hocon.typeserializer.quest;

import com.google.common.reflect.TypeToken;
import me.mrdaniel.ageofittgard.quest.quest.QuestObjective;
import me.mrdaniel.ageofittgard.quest.quest.QuestTrigger;
import me.mrdaniel.ageofittgard.util.TextUtils;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public class QuestTriggerTypeSerializer implements TypeSerializer<QuestTrigger> {

    @Nullable
    @Override
    public QuestTrigger deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
        QuestTrigger trigger = new QuestTrigger();
        trigger.setObjective(value.getNode("objective").getValue(TypeToken.of(QuestObjective.class)));
        trigger.addPreDesc(TextUtils.deserialize(value.getNode("preDesc")));
        trigger.addPostDesc(TextUtils.deserialize(value.getNode("postDesc")));

        return trigger;
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable QuestTrigger obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        if (obj != null) {
            value.getNode("objective").setValue(TypeToken.of(QuestObjective.class), obj.getObjective());
            TextUtils.serialize(value.getNode("preDesc"), obj.getPreDesc());
            TextUtils.serialize(value.getNode("postDesc"), obj.getPostDesc());
        }
    }
}
