package me.mrdaniel.ageofittgard.io.hocon;

import me.mrdaniel.ageofittgard.io.hocon.config.Config;
import me.mrdaniel.ageofittgard.io.IPersistStrategy;

public class HoconPersistStrategy implements IPersistStrategy {

    private Config<?> config;

    @Override
    public void load(Object saveable) {
        this.config = (Config<?>) saveable;
    }

    @Override
    public void unload() {
        this.config = null;
    }

    @Override
    public void save() {
        if (this.config != null) {
            this.config.save();
        }
    }

    @Override
    public void delete() {
        this.unload();

        if (this.config != null) {
            this.config.delete();
        }
    }
}
