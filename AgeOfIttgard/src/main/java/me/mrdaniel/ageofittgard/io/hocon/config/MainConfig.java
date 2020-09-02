package me.mrdaniel.ageofittgard.io.hocon.config;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class MainConfig {

    @Setting public Storage storage = new Storage();

    @ConfigSerializable
    public static class Storage {
        public String storageType = "hocon";
    }
}
