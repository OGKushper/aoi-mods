package me.mrdaniel.ageofittgard.manager;

import com.google.common.collect.Maps;
import me.mrdaniel.ageofittgard.AgeOfIttgard;
import me.mrdaniel.ageofittgard.data.dialogue.DialogueData;
import me.mrdaniel.ageofittgard.io.IPersistStrategy;
import me.mrdaniel.ageofittgard.io.hocon.HoconDialogueStore;
import me.mrdaniel.ageofittgard.io.hocon.config.MainConfig;
import me.mrdaniel.ageofittgard.quest.IDialogueStore;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueRunner;
import me.mrdaniel.ageofittgard.quest.dialogue.NPCDialogue;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class DialogueManager {

    private IDialogueStore dialogueStore;

    private final Map<UUID, DialogueRunner> runners;

    public DialogueManager() {
        this.runners = Maps.newHashMap();
    }

    public void load(MainConfig config) {
        if (this.dialogueStore != null) {
            this.dialogueStore.unload();
        }

        // TODO: Add different storage types
        this.dialogueStore = new HoconDialogueStore(AgeOfIttgard.getInstance().getConfigDir());
        this.dialogueStore.load();

        this.runners.clear();
    }

    public NPCDialogue createDialogue() {
        return this.dialogueStore.create(this.getNextDialogueId());
    }

    public Optional<NPCDialogue> getDialogue(Integer id) {
        return this.dialogueStore.get(id);
    }

    public List<NPCDialogue> getAllDialogues() {
        return this.dialogueStore.getAll();
    }

    private int getNextDialogueId() {
        int id = 1;

        while (this.getDialogue(id).isPresent()) {
            id++;
        }
        return id;
    }

    public void startDialogue(int dialogueId, Player player) {
        if (this.runners.containsKey(player.getUniqueId())) {
            return;
        }

        NPCDialogue dialogue = this.getDialogue(dialogueId).orElse(null);
        DialogueData data = player.get(DialogueData.class).orElse(new DialogueData());

        if (dialogue == null) {
            AgeOfIttgard.getInstance().getLogger().warn("Failed to find Dialogue " + dialogueId + " for a Quest Objective!");
            player.sendMessage(Text.of(TextColors.RED, "Dialogue not found. Please contact an admin."));
            return;
        }

        DialogueRunner runner = new DialogueRunner(player, data, dialogue);
        this.runners.put(player.getUniqueId(), runner);
        runner.start();
    }

    public void stopDialogue(UUID uniqueId) {
        this.runners.remove(uniqueId);
    }

    public IPersistStrategy getPersistStrategy() {
        return this.dialogueStore.getPersistStrategy();
    }
}
