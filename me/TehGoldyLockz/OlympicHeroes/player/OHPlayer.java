package me.TehGoldyLockz.OlympicHeroes.player;

import org.bukkit.entity.Player;

import me.TehGoldyLockz.OlympicHeroes.Config;
import me.TehGoldyLockz.OlympicHeroes.Variables;

public class OHPlayer {
	
	private static Config config;
	
	public OHPlayer(Player player) {
		config = new Config("storage/" + player.getUniqueId().toString() + ".yml");
		config.getConfig().set("username", player.getName());
		config.save();
	}
	
	public int getXP(String god) {
		return config.getConfig().getInt("xp." + god);
	}
	
	public void setXP(int xp, String god) {
		config.getConfig().set("xp." + god, xp);
		config.save();
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
