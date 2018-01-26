package me.NodeDigital.OlympicHeroes.listeners.gods;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
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

	private OlympicHeroes plugin;
	
	public ApolloListener(OlympicHeroes plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onShootEntity(ProjectileHitEvent e) {
		
		if(e.getEntity().getShooter() instanceof Player && e.getEntity() instanceof Arrow) {
			Player player = (Player) e.getEntity().getShooter();
			OHPlayer ohPlayer = new OHPlayer(player);
			
			if(ohPlayer.getLevel("Apollo") >= 2) {
				
				World w;
				Location l;
				
				if(e.getHitEntity() != null) {
					w = e.getHitEntity().getWorld();
					l = e.getHitEntity().getLocation();
				}else {
					w = e.getHitBlock().getWorld();
					l = e.getHitBlock().getLocation();
				}
				
				List<Entity> entities = new ArrayList<Entity>(w.getNearbyEntities(l, 5, 5, 5));
				
				for(Entity ent : entities) {
					if(ent instanceof LivingEntity) {
						LivingEntity livEnt = (LivingEntity) ent;
						
						if(livEnt instanceof Player) {
							Player p = (Player) livEnt;
							if(p.equals((Player) e.getEntity().getShooter())) {
								continue;
							}
						}
						
						livEnt.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 200, 0));
					}
				}
				
				
				// Give hit entity fire ticks when apollo hits them with an arrow at > level 3
				
				if(e.getHitEntity() != null && e.getHitEntity() instanceof LivingEntity && ohPlayer.getLevel("Apollo") >= 3) {
					long time = player.getWorld().getTime();
					if(e.getHitEntity() != null && ( time <= 13500 || time >= 23000 )) {
						e.getHitEntity().setFireTicks(300);
					}
				}
			}
		}
	}
	
}
