package me.nodedigital.olympicheroes.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import me.nodedigital.olympicheroes.OlympicHeroes;
import me.nodedigital.olympicheroes.item.OHItems;

/**
 * Listener for craft and enchant events
 * @author Steven Green
 *
 */
@SuppressWarnings("unused")
public class CraftListener implements Listener {

    /**
     * Initializes the listener
     * @param plugin Reference to the main plugin class
     */
    public CraftListener(OlympicHeroes plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Event handler for a player crafting an item.
     * Prevents player crafting currency items
     * @param e The event for crafting an item
     */
    @EventHandler
    public void onCraft(CraftItemEvent e) {

        // Prevent crafting with currency
        for (ItemStack ing : e.getInventory().getContents()) {
            if (ing != null) {
                if (OHItems.isItemSimilarTo(ing, OHItems.silverDrachma, true)
                        || OHItems.isItemSimilarTo(ing, OHItems.goldenDrachma, false)) {
                    e.setCancelled(true);
                    break;
                }
            }
        }
    }

    /**
     * Event handler for enchanting an item.
     * The player can not enchant an item unless they have a specific permission
     * @param e The enchant item event
     */
 //   @EventHandler
 //   public void onEnchant(EnchantItemEvent e) {
 //       Player player = e.getEnchanter();
  //      if (!player.hasPermission("olympicheroes.enchant")) {
  //          e.setCancelled(true);
  //      }
  //  }

}
