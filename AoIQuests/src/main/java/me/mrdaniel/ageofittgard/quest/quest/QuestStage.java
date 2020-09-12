package me.mrdaniel.ageofittgard.quest.quest;

import com.google.common.collect.Lists;
import me.mrdaniel.ageofittgard.quest.player.ActiveQuest;
import org.spongepowered.api.text.Text;

import java.util.List;
import java.util.Optional;

public class QuestStage {

    private final int stageId;
    private final List<QuestObjective> objectives;
    private final List<Text> preDesc;
    private final List<Text> postDesc;

    public QuestStage() {
        this(0);
    }

    public QuestStage(int stageId) {
        this.stageId = stageId;
        this.objectives = Lists.newArrayList();
        this.preDesc = Lists.newArrayList();
        this.postDesc = Lists.newArrayList();
    }

    public int getStageId() {
        return stageId;
    }

    public List<QuestObjective> getObjectives() {
        return objectives;
    }

    public Optional<QuestObjective> getObjective(int objectiveId) {
        return this.objectives.stream().filter(obj -> obj.getObjectiveId() == objectiveId).findFirst();
    }

    public QuestStage addObjective(QuestObjective obj) {
        this.objectives.add(obj);
        return this;
    }

    public List<Text> getPreDesc() {
        return preDesc;
    }

    public QuestStage addPreDesc(Text line) {
        this.preDesc.add(line);
        return this;
    }

    public QuestStage addPreDesc(List<Text> lines) {
        this.preDesc.addAll(lines);
        return this;
    }

    public List<Text> getPostDesc() {
        return postDesc;
    }

    public QuestStage addPostDesc(Text line) {
        this.postDesc.add(line);
        return this;
    }

    public QuestStage addPostDesc(List<Text> lines) {
        this.postDesc.addAll(lines);
        return this;
    }

    public boolean isCompleted(ActiveQuest quest) {
        for (QuestObjective obj : this.objectives) {
            if (!obj.isCompleted(quest)) {
                return false;
            }
        }

        return true;
    }
}
