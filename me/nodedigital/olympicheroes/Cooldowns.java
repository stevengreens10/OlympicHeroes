package me.nodedigital.olympicheroes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Stores all of the cooldowns for powers in the plugin
 * @author Steven Green
 *
 */
public class Cooldowns {
    /** A list of players who are unable to pray due to the pray cooldown */
	public static List<Player> prayCooldown = new ArrayList<Player>();
	/** A list of players who are not able to use Zeus's lightning ability */
	public static List<Player> zeusLightningCooldown = new ArrayList<Player>();
	/** A list of players who are not able to use Ares's rage ability */
	public static List<Player> aresRageCooldown = new ArrayList<Player>();
	/** A list of players who are not able to use Aphrodite's resistance ability */
	public static List<Player> aphroditeResCooldown = new ArrayList<Player>();
	/** A list of players who are not able to use Hera's bestow ability */
	public static List<Player> heraBestowCooldown = new ArrayList<Player>();
	/** A list of players who are not able to use Dionysus's poison ability */
	public static List<Player> dionysusPoisonCooldown = new ArrayList<Player>();
	/** A list of players who are not able to use Poseidon's surge ability */
	public static List<Player> poseidonSurgeCooldown = new ArrayList<Player>();
	/** A list of players who are not able to use Hade's spawn minions ability */
	public static List<Player> hadesSpawnCooldown = new ArrayList<Player>();
	/** A list of players who are not able to use Hermes's glide ability */
	public static List<Player> hermesGlideCooldown = new ArrayList<Player>();
	/** A list of players who are not able to use Demeter's bonemeal ability */
	public static List<Player> demeterBonemealCooldown = new ArrayList<Player>();
	/** A map of players to a list of players whom they can not gain xp for killing for a duration */
	public static Map<Player, List<Player>> killCooldownMap = new HashMap<Player, List<Player>>();
	
	/**
	 * Removes a player from a cooldown after a specified number of ticks
	 * @param plugin Reference to the main plugin class
	 * @param cooldown The cooldown to remove the player from
	 * @param p The player to remove
	 * @param ticks The number of ticks in which to remove the player from the cooldown
	 */
	public static void removeCooldown(OlympicHeroes plugin, List<Player> cooldown, Player p, long ticks) {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				cooldown.remove(p.getPlayer());
			}
		}, ticks);
	}
	
	/**
     * Removes a player from a player's kill cooldown after a specified number of ticks
     * @param plugin Reference to the main plugin class
     * @param killer The killer
     * @param player The player to remove from the killer's cooldown
     * @param ticks The number of ticks in which to remove the player from the kill cooldown
     */
	public static void removePlayerFromKillCooldown(OlympicHeroes plugin, Player killer, Player player, long ticks) {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				killCooldownMap.get(killer).remove(player);
			}
		}, ticks);
	}

}
