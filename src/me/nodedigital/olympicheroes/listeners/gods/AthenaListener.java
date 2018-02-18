package me.nodedigital.olympicheroes.listeners.gods;

import org.bukkit.event.Listener;

import me.nodedigital.olympicheroes.OlympicHeroes;

/**
 * Listener for events related to Athena's powers
 * There are currently no events required, so this class is a placeholder
 * @author Steven Green
 *
 */
public class AthenaListener implements Listener {

    private OlympicHeroes plugin;

    /**
     * Initializes the listener
     * @param plugin Reference to the main plugin class
     */
    public AthenaListener(OlympicHeroes plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

}
