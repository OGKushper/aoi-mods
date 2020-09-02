package me.mrdaniel.ageofittgard.quest.dialogue;

import com.google.common.collect.Lists;
import org.spongepowered.api.text.Text;

import java.util.List;

public class DialogueLink {

    private final int linkId;
    private final int nextNodeId;

    private Text choiceLine;
    private final List<Text> npcLines;
    private final List<Integer> conditions;

    public DialogueLink(int linkId, int nextNodeId) {
        this.linkId = linkId;
        this.nextNodeId = nextNodeId;
        this.choiceLine = null;
        this.npcLines = Lists.newArrayList();
        this.conditions = Lists.newArrayList();
    }

    public void run(DialogueRunner runner, int lineIndex) {
        runner.getPlayer().sendMessage(this.npcLines.get(lineIndex));

        if (lineIndex + 1 < this.npcLines.size()) {
            runner.runLinkDelayed(this, lineIndex + 1);
        } else {
            runner.runNodeDelayed(this.nextNodeId);
        }
    }

    public int getLinkId() {
        return linkId;
    }

    public int getNextNodeId() {
        return nextNodeId;
    }

    public Text getChoiceLine() {
        return choiceLine;
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

    public List<Integer> getConditions() {
        return conditions;
    }

    public DialogueLink addCondition(int conditionId) {
        this.conditions.add(conditionId);
        return this;
    }
}
