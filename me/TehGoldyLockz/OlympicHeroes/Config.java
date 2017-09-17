package me.TehGoldyLockz.OlympicHeroes;

import java.io.File;
import java.io.FileNotFoundException;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
	private FileConfiguration config;
	private String filePath;
	
	public Config(String path) {
		filePath = path;
		config = YamlConfiguration.loadConfiguration(new File(filePath));
	}
	
	public FileConfiguration getConfig() {
		try {
			config.load(filePath);
		}catch(FileNotFoundException e) {
			try {
				new File(filePath).createNewFile();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return config;
	}
	
	public void save() {
		try {
			config.save(filePath);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setBlock(String path, Block b) {
		Location l = b.getLocation();
		config.set(path + ".x", l.getBlockX());
		config.set(path + ".y", l.getBlockY());
		config.set(path + ".z", l.getBlockZ());
		config.set(path + ".world", l.getWorld().getName());

	}
}
