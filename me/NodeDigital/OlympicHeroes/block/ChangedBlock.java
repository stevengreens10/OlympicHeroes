package me.NodeDigital.OlympicHeroes.block;

import org.bukkit.Location;
import org.bukkit.Material;

public class ChangedBlock {
	public Location location;
	public Material oldType;
	public byte oldData;
	
	public ChangedBlock(Location loc, Material oldType, Material newType, byte oldData) {
		location = loc;
		this.oldType = oldType;
		this.oldData = oldData;
	}
	
}
