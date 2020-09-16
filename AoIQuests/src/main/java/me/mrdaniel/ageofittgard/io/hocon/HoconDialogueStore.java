package me.mrdaniel.ageofittgard.io.hocon;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import me.mrdaniel.ageofittgard.AoIQuests;
import me.mrdaniel.ageofittgard.io.IPersistStrategy;
import me.mrdaniel.ageofittgard.io.hocon.config.Config;
import me.mrdaniel.ageofittgard.quest.IDialogueStore;
import me.mrdaniel.ageofittgard.quest.dialogue.Dialogue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HoconDialogueStore implements IDialogueStore {

    private final Path storageDir;
    private final Map<Integer, Dialogue> data;

    public HoconDialogueStore(Path configDir) {
        this.storageDir = configDir.resolve("dialogue");
        this.data = Maps.newHashMap();
    }

    @Override
    public void load() {
        this.unload();

        if (!Files.exists(this.storageDir)) {
            try {
                Files.createDirectories(this.storageDir);
            } catch (final IOException exc) {
                AoIQuests.getInstance().getLogger().error("Failed to create main dialogue storage directory",  exc);
                return;
            }
        }

        for (String fileName : this.storageDir.toFile().list()) {
            Config<Dialogue> config = new Config<>(Dialogue.class, this.storageDir, fileName);
            Dialogue dialogue = config.get();
            dialogue.load(config);

            this.data.put(dialogue.getNpcId(), dialogue);
        }
    }

    @Override
    public void unload() {
        this.data.values().forEach(Dialogue::unload); // Prevents memory leaks
        this.data.clear();
    }

    @Override
    public void delete(Dialogue dialogue) {
        Dialogue deleted = this.data.remove(dialogue.getNpcId());
        if (deleted != null) {
            deleted.delete();
        }
    }

    @Override
    public Optional<Dialogue> get(int npcId) {
        return Optional.ofNullable(this.data.get(npcId));
    }

    @Override
    public Dialogue getOrCreate(int npcId) {
        return this.data.computeIfAbsent(npcId, this::create);
    }

    @Override
    public List<Dialogue> getAll() {
        return Lists.newArrayList(this.data.values());
    }

    private Dialogue create(int npcId) {
        Config<Dialogue> config = new Config<>(new Dialogue(this.getPersistStrategy(), npcId), this.storageDir, "dialogue_npc_" + npcId + ".conf");
        Dialogue dialogue = config.get();
        dialogue.load(config);

        this.data.put(npcId, dialogue);

        return dialogue;
    }

    @Override
    public IPersistStrategy getPersistStrategy() {
        return new HoconPersistStrategy();
    }
}
