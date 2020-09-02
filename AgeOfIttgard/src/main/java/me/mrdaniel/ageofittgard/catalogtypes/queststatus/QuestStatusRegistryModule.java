package me.mrdaniel.ageofittgard.catalogtypes.queststatus;

import org.spongepowered.api.registry.CatalogRegistryModule;

import java.util.Collection;
import java.util.Optional;

public class QuestStatusRegistryModule implements CatalogRegistryModule<QuestStatus> {

	@Override
	public Optional<QuestStatus> getById(String id) {
		for (QuestStatus type : this.getAll()) {
			if (type.getId().equalsIgnoreCase(id)) {
				return Optional.of(type);
			}
		}
		return Optional.empty();
	}

	@Override
	public Collection<QuestStatus> getAll() {
		return QuestStatusus.ALL;
	}
}
