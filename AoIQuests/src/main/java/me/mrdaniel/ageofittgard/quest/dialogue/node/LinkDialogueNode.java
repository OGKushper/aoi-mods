//package me.mrdaniel.ageofittgard.quest.dialogue.node;
//
//import me.mrdaniel.ageofittgard.catalogtypes.nodetype.NodeTypes;
//import me.mrdaniel.ageofittgard.quest.dialogue.DialogueNode;
//import me.mrdaniel.ageofittgard.quest.dialogue.DialogueRunner;
//
//public class LinkDialogueNode extends DialogueNode {
//
//    private int linkId;
//
//    public LinkDialogueNode(int nodeId) {
//        super(nodeId, NodeTypes.LINK);
//    }
//
//    @Override
//    public void run(DialogueRunner runner) {
//        super.run(runner);
//
//        runner.runLink(this.linkId);
//    }
//
//    public int getLinkId() {
//        return linkId;
//    }
//
//    public LinkDialogueNode setLinkId(int linkId) {
//        this.linkId = linkId;
//        return this;
//    }
//}
