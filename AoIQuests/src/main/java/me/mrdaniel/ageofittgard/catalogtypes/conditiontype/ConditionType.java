package me.mrdaniel.ageofittgard.catalogtypes.conditiontype;

import org.spongepowered.api.CatalogType;
import org.spongepowered.api.util.annotation.CatalogedBy;

@CatalogedBy(ConditionTypes.class)
public class ConditionType implements CatalogType {

	private final String name;
	private final String id;

	ConditionType(String name, String id) {
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
