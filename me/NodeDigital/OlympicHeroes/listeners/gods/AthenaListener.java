package me.NodeDigital.OlympicHeroes.listeners.gods;

import org.bukkit.event.Listener;

import me.NodeDigital.OlympicHeroes.OlympicHeroes;

public class AthenaListener implements Listener{

	private OlympicHeroes plugin;
	
	public AthenaListener(OlympicHeroes plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	// Placeholder for any future events
	
}
