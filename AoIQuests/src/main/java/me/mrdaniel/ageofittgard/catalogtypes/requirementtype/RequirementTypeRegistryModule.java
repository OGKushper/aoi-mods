package me.mrdaniel.ageofittgard.catalogtypes.requirementtype;

import org.spongepowered.api.registry.CatalogRegistryModule;

import java.util.Collection;
import java.util.Optional;

public class RequirementTypeRegistryModule implements CatalogRegistryModule<RequirementType> {

	@Override
	public Optional<RequirementType> getById(String id) {
		for (RequirementType type : this.getAll()) {
			if (type.getId().equalsIgnoreCase(id)) {
				return Optional.of(type);
			}
		}
		return Optional.empty();
	}

	@Override
	public Collection<RequirementType> getAll() {
		return RequirementTypes.ALL;
	}
}
