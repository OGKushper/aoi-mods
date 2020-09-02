package me.mrdaniel.ageofittgard.io.hocon;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import me.mrdaniel.ageofittgard.AoIQuests;
import me.mrdaniel.ageofittgard.io.hocon.config.Config;
import me.mrdaniel.ageofittgard.io.IPersistStrategy;
import me.mrdaniel.ageofittgard.quest.IQuestStore;
import me.mrdaniel.ageofittgard.quest.quest.Quest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HoconQuestStore implements IQuestStore {

    private final Path storageDir;
    private final Map<Integer, Quest> data;

    public HoconQuestStore(Path configDir) {
        this.storageDir = configDir.resolve("quests");
        this.data = Maps.newHashMap();
    }

    @Override
    public void load() {
        this.unload();

        if (!Files.exists(this.storageDir)) {
            try {
                Files.createDirectories(this.storageDir);
            } catch (final IOException exc) {
                AoIQuests.getInstance().getLogger().error("Failed to create main quest storage directory",  exc);
                return;
            }
        }

        for (String fileName : this.storageDir.toFile().list()) {
            Config<Quest> config = new Config<>(Quest.class, this.storageDir, fileName);
            Quest quest = config.get();
            quest.load(config);

            this.data.put(quest.getQuestId(), quest);
        }
    }

    @Override
    public void unload() {
        this.data.values().forEach(Quest::unload); // Prevents memory leaks
        this.data.clear();
    }

    @Override
    public Quest create(int id) {
        String fileName = "quest_" + id + ".conf";

        Config<Quest> config = new Config<>(new Quest(new HoconPersistStrategy(), id), this.storageDir, fileName);
        Quest quest = config.get();
        quest.load(config);

        this.data.put(id, quest);

        return quest;
    }

    @Override
    public void delete(Quest quest) {
        Quest deleted = this.data.remove(quest.getQuestId());
        if (deleted != null) {
            deleted.delete();
        }
    }

    @Override
    public Optional<Quest> get(Integer id) {
        return Optional.ofNullable(this.data.get(id));
    }

    @Override
    public List<Quest> getAll() {
        return Lists.newArrayList(this.data.values());
    }

    @Override
    public IPersistStrategy getPersistStrategy() {
        return new HoconPersistStrategy();
    }
}
