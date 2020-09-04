package io.github.OGKushper.aoimods.regions;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.github.OGKushper.aoimods.regions.region.RegionMap;

public class RegionManager {
	public static Integer originX;
	public static Integer originZ;
	public static AOIRegions plugin;
	public static Map<UUID, Integer> playerRegions = new HashMap<UUID, Integer>();
	
	public static RegionMap regionMap;

	public static String getPlayerRegionName(UUID uniqueId) {
		Integer region = playerRegions.get(uniqueId);
		if(region == null) return null;
		return regionMap.getRegionName(region);
	}
	
	public static void register(AOIRegions plugin) {
		RegionManager.plugin = plugin;
	}
}
