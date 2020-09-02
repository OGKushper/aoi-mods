package me.mrdaniel.ageofittgard.quest.dialogue.node;

import me.mrdaniel.ageofittgard.catalogtypes.nodetype.NodeTypes;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueNode;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueRunner;

public class BreakDialogueNode extends DialogueNode {

    private int nextNodeId;

    public BreakDialogueNode(int nodeId) {
        super(nodeId, NodeTypes.BREAK);
    }

    @Override
    public void run(DialogueRunner runner) {
        runner.getData().setProgress(runner.getDialogue().getDialogueId(), this.nextNodeId);
        runner.getPlayer().offer(runner.getData());
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
