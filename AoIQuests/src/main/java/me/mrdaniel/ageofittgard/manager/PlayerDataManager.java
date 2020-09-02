package me.mrdaniel.ageofittgard.manager;

import me.mrdaniel.ageofittgard.AoIQuests;
import me.mrdaniel.ageofittgard.io.IPersistStrategy;
import me.mrdaniel.ageofittgard.io.hocon.config.MainConfig;
import me.mrdaniel.ageofittgard.io.hocon.HoconPlayerDataStore;
import me.mrdaniel.ageofittgard.quest.player.PlayerData;
import me.mrdaniel.ageofittgard.quest.IPlayerDataStore;

import java.util.UUID;

public class PlayerDataManager {

    private IPlayerDataStore playerDataStore;

    public PlayerDataManager() {
        this.playerDataStore = null;
    }

    public void load(MainConfig config) {
        if (this.playerDataStore != null) {
            this.playerDataStore.unload();
        }

        // TODO: Add different storage types
        this.playerDataStore = new HoconPlayerDataStore(AoIQuests.getInstance().getConfigDir());
        this.playerDataStore.load();
    }

    public PlayerData getPlayerData(UUID uuid) {
        return this.playerDataStore.getPlayerData(uuid);
    }

    public PlayerData load(UUID uuid) {
        return this.playerDataStore.load(uuid);
    }

    public void unload(UUID uuid) {
        this.playerDataStore.unload(uuid);
    }

    public IPersistStrategy getPersistStrategy() {
        return this.playerDataStore.getPersistStrategy();
    }
}
