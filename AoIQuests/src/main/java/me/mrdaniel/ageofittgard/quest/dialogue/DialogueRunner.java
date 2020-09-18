package me.mrdaniel.ageofittgard.quest.dialogue;

import me.mrdaniel.ageofittgard.AoIQuests;
import me.mrdaniel.ageofittgard.data.dialogue.DialogueData;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.scheduler.Task;

import javax.annotation.Nullable;

public class DialogueRunner {

    private final Player player;
    private final DialogueData data;
    private final Dialogue dialogue;

    public DialogueRunner(Player player, DialogueData data, Dialogue dialogue) {
        this.player = player;
        this.data = data;
        this.dialogue = dialogue;
    }

    public void start() {
        int currentNode = this.data.getProgress(this.dialogue.getNpcId());
        this.runNode(currentNode == 0 ? this.dialogue.getRoot() : this.dialogue.getRoot().getNode(currentNode));
    }

    public void runNode(@Nullable DialogueNode node) {
        if (node == null) {
            this.deleteProgress();
            this.stop();
        } else if (!this.player.isOnline() || !this.player.isLoaded()) {
            this.stop();
        } else {
            node.run(this);
        }
    }

    public void runLink(@Nullable DialogueLink link) {
        this.runLink(link, 0);
    }

    public void runLink(@Nullable DialogueLink link, int lineIndex) {
        if (link == null) {
            this.deleteProgress();
            this.stop();
        } else if (!this.player.isOnline() || !this.player.isLoaded()) {
            this.stop();
        } else {
            link.run(this, lineIndex);
        }
    }

    public void runLinkDelayed(@Nullable DialogueLink link, int lineIndex) {
        Task.builder().delayTicks(Dialogue.DELAY_TICKS).execute(() -> this.runLink(link, lineIndex)).submit(AoIQuests.getInstance());
    }

    public void setProgress(int nodeId) {
        this.data.setProgress(this.dialogue.getNpcId(), nodeId);
        this.player.offer(this.data);
    }

    public void deleteProgress() {
        this.data.deleteProgress(this.dialogue.getNpcId());
        this.player.offer(this.data);
    }

    public void stop() {
        AoIQuests.getInstance().getDialogueManager().stopDialogue(this.player.getUniqueId());
    }

    public Player getPlayer() {
        return player;
    }

    public DialogueData getData() {
        return data;
    }

    public Dialogue getDialogue() {
        return dialogue;
    }
}
