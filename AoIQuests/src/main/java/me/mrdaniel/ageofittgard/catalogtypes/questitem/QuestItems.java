package me.mrdaniel.ageofittgard.catalogtypes.questitem;

import com.google.common.collect.Lists;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.List;

public final class QuestItems {

	public static final QuestItem BILLY_TOY = new QuestItem("Billy's Toy", "billy_toy", ItemTypes.TOTEM_OF_UNDYING, Text.of(TextColors.GOLD, "Billy's Toy"), Lists.newArrayList(Text.of(TextColors.GRAY, "Billy's favorite childhood toy.")));
	public static final QuestItem BILLY_TOY_PART = new QuestItem("Part of Billy's Toy", "billy_toy_part", ItemTypes.GOLD_NUGGET, Text.of(TextColors.GOLD, "Part of Billy's Toy"), Lists.newArrayList(Text.of(TextColors.GRAY, "A part of Billy's favorite childhood toy.")));

	public static final List<QuestItem> ALL = Lists.newArrayList(BILLY_TOY, BILLY_TOY_PART);
}
