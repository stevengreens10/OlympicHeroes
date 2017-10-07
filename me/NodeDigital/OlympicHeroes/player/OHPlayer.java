package me.NodeDigital.OlympicHeroes.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.NodeDigital.OlympicHeroes.Config;
import me.NodeDigital.OlympicHeroes.Variables;

public class OHPlayer {
	
	private Config config;
	private Player player;
	
	public OHPlayer(Player player) {
		this.player = player;
		config = new Config("storage/" + player.getUniqueId().toString() + ".yml");
		config.getConfig().set("username", player.getName());
		config.save();
	}
	
	public int getXP(String god) {
		return config.getConfig().getInt("xp." + god);
	}
	
	public void setXP(int xp, String god) {
		int oldLevel = getLevel(god);
		config.getConfig().set("xp." + god, xp);
		config.save();

		int newLevel = getLevel(god);
		
		if(oldLevel < 5 && newLevel >= 5) {
			
			int numMax = 0;
			
			for(String godName : Variables.GODS) {
				if(getLevel(godName) >= 5) {
					numMax++;
				}
			}
			
			if(numMax == 1) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + player.getName() + " group add Devotee");
				player.sendMessage("You are now a devotee!");
			}else if(numMax == 3) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + player.getName() + " group add Priest");
				player.sendMessage("You are now a priest!");
			}
		}
	}
	
	public int getLevel(String god) {
		int xp = getXP(god);
		
		int level = 1;
		
		for(int cutoff : Variables.LEVEL_CUTOFFS) {
			if(xp > cutoff) {
				level++;
			}
		}
		
		return level;
	}
}
