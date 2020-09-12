package me.mrdaniel.ageofittgard.quest.quest.objective;

import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveTypes;
import me.mrdaniel.ageofittgard.quest.quest.QuestObjective;
import me.mrdaniel.ageofittgard.util.ItemUtils;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.item.inventory.ItemStack;

public class ObjectiveCollect extends QuestObjective {

    private ItemStack item;
    private int itemAmount;

    public ObjectiveCollect(int objectiveId) {
        this(objectiveId, null, 0);
    }

    public ObjectiveCollect(int objectiveId, ItemStack item, int itemAmount) {
        super(objectiveId, ObjectiveTypes.COLLECT);

        this.item = item;
        this.itemAmount = itemAmount;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    @Override
    protected boolean evaluateObjective(Player player, Event e) {
        return ItemUtils.hasItems(player, item, itemAmount);
    }
}
