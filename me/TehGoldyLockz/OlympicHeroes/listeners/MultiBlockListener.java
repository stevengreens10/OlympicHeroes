package me.TehGoldyLockz.OlympicHeroes.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.material.Stairs;

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
			if(isShrine(block, Material.STONE, (byte) 4, Material.SPRUCE_WOOD_STAIRS)) {
				e.getPlayer().sendMessage("You just made a shrine to Artemis!");
			}
		}else if(type == Material.GOLD_BLOCK) {
			if(isShrine(block, Material.QUARTZ_BLOCK, (byte) 0, Material.SANDSTONE_STAIRS)) {
				e.getPlayer().sendMessage("You just made a shrine to Apollo!");
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
	
	@SuppressWarnings("deprecation")
	public boolean isShrine(Block block, Material exterior, byte exteriorData, Material stair) {
		World w = block.getWorld();
		Location l = block.getLocation();
		if(w.getBlockAt(l.clone().add(0, 1, 0)).getType() == exterior && w.getBlockAt(l.clone().add(0, -1, 0)).getType() == exterior) {
			boolean isShrine = false;
			
			// FACING NORTH AND SOUTH
			if(w.getBlockAt(l.clone().add(1, 0, 0)).getType() == exterior && 
			   w.getBlockAt(l.clone().add(-1, 0, 0)).getType() == exterior &&
			   w.getBlockAt(l.clone().add(1, -1, 0)).getType() == exterior &&
			   w.getBlockAt(l.clone().add(-1, -1, 0)).getType() == exterior &&
			   w.getBlockAt(l.clone().add(1, 0, 0)).getData() == exteriorData && 
			   w.getBlockAt(l.clone().add(-1, 0, 0)).getData() == exteriorData &&
			   w.getBlockAt(l.clone().add(1, -1, 0)).getData() == exteriorData &&
			   w.getBlockAt(l.clone().add(-1, -1, 0)).getData() == exteriorData) {
				for(int z = -1; z <= 1; z+=2) {
					if(w.getBlockAt(l.clone().add(0, -2, z)).getType() == stair &&
					   w.getBlockAt(l.clone().add(1, -1, z)).getType() == stair &&
					   w.getBlockAt(l.clone().add(-1, -1, z)).getType() == stair) {
						if( ( (Stairs) w.getBlockAt(l.clone().add(0, -2, z)).getState().getData() ).isInverted() &&
							( (Stairs) w.getBlockAt(l.clone().add(1, -1, z)).getState().getData() ).getAscendingDirection() == BlockFace.EAST &&
							( (Stairs) w.getBlockAt(l.clone().add(-1, -1, z)).getState().getData() ).getAscendingDirection() == BlockFace.WEST){
							// z = -1: north ; z = 1: south
							if(z == -1 &&  ((Stairs) w.getBlockAt(l.clone().add(0, -2, z)).getState().getData() ).getAscendingDirection() == BlockFace.SOUTH) {
								isShrine = true;
							}else if(z == 1 &&  ((Stairs) w.getBlockAt(l.clone().add(0, -2, z)).getState().getData() ).getAscendingDirection() == BlockFace.NORTH){
								isShrine = true;
							}
						}
					}	
				}
			}
			
			// FACING EAST AND WEST
			if(w.getBlockAt(l.clone().add(0, 0, 1)).getType() == exterior && 
			   w.getBlockAt(l.clone().add(0, 0, -1)).getType() == exterior &&
			   w.getBlockAt(l.clone().add(0, -1, 1)).getType() == exterior &&
			   w.getBlockAt(l.clone().add(0, -1, -1)).getType() == exterior &&
			   w.getBlockAt(l.clone().add(0, 0, 1)).getData() == exteriorData && 
			   w.getBlockAt(l.clone().add(0, 0, -1)).getData() == exteriorData &&
			   w.getBlockAt(l.clone().add(0, -1, 1)).getData() == exteriorData &&
			   w.getBlockAt(l.clone().add(0, -1, -1)).getData() == exteriorData) {
				for(int x = -1; x <= 1; x+=2) {
					if(w.getBlockAt(l.clone().add(x, -2, 0)).getType() == stair &&
					   w.getBlockAt(l.clone().add(x, -1, 1)).getType() == stair &&
					   w.getBlockAt(l.clone().add(x, -1, -1)).getType() == stair) {
						if( ( (Stairs) w.getBlockAt(l.clone().add(x, -2, 0)).getState().getData() ).isInverted() &&
							( (Stairs) w.getBlockAt(l.clone().add(x, -1, -1)).getState().getData() ).getAscendingDirection() == BlockFace.NORTH &&
							( (Stairs) w.getBlockAt(l.clone().add(x, -1, 1)).getState().getData() ).getAscendingDirection() == BlockFace.SOUTH) {
							// z = -1: west ; z = 1: east
							if(x == -1 &&  ((Stairs) w.getBlockAt(l.clone().add(x, -2, 0)).getState().getData() ).getAscendingDirection() == BlockFace.EAST) {
								isShrine = true;
							}else if(x == 1 &&  ((Stairs) w.getBlockAt(l.clone().add(x, -2, 0)).getState().getData() ).getAscendingDirection() == BlockFace.WEST){
								isShrine = true;
							}
						}
					}
				}
				
			}
			
			return isShrine;
		}else {
			return false;
		}
	}
	
}
