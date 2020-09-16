package me.mrdaniel.ageofittgard.catalogtypes.eventtype;

import org.spongepowered.api.registry.CatalogRegistryModule;

import java.util.Collection;
import java.util.Optional;

public class EventTypeRegistryModule implements CatalogRegistryModule<EventType> {

	@Override
	public Optional<EventType> getById(String id) {
		for (EventType type : this.getAll()) {
			if (type.getId().equalsIgnoreCase(id)) {
				return Optional.of(type);
			}
		}
		return Optional.empty();
	}

	@Override
	public Collection<EventType> getAll() {
		return EventTypes.ALL;
	}
}
