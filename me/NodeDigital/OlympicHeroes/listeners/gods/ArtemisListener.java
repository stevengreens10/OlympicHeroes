package me.NodeDigital.OlympicHeroes.listeners.gods;

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

import me.NodeDigital.OlympicHeroes.OlympicHeroes;
import me.NodeDigital.OlympicHeroes.player.OHPlayer;

public class ArtemisListener implements Listener{

	private OlympicHeroes plugin;
	
	public ArtemisListener(OlympicHeroes plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onBowShoot(EntityShootBowEvent e) {
		if(e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			OHPlayer ohPlayer = new OHPlayer(player);

			if(ohPlayer.getLevel("Artemis") >= 3) {
				if(e.getBow().containsEnchantment(Enchantment.ARROW_INFINITE) == false && player.getGameMode() != GameMode.CREATIVE) {
					((Arrow) e.getProjectile()).setPickupStatus(PickupStatus.DISALLOWED);
					player.getWorld().dropItemNaturally(player.getLocation(), new ItemStack(Material.ARROW, 1));
				}
			}
		}
	}
	
}
