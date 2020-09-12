package me.mrdaniel.ageofittgard.quest.quest.requirement;

import me.mrdaniel.ageofittgard.catalogtypes.requirementtype.RequirementTypes;
import me.mrdaniel.ageofittgard.quest.quest.QuestRequirement;
import me.mrdaniel.ageofittgard.util.ItemUtils;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.ItemStack;

public class ItemQuestRequirement extends QuestRequirement {

    private ItemStack item;
    private int itemAmount;
    private boolean take;

    public ItemQuestRequirement(int requirementId) {
        super(RequirementTypes.ITEM, requirementId);
    }

    public ItemQuestRequirement(int requirementId, ItemStack item, int itemAmount, boolean take) {
        this(requirementId);

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
