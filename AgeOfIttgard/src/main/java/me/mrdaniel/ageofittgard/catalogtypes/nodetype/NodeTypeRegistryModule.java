package me.mrdaniel.ageofittgard.catalogtypes.nodetype;

import org.spongepowered.api.registry.CatalogRegistryModule;

import java.util.Collection;
import java.util.Optional;

public class NodeTypeRegistryModule implements CatalogRegistryModule<NodeType> {

	@Override
	public Optional<NodeType> getById(String id) {
		for (NodeType type : this.getAll()) {
			if (type.getId().equalsIgnoreCase(id)) {
				return Optional.of(type);
			}
		}
		return Optional.empty();
	}

	@Override
	public Collection<NodeType> getAll() {
		return NodeTypes.ALL;
	}
}
