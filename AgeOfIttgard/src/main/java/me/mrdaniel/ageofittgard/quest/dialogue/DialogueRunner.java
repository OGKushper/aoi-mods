package me.mrdaniel.ageofittgard.quest.dialogue;

import me.mrdaniel.ageofittgard.AgeOfIttgard;
import me.mrdaniel.ageofittgard.data.dialogue.DialogueData;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.scheduler.Task;

public class DialogueRunner {

    private final Player player;
    private final DialogueData data;
    private final NPCDialogue dialogue;

    public DialogueRunner(Player player, DialogueData data, NPCDialogue dialogue) {
        this.player = player;
        this.data = data;
        this.dialogue = dialogue;
    }

    public void start() {
        int currentNode = this.data.getProgress(this.dialogue.getDialogueId());
        this.runNode(currentNode == 0 ? dialogue.getFirstNode() : currentNode);
    }

    public void runNode(int nodeId) {
        if (!this.player.isOnline()) {
            this.stop();
        } else {
            this.dialogue.getNodes().get(nodeId).run(this);
        }
    }

    public void runNodeDelayed(int nodeId) {
        Task.builder().delayTicks(NPCDialogue.DELAY_TICKS).execute(() -> this.runNode(nodeId)).submit(AgeOfIttgard.getInstance());
    }

    public void runLink(int linkId) {
        this.runLink(linkId, 0);
    }

    public void runLink(DialogueLink link) {
        this.runLink(link, 0);
    }

    public void runLink(int linkId, int lineIndex) {
        this.runLink(this.dialogue.getLinks().get(linkId), lineIndex);
    }

    public void runLink(DialogueLink link, int lineIndex) {
        if (!this.player.isOnline()) {
            this.stop();
        } else {
            link.run(this, lineIndex);
        }
    }

    public void runLinkDelayed(DialogueLink link, int lineIndex) {
        Task.builder().delayTicks(NPCDialogue.DELAY_TICKS).execute(() -> this.runLink(link, lineIndex)).submit(AgeOfIttgard.getInstance());
    }

    public void stop() {
        AgeOfIttgard.getInstance().getDialogueManager().stopDialogue(this.player.getUniqueId());
    }

    public Player getPlayer() {
        return player;
    }

    public DialogueData getData() {
        return data;
    }

    public NPCDialogue getDialogue() {
        return dialogue;
    }
}
