package me.nodedigital.olympicheroes.listeners.gods;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Arrow.PickupStatus;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

import me.nodedigital.olympicheroes.OlympicHeroes;
import me.nodedigital.olympicheroes.player.OHPlayer;

/**
 * Listener for events related to Artemis's powers
 * @author Steven Green
 *
 */
public class ArtemisListener implements Listener {

    private OlympicHeroes plugin;

    /**
     * Initializes the listener
     * @param plugin Reference to the main plugin class
     */
    public ArtemisListener(OlympicHeroes plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Event handler for an entity shooting a bow.
     * If the player's devotion to Artemis is high enough, they will never run out of arrows.
     * @param e The event
     */
    @EventHandler
    public void onBowShoot(EntityShootBowEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            OHPlayer ohPlayer = new OHPlayer(player);

            if (ohPlayer.getLevel("Artemis") >= 3) {
                if (!e.getBow().containsEnchantment(Enchantment.ARROW_INFINITE)
                        && player.getGameMode() != GameMode.CREATIVE) {
                    ((Arrow) e.getProjectile()).setPickupStatus(PickupStatus.DISALLOWED);
                    player.getWorld().dropItemNaturally(player.getLocation(), new ItemStack(Material.ARROW, 1));
                }
            }
        }
    }

}
