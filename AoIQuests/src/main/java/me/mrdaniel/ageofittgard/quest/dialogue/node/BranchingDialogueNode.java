package me.mrdaniel.ageofittgard.quest.dialogue.node;

import com.google.common.collect.Lists;
import me.mrdaniel.ageofittgard.catalogtypes.nodetype.NodeTypes;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueNode;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueRunner;

import java.util.List;

public class BranchingDialogueNode extends DialogueNode {

    private final List<Integer> links;

    public BranchingDialogueNode(int nodeId) {
        super(nodeId, NodeTypes.BRANCHING);

        this.links = Lists.newArrayList();
    }

    @Override
    public void run(DialogueRunner runner) {
        super.run(runner);

        for (Integer linkId : this.links) {
            if (runner.getDialogue().metRequirements(runner.getPlayer(), linkId, true)) {
                runner.runLink(linkId);
                return;
            }
        }

        runner.deleteProgress();
        runner.stop();
    }

    public List<Integer> getLinks() {
        return this.links;
    }

    public BranchingDialogueNode addLinks(int... linkIds) {
        for (int linkId : linkIds) {
            this.links.add(linkId);
        }
        return this;
    }
}
