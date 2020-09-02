package me.mrdaniel.ageofittgard.quest.dialogue;

import me.mrdaniel.ageofittgard.catalogtypes.nodetype.NodeType;

public abstract class DialogueNode {

    private int nodeId;
    private NodeType nodeType;

    public DialogueNode(int nodeId, NodeType nodeType) {
        this.nodeId = nodeId;
        this.nodeType = nodeType;
    }

    public void run(DialogueRunner runner) {
        runner.getData().setProgress(runner.getDialogue().getDialogueId(), this.nodeId);
        runner.getPlayer().offer(runner.getData());
    }

    public int getNodeId() {
        return this.nodeId;
    }

    public NodeType getNodeType() {
        return this.nodeType;
    }
}
