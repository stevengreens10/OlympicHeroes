package me.TehGoldyLockz.OlympicHeroes.player;

import org.bukkit.entity.Player;

import me.TehGoldyLockz.OlympicHeroes.Config;

public class OHPlayer {
	
	private static Config config;
	
	public OHPlayer(Player player) {
		config = new Config("storage/" + player.getUniqueId().toString() + ".yml");
	}
	
	public int getXP(String god) {
		return config.getConfig().getInt("xp." + god);
	}
	
	public void setXP(int xp, String god) {
		config.getConfig().set("xp." + god, xp);
		config.save();
	}
}
