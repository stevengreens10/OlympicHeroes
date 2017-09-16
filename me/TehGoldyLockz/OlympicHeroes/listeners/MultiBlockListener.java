package me.TehGoldyLockz.OlympicHeroes.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.TehGoldyLockz.OlympicHeroes.OlympicHeroes;

public class MultiBlockListener implements Listener{
	
	public MultiBlockListener(OlympicHeroes plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Block block = e.getBlock();
		Material type = block.getType();
		
		if(type == Material.IRON_BLOCK) {
			if(isShrine(block, Material.STONE, Material.SPRUCE_WOOD_STAIRS)) {
				e.getPlayer().sendMessage("You just made a shrine to artemis!");
			}
		}else if(type == Material.GOLD_BLOCK) {
			if(isShrine(block, Material.QUARTZ_BLOCK, Material.SANDSTONE_STAIRS)) {
				e.getPlayer().sendMessage("You just made a shrine to apollo!");
			}
		}else if(type == Material.DIAMOND_BLOCK) {
			// hera
		}else if(type == Material.MAGMA) {
			// hephaestus
		}else if(type == Material.MELON_BLOCK) {
			// demeter
		}else if(type == Material.LAPIS_BLOCK) {
			// hermes
		}else if(type == Material.PRISMARINE) {
			// poseidon
		}
	}
	
	public boolean isShrine(Block block, Material exterior, Material stair) {
		World w = block.getWorld();
		Location l = block.getLocation();
		if(w.getBlockAt(l.clone().add(0, 1, 0)).getType() == exterior && w.getBlockAt(l.clone().add(0, -1, 0)).getType() == exterior) {
			boolean isShrine = false;
			
			// FACING NORTH AND SOUTH
			if(w.getBlockAt(l.clone().add(1, 0, 0)).getType() == exterior && 
			   w.getBlockAt(l.clone().add(-1, 0, 0)).getType() == exterior &&
			   w.getBlockAt(l.clone().add(1, -1, 0)).getType() == exterior &&
			   w.getBlockAt(l.clone().add(-1, -1, 0)).getType() == exterior) {
				for(int z = -1; z <= 1; z+=2) {
					if(w.getBlockAt(l.clone().add(0, -2, z).clone()).getType() == stair &&
					   w.getBlockAt(l.clone().add(1, -1, z).clone()).getType() == stair &&
					   w.getBlockAt(l.clone().add(-1, -1, z).clone()).getType() == stair) {
						isShrine = true;
					}
				}
			}
			
			// FACING EAST AND WEST
			if(w.getBlockAt(l.clone().add(0, 0, 1)).getType() == exterior && 
			   w.getBlockAt(l.clone().add(0, 0, -1)).getType() == exterior &&
			   w.getBlockAt(l.clone().add(0, -1, 1)).getType() == exterior &&
			   w.getBlockAt(l.clone().add(0, -1, -1)).getType() == exterior) {
				for(int x = -1; x <= 1; x+=2) {
					if(w.getBlockAt(l.clone().add(x, -2, 0)).getType() == stair &&
					   w.getBlockAt(l.clone().add(x, -1, 1)).getType() == stair &&
					   w.getBlockAt(l.clone().add(x, -1, -1)).getType() == stair) {
						isShrine = true;
					}
				}
				
			}
			
			return isShrine;
		}else {
			return false;
		}
	}
	
}
