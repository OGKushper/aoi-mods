package me.mrdaniel.ageofittgard.util;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.query.QueryOperationTypes;

public class ItemUtils {

    public static boolean hasItems(Player player, ItemStack item, int itemAmount) {
        for (Inventory slot : player.getInventory().query(QueryOperationTypes.ITEM_STACK_IGNORE_QUANTITY.of(item)).slots()) {
            itemAmount -= slot.peek().get().getQuantity();
        }

        return itemAmount <= 0;
    }

    public static boolean takeItems(Player player, ItemStack item, int itemAmount) {
        if (!hasItems(player, item, itemAmount)) {
            return false;
        }

        int count = 0;

        for (Inventory slot : player.getInventory().query(QueryOperationTypes.ITEM_STACK_IGNORE_QUANTITY.of(item)).slots()) {
            count += slot.peek().get().getQuantity();

            if (count > itemAmount) {
                ItemStack is = slot.peek().get();
                is.setQuantity(count - itemAmount);
                slot.set(is);
                return true;
            } else if (count == itemAmount) {
                slot.clear();
                return true;
            } else {
                slot.clear();
            }
        }

        return true;
    }
}
