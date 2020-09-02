package me.mrdaniel.ageofittgard.listener;

import me.mrdaniel.ageofittgard.gui.inventory.QuestListInventoryMenu;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.filter.cause.First;

public class TestListener {

    @Listener
    public void onBlockClick(InteractBlockEvent.Secondary.OffHand e, @First Player player) {
        if (e.getTargetBlock().getState().getType() == BlockTypes.BOOKSHELF) {
            new QuestListInventoryMenu(player).open();
//            new QuestListBookMenu(player).open();
        }
    }
}
