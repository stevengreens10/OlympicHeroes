package me.TehGoldyLockz.OlympicHeroes.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.TehGoldyLockz.OlympicHeroes.OlympicHeroes;
import me.TehGoldyLockz.OlympicHeroes.player.OHPlayer;

public class PlayerListener implements Listener{

	public PlayerListener(OlympicHeroes plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			OHPlayer ohPlayer = new OHPlayer(player);
			
			if(e.getCause() == DamageCause.FALL) {
				if(ohPlayer.getXP("Zeus") > 0) {
					e.setDamage(e.getFinalDamage() / 1.5);
					player.sendMessage("Your fall damage has been reduced thanks to Zeus");
				}
			}
		}
	}
	
}
