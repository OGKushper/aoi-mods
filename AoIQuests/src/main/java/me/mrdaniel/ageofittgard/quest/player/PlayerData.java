package me.mrdaniel.ageofittgard.quest.player;

import com.google.common.collect.Lists;
import me.mrdaniel.ageofittgard.AoIQuests;
import me.mrdaniel.ageofittgard.io.IPersistStrategy;
import me.mrdaniel.ageofittgard.quest.quest.Quest;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class PlayerData {

    private IPersistStrategy persistStrategy;

    private List<Integer> started;
    private List<ActiveQuest> active;
    private List<Integer> completed;
    private List<ItemStack> unclaimed;

    /**
     * Required empty constructor for Hocon config file instantiation. Can be used safely.
     */
    public PlayerData() {
        this(AoIQuests.getInstance().getPlayerDataManager().getPersistStrategy());
    }

    public PlayerData(IPersistStrategy persistStrategy) {
        this.persistStrategy = persistStrategy;

        this.started = Lists.newArrayList();
        this.active = Lists.newArrayList();
        this.completed = Lists.newArrayList();
        this.unclaimed = Lists.newArrayList();
    }

    public List<Integer> getStarted() {
        return this.started;
    }

    public List<ActiveQuest> getActive() {
        return this.active;
    }

    public Optional<ActiveQuest> getActive(Quest quest) {
        return this.active.stream().filter(active -> active.getQuest().getQuestId() == quest.getQuestId()).findFirst();
    }

    public List<Integer> getCompleted() {
        return this.completed;
    }

    public boolean isStarted(int questId) {
        return this.started.contains(questId);
    }

    public boolean isActive(int questId) {
        return this.active.stream().anyMatch(a -> a.getQuest().getQuestId() == questId);
    }

    public boolean isCompleted(int questId) {
        return this.completed.contains(questId);
    }

    public List<ItemStack> getUnclaimed() {
        return this.unclaimed;
    }

    public ActiveQuest setup(Quest quest) {
        ActiveQuest active = new ActiveQuest(quest, null);
        active.load(this);
        this.active.add(active);

        return active;
    }

    public void start(ActiveQuest active) {
        this.started.add(active.getQuest().getQuestId());

        if (!this.active.contains(active)) {
            this.active.add(active);
        }
    }

    public void complete(ActiveQuest active) {
        this.active.remove(active);

        if (!this.completed.contains(active.getQuest().getQuestId())) {
            this.completed.add(active.getQuest().getQuestId());
        }
    }

    public PlayerData addUnclaimed(ItemStack item) {
        this.unclaimed.add(item);
        return this;
    }

    public PlayerData addUnclaimed(Collection<ItemStack> items) {
        this.unclaimed.addAll(items);
        return this;
    }

    public void save() {
        this.persistStrategy.save();
    }

    public void load(Object config) {
        this.persistStrategy.load(config);
    }

    public void unload() {
        this.persistStrategy.unload();
    }
}
