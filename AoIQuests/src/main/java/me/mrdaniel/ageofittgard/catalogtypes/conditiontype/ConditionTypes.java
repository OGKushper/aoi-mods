package me.mrdaniel.ageofittgard.catalogtypes.conditiontype;

import com.google.common.collect.Lists;

import java.util.List;

public final class ConditionTypes {

	public static final ConditionType ITEM = new ConditionType("Item", "item");
	public static final ConditionType MONEY = new ConditionType("Money", "money");

	public static final List<ConditionType> ALL = Lists.newArrayList(ITEM, MONEY);
}
