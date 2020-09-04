package io.github.OGKushper.aoimods.regions.region;

import java.util.function.Consumer;

import org.spongepowered.api.scheduler.Task;

public class RegionGenerator implements Consumer<Task>{

	private int originX;
	private int originZ;
	
	public RegionGenerator(int x, int z) {
		originX = x;
		originZ = z;
	}
	@Override
	public void accept(Task t) {
		// TODO
		
	}
	
}
