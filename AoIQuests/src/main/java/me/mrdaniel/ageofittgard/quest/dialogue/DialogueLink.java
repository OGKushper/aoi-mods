package me.mrdaniel.ageofittgard.quest.dialogue;

import com.google.common.collect.Lists;
import org.spongepowered.api.text.Text;

import java.util.List;

public class DialogueLink {

    private final int linkId;
    private int nextNodeId;

    private Text choiceLine;
    private final List<Text> npcLines;
    private final List<Integer> requirements;

    public DialogueLink(int linkId, int nextNodeId) {
        this.linkId = linkId;
        this.nextNodeId = nextNodeId;
        this.choiceLine = null;
        this.npcLines = Lists.newArrayList();
        this.requirements = Lists.newArrayList();
    }

    public void run(DialogueRunner runner, int lineIndex) {
        if (lineIndex < this.npcLines.size()) {
            runner.getPlayer().sendMessage(this.npcLines.get(lineIndex));
            runner.runLinkDelayed(this, lineIndex + 1);
        } else {
            runner.runNode(this.nextNodeId);
        }
    }

    public int getLinkId() {
        return this.linkId;
    }

    public int getNextNodeId() {
        return nextNodeId;
    }

    public void setNextNodeId(int nextNodeId) {
        this.nextNodeId = nextNodeId;
    }

    public Text getChoiceLine() {
        return this.choiceLine;
    }

    public DialogueLink setChoiceLine(Text choiceLine) {
        this.choiceLine = choiceLine;
        return this;
    }

    public List<Text> getNpcLines() {
        return npcLines;
    }

    public DialogueLink addNpcLine(Text line) {
        this.npcLines.add(line);
        return this;
    }

    public List<Integer> getRequirements() {
        return requirements;
    }

    public DialogueLink addRequirements(int... requirementIds) {
        for (int requirementId : requirementIds) {
            this.requirements.add(requirementId);
        }
        return this;
    }
}
