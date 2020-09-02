package me.mrdaniel.ageofittgard.quest.dialogue;

import com.google.common.collect.Maps;
import me.mrdaniel.ageofittgard.AgeOfIttgard;
import me.mrdaniel.ageofittgard.io.IPersistStrategy;

import java.util.Map;

public class NPCDialogue {

    public static final long DELAY_TICKS = 60L;

    private IPersistStrategy persistStrategy;

    private final int dialogueId;
    private int firstNode;
    private final Map<Integer, DialogueNode> nodes;
    private final Map<Integer, DialogueLink> links;
    private final Map<Integer, DialogueCondition> conditions;

    public NPCDialogue() {
        this(AgeOfIttgard.getInstance().getDialogueManager().getPersistStrategy(), 0);
    }

    public NPCDialogue(IPersistStrategy persistStrategy, int dialogueId) {
        this.persistStrategy = persistStrategy;

        this.dialogueId = dialogueId;
        this.nodes = Maps.newHashMap();
        this.links = Maps.newHashMap();
        this.conditions = Maps.newHashMap();
    }

    public int getDialogueId() {
        return this.dialogueId;
    }

    public int getFirstNode() {
        return this.firstNode;
    }

    public NPCDialogue setFirstNode(int firstNode) {
        this.firstNode = firstNode;
        return this;
    }

    public Map<Integer, DialogueNode> getNodes() {
        return this.nodes;
    }

    public NPCDialogue addNode(DialogueNode node) {
        this.nodes.put(node.getNodeId(), node);
        return this;
    }

    public Map<Integer, DialogueLink> getLinks() {
        return this.links;
    }

    public NPCDialogue addLink(DialogueLink link) {
        this.links.put(link.getLinkId(), link);
        return this;
    }

    public Map<Integer, DialogueCondition> getConditions() {
        return this.conditions;
    }

    public NPCDialogue addCondition(DialogueCondition condition) {
        this.conditions.put(condition.getConditionId(), condition);
        return this;
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
