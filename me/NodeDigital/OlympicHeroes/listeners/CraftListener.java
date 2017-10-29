package me.NodeDigital.OlympicHeroes.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import me.NodeDigital.OlympicHeroes.OlympicHeroes;
import me.NodeDigital.OlympicHeroes.item.OHItems;

public class CraftListener implements Listener{

	public CraftListener(OlympicHeroes plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onCraft(CraftItemEvent e) {
		
		// Prevent crafting with currency
		for(ItemStack ing : e.getInventory().getContents()) {
			if(ing != null) {
				if(OHItems.isItemSimilarTo(ing, OHItems.SILVER_DRACHMA, true) || 
					OHItems.isItemSimilarTo(ing, OHItems.GOLDEN_DRACHMA, false)) {
					e.setCancelled(true);
					break;
				}
			}
		}
	}
	
	@EventHandler
	public void onEnchant(EnchantItemEvent e) {
		Player player = e.getEnchanter();
		if(player.hasPermission("olympicheroes.enchant") == false) {
			e.setCancelled(true);
		}
	}
	
}
