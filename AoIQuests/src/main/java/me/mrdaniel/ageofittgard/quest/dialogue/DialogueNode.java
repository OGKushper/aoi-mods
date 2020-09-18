package me.mrdaniel.ageofittgard.quest.dialogue;

import com.google.common.collect.Lists;
import me.mrdaniel.ageofittgard.gui.chat.DialogueChoiceMenu;

import java.util.List;

public class DialogueNode {

    private int nodeId;
    private boolean choiceMenu;

    private final List<Integer> links;
    private final List<Integer> events;

    public DialogueNode(int nodeId, boolean choiceMenu) {
        this.nodeId = nodeId;
        this.choiceMenu = choiceMenu;
        this.links = Lists.newArrayList();
        this.events = Lists.newArrayList();
    }

    public void run(DialogueRunner runner) {
        runner.setProgress(this.nodeId);

        this.events.stream().map(runner.getDialogue().getEvents()::get).forEach(e -> e.run(runner));

        if (this.choiceMenu) {
            new DialogueChoiceMenu(runner, this).open();
            return;
        } else {
            for (Integer linkId : this.links) {
                if (runner.getDialogue().metRequirements(runner.getPlayer(), linkId, true)) {
                    runner.runLink(linkId);
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

    public List<Integer> getLinks() {
        return this.links;
    }

    public DialogueNode addLinks(int... linkIds) {
        for (int linkId : linkIds) {
            this.links.add(linkId);
        }
        return this;
    }

    public List<Integer> getEvents() {
        return this.events;
    }

    public DialogueNode addEvents(int... eventIds) {
        for (int eventId : eventIds) {
            this.events.add(eventId);
        }
        return this;
    }
}
