package me.NodeDigital.OlympicHeroes.listeners.gods;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.NodeDigital.OlympicHeroes.OlympicHeroes;
import me.NodeDigital.OlympicHeroes.player.OHPlayer;

public class ApolloListener implements Listener{

	OlympicHeroes plugin;
	
	public ApolloListener(OlympicHeroes plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onShootEntity(ProjectileHitEvent e) {
		if(e.getEntity().getShooter() instanceof Player && e.getEntity() instanceof Arrow) {
			Player player = (Player) e.getEntity().getShooter();
			OHPlayer ohPlayer = new OHPlayer(player);
			
			if(e.getHitEntity() != null && e.getHitEntity() instanceof LivingEntity) {
			
				LivingEntity ent = (LivingEntity) e.getHitEntity();
				
				// Make hit entity glow for 10 seconds after hitting with arrow at > level 2
				
				if(ohPlayer.getLevel("Apollo") >= 2) {
					ent.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 200, 0));
				}
				
				// Give hit entity fire ticks when apollo hits them with an arrow at > level 3
				
				if(ohPlayer.getLevel("Apollo") >= 3) {
					long time = player.getWorld().getTime();
					if(e.getHitEntity() != null && ( time <= 13500 || time >= 23000 )) {
						e.getHitEntity().setFireTicks(300);
					}
				}
			}
		}
	}
	
}
