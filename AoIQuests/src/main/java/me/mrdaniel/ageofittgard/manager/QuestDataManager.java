package me.mrdaniel.ageofittgard.manager;

import me.mrdaniel.ageofittgard.AgeOfIttgard;
import me.mrdaniel.ageofittgard.io.IPersistStrategy;
import me.mrdaniel.ageofittgard.io.hocon.config.MainConfig;
import me.mrdaniel.ageofittgard.io.hocon.HoconQuestStore;
import me.mrdaniel.ageofittgard.quest.IQuestStore;
import me.mrdaniel.ageofittgard.quest.quest.Quest;

import java.util.List;
import java.util.Optional;

public class QuestDataManager {

    private IQuestStore questStore;

    public QuestDataManager() {
        this.questStore = null;
    }

    public void load(MainConfig config) {
        if (this.questStore != null) {
            this.questStore.unload();
        }

        // TODO: Add different storage types
        this.questStore = new HoconQuestStore(AgeOfIttgard.getInstance().getConfigDir());
        this.questStore.load();
    }

    public Quest createQuest() {
        return this.questStore.create(this.getNextID());
    }

    public Optional<Quest> getQuest(Integer id) {
        return this.questStore.get(id);
    }

    public List<Quest> getAllQuests() {
        return this.questStore.getAll();
    }

    private int getNextID() {
        int id = 1;

        while (this.getQuest(id).isPresent()) {
            id++;
        }
        return id;
    }

    public IPersistStrategy getPersistStrategy() {
        return this.questStore.getPersistStrategy();
    }
}
