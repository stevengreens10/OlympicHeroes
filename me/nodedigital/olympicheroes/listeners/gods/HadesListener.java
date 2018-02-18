package me.nodedigital.olympicheroes.listeners.gods;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import me.nodedigital.olympicheroes.Cooldowns;
import me.nodedigital.olympicheroes.OlympicHeroes;
import me.nodedigital.olympicheroes.Variables;
import me.nodedigital.olympicheroes.player.OHPlayer;

public class HadesListener implements Listener{

	private OlympicHeroes plugin;
	
	public HadesListener(OlympicHeroes plugin) {
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
				
				if(ohPlayer.getLevel("Hades") >= 5) {
					
					if(!Cooldowns.hadesSpawnCooldown.contains(player)) {
						Cooldowns.hadesSpawnCooldown.add(player);
						Cooldowns.removeCooldown(plugin, Cooldowns.hadesSpawnCooldown, player, Variables.HADES_SPAWN_COOLDOWN);
						Location loc = player.getLocation();
						int x = loc.getBlockX();
						int y = loc.getBlockY();
						int z = loc.getBlockZ();
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), 
								String.format("mm mobs spawn HadesSkeles 1 %s,%d,%d,%d",
								player.getWorld().getName(), x, y, z));
					}else {
						e.getPlayer().sendMessage("That ability is on cooldown.");
					}
				}
				
			}
		}
	}
	
}
