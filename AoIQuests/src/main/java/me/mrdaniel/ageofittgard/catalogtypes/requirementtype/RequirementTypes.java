package me.mrdaniel.ageofittgard.catalogtypes.requirementtype;

import com.google.common.collect.Lists;

import java.util.List;

public final class RequirementTypes {

	public static final RequirementType ITEM = new RequirementType("Item", "item");
	public static final RequirementType MONEY = new RequirementType("Money", "money");

	public static final List<RequirementType> ALL = Lists.newArrayList(ITEM, MONEY);
}
