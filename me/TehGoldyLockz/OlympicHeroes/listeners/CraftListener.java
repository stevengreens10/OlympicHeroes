package me.TehGoldyLockz.OlympicHeroes.listeners;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.TehGoldyLockz.OlympicHeroes.OlympicHeroes;
import me.TehGoldyLockz.OlympicHeroes.item.OHItems;
import me.TehGoldyLockz.OlympicHeroes.player.OHPlayer;

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
					OHItems.isItemSimilarTo(ing, OHItems.GOLD_DRACHMA, true)) {
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
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onAnvilUse(InventoryClickEvent e) {
		if(!e.isCancelled()) {
			HumanEntity ent = e.getWhoClicked();
			
			if(ent instanceof Player) {
				Player player = (Player) ent;
				Inventory inv = e.getInventory();
				
				if(inv instanceof AnvilInventory) {
					AnvilInventory anvil = (AnvilInventory) inv;
					ItemStack[] items = anvil.getContents();

					// item in the left slot
					ItemStack item1 = items[0];
					 
					// item in the right slot
					ItemStack item2 = items[1];
					
					if(item1 != null && item2 != null) {
						
						if(e.getRawSlot() == 2) {
							ItemStack result = e.getCurrentItem();
							if(result != null && (result.getType() == item1.getType() || result.getType() == item2.getType())){
								OHPlayer ohPlayer = new OHPlayer(player);
								int hephLevel = ohPlayer.getLevel("Hephaestus");

								if(result.getEnchantments().size() > 0) {
									if(hephLevel < 5) {
										e.setCancelled(true);
										player.closeInventory();
										player.sendMessage("Your devotion to Hephaestus is not high enough to repair enchanted items.");
									}
								}else {
									if(hephLevel < 3) {
										e.setCancelled(true);
										player.closeInventory();
										player.sendMessage("Your devotion to Hephaestus is not high enough to repair items.");
									}
								}
							}
						}
						
					}
				}
			}
		}
	}
}
