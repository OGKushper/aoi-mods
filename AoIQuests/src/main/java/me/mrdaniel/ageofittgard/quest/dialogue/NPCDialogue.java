package me.mrdaniel.ageofittgard.quest.dialogue;

import com.google.common.collect.Maps;
import me.mrdaniel.ageofittgard.AoIQuests;
import me.mrdaniel.ageofittgard.io.IPersistStrategy;
import me.mrdaniel.ageofittgard.quest.Requirement;
import org.spongepowered.api.entity.living.player.Player;

import java.util.Map;

public class NPCDialogue {

    public static final long DELAY_TICKS = 60L;

    private IPersistStrategy persistStrategy;

    private final int npcId;
    private int firstNode;
    private final Map<Integer, DialogueNode> nodes;
    private final Map<Integer, DialogueLink> links;
    private final Map<Integer, Requirement> requirements;

    /**
     * Required empty constructor for Hocon config file instantiation. Should never be used.
     */
    public NPCDialogue() {
        this(AoIQuests.getInstance().getDialogueManager().getPersistStrategy(), 0);
    }

    public NPCDialogue(IPersistStrategy persistStrategy, int npcId) {
        this.persistStrategy = persistStrategy;

        this.npcId = npcId;
        this.nodes = Maps.newHashMap();
        this.links = Maps.newHashMap();
        this.requirements = Maps.newHashMap();
    }

    public int getNpcId() {
        return this.npcId;
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

    public Map<Integer, Requirement> getRequirements() {
        return this.requirements;
    }

    public NPCDialogue addRequirement(Requirement requirement) {
        this.requirements.put(requirement.getRequirementId(), requirement);
        return this;
    }

    public boolean metRequirements(Player player, int linkId, boolean apply) {
        DialogueLink link = this.links.get(linkId);

        return link != null && this.metRequirements(player, link, apply);
    }

    public boolean metRequirements(Player player, DialogueLink link, boolean apply) {
        return link.getRequirements().stream()
                .map(this.requirements::get)
                .allMatch(req -> apply ? req.apply(player) : req.evaluate(player));
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
