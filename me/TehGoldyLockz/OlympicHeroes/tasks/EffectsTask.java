package me.TehGoldyLockz.OlympicHeroes.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.TehGoldyLockz.OlympicHeroes.player.OHPlayer;

public class EffectsTask implements Runnable {

	@Override
	public void run() {
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			OHPlayer ohPlayer = new OHPlayer(p);
			
			if(ohPlayer.getLevel("Artemis") >= 2 ) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20, 0), true);
			}
			
			if(ohPlayer.getLevel("Artemis") >= 5) {
				long time = p.getWorld().getTime();
				if(time >= 13500 && time <= 23000) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20, 0), true);
				}
			}
			
			if(ohPlayer.getLevel("Hermes") >= 2) {
				int speedLevel = ohPlayer.getLevel("Hermes") - 2;
				speedLevel = Math.min(speedLevel, 3);
				
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, speedLevel), true);
			}
		}
	}

}
