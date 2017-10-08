package me.NodeDigital.OlympicHeroes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.NodeDigital.OlympicHeroes.block.ChangedBlock;
import me.NodeDigital.OlympicHeroes.command.OHCommand;
import me.NodeDigital.OlympicHeroes.item.OHItems;
import me.NodeDigital.OlympicHeroes.listeners.CraftListener;
import me.NodeDigital.OlympicHeroes.listeners.MultiBlockListener;
import me.NodeDigital.OlympicHeroes.listeners.PlayerListener;
import me.NodeDigital.OlympicHeroes.multiblock.MultiBlocks;
import me.NodeDigital.OlympicHeroes.tasks.EffectsTask;

public class OlympicHeroes extends JavaPlugin{

	public static Config mbConfig;
	public static List<List<ChangedBlock>> changedBlocksList = new ArrayList<List<ChangedBlock>>();
	
	public void onEnable() {
		Bukkit.getLogger().info("Olympic Heroes is startin up :D");
		Variables.setup();
		mbConfig = new Config("storage/mb.yml");
		MultiBlocks.load();
		
		new CraftListener(this);
		new MultiBlockListener(this);
		new PlayerListener(this);
		
		getCommand("oh").setExecutor(new OHCommand());
		
		OHItems.constructItems();
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new EffectsTask(), 0L, 10L);
	}
	
	public static void regenerateTerrain(OlympicHeroes plugin, List<ChangedBlock> blocks, long ticks) {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				for(ChangedBlock cb : blocks) {
					cb.location.getBlock().setType(cb.oldType);
					cb.location.getBlock().setData(cb.oldData);
				}
				changedBlocksList.remove(blocks);
			}
		}, ticks);
	}
	
}
