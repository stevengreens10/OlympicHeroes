package me.TehGoldyLockz.OlympicHeroes.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.TehGoldyLockz.OlympicHeroes.Cooldowns;
import me.TehGoldyLockz.OlympicHeroes.OlympicHeroes;
import me.TehGoldyLockz.OlympicHeroes.player.OHPlayer;

public class PlayerListener implements Listener{

	OlympicHeroes plugin;
	
	public PlayerListener(OlympicHeroes plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			OHPlayer ohPlayer = new OHPlayer(player);
			
			if(e.getCause() == DamageCause.FALL) {
				if(ohPlayer.getXP("Zeus") > 0) {
					e.setDamage(e.getFinalDamage() / 1.5);
					player.sendMessage("Your fall damage has been reduced thanks to Zeus");
				}
			}
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_AIR) {
			ItemStack item = e.getItem();
			
			if(item.getType() == Material.DIAMOND_SWORD || item.getType() == Material.GOLD_SWORD ||
			   item.getType() == Material.IRON_SWORD || item.getType() == Material.STONE_SWORD ||
			   item.getType() == Material.WOOD_SWORD) {
				OHPlayer ohPlayer = new OHPlayer(e.getPlayer());
				if(ohPlayer.getLevel("Zeus") >= 5) {
					Block block = e.getPlayer().getTargetBlock(null, 100);
					Location l = block.getLocation();
					e.getPlayer().getWorld().strikeLightning(l);
					
					Cooldowns.lightningCooldown.add(e.getPlayer());
					OlympicHeroes.removeCooldown(plugin, Cooldowns.lightningCooldown, e.getPlayer(), 6000L);
				}
			}
		}
	}
	
}
