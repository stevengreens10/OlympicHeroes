package me.nodedigital.olympicheroes.block;

import org.bukkit.Location;
import org.bukkit.Material;

/**
 * Class holding data about a block that has been altered
 * @author Steven Green
 *
 */
public class ChangedBlock {
	private Location location;
	private Material oldType;
	private byte oldData;
	
	/**
	 * Constructs ChangedBlock
	 * @param loc The location of the block that was changed
	 * @param oldType The old material of the block
	 * @param oldData The old data for the block
	 */
	public ChangedBlock(Location loc, Material oldType, byte oldData) {
		setLocation(loc);
		this.setOldType(oldType);
		this.setOldData(oldData);
	}

	/**
	 * Gets the old data for the block
	 * @return The old data
	 */
    public byte getOldData() {
        return oldData;
    }

    /**
     * Sets the old data for the block
     * @param oldData The old data
     */
    public void setOldData(byte oldData) {
        this.oldData = oldData;
    }

    /**
     * Gets the old material for the block
     * @return The old material
     */
    public Material getOldType() {
        return oldType;
    }

    /**
     * Sets the old material for the block
     * @param oldType The old material
     */
    public void setOldType(Material oldType) {
        this.oldType = oldType;
    }

    /**
     * Gets the location of the block that was changed
     * @return The block's location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location of the block that was changed
     * @param location The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }
	
}
