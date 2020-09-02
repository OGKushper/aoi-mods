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
        stage.setPreDesc(TextUtils.toText(value.getNode("preDesc").getString("")));
        stage.setPostDesc(TextUtils.toText(value.getNode("postDesc").getString("")));
        value.getNode("objectives").getList(TypeToken.of(QuestObjective.class)).forEach(stage.getObjectives()::add);

        return stage;
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable QuestStage obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        value.getNode("stageId").setValue(obj.getStageId());
        value.getNode("preDesc").setValue(TextUtils.toString(obj.getPreDesc()));
        value.getNode("postDesc").setValue(TextUtils.toString(obj.getPostDesc()));
        value.getNode("objectives").setValue(new TypeToken<List<QuestObjective>>(){}, obj.getObjectives());
    }
}
