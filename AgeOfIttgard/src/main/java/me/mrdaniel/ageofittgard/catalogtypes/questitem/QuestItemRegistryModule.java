package me.mrdaniel.ageofittgard.catalogtypes.questitem;

import org.spongepowered.api.registry.CatalogRegistryModule;

import java.util.Collection;
import java.util.Optional;

public class QuestItemRegistryModule implements CatalogRegistryModule<QuestItem> {

	@Override
	public Optional<QuestItem> getById(String id) {
		for (QuestItem type : this.getAll()) {
			if (type.getId().equalsIgnoreCase(id)) {
				return Optional.of(type);
			}
		}
		return Optional.empty();
	}

	@Override
	public Collection<QuestItem> getAll() {
		return QuestItems.ALL;
	}
}
