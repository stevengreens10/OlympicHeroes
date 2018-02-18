package me.nodedigital.olympicheroes.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.nodedigital.olympicheroes.item.OHItems;
import me.nodedigital.olympicheroes.player.OHPlayer;

public class EffectsTask implements Runnable {
	
	@Override
	public void run() {
	
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			OHPlayer ohPlayer = new OHPlayer(p);
			
			long time = p.getWorld().getTime();
			ItemStack mainItem = p.getInventory().getItemInMainHand();
			ItemStack offItem = p.getInventory().getItemInOffHand();
			
			boolean swordInMain = false;
			boolean axeInMain = false;
			boolean pickInMain = false;
			
			if(mainItem != null && (mainItem.getType() == Material.DIAMOND_SWORD || mainItem.getType() == Material.GOLD_SWORD || 
					   mainItem.getType() == Material.IRON_SWORD || mainItem.getType() == Material.STONE_SWORD ||
					   mainItem.getType() == Material.WOOD_SWORD)) {
				swordInMain = true;
			}
			
			if(mainItem != null && (mainItem.getType() == Material.DIAMOND_AXE || mainItem.getType() == Material.GOLD_AXE || 
					   mainItem.getType() == Material.IRON_AXE || mainItem.getType() == Material.STONE_AXE ||
					   mainItem.getType() == Material.WOOD_AXE)) {
				axeInMain = true;
			}
			
			if(mainItem != null && (mainItem.getType() == Material.DIAMOND_PICKAXE || mainItem.getType() == Material.GOLD_PICKAXE || 
			   mainItem.getType() == Material.IRON_PICKAXE || mainItem.getType() == Material.STONE_PICKAXE ||
			   mainItem.getType() == Material.WOOD_PICKAXE)) {
				pickInMain = true;
			}
			
			if(ohPlayer.getLevel("Zeus") >= 3) {
				int jumpLevel = Math.min(ohPlayer.getLevel("Zeus") - 3, 1);

				if(shouldApply(p, PotionEffectType.JUMP, jumpLevel)) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20, jumpLevel), true);
				}

			}
			
			if(ohPlayer.getXP("Poseidon") > 0) {
				if(p.getLocation().getBlock().getType() == Material.WATER ||
				   p.getLocation().getBlock().getType() == Material.STATIONARY_WATER){
					int strengthLevel = Math.min(ohPlayer.getLevel("Poseidon")-2, 1);
					
					if(shouldApply(p, PotionEffectType.INCREASE_DAMAGE, strengthLevel)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20, strengthLevel), true);
					}
				}
			}
			
			if(ohPlayer.getLevel("Artemis") >= 2 ) {
				
				
				boolean apply = true;
				for(PotionEffect e : p.getActivePotionEffects()) {
					if(e.getType().equals(PotionEffectType.NIGHT_VISION)) {
						if(e.getDuration() > 240) {
							apply = false;
						}
					}
				}
				
				if(time >= 13500 && time <= 23000 && apply) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 400, 0), true);
				}
			}
			
			if(ohPlayer.getLevel("Artemis") >= 5) {
				if(time >= 13500 && time <= 23000) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20, 0), true);
				}
			}
			
			if(ohPlayer.getLevel("Apollo") >= 5) {			
				boolean apply = true;
				for(PotionEffect e : p.getActivePotionEffects()) {
					if(e.getType().equals(PotionEffectType.REGENERATION)) {
						apply = false;
					}
				}
				
				if((time <= 13500 || time >= 23000) && apply) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 0), true);
				}
			}
			
			if(ohPlayer.getLevel("Aphrodite") >= 3) {
				int absLevel = Math.min(ohPlayer.getLevel("Aphrodite") - 3, 1);
				
				if(shouldApply(p,PotionEffectType.ABSORPTION,absLevel)) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2400, absLevel), false);
				}
			}
			
			if(ohPlayer.getLevel("Athena") >= 3) {
				if(swordInMain) {
					int hasteLevel = Math.min(ohPlayer.getLevel("Athena")-3, 2);
					if(shouldApply(p,PotionEffectType.FAST_DIGGING,hasteLevel)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20, hasteLevel), true);
					}
				}
			}
			
			boolean hasShield = (offItem != null) ? OHItems.isItemSimilarTo(offItem, OHItems.AEGIS_SHIELD, true) : false;
			
			if(ohPlayer.getLevel("Athena") >= 5) {
				if(!hasShield && swordInMain) {
					if(offItem == null || offItem.getType() == Material.AIR) {
						p.getInventory().setItemInOffHand(OHItems.AEGIS_SHIELD);
					}
				}
			}
			
			if(hasShield && ohPlayer.getLevel("Athena") >= 5){
				if(!swordInMain) {
					p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
				}
			}
			
			
			if(ohPlayer.getLevel("Hermes") >= 2) {
				int speedLevel = ohPlayer.getLevel("Hermes") - 2;
				speedLevel = Math.min(speedLevel, 3);
				
				if(shouldApply(p,PotionEffectType.SPEED,speedLevel)) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, speedLevel), true);
				}
			}
			
			if(ohPlayer.getXP("Hades") > 0) {
				int luckLevel = Math.min(ohPlayer.getLevel("Hades")-1, 1);
				if(shouldApply(p,PotionEffectType.LUCK,luckLevel)) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 20, luckLevel), true);
				}
			}
			
			if(ohPlayer.getLevel("Hades") >= 3) {
				if(pickInMain) {
					int hasteLevel = Math.min(ohPlayer.getLevel("Hades")-4, 1);
					if(shouldApply(p,PotionEffectType.FAST_DIGGING,hasteLevel)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20, hasteLevel), true);
					}
				}
			}
			
			if(ohPlayer.getLevel("Ares") >= 3) {
				int strengthLevel = Math.min(ohPlayer.getLevel("Ares") - 3, 1);
				if(axeInMain) {
					if(shouldApply(p,PotionEffectType.INCREASE_DAMAGE,strengthLevel)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20, strengthLevel), true);
					}
				}

			}

		}

	}
	
	public static boolean shouldApply(Player player, PotionEffectType type, int level) {
		boolean apply = true;
		for(PotionEffect e : player.getActivePotionEffects()) {
			if(e.getType().equals(type)) {
				if(e.getAmplifier() > level) {
					apply = false;
				}
			}
		}
		
		return apply;
	}

}
