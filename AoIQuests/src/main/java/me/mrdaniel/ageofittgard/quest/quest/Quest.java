package me.mrdaniel.ageofittgard.quest.quest;

import com.google.common.collect.Lists;
import me.mrdaniel.ageofittgard.AgeOfIttgard;
import me.mrdaniel.ageofittgard.io.IPersistStrategy;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;

import java.util.List;
import java.util.Optional;

public class Quest {

    private IPersistStrategy persistStrategy;

    private final int questId;
    private Text name;

    private QuestObjective trigger;
    private final List<Text> startClues;
    private final List<QuestStage> stages;

    private double rewardMoney;
    private final List<ItemStack> rewards;

    public Quest() {
        this(AgeOfIttgard.getInstance().getQuestManager().getPersistStrategy(), 0);
    }

    public Quest(IPersistStrategy persistStrategy, int questId) {
        this.persistStrategy = persistStrategy;

        this.questId = questId;
        this.startClues = Lists.newArrayList();
        this.stages = Lists.newArrayList();
        this.rewards = Lists.newArrayList();
    }

    public int getQuestId() {
        return this.questId;
    }

    public Text getName() {
        return this.name;
    }

    public Quest setName(Text name) {
        this.name = name;
        return this;
    }

    public QuestObjective getTrigger() {
        return this.trigger;
    }

    public Quest setTrigger(QuestObjective trigger) {
        this.trigger = trigger;
        return this;
    }

    public List<Text> getStartClues() {
        return this.startClues;
    }

    public Quest addStartClue(Text clue) {
        this.startClues.add(clue);
        return this;
    }

    public List<QuestStage> getStages() {
        return this.stages;
    }

    public Optional<QuestStage> getStage(int stageId) {
        return this.stages.stream().filter(stage -> stage.getStageId() == stageId).findFirst();
    }

    public Quest addStage(QuestStage stage) {
        this.stages.add(stage);
        return this;
    }

    public double getRewardMoney() {
        return this.rewardMoney;
    }

    public Quest setRewardMoney(double rewardMoney) {
        this.rewardMoney = rewardMoney;
        return this;
    }

    public List<ItemStack> getRewards() {
        return this.rewards;
    }

    public Quest addReward(ItemStack reward) {
        this.rewards.add(reward);
        return this;
    }

    public void load(Object saveable) {
        this.persistStrategy.load(saveable);
        this.stages.forEach(stage -> stage.load(this));
    }

    public void unload() {
        this.persistStrategy.unload();
        this.stages.forEach(QuestStage::unload);
    }

    public void save() {
        this.persistStrategy.save();
    }

    public void delete() {
        this.persistStrategy.delete();
    }
}
