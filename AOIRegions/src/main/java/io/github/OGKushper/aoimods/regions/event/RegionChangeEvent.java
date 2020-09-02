package io.github.OGKushper.aoimods.regions.event;

import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.impl.AbstractEvent;

public class RegionChangeEvent extends AbstractEvent{

	private final Cause cause;
	private final int origin;
	private final int destination;
	
	public RegionChangeEvent(Cause cause, int origin, int destination) {
		this.cause = cause;
		this.origin = origin;
		this.destination = destination;
	}
	
	@Override
	public Cause getCause() {
		return cause;
	}
	
	public int getOrigin() {
		return origin;
	}
	
	public int getDestination() {
		return destination;
	}

}
