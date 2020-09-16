//package me.mrdaniel.ageofittgard.quest.dialogue.node;
//
//import me.mrdaniel.ageofittgard.catalogtypes.nodetype.NodeTypes;
//import me.mrdaniel.ageofittgard.event.CompleteDialogueEvent;
//import me.mrdaniel.ageofittgard.quest.dialogue.DialogueNode;
//import me.mrdaniel.ageofittgard.quest.dialogue.DialogueRunner;
//import org.spongepowered.api.Sponge;
//
//// TODO: Overhaul this node type to be used specifically for completing quest objectives.
//public class EndDialogueNode extends DialogueNode {
//
//    public EndDialogueNode(int nodeId) {
//        super(nodeId, NodeTypes.END);
//    }
//
//    @Override
//    public void run(DialogueRunner runner) {
//        runner.deleteProgress();
//        runner.stop();
//
//        Sponge.getEventManager().post(new CompleteDialogueEvent(runner.getPlayer(), runner.getDialogue().getNpcId()));
//    }
//}
