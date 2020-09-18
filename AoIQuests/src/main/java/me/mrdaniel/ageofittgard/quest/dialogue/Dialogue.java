package me.mrdaniel.ageofittgard.quest.dialogue;

import me.mrdaniel.ageofittgard.AoIQuests;
import me.mrdaniel.ageofittgard.io.IPersistStrategy;

public class Dialogue {

    public static final long DELAY_TICKS = 60L;

    private IPersistStrategy persistStrategy;

    private final int npcId;
    private DialogueNode root;

    /**
     * Required empty constructor for Hocon config file instantiation. Should never be used.
     */
    public Dialogue() {
        this(AoIQuests.getInstance().getDialogueManager().getPersistStrategy(), 0);
    }

    public Dialogue(IPersistStrategy persistStrategy, int npcId) {
        this.persistStrategy = persistStrategy;

        this.npcId = npcId;
    }

    public int getNpcId() {
        return this.npcId;
    }

    public DialogueNode getRoot() {
        return this.root;
    }

    public Dialogue setRoot(DialogueNode root) {
        this.root = root;
        return this;
    }

    public DialogueNode getNode(int nodeId) {
        return this.root.getNode(nodeId);
    }

    public void load(Object saveable) {
        this.persistStrategy.load(saveable);
    }

    public void unload() {
        this.persistStrategy.unload();
    }

    public void save() {
        this.persistStrategy.save();
    }

    public void delete() {
        this.persistStrategy.delete();
    }
}
