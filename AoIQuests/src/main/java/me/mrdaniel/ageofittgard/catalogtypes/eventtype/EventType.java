package me.mrdaniel.ageofittgard.catalogtypes.eventtype;

import org.spongepowered.api.CatalogType;
import org.spongepowered.api.util.annotation.CatalogedBy;

@CatalogedBy(EventTypes.class)
public class EventType implements CatalogType {

	private final String name;
	private final String id;

	EventType(String name, String id) {
		this.name = name;
		this.id = id;
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
