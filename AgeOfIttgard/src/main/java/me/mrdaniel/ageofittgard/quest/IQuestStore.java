package me.mrdaniel.ageofittgard.quest;

import me.mrdaniel.ageofittgard.io.IPersistStrategy;
import me.mrdaniel.ageofittgard.quest.quest.Quest;

import java.util.List;
import java.util.Optional;

public interface IQuestStore {

    void load();
    void unload();

    Quest create(int id);
    void delete(Quest data);

    Optional<Quest> get(Integer id);
    List<Quest> getAll();

    IPersistStrategy getPersistStrategy();
}
