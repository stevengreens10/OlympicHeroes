package me.nodedigital.olympicheroes.listeners.gods;

import org.bukkit.event.Listener;

import me.nodedigital.olympicheroes.OlympicHeroes;

public class AthenaListener implements Listener{

	private OlympicHeroes plugin;
	
	public AthenaListener(OlympicHeroes plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	// Placeholder for any future events
	
}
