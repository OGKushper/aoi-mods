package me.mrdaniel.ageofittgard.catalogtypes.conditiontype;

import org.spongepowered.api.registry.CatalogRegistryModule;

import java.util.Collection;
import java.util.Optional;

public class ConditionTypeRegistryModule implements CatalogRegistryModule<ConditionType> {

	@Override
	public Optional<ConditionType> getById(String id) {
		for (ConditionType type : this.getAll()) {
			if (type.getId().equalsIgnoreCase(id)) {
				return Optional.of(type);
			}
		}
		return Optional.empty();
	}

	@Override
	public Collection<ConditionType> getAll() {
		return ConditionTypes.ALL;
	}
}