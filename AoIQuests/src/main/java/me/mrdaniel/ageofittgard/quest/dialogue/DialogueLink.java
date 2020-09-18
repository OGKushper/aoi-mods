package me.mrdaniel.ageofittgard.quest.dialogue;

import com.google.common.collect.Lists;
import me.mrdaniel.ageofittgard.quest.Requirement;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import java.util.List;

public class DialogueLink {

    private DialogueNode nextNode;

    private Text choiceLine;
    private final List<Text> npcLines;
    private final List<Requirement> requirements;

    public DialogueLink() {
        this(null);
    }

    public DialogueLink(DialogueNode nextNode) {
        this.nextNode = nextNode;
        this.choiceLine = null;
        this.npcLines = Lists.newArrayList();
        this.requirements = Lists.newArrayList();
    }

    public void run(DialogueRunner runner, int lineIndex) {
        if (lineIndex < this.npcLines.size()) {
            runner.getPlayer().sendMessage(this.npcLines.get(lineIndex));
            runner.runLinkDelayed(this, lineIndex + 1);
        } else {
            runner.runNode(this.nextNode);
        }
    }

    public DialogueNode getNextNode() {
        return this.nextNode;
    }

    public DialogueLink setNextNode(DialogueNode nextNode) {
        this.nextNode = nextNode;
        return this;
    }

    public Text getChoiceLine() {
        return this.choiceLine;
    }

    public DialogueLink setChoiceLine(Text choiceLine) {
        this.choiceLine = choiceLine;
        return this;
    }

    public List<Text> getNpcLines() {
        return this.npcLines;
    }

    public DialogueLink addNpcLine(Text line) {
        this.npcLines.add(line);
        return this;
    }

    public List<Requirement> getRequirements() {
        return this.requirements;
    }

    public DialogueLink addRequirement(Requirement req) {
        this.requirements.add(req);
        return this;
    }

    public boolean metRequirements(Player player, boolean apply) {
        for (Requirement req : this.requirements) {
            if (!(apply ? req.apply(player) : req.evaluate(player))) {
                return false;
            }
        }

        return true;
    }
}
