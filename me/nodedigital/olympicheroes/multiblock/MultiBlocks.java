package me.nodedigital.olympicheroes.multiblock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;

import me.nodedigital.olympicheroes.OlympicHeroes;

public class MultiBlocks {
	public static List<MultiBlock> multiBlocks = new ArrayList<MultiBlock>();
	
	public static void load() {
		FileConfiguration config = OlympicHeroes.mbConfig.getConfig();
		
		if(config.getConfigurationSection("multiblocks") != null && config.getConfigurationSection("multiblocks").getKeys(false) != null) {
			for(int i = 0; i < config.getConfigurationSection("multiblocks").getKeys(false).size(); i++) {
				if(config.getConfigurationSection("multiblocks." + i) != null) {
					int x = config.getInt("multiblocks." +  i + ".trigger.x");
					int y = config.getInt("multiblocks." +  i + ".trigger.y");
					int z = config.getInt("multiblocks." +  i + ".trigger.z");
					String world = config.getString("multiblocks." +  i + ".trigger.world");
					String direction = config.getString("multiblocks." + i + ".facing");
					String god = config.getString("multiblocks." + i + ".god");
					
					Location l =  new Location(Bukkit.getWorld(world),x,y,z);
					
					Block trigger = l.getBlock();
					List<Block> blocks = new ArrayList<Block>();
					blocks.add(trigger);
					
					if(direction.equalsIgnoreCase("NORTH")) {
						blocks.add(l.clone().add(0, 1, 0).getBlock());
						blocks.add(l.clone().add(0, -1, 0).getBlock());
						blocks.add(l.clone().add(1, 0, 0).getBlock());
						blocks.add(l.clone().add(-1, 0, 0).getBlock());
						blocks.add(l.clone().add(1, -1, 0).getBlock());
						blocks.add(l.clone().add(-1, -1, 0).getBlock());
						blocks.add(l.clone().add(0, -2, -1).getBlock());
						blocks.add(l.clone().add(1, -1, -1).getBlock());
						blocks.add(l.clone().add(-1, -1, -1).getBlock());
					} else if(direction.equalsIgnoreCase("EAST")) {
						blocks.add(l.clone().add(0, 1, 0).getBlock());
						blocks.add(l.clone().add(0, -1, 0).getBlock());
						blocks.add(l.clone().add(0, 0, 1).getBlock());
						blocks.add(l.clone().add(0, 0, -1).getBlock());
						blocks.add(l.clone().add(0, -1, 1).getBlock());
						blocks.add(l.clone().add(0, -1, -1).getBlock());
						blocks.add(l.clone().add(1, -2, 0).getBlock());
						blocks.add(l.clone().add(1, -1, -1).getBlock());
						blocks.add(l.clone().add(1, -1, 1).getBlock());
					} else if(direction.equalsIgnoreCase("SOUTH")) {
						blocks.add(l.clone().add(0, 1, 0).getBlock());
						blocks.add(l.clone().add(0, -1, 0).getBlock());
						blocks.add(l.clone().add(1, 0, 0).getBlock());
						blocks.add(l.clone().add(-1, 0, 0).getBlock());
						blocks.add(l.clone().add(1, -1, 0).getBlock());
						blocks.add(l.clone().add(-1, -1, 0).getBlock());
						blocks.add(l.clone().add(0, -2, 1).getBlock());
						blocks.add(l.clone().add(1, -1, 1).getBlock());
						blocks.add(l.clone().add(-1, -1, 1).getBlock());
					} else if(direction.equalsIgnoreCase("WEST")) {
						blocks.add(l.clone().add(0, 1, 0).getBlock());
						blocks.add(l.clone().add(0, -1, 0).getBlock());
						blocks.add(l.clone().add(0, 0, 1).getBlock());
						blocks.add(l.clone().add(0, 0, -1).getBlock());
						blocks.add(l.clone().add(0, -1, 1).getBlock());
						blocks.add(l.clone().add(0, -1, -1).getBlock());
						blocks.add(l.clone().add(-1, -2, 0).getBlock());
						blocks.add(l.clone().add(-1, -1, -1).getBlock());
						blocks.add(l.clone().add(-1, -1, 1).getBlock());
					}
					
					MultiBlock mb = new MultiBlock(trigger, blocks, god, BlockFace.valueOf(direction), i);
					multiBlocks.add(mb);
				}
			}
		}
	}
	
	public static void addMultiBlock(Block triggerBlock, List<Block> blocks, String god, BlockFace facing) {
		multiBlocks.add(new MultiBlock(triggerBlock, blocks, god, facing, generateMultiBlockID()));
	}
	
	@SuppressWarnings("deprecation")
	public static boolean isBlockInMultiBlock(Block b) {
		for(MultiBlock multiBlock : multiBlocks) {
			for(Block block : multiBlock.blocks) {
				if(block.getLocation().equals(b.getLocation()) &&
				   block.getType() == b.getType() &&
				   block.getData() == b.getData()) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public static MultiBlock getMultiBlock(Block b){
		for(MultiBlock multiBlock : multiBlocks) {
			for(Block block : multiBlock.blocks) {
				if(block.getLocation().equals(b.getLocation()) &&
				   block.getType() == b.getType() &&
				   block.getData() == b.getData()) {
					return multiBlock;
				}
			}
		}
		
		return null;
	}
	
	public static void removeMultiBlock(MultiBlock multiBlock) {
		FileConfiguration config = OlympicHeroes.mbConfig.getConfig();
		config.set("multiblocks." + multiBlock.id, null);
		OlympicHeroes.mbConfig.save();
		multiBlocks.remove(multiBlock);
	}
	
	public static void save() {
		FileConfiguration config = OlympicHeroes.mbConfig.getConfig();
		for(MultiBlock multiBlock : multiBlocks) {
			OlympicHeroes.mbConfig.setBlock("multiblocks." + multiBlock.id + ".trigger", multiBlock.triggerBlock);
			config.set("multiblocks." + multiBlock.id + ".facing", multiBlock.facing.name());
			config.set("multiblocks." + multiBlock.id + ".god", multiBlock.god);
			OlympicHeroes.mbConfig.save();
		}
		
	}
	
	private static int generateMultiBlockID() {
		FileConfiguration config = OlympicHeroes.mbConfig.getConfig();
		int id = 0;
		if(config.getConfigurationSection("multiblocks") != null && config.getConfigurationSection("multiblocks").getKeys(false) != null) {
			String [] idStr = config.getConfigurationSection("multiblocks").getKeys(false).toArray(new String[0]);
			boolean foundID = false;
			int[] ids = new int[idStr.length];
			for(int i = 0; i < idStr.length; i++) {
				String s = idStr[i];
				ids[i] = Integer.parseInt(s);
			}
			
			Arrays.sort(ids);
			for(int i = 0; i < ids.length; i++) {
				int id2 = ids[i];
				if(id2 != i) {
					id = i;
					foundID = true;
					break;
				}
			}
			if(!foundID) {
				id = ids.length;
			}
		} else {
			id = 0;
		}
		return id;
	}
}
