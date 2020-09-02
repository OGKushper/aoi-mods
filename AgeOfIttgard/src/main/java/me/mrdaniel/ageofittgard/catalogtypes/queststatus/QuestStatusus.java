package me.mrdaniel.ageofittgard.catalogtypes.queststatus;

import com.google.common.collect.Lists;
import org.spongepowered.api.item.ItemTypes;

import java.util.List;

public final class QuestStatusus {

	public static final QuestStatus COMPLETED = new QuestStatus("Completed", "completed", ItemTypes.ENCHANTED_BOOK);
	public static final QuestStatus ACTIVE = new QuestStatus("Active", "active", ItemTypes.WRITABLE_BOOK);
	public static final QuestStatus AVAILABLE = new QuestStatus("Available", "available", ItemTypes.BOOK);

	public static final List<QuestStatus> ALL = Lists.newArrayList(COMPLETED, ACTIVE, AVAILABLE);
}
