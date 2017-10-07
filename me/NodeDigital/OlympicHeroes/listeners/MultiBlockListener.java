package me.NodeDigital.OlympicHeroes.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.material.Stairs;

import me.NodeDigital.OlympicHeroes.Cooldowns;
import me.NodeDigital.OlympicHeroes.OlympicHeroes;
import me.NodeDigital.OlympicHeroes.Variables;
import me.NodeDigital.OlympicHeroes.multiblock.MultiBlock;
import me.NodeDigital.OlympicHeroes.multiblock.MultiBlocks;
import me.NodeDigital.OlympicHeroes.player.OHPlayer;

public class MultiBlockListener implements Listener{
	
	OlympicHeroes plugin;
	
	public MultiBlockListener(OlympicHeroes plugin) {
		this.plugin = plugin;
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
			if(isShrine(block, Material.STONE, (byte) 6, Material.SMOOTH_STAIRS, "Hephaestus")) {
				e.getPlayer().sendMessage("You just made a shrine to Hephaestus!");
			}
		}else if(type == Material.MELON_BLOCK) {
			if(isShrine(block, Material.HAY_BLOCK, (byte) -1, Material.BIRCH_WOOD_STAIRS, "Demeter")) {
				e.getPlayer().sendMessage("You just made a shrine to Demeter!");
			}
		}else if(type == Material.LAPIS_BLOCK) {
			if(isShrine(block, Material.CONCRETE, (byte) 3, Material.JUNGLE_WOOD_STAIRS, "Hermes")) {
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
		
		MultiBlocks.save();
	}
	
	@EventHandler
	public void onPray(PlayerInteractEvent e) {
		Block block = e.getClickedBlock();
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getHand() == EquipmentSlot.HAND) {
			if(MultiBlocks.isBlockInMultiBlock(block)) {
				MultiBlock mb = MultiBlocks.getMultiBlock(block);
				
				if(block.equals(mb.triggerBlock)) {
					boolean prayed = false;
					int xpInc = 0;
					if(e.getItem() != null) {
						if(e.getItem().getType() == Material.IRON_INGOT) {
							prayed = true;
							xpInc = Variables.IRON_XP;
						}else if(e.getItem().getType() == Material.GOLD_INGOT) {
							prayed = true;
							xpInc = Variables.GOLD_XP;
						}else if(e.getItem().getType() == Material.DIAMOND) {
							prayed = true;
							xpInc = Variables.DIAMOND_XP;
						}else if(e.getItem().getType() == Material.EMERALD) {
							prayed = true;
							xpInc = Variables.EMERALD_XP;
						}else if(e.getItem().getType() == Material.COOKIE) {
							prayed = true;
							xpInc = 1000;
						}
						
						if(prayed) {
							if(!Cooldowns.prayCooldown.contains(e.getPlayer())) {
								
								OHPlayer player = new OHPlayer(e.getPlayer());
								boolean canPray = true;
								
								for(String god : Variables.OPPOSING_GODS.get(mb.god)) {
									if(player.getXP(god) > 0)
										canPray = false;
								}
								
								if(canPray) {
									
									boolean firstPray = true;
									for(String god : Variables.GODS) {
										if(player.getXP(god) > 0) {
											firstPray = false;
											break;
										}
									}
									
									if(firstPray) {
										Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + e.getPlayer().getName() + " group add Follower");
										e.getPlayer().sendMessage("You are now a follower!");
									}
									
									e.getPlayer().sendMessage("You just prayed to " + mb.god + "!");
									e.getPlayer().sendMessage("You gained " + xpInc + " xp for " + mb.god + ".");
									int levelBefore = player.getLevel(mb.god);
									player.setXP(player.getXP(mb.god)+xpInc, mb.god);
									e.getPlayer().sendMessage("You now have " + player.getXP(mb.god) + " xp!");
									
									if(levelBefore != player.getLevel(mb.god)) {
										e.getPlayer().sendMessage("Your level for " + mb.god + " has just increased to " + player.getLevel(mb.god));
									}
									
									e.getItem().setAmount(e.getItem().getAmount()-1);
									
									Cooldowns.prayCooldown.add(e.getPlayer());
									OlympicHeroes.removeCooldown(plugin, Cooldowns.prayCooldown, e.getPlayer(), Variables.PRAYER_COOLDOWN);
								
								}else {
									e.getPlayer().sendMessage("You can not pray to this god!");
								}
							}else {
								e.getPlayer().sendMessage("You must wait to pray again!");
							}
						}
					}
				}
				
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreak(BlockBreakEvent e) {
		Block b = e.getBlock();
		if(!e.isCancelled() && MultiBlocks.isBlockInMultiBlock(b)) {
			MultiBlock mb = MultiBlocks.getMultiBlock(b);
			MultiBlocks.removeMultiBlock(mb);
			MultiBlocks.save();
			Bukkit.broadcastMessage(e.getPlayer().getName() + " just broke a shrine to " + mb.god + "!");
			
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onExplode(EntityExplodeEvent e) {
		for(Block b : e.blockList()) {
			if(!e.isCancelled() && MultiBlocks.isBlockInMultiBlock(b)) {
				MultiBlock mb = MultiBlocks.getMultiBlock(b);
				MultiBlocks.removeMultiBlock(mb);
				MultiBlocks.save();
				Bukkit.broadcastMessage("A shrine to " + mb.god + " has been blown up!");
			}
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
