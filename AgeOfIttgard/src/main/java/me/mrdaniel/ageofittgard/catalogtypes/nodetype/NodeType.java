package me.mrdaniel.ageofittgard.catalogtypes.nodetype;

import org.spongepowered.api.CatalogType;
import org.spongepowered.api.util.annotation.CatalogedBy;

@CatalogedBy(NodeTypes.class)
public class NodeType implements CatalogType {

	private final String name;
	private final String id;

	NodeType(String name, String id) {
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
