package me.mrdaniel.ageofittgard.catalogtypes.questitem;

import org.spongepowered.api.CatalogType;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.annotation.CatalogedBy;

import java.util.List;

@CatalogedBy(QuestItems.class)
public class QuestItem implements CatalogType {

	private final String name;
	private final String id;

	private final ItemType itemType;
	private final Text itemName;
	private final List<Text> itemLore;

	QuestItem(String name, String id, ItemType itemType, Text itemName, List<Text> itemLore) {
		this.name = name;
		this.id = id;
		this.itemType = itemType;
		this.itemName = itemName;
		this.itemLore = itemLore;
	}

	public ItemStack build() {
		return ItemStack.builder()
				.itemType(this.itemType)
				.quantity(1)
				.add(Keys.DISPLAY_NAME, this.itemName)
				.add(Keys.ITEM_LORE, this.itemLore)
				.build();
	}

    @Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getId() {
		return this.id;
	}
}
