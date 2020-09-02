package io.github.OGKushper.aoimods.regions.region;

import org.spongepowered.api.entity.living.player.Player;

//Class that defines all the code that is run each tick to monitor whether a player moves into a new region. Movement into a new region will trigger a RegionChangeEvent
public class PlayerRegionMonitor implements Runnable{

	RegionMap map;
	Player player;
	int currentRegion;
	public PlayerRegionMonitor(Player p, RegionMap m) {
		map = m;
		player = p;
		
	}
	
	@Override
	public void run() {
		
	}

}
