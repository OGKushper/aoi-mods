package me.mrdaniel.ageofittgard.catalogtypes.queststatus;

import org.spongepowered.api.CatalogType;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.util.annotation.CatalogedBy;

@CatalogedBy(QuestStatusus.class)
public class QuestStatus implements CatalogType {

	private final String name;
	private final String id;
	private final ItemType itemType;

	QuestStatus(String name, String id, ItemType itemType) {
		this.name = name;
		this.id = id;
		this.itemType = itemType;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getId() {
		return this.id;
	}

	public ItemType getItemType() {
		return this.itemType;
	}
}
