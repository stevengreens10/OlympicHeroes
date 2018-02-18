package me.nodedigital.olympicheroes.listeners.gods;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.nodedigital.olympicheroes.Cooldowns;
import me.nodedigital.olympicheroes.OlympicHeroes;
import me.nodedigital.olympicheroes.Variables;
import me.nodedigital.olympicheroes.player.OHPlayer;

public class AresListener implements Listener{

	private OlympicHeroes plugin;
	
	public AresListener(OlympicHeroes plugin) {
		this.plugin = plugin;
		
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		OHPlayer ohPlayer = new OHPlayer(player);
		
		if( (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && e.getHand() == EquipmentSlot.HAND) {
			
			ItemStack item = e.getItem();
			if((item != null) && (item.getType() == Material.DIAMOND_AXE || item.getType() == Material.GOLD_AXE ||
			   item.getType() == Material.IRON_AXE || item.getType() == Material.STONE_AXE ||
			   item.getType() == Material.WOOD_AXE) ) {
				if(ohPlayer.getLevel("Ares") >= 5) {
					
					if(!Cooldowns.aresRageCooldown.contains(e.getPlayer())) {
						Cooldowns.aresRageCooldown.add(e.getPlayer());
						e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 3), true);
						e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 100, 1));
						
						Cooldowns.removeCooldown(plugin, Cooldowns.aresRageCooldown, e.getPlayer(), Variables.ARES_RAGE_COOLDOWN);
					}else {
						e.getPlayer().sendMessage("That ability is on cooldown.");
					}
				}
			}
		}
	}
	
}
