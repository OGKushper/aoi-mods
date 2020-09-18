package me.mrdaniel.ageofittgard.quest.dialogue.event;

import me.mrdaniel.ageofittgard.AoIQuests;
import me.mrdaniel.ageofittgard.catalogtypes.eventtype.EventTypes;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueEvent;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueRunner;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;

public class DialogueEventGift extends DialogueEvent {

    private ItemStack item;

    public DialogueEventGift() {
        super(EventTypes.GIFT);
    }

    public DialogueEventGift(ItemStack item) {
        this();
        this.item = item;
    }

    @Override
    public void run(DialogueRunner runner) {
        Inventory inv = runner.getPlayer().getInventory();
        if (inv.canFit(this.item)) {
            inv.offer(this.item.copy());
        } else {
            AoIQuests.getInstance().getPlayerDataManager().getPlayerData(runner.getPlayer().getUniqueId()).addUnclaimed(this.item.copy()).save();
        }
    }

    public ItemStack getItem() {
        return this.item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }
}
