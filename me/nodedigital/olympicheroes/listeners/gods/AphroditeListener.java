package me.nodedigital.olympicheroes.listeners.gods;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.nodedigital.olympicheroes.Cooldowns;
import me.nodedigital.olympicheroes.OlympicHeroes;
import me.nodedigital.olympicheroes.Variables;
import me.nodedigital.olympicheroes.player.OHPlayer;

/**
 * Listener for events related to Aphrodite's powers
 * @author Steven Green
 *
 */
public class AphroditeListener implements Listener {

	private OlympicHeroes plugin;
	
	/**
	 * Initializes the listener
	 * @param plugin Reference to the main plugin class
	 */
	public AphroditeListener(OlympicHeroes plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	/**
	 * Event fired when an entity is damaged.
	 * If a player is damaged and has enough devotion to Aphrodite,
	 * they can get damage resistance if they haven't gotten it already recently
	 * @param e The entity damage event
	 */
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			OHPlayer ohPlayer = new OHPlayer(player);

			if(e.getCause() == DamageCause.ENTITY_ATTACK) {
				if(ohPlayer.getLevel("Aphrodite") >= 5) {
					if(!Cooldowns.aphroditeResCooldown.contains(player.getPlayer())) {
						Cooldowns.aphroditeResCooldown.add(player.getPlayer());
						player.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 1));
						
						Cooldowns.removeCooldown(plugin, Cooldowns.aphroditeResCooldown, player.getPlayer(), Variables.APHRODITE_RES_COOLDOWN);
					}
				}
			}
			
			if(e.getCause() == DamageCause.LIGHTNING) {
				e.setDamage(e.getFinalDamage() + 1);
			}
		}
	}
	
}
