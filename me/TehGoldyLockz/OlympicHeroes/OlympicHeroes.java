package me.TehGoldyLockz.OlympicHeroes;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.TehGoldyLockz.OlympicHeroes.command.OHCommand;
import me.TehGoldyLockz.OlympicHeroes.item.OHItems;

public class OlympicHeroes extends JavaPlugin{

	public void onEnable() {
		Bukkit.getLogger().info("Olympic Heroes is startin up :D");
		getCommand("oh").setExecutor(new OHCommand());
		
		OHItems.constructItems();
	}
	
}
