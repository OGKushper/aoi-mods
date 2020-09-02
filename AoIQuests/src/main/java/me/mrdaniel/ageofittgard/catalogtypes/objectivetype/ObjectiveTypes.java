package me.mrdaniel.ageofittgard.catalogtypes.objectivetype;

import com.google.common.collect.Lists;
import me.mrdaniel.ageofittgard.quest.quest.objective.*;

import java.util.List;

public final class ObjectiveTypes {

	public static final ObjectiveType NPC_TALK = new ObjectiveType("Talk to an NPC", "npc_talk", ObjectiveNPCTalk.class);
//	public static final ObjectiveType NPC_ITEM = new ObjectiveType("Bring items to an NPC", "npc_item", HoconObjectiveNPCItem.class);
//	public static final ObjectiveType NPC_ESCORT = new ObjectiveType("Escort an NPC", "npc_escort", HoconObjectiveNPCEscort.class);
//	public static final ObjectiveType NPC_FOLLOW = new ObjectiveType("Follow an NPC", "npc_follow", HoconObjectiveNPCFollow.class);

	public static final ObjectiveType LOCATION = new ObjectiveType("Go to a Location", "location", ObjectiveLocation.class);
	public static final ObjectiveType KILL = new ObjectiveType("Kill enemies", "kill", ObjectiveKill.class);
	public static final ObjectiveType COLLECT = new ObjectiveType("Collect items", "collect", ObjectiveCollect.class);
	public static final ObjectiveType QUEST = new ObjectiveType("Complete an other quest", "quest", ObjectiveQuest.class);
//	public static final ObjectiveType LEVEL = new ObjectiveType("Level up", "level", HoconObjectiveLevel.class);
//	public static final ObjectiveType MONEY = new ObjectiveType("Gather Money", "money", HoconObjectiveMoney.class);

	public static final List<ObjectiveType> ALL = Lists.newArrayList(NPC_TALK, LOCATION, KILL, COLLECT, QUEST);
}
