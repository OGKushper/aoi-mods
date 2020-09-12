package me.mrdaniel.ageofittgard.catalogtypes.requirementtype;

import org.spongepowered.api.CatalogType;
import org.spongepowered.api.util.annotation.CatalogedBy;

@CatalogedBy(RequirementTypes.class)
public class RequirementType implements CatalogType {

	private final String name;
	private final String id;

	RequirementType(String name, String id) {
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
