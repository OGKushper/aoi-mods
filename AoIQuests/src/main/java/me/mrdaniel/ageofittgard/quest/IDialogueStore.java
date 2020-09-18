package me.mrdaniel.ageofittgard.quest;

import me.mrdaniel.ageofittgard.io.IPersistStrategy;
import me.mrdaniel.ageofittgard.quest.dialogue.Dialogue;

import java.util.List;
import java.util.Optional;

public interface IDialogueStore {

    void load();
    void unload();
    void delete(Dialogue dialogue);

    Optional<Dialogue> get(int npcId);
    Dialogue getOrCreate(int npcId);
    List<Dialogue> getAll();

    IPersistStrategy getPersistStrategy();
}
