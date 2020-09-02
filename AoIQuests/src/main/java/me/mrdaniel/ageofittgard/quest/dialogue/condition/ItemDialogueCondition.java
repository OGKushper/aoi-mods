package me.mrdaniel.ageofittgard.quest.dialogue.condition;

import me.mrdaniel.ageofittgard.catalogtypes.conditiontype.ConditionTypes;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueCondition;
import me.mrdaniel.ageofittgard.util.ItemUtils;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.ItemStack;

public class ItemDialogueCondition extends DialogueCondition {

    private ItemStack item;
    private int itemAmount;
    private boolean take;

    public ItemDialogueCondition(int conditionId) {
        super(ConditionTypes.ITEM, conditionId);
    }

    public ItemDialogueCondition(int conditionId, ItemStack item, int itemAmount, boolean take) {
        this(conditionId);

        this.item = item;
        this.itemAmount = itemAmount;
        this.take = take;
    }

    @Override
    public boolean evaluate(Player player) {
        return ItemUtils.hasItems(player, this.item, this.itemAmount);
    }

    @Override
    public boolean apply(Player player) {
        return this.take ? ItemUtils.takeItems(player, this.item, this.itemAmount) : this.evaluate(player);
    }

    public ItemStack getItem() {
        return this.item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public int getItemAmount() {
        return this.itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    public boolean isTake() {
        return this.take;
    }

    public void setTake(boolean take) {
        this.take = take;
    }
}
