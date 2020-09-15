package me.mrdaniel.ageofittgard.quest.dialogue.node;

import me.mrdaniel.ageofittgard.catalogtypes.nodetype.NodeTypes;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueNode;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueRunner;

public class BreakDialogueNode extends DialogueNode {

    private int nextNodeId;

    public BreakDialogueNode(int nodeId) {
        super(nodeId, NodeTypes.BREAK);
    }

    public BreakDialogueNode(int nodeId, int nextNodeId) {
        this(nodeId);

        this.nextNodeId = nextNodeId;
    }

    @Override
    public void run(DialogueRunner runner) {
        runner.setProgress(this.nextNodeId);
        runner.stop();
    }

    public int getNextNodeId() {
        return nextNodeId;
    }

    public BreakDialogueNode setNextNodeId(int nextNodeId) {
        this.nextNodeId = nextNodeId;
        return this;
    }
}
