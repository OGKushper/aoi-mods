//package me.mrdaniel.ageofittgard.quest.dialogue.node;
//
//import com.google.common.collect.Lists;
//import me.mrdaniel.ageofittgard.catalogtypes.nodetype.NodeTypes;
//import me.mrdaniel.ageofittgard.gui.chat.DialogueChoiceMenu;
//import me.mrdaniel.ageofittgard.quest.dialogue.DialogueNode;
//import me.mrdaniel.ageofittgard.quest.dialogue.DialogueRunner;
//
//import java.util.List;
//
//public class ChooseDialogueNode extends DialogueNode {
//
//    private final List<Integer> links;
//
//    public ChooseDialogueNode(int nodeId) {
//        super(nodeId, NodeTypes.CHOOSE);
//
//        this.links = Lists.newArrayList();
//    }
//
//    @Override
//    public void run(DialogueRunner runner) {
//        super.run(runner);
//
//        new DialogueChoiceMenu(runner, this).open();
//    }
//
//    public List<Integer> getLinks() {
//        return links;
//    }
//
//    public ChooseDialogueNode addLinks(int... linkIds) {
//        for (int linkId : linkIds) {
//            this.links.add(linkId);
//        }
//        return this;
//    }
//}
