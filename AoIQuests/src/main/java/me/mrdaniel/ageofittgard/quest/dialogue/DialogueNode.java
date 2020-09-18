package me.mrdaniel.ageofittgard.quest.dialogue;

import com.google.common.collect.Lists;
import me.mrdaniel.ageofittgard.gui.chat.DialogueChoiceMenu;

import java.util.List;

public class DialogueNode {

    private int nodeId;
    private boolean choiceMenu;

    private final List<DialogueLink> links;
    private final List<DialogueEvent> events;

    public DialogueNode(int nodeId, boolean choiceMenu) {
        this.nodeId = nodeId;
        this.choiceMenu = choiceMenu;
        this.links = Lists.newArrayList();
        this.events = Lists.newArrayList();
    }

    public void run(DialogueRunner runner) {
        runner.setProgress(this.nodeId);

        this.events.forEach(e -> e.run(runner));

        if (this.choiceMenu) {
            new DialogueChoiceMenu(runner, this).open();
            return;
        } else {
            for (DialogueLink link : this.links) {
                if (link.metRequirements(runner.getPlayer(), true)) {
                    runner.runLink(link);
                    return;
                }
            }
        }

        runner.deleteProgress();
        runner.stop();
    }

    public int getNodeId() {
        return this.nodeId;
    }

    public boolean isChoiceMenu() {
        return this.choiceMenu;
    }

    public List<DialogueLink> getLinks() {
        return this.links;
    }

    public DialogueNode addLink(DialogueLink link) {
        this.links.add(link);
        return this;
    }

    public List<DialogueEvent> getEvents() {
        return this.events;
    }

    public DialogueNode addEvent(DialogueEvent event) {
        this.events.add(event);
        return this;
    }

    public DialogueNode getNode(int nodeId) {
        if (this.nodeId == nodeId) {
            return this;
        }

        DialogueNode node;
        for (DialogueLink link : this.links) {
            if (link.getNextNode() != null && (node = link.getNextNode().getNode(nodeId)) != null) {
                return node;
            }
        }

        return null;
    }
}
