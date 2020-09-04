package io.github.OGKushper.aoimods.regions;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import java.util.Iterator;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.scheduler.Task;

import io.github.OGKushper.aoimods.regions.region.PlayerRegionMonitor;
import io.github.OGKushper.aoimods.regions.region.RegionMap;

//Manager of all region monitoring tasks employed by the plugin
public class MonitorScheduler {
	Map<UUID,Task> monitors;
	
	public MonitorScheduler() {
		monitors = new HashMap<UUID,Task>();
	}
	
	public void Add(Player p, RegionMap m, Object plugin, int interval) {
		PlayerRegionMonitor monitor = new PlayerRegionMonitor(p, m);
		Sponge.getEventManager().registerListeners(this, monitor);
		monitors.put(p.getUniqueId(),Task.builder().intervalTicks(interval).execute(monitor).submit(plugin));
		
	}
	
	public void Remove(Player p) {
		UUID id = p.getUniqueId();
		monitors.get(id).cancel();
		monitors.remove(id);
	}
	
	public void RemoveAll() {
		Iterator<Entry<UUID, Task>> i = monitors.entrySet().iterator();
		while(i.hasNext()) {
			Map.Entry<UUID,Task> pair = (Map.Entry<UUID,Task>)i.next();
			((Task) pair.getValue()).cancel();
		}
		monitors.clear();
	}
	
	public int getCurrentRegion(Player p) {
		monitors.get(p.getUniqueId())
	}
}
