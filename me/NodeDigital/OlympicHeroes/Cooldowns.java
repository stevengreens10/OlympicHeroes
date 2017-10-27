package me.NodeDigital.OlympicHeroes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Cooldowns {
	public static List<Player> prayCooldown = new ArrayList<Player>();
	public static List<Player> zeusLightningCooldown = new ArrayList<Player>();
	public static List<Player> aresRageCooldown = new ArrayList<Player>();
	public static List<Player> aphroditeResCooldown = new ArrayList<Player>();
	public static List<Player> heraBestowCooldown = new ArrayList<Player>();
	public static List<Player> dionysusPoisonCooldown = new ArrayList<Player>();
	public static List<Player> poseidonSurgeCooldown = new ArrayList<Player>();
	public static List<Player> hadesSpawnCooldown = new ArrayList<Player>();
	public static List<Player> hermesGlideCooldown = new ArrayList<Player>();
	public static List<Player> demeterBonemealCooldown = new ArrayList<Player>();
	public static Map<Player, List<Player>> killCooldownMap = new HashMap<Player, List<Player>>();
	
	public static void removeCooldown(OlympicHeroes plugin, List<Player> cooldown, Player p, long ticks) {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				cooldown.remove(p.getPlayer());
			}
		}, ticks);
	}
	
	public static void removePlayerFromKillCooldown(OlympicHeroes plugin, Player killer, Player player, long ticks) {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				killCooldownMap.get(killer).remove(player);
			}
		}, ticks);
	}

}
