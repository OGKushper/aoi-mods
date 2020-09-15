package me.mrdaniel.ageofittgard.catalogtypes.requirementtype;

import com.google.common.collect.Lists;

import java.util.List;

public final class RequirementTypes {

	public static final RequirementType ITEM = new RequirementType("Item", "item");
	public static final RequirementType LOCATION = new RequirementType("Location", "location");
	public static final RequirementType MONEY = new RequirementType("Money", "money");
	public static final RequirementType TIME = new RequirementType("Time", "time");
	public static final RequirementType QUEST_STATUS = new RequirementType("Quest Status", "quest");
	public static final RequirementType STAGE_ACTIVE = new RequirementType("Stage Active", "stage");

	public static final List<RequirementType> ALL = Lists.newArrayList(ITEM, LOCATION, MONEY, TIME, QUEST_STATUS, STAGE_ACTIVE);
}
