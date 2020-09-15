package me.mrdaniel.ageofittgard.quest;

import me.mrdaniel.ageofittgard.io.IPersistStrategy;
import me.mrdaniel.ageofittgard.quest.dialogue.NPCDialogue;

import java.util.List;
import java.util.Optional;

public interface IDialogueStore {

    void load();
    void unload();
    void delete(NPCDialogue dialogue);

    Optional<NPCDialogue> get(int npcId);
    NPCDialogue getOrCreate(int npcId);
    List<NPCDialogue> getAll();

    IPersistStrategy getPersistStrategy();
}
