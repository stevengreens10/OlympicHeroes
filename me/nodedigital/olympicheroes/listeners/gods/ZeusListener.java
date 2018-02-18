package me.nodedigital.olympicheroes.listeners.gods;

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
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import me.nodedigital.olympicheroes.Cooldowns;
import me.nodedigital.olympicheroes.OlympicHeroes;
import me.nodedigital.olympicheroes.Variables;
import me.nodedigital.olympicheroes.player.OHPlayer;

public class ZeusListener implements Listener{
	OlympicHeroes plugin;
	
	public ZeusListener(OlympicHeroes plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		OHPlayer ohPlayer = new OHPlayer(player);
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR && e.getHand() == EquipmentSlot.HAND) {
			ItemStack item = e.getItem();
			
			if((item != null) && (item.getType() == Material.DIAMOND_SWORD || item.getType() == Material.GOLD_SWORD ||
			   item.getType() == Material.IRON_SWORD || item.getType() == Material.STONE_SWORD ||
			   item.getType() == Material.WOOD_SWORD) ) {
				
				if(ohPlayer.getLevel("Zeus") >= 5) {
					if(!Cooldowns.zeusLightningCooldown.contains(player)) {
						Block block = player.getTargetBlock(null, 100);
						Location l = block.getLocation();
						e.getPlayer().getWorld().strikeLightning(l);
						Cooldowns.zeusLightningCooldown.add(player);
						Cooldowns.removeCooldown(plugin, Cooldowns.zeusLightningCooldown, player, Variables.ZEUS_LIGHTNING_COOLDOWN);
					}else {
						e.getPlayer().sendMessage("That ability is on cooldown.");
					}
				}
			}
		}
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
	
}
