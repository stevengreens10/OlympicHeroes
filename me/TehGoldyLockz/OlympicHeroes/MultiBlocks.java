package me.TehGoldyLockz.OlympicHeroes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class MultiBlocks {
	public static List<MultiBlock> multiBlocks = new ArrayList<MultiBlock>();
	
	public static void addMultiBlock(Block triggerBlock, List<Block> blocks, String god, BlockFace facing) {
		multiBlocks.add(new MultiBlock(triggerBlock, blocks, god, facing));
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
		multiBlocks.remove(multiBlock);
	}
	
	public static void save() {
		
	}
}
