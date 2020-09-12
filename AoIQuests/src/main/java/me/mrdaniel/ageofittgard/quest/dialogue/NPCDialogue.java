package me.mrdaniel.ageofittgard.quest.dialogue;

import com.google.common.collect.Maps;
import me.mrdaniel.ageofittgard.AoIQuests;
import me.mrdaniel.ageofittgard.io.IPersistStrategy;
import me.mrdaniel.ageofittgard.quest.quest.QuestRequirement;

import java.util.Map;

public class NPCDialogue {

    public static final long DELAY_TICKS = 60L;

    private IPersistStrategy persistStrategy;

    private final int dialogueId;
    private int firstNode;
    private final Map<Integer, DialogueNode> nodes;
    private final Map<Integer, DialogueLink> links;
    private final Map<Integer, QuestRequirement> requirements;

    public NPCDialogue() {
        this(AoIQuests.getInstance().getDialogueManager().getPersistStrategy(), 0);
    }

    public NPCDialogue(IPersistStrategy persistStrategy, int dialogueId) {
        this.persistStrategy = persistStrategy;

        this.dialogueId = dialogueId;
        this.nodes = Maps.newHashMap();
        this.links = Maps.newHashMap();
        this.requirements = Maps.newHashMap();
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

    public Map<Integer, QuestRequirement> getRequirements() {
        return this.requirements;
    }

    public NPCDialogue addRequirement(QuestRequirement requirement) {
        this.requirements.put(requirement.getRequirementId(), requirement);
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
