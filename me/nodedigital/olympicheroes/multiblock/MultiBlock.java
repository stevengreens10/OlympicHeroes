package me.nodedigital.olympicheroes.multiblock;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class MultiBlock {
	
	public Block triggerBlock = null;
	public List<Block> blocks = new ArrayList<Block>();
	public String god = "";
	public BlockFace facing = null;
	public int ID = 0;
	
	public MultiBlock(Block triggerBlock, List<Block> blocks, String god, BlockFace facing, int ID) {
		this.triggerBlock = triggerBlock;
		this.blocks = blocks;
		this.god = god;
		this.facing = facing;
		this.ID = ID;
	}
}
