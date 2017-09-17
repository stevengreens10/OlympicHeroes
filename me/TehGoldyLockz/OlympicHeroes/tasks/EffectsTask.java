package me.TehGoldyLockz.OlympicHeroes.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.TehGoldyLockz.OlympicHeroes.player.OHPlayer;

public class EffectsTask implements Runnable {

	@Override
	public void run() {
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			OHPlayer ohPlayer = new OHPlayer(p);
			
			if(ohPlayer.getLevel("Zeus") >= 3) {
				int jumpLevel = Math.min(ohPlayer.getLevel("Zeus") - 3, 1);
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20, jumpLevel), true);

			}
			
			if(ohPlayer.getLevel("Artemis") >= 2 ) {
				long time = p.getWorld().getTime();
				if(time >= 13500 && time <= 23000) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20, 0), true);
				}
			}
			
			if(ohPlayer.getLevel("Artemis") >= 5) {
				long time = p.getWorld().getTime();
				if(time >= 13500 && time <= 23000) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20, 0), true);
				}
			}
			
			if(ohPlayer.getLevel("Apollo") >= 5) {
				long time = p.getWorld().getTime();
				if(time <= 13500 || time >= 23000) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 0), true);
				}
			}
			
			if(ohPlayer.getLevel("Athena") >= 3) {
				ItemStack item = p.getInventory().getItemInMainHand();
				if(item.getType() == Material.DIAMOND_SWORD || item.getType() == Material.GOLD_SWORD || 
				   item.getType() == Material.IRON_SWORD || item.getType() == Material.STONE_SWORD ||
				   item.getType() == Material.WOOD_SWORD) {
					int hasteLevel = Math.min(ohPlayer.getLevel("Athena")-3, 2);
					p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20, hasteLevel), true);
				}
			}
			
			if(ohPlayer.getLevel("Hermes") >= 2) {
				int speedLevel = ohPlayer.getLevel("Hermes") - 2;
				speedLevel = Math.min(speedLevel, 3);
				
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, speedLevel), true);
			}
			
			if(ohPlayer.getXP("Hades") > 0) {
				int luckLevel = Math.min(ohPlayer.getLevel("Hades")-1, 1);
				p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 20, luckLevel), true);
			}
			
			if(ohPlayer.getLevel("Hades") >= 3) {
				ItemStack item = p.getInventory().getItemInMainHand();
				if(item.getType() == Material.DIAMOND_PICKAXE || item.getType() == Material.GOLD_PICKAXE || 
				   item.getType() == Material.IRON_PICKAXE || item.getType() == Material.STONE_PICKAXE ||
				   item.getType() == Material.WOOD_PICKAXE) {
					int hasteLevel = Math.min(ohPlayer.getLevel("Hades")-4, 1);
					p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20, hasteLevel), true);
				}
			}
			
			if(ohPlayer.getLevel("Ares") >= 3) {
				int strengthLevel = Math.min(ohPlayer.getLevel("Ares") - 3, 1);
				ItemStack item = p.getInventory().getItemInMainHand();
				if(item.getType() == Material.DIAMOND_SWORD || item.getType() == Material.GOLD_SWORD || 
				   item.getType() == Material.IRON_SWORD || item.getType() == Material.STONE_SWORD ||
				   item.getType() == Material.WOOD_SWORD || item.getType() == Material.DIAMOND_AXE || 
				   item.getType() == Material.GOLD_AXE || item.getType() == Material.IRON_AXE || 
				   item.getType() == Material.STONE_AXE || item.getType() == Material.WOOD_AXE) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20, strengthLevel), true);
				}

			}
		}
	}

}
