package io.github.OGKushper.aoimods.regions;

import java.util.logging.Logger;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;

import org.spongepowered.api.text.channel.MessageChannel;


import com.google.inject.Inject;

import io.github.OGKushper.aoimods.regions.command.RegionCommandRegisterer;
import io.github.OGKushper.aoimods.regions.region.RegionMap;

@Plugin(id = "dummyplugin", name = "Example Plugin", version = "1.0", description = "Test plugin")

public class AOIRegions {

	RegionMap map;
	MonitorScheduler monitorScheduler;
	@Inject
    private Logger logger;

    @Listener
    public void onServerStarted(GameStartedServerEvent event) {
        monitorScheduler = new MonitorScheduler();
        RegionCommandRegisterer.Register(this);
        map = new RegionMap(); //TODO Needs to call to a function of a (now non existing) class that deserializes the region JSON to a filled RegionMap
    }
	
	@Listener
	public void OnServerJoin(ClientConnectionEvent.Join event) {
		Player player = event.getTargetEntity();
		monitorScheduler.Add(player, map, this, 5);
	}
	
	@Listener
	public void OnServerLeave(ClientConnectionEvent.Disconnect event) {
		Player player = event.getTargetEntity();
		monitorScheduler.Remove(player);
	}
	
	@Listener
	public void OnServerStopping(GameStoppingServerEvent event) {
		monitorScheduler.RemoveAll();
	}
	
	
}
