package me.mrdaniel.ageofittgard.catalogtypes.eventtype;

import com.google.common.collect.Lists;

import java.util.List;

public final class EventTypes {

	public static final EventType OBJECTIVE = new EventType("Objective", "objective");
	public static final EventType GIFT = new EventType("Gift", "gift");

	public static final List<EventType> ALL = Lists.newArrayList(OBJECTIVE, GIFT);
}
