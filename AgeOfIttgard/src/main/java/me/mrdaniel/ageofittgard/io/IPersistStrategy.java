package me.mrdaniel.ageofittgard.io;

public interface IPersistStrategy {

    void load(Object saveable);
    void unload();

    void save();
    void delete();
}
