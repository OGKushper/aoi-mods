package me.mrdaniel.ageofittgard.catalogtypes.objectivetype;

import me.mrdaniel.ageofittgard.quest.quest.QuestObjective;
import org.spongepowered.api.CatalogType;
import org.spongepowered.api.util.annotation.CatalogedBy;

@CatalogedBy(ObjectiveTypes.class)
public class ObjectiveType implements CatalogType {

	private final String name;
	private final String id;
	private final Class<? extends QuestObjective> hoconClazz;

	ObjectiveType(String name, String id, Class<? extends QuestObjective> hoconClazz) {
		this.name = name;
		this.id = id;
		this.hoconClazz = hoconClazz;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getId() {
		return this.id;
	}

	public <T extends QuestObjective> Class<T> getObjectiveClass() {
		return (Class<T>) this.hoconClazz;
	}
}
