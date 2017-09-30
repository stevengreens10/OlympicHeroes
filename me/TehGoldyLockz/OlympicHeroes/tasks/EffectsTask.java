package me.TehGoldyLockz.OlympicHeroes.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.TehGoldyLockz.OlympicHeroes.item.OHItems;
import me.TehGoldyLockz.OlympicHeroes.player.OHPlayer;

public class EffectsTask implements Runnable {
	public static ItemStack PrevOffHand;
	
	@Override
	public void run() {
	
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			OHPlayer ohPlayer = new OHPlayer(p);
			
			if(ohPlayer.getLevel("Zeus") >= 3) {
				int jumpLevel = Math.min(ohPlayer.getLevel("Zeus") - 3, 1);
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20, jumpLevel), true);

			}
			
			if(ohPlayer.getXP("Poseidon") > 0) {
				if(p.getLocation().getBlock().getType() == Material.WATER ||
				   p.getLocation().getBlock().getType() == Material.STATIONARY_WATER){
					int strengthLevel = Math.min(ohPlayer.getLevel("Poseidon")-2, 1);
					p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20, strengthLevel), true);
				}
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
			
			if(ohPlayer.getLevel("Aphrodite") >= 3) {
				int absLevel = Math.min(ohPlayer.getLevel("Aphrodite") - 3, 1);
				p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2400, absLevel), false);
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
			ItemStack offhand = p.getInventory().getItemInOffHand();
			if(OHItems.isItemSimilarTo(offhand, OHItems.AEGIS_SHIELD, true) == false) {
				@SuppressWarnings("unused")
				ItemStack PrevOffHand = p.getInventory().getItemInOffHand();
				if(ohPlayer.getLevel("Athena") >= 5) {
					ItemStack item = p.getInventory().getItemInMainHand();
					if(OHItems.isItemSimilarTo(offhand, OHItems.AEGIS_SHIELD, true) == false) {
						if(item.getType() == Material.DIAMOND_SWORD || item.getType() == Material.GOLD_SWORD || 
									item.getType() == Material.IRON_SWORD || item.getType() == Material.STONE_SWORD ||
									item.getType() == Material.WOOD_SWORD) {
									if(OHItems.isItemSimilarTo(offhand, OHItems.AEGIS_SHIELD, true) == false) {
										p.getInventory().setItemInOffHand(OHItems.AEGIS_SHIELD);
							}
						}
					}
				}
			}else if(ohPlayer.getLevel("Athena") >= 5){
				ItemStack item = p.getInventory().getItemInMainHand();
				if(item.getType() != Material.DIAMOND_SWORD && item.getType() != Material.GOLD_SWORD && 
					item.getType() != Material.IRON_SWORD && item.getType() != Material.STONE_SWORD &&
					item.getType() != Material.WOOD_SWORD) {
						p.getInventory().removeItem(OHItems.AEGIS_SHIELD);
				}
			}else {
				p.getInventory().removeItem(OHItems.AEGIS_SHIELD);
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
					boolean apply = true;
					for(PotionEffect e : p.getActivePotionEffects()) {
						if(e.getType().equals(PotionEffectType.INCREASE_DAMAGE)) {
							if(e.getAmplifier() > strengthLevel) {
								apply = false;
							}
						}
					}
					if(apply) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20, strengthLevel), true);
					}
				}

			}

		}

	}

}
