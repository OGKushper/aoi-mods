package me.mrdaniel.ageofittgard.quest.quest;

import com.google.common.collect.Lists;
import me.mrdaniel.ageofittgard.AoIQuests;
import me.mrdaniel.ageofittgard.io.IPersistStrategy;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;

import java.util.List;
import java.util.Optional;

public class Quest {

    private IPersistStrategy persistStrategy;

    private final int questId;
    private Text name;

    private QuestTrigger trigger;
    private final List<QuestStage> stages;

    private double rewardMoney;
    private final List<ItemStack> rewards;

    public Quest() {
        this(0);
    }

    public Quest(int questId) {
        this(AoIQuests.getInstance().getQuestManager().getPersistStrategy(), questId);
    }

    public Quest(IPersistStrategy persistStrategy, int questId) {
        this.persistStrategy = persistStrategy;

        this.questId = questId;
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

    public QuestTrigger getTrigger() {
        return this.trigger;
    }

    public Quest setTrigger(QuestTrigger trigger) {
        this.trigger = trigger;
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
    }

    public void unload() {
        this.persistStrategy.unload();
    }

    public void save() {
        this.persistStrategy.save();
    }

    public void delete() {
        this.persistStrategy.delete();
    }
}
