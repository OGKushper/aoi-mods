package me.mrdaniel.ageofittgard.io.hocon.typeserializer.quest;

import com.google.common.reflect.TypeToken;
import me.mrdaniel.ageofittgard.quest.quest.QuestObjective;
import me.mrdaniel.ageofittgard.quest.quest.QuestStage;
import me.mrdaniel.ageofittgard.util.TextUtils;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

public class QuestStageTypeSerializer implements TypeSerializer<QuestStage> {

    @Nullable
    @Override
    public QuestStage deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
        QuestStage stage = new QuestStage(value.getNode("stageId").getInt());
        value.getNode("objectives").getList(TypeToken.of(QuestObjective.class)).forEach(stage.getObjectives()::add);
        stage.addPreDesc(TextUtils.deserialize(value.getNode("preDesc")));
        stage.addPostDesc(TextUtils.deserialize(value.getNode("postDesc")));

        return stage;
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable QuestStage obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        if (obj != null) {
            value.getNode("stageId").setValue(obj.getStageId());
            value.getNode("objectives").setValue(new TypeToken<List<QuestObjective>>(){}, obj.getObjectives());
            TextUtils.serialize(value.getNode("preDesc"), obj.getPreDesc());
            TextUtils.serialize(value.getNode("postDesc"), obj.getPostDesc());
        }
    }
}
