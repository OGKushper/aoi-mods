package me.mrdaniel.ageofittgard.quest.player;

import com.google.common.collect.Maps;
import me.mrdaniel.ageofittgard.exception.InvalidQuestException;
import me.mrdaniel.ageofittgard.quest.quest.Quest;
import me.mrdaniel.ageofittgard.quest.quest.QuestStage;

import javax.annotation.Nullable;
import java.util.Map;

public class ActiveQuest {

    private PlayerData playerData;

    private Quest quest;
    private QuestStage stage;
    private Map<Integer, Integer> progress;

    public ActiveQuest(Quest quest) throws InvalidQuestException {
        this(quest, quest.getStage(1).orElseThrow(InvalidQuestException::new));
    }

    public ActiveQuest(Quest quest, @Nullable QuestStage stage) {
        this.quest = quest;
        this.stage = stage;
        this.progress = Maps.newHashMap();
    }

    public PlayerData getPlayerData() {
        return this.playerData;
    }

    public Quest getQuest() {
        return this.quest;
    }

    public QuestStage getStage() {
        return this.stage;
    }

    public Map<Integer, Integer> getProgress() {
        return this.progress;
    }

    public int getProgress(Integer objectiveId) {
        return this.progress.getOrDefault(objectiveId, 0);
    }

    public void setProgress(Integer objectiveId, Integer progress) {
        this.progress.put(objectiveId, progress);
    }

    public void setStage(QuestStage stage) {
        this.stage = stage;
        this.progress.clear();
    }

    /**
     * Should be called after the creation of any ActiveQuest.
     *
     * @param playerData The PlayerData of the Player this active quest belongs to.
     */
    public void load(PlayerData playerData) {
        this.playerData = playerData;
    }

    /**
     * Should be called by objective listeners when the objective is completed.
     */
    public void unload() {
        this.playerData = null;
        this.quest = null;
        this.stage = null;
        this.progress.clear();
    }
}
