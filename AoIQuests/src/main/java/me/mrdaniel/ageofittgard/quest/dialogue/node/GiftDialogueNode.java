package me.mrdaniel.ageofittgard.quest.dialogue.node;

import me.mrdaniel.ageofittgard.AgeOfIttgard;
import me.mrdaniel.ageofittgard.catalogtypes.nodetype.NodeTypes;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueNode;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueRunner;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;

public class GiftDialogueNode extends DialogueNode {

    private int linkId;
    private ItemStack item;

    public GiftDialogueNode(int nodeId) {
        super(nodeId, NodeTypes.GIFT);
    }

    @Override
    public void run(DialogueRunner runner) {
        super.run(runner);

        Inventory inv = runner.getPlayer().getInventory();
        if (inv.canFit(this.item)) {
            inv.offer(this.item.copy());
        } else {
            AgeOfIttgard.getInstance().getPlayerDataManager().getPlayerData(runner.getPlayer().getUniqueId()).addUnclaimed(this.item.copy()).save();
        }
        runner.runLink(this.linkId);
    }

    public int getLinkId() {
        return this.linkId;
    }

    public GiftDialogueNode setLinkId(int linkId) {
        this.linkId = linkId;
        return this;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public GiftDialogueNode setItem(ItemStack item) {
        this.item = item;
        return this;
    }
}
