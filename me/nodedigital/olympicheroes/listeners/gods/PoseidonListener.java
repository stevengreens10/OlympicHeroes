package me.nodedigital.olympicheroes.listeners.gods;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import me.nodedigital.olympicheroes.Cooldowns;
import me.nodedigital.olympicheroes.OlympicHeroes;
import me.nodedigital.olympicheroes.Variables;
import me.nodedigital.olympicheroes.gods.GodData;
import me.nodedigital.olympicheroes.item.OHItems;
import me.nodedigital.olympicheroes.player.OHPlayer;

public class PoseidonListener implements Listener{

	private OlympicHeroes plugin;
	
	public PoseidonListener(OlympicHeroes plugin) {
		this.plugin = plugin;
		
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		OHPlayer ohPlayer = new OHPlayer(player);
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR && e.getHand() == EquipmentSlot.HAND) {
			ItemStack item = e.getItem();
			
			if((item != null) && (item.getType() == Material.DIAMOND_SWORD || item.getType() == Material.GOLD_SWORD ||
			   item.getType() == Material.IRON_SWORD || item.getType() == Material.STONE_SWORD ||
			   item.getType() == Material.WOOD_SWORD) ) {
				
				if(ohPlayer.getLevel("Poseidon") >= 5 ) {
					
					if(!Cooldowns.poseidonSurgeCooldown.contains(player)) {
						int radius = 4;
						int playerX = player.getLocation().getBlockX();
						int playerY = player.getLocation().getBlockY();
						int playerZ = player.getLocation().getBlockZ();
						
						Cooldowns.poseidonSurgeCooldown.add(player);
						Cooldowns.removeCooldown(plugin, Cooldowns.poseidonSurgeCooldown, player, Variables.POSEIDON_SURGE_COOLDOWN);
						
						List<Block> blocksToReplace = new ArrayList<Block>();
							
						for(int x = playerX - radius; x <= playerX + radius; x++) {
							for(int z = playerZ - radius; z <= playerZ + radius; z++) {
								Block block = player.getLocation().getWorld().getBlockAt(x, playerY, z);
								if(block.getType() == Material.AIR || block.getType() == Material.DOUBLE_PLANT ||
								   block.getType() == Material.LONG_GRASS || block.getType() == Material.YELLOW_FLOWER ||
								   block.getType() == Material.RED_ROSE) {
									blocksToReplace.add(block);
								}
							}
						}
						for(Block b : blocksToReplace) {
							b.setType(Material.WATER);
							b.setData((byte) 8);
						}
					}else {
						e.getPlayer().sendMessage("That ability is on cooldown.");
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		OHPlayer ohPlayer = new OHPlayer(player);
		
		// CHECK IF POSEIDON IS IN WATER

		if(ohPlayer.getXP("Poseidon")> 0) {
			if(player.getLocation().getBlock().getType() == Material.WATER 
				|| player.getLocation().getBlock().getType() == Material.STATIONARY_WATER) {
				if(!OHItems.isItemSimilarTo(player.getInventory().getBoots(), OHItems.POSEIDON_BOOTS, false)) {
					GodData.poseidonBootsMap.put(player, player.getInventory().getBoots());
					player.getInventory().setBoots(OHItems.POSEIDON_BOOTS);
					player.getInventory().getBoots().addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, Math.min(ohPlayer.getLevel("Poseidon")+1, 3));
				}
			}else {
				if(OHItems.isItemSimilarTo(player.getInventory().getBoots(), OHItems.POSEIDON_BOOTS, false)) {
					player.getInventory().setBoots(GodData.poseidonBootsMap.get(player));
					GodData.poseidonBootsMap.remove(player);
				}
			}
		}
	}
	
}
