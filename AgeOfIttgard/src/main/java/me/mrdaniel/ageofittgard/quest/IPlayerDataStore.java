package me.mrdaniel.ageofittgard.quest;

import me.mrdaniel.ageofittgard.io.IPersistStrategy;
import me.mrdaniel.ageofittgard.quest.player.PlayerData;

import java.util.UUID;

public interface IPlayerDataStore {

    void load();
    void unload();

    PlayerData load(UUID uuid);
    void unload(UUID uuid);

    PlayerData getPlayerData(UUID uuid);

    IPersistStrategy getPersistStrategy();
}
