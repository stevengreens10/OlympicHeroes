package me.TehGoldyLockz.OlympicHeroes.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

import me.TehGoldyLockz.OlympicHeroes.OlympicHeroes;

public class EnchantListener implements Listener{

	public EnchantListener(OlympicHeroes plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onEnchant(EnchantItemEvent e) {
		Player player = e.getEnchanter();
		
		if(player.hasPermission("olympicheroes.enchant") == false) {
			e.setCancelled(true);
		}
	}
}
