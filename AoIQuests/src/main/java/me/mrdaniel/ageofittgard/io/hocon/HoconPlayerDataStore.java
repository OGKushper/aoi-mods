package me.mrdaniel.ageofittgard.io.hocon;

import com.google.common.collect.Maps;
import me.mrdaniel.ageofittgard.AgeOfIttgard;
import me.mrdaniel.ageofittgard.io.hocon.config.Config;
import me.mrdaniel.ageofittgard.io.IPersistStrategy;
import me.mrdaniel.ageofittgard.quest.IPlayerDataStore;
import me.mrdaniel.ageofittgard.quest.player.PlayerData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;

public class HoconPlayerDataStore implements IPlayerDataStore {

    private final Path storageDir;
    private final Map<UUID, PlayerData> data;

    public HoconPlayerDataStore(Path configDir) {
        this.storageDir = configDir.resolve("players");
        this.data = Maps.newHashMap();
    }

    @Override
    public void load() {
        this.unload();

        if (!Files.exists(this.storageDir)) {
            try {
                Files.createDirectories(this.storageDir);
            } catch (final IOException exc) {
                AgeOfIttgard.getInstance().getLogger().error("Failed to create main player storage directory",  exc);
            }
        }
    }

    @Override
    public void unload() {
        this.data.values().forEach(PlayerData::unload); // Prevents memory leaks
        this.data.clear();
    }

    @Override
    public PlayerData getPlayerData(UUID uuid) {
        if (this.data.containsKey(uuid)) {
            return this.data.get(uuid);
        }

        return this.load(uuid);
    }

    @Override
    public PlayerData load(UUID uuid) {
        Config<PlayerData> config = new Config<>(PlayerData.class, this.storageDir, uuid.toString() + ".conf");
        PlayerData data = config.get();

        data.load(config);
        this.data.put(uuid, data);

        return data;
    }

    @Override
    public void unload(UUID uuid) {
        PlayerData data = this.data.remove(uuid);

        if (data != null) {
            data.unload();
        }
    }

    @Override
    public IPersistStrategy getPersistStrategy() {
        return new HoconPersistStrategy();
    }
}
