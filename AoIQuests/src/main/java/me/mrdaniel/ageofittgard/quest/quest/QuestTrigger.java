package me.mrdaniel.ageofittgard.quest.quest;

import com.google.common.collect.Lists;
import org.spongepowered.api.text.Text;

import java.util.List;

public class QuestTrigger {

    private QuestObjective objective;
    private final List<Text> preDesc;
    private final List<Text> postDesc;

    public QuestTrigger() {
        this.preDesc = Lists.newArrayList();
        this.postDesc = Lists.newArrayList();
    }

    public QuestObjective getObjective() {
        return objective;
    }

    public QuestTrigger setObjective(QuestObjective objective) {
        this.objective = objective;
        return this;
    }

    public List<Text> getPreDesc() {
        return preDesc;
    }

    public QuestTrigger addPreDesc(Text preDesc) {
        this.preDesc.add(preDesc);
        return this;
    }

    public QuestTrigger addPreDesc(List<Text> lines) {
        this.preDesc.addAll(lines);
        return this;
    }

    public List<Text> getPostDesc() {
        return postDesc;
    }

    public QuestTrigger addPostDesc(Text postDesc) {
        this.postDesc.add(postDesc);
        return this;
    }

    public QuestTrigger addPostDesc(List<Text> lines) {
        this.postDesc.addAll(lines);
        return this;
    }
}
