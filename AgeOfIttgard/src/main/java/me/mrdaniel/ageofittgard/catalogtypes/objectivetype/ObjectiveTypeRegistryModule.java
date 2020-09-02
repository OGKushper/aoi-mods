package me.mrdaniel.ageofittgard.catalogtypes.objectivetype;

import org.spongepowered.api.registry.CatalogRegistryModule;

import java.util.Collection;
import java.util.Optional;

public class ObjectiveTypeRegistryModule implements CatalogRegistryModule<ObjectiveType> {

	@Override
	public Optional<ObjectiveType> getById(String id) {
		for (ObjectiveType type : this.getAll()) {
			if (type.getId().equalsIgnoreCase(id)) {
				return Optional.of(type);
			}
		}
		return Optional.empty();
	}

	@Override
	public Collection<ObjectiveType> getAll() {
		return ObjectiveTypes.ALL;
	}
}
