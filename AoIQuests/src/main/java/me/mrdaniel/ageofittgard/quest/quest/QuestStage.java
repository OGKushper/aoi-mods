package me.mrdaniel.ageofittgard.quest.quest;

import com.google.common.collect.Lists;
import me.mrdaniel.ageofittgard.quest.player.ActiveQuest;
import org.spongepowered.api.text.Text;

import java.util.List;
import java.util.Optional;

public class QuestStage {

    private Quest quest;

    private final int stageId;
    private final List<QuestObjective> objectives;
    private Text preDesc;
    private Text postDesc;

    public QuestStage() {
        this(0);
    }

    public QuestStage(int stageId) {
        this.stageId = stageId;
        this.objectives = Lists.newArrayList();
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

    public Text getPreDesc() {
        return preDesc;
    }

    public QuestStage setPreDesc(Text preDesc) {
        this.preDesc = preDesc;
        return this;
    }

    public Text getPostDesc() {
        return postDesc;
    }

    public QuestStage setPostDesc(Text postDesc) {
        this.postDesc = postDesc;
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

    public void load(Quest quest) {
        this.quest = quest;
        this.objectives.forEach(obj -> obj.load(this));
    }

    public void unload() {
        this.quest = null;
        this.objectives.forEach(QuestObjective::unload);
    }

    public void save() {
        this.quest.save();
    }
}
