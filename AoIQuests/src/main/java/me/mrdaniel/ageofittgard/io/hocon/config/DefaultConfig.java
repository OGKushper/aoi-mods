package me.mrdaniel.ageofittgard.io.hocon.config;

import me.mrdaniel.ageofittgard.AgeOfIttgard;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Temp DefaultConfig class till i can clean up the NPCs class.
 *
 * @param <T>
 */
public class DefaultConfig<T> extends Config<T> {

    public DefaultConfig(Class<T> clazz, Path configDir, String fileName) {
        super(clazz, configDir, fileName);
    }

    protected void createFile(Path configDir, String fileName) {
        try {
            AgeOfIttgard.getInstance().getContainer().getAsset(fileName).get().copyToDirectory(configDir);
        } catch (IOException exc) {
            AgeOfIttgard.getInstance().getLogger().error("Failed to copy default config from assets folder", exc);
        }
    }
}