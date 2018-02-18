package me.nodedigital.olympicheroes;

import java.io.File;
import java.io.FileNotFoundException;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Handles storing data in YML files
 * @author Steven Green
 *
 */
public class Config {
    private FileConfiguration config;
    private String filePath;

    /**
     * Initializes the config
     * @param path the path to the config
     */
    public Config(String path) {
        filePath = path;
        config = YamlConfiguration.loadConfiguration(new File(filePath));
    }

    /**
     * Gets the bukkit config object
     * @return The config
     */
    public FileConfiguration getConfig() {
        try {
            config.load(filePath);
        } catch (FileNotFoundException e) {
            try {
                new File(filePath).createNewFile();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return config;
    }

    /**
     * Saves the config
     */
    public void save() {
        try {
            config.save(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets a block in the config.
     * Stores the location of the block
     * @param path The path in the config where the block information is stored
     * @param b The block object
     */
    public void setBlock(String path, Block b) {
        Location l = b.getLocation();
        config.set(path + ".x", l.getBlockX());
        config.set(path + ".y", l.getBlockY());
        config.set(path + ".z", l.getBlockZ());
        config.set(path + ".world", l.getWorld().getName());

    }
}
