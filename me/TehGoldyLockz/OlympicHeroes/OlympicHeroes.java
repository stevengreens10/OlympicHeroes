package me.TehGoldyLockz.OlympicHeroes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.TehGoldyLockz.OlympicHeroes.command.OHCommand;
import me.TehGoldyLockz.OlympicHeroes.item.OHItems;
import me.TehGoldyLockz.OlympicHeroes.listeners.EnchantListener;
import me.TehGoldyLockz.OlympicHeroes.listeners.MultiBlockListener;
import me.TehGoldyLockz.OlympicHeroes.listeners.PlayerListener;
import me.TehGoldyLockz.OlympicHeroes.multiblock.MultiBlocks;
import me.TehGoldyLockz.OlympicHeroes.tasks.EffectsTask;

public class OlympicHeroes extends JavaPlugin{

	public static Config mbConfig;
	public static List<Player> cooldownList = new ArrayList<Player>();
	
	public void onEnable() {
		Bukkit.getLogger().info("Olympic Heroes is startin up :D");
		Variables.setup();
		mbConfig = new Config("storage/mb.yml");
		
		MultiBlocks.load();
		
		new EnchantListener(this);
		new MultiBlockListener(this);
		new PlayerListener(this);
		
		getCommand("oh").setExecutor(new OHCommand());
		
		OHItems.constructItems();
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new EffectsTask(), 0L, 10L);
	}
	
}
