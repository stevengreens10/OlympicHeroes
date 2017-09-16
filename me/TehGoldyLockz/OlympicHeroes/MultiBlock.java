package me.TehGoldyLockz.OlympicHeroes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;

public class MultiBlock {
	
	public Block triggerBlock = null;
	public List<Block> blocks = new ArrayList<Block>();
	public String god = "";
	
	public MultiBlock(Block triggerBlock, List<Block> blocks, String god) {
		this.triggerBlock = triggerBlock;
		this.blocks = blocks;
		this.god = god;
	}
}
