package io.github.OGKushper.aoimods.regions.region;

import java.util.Map;

public class RegionMap {
//object holding ALL data relating to regions
	
	private String[] names;
	private Map<Loc2D, Boolean>[] borders;
	
	public RegionMap() {
		
		
	}
	
	public boolean renameRegion(String oldName, String newName) {
		Integer region = getRegion(oldName);
		if(region == null) return false;
		return renameRegion(region, newName);
	}
	public boolean renameRegion(Integer region, String newName) {
		names[region] = newName;
		return true;
	}
	
	public String getRegionName(int region) {
		if(region >= names.length) return null;
		return names[region];
	}
	
	public int getRegion(int x, int z) {
		//TODO
		return null;
	}
	
	private Integer getRegion(String name) {
		for(int i = 0; i < names.length; i++) {
			if(name.equals(names[i])) return i;
		}
		return null;
	}
	class Loc2D {
		public int X;
		public int Z;
		
		public Loc2D(int x, int z) {
			X = x;
			Z = z;
		}
	}
}


