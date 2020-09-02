package me.mrdaniel.ageofittgard.io.hocon;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import me.mrdaniel.ageofittgard.AgeOfIttgard;
import me.mrdaniel.ageofittgard.io.hocon.config.Config;
import me.mrdaniel.ageofittgard.io.IPersistStrategy;
import me.mrdaniel.ageofittgard.quest.IDialogueStore;
import me.mrdaniel.ageofittgard.quest.dialogue.NPCDialogue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HoconDialogueStore implements IDialogueStore {

    private final Path storageDir;
    private final Map<Integer, NPCDialogue> data;

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
                AgeOfIttgard.getInstance().getLogger().error("Failed to create main dialogue storage directory",  exc);
                return;
            }
        }

        for (String fileName : this.storageDir.toFile().list()) {
            Config<NPCDialogue> config = new Config<>(NPCDialogue.class, this.storageDir, fileName);
            NPCDialogue dialogue = config.get();
            dialogue.load(config);

            this.data.put(dialogue.getDialogueId(), dialogue);
        }
    }

    @Override
    public void unload() {
        this.data.values().forEach(NPCDialogue::unload); // Prevents memory leaks
        this.data.clear();
    }

    @Override
    public NPCDialogue create(int id) {
        Config<NPCDialogue> config = new Config<>(new NPCDialogue(new HoconPersistStrategy(), id), this.storageDir, "dialogue_" + id + ".conf");
        NPCDialogue dialogue = config.get();
        dialogue.load(config);

        this.data.put(id, dialogue);

        return dialogue;
    }

    @Override
    public void delete(NPCDialogue dialogue) {
        NPCDialogue deleted = this.data.remove(dialogue.getDialogueId());
        if (deleted != null) {
            deleted.delete();
        }
    }

    @Override
    public Optional<NPCDialogue> get(Integer id) {
        return Optional.ofNullable(this.data.get(id));
    }

    @Override
    public List<NPCDialogue> getAll() {
        return Lists.newArrayList(this.data.values());
    }

    @Override
    public IPersistStrategy getPersistStrategy() {
        return new HoconPersistStrategy();
    }
}
