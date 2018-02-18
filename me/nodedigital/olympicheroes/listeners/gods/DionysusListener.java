package me.nodedigital.olympicheroes.listeners.gods;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.nodedigital.olympicheroes.Cooldowns;
import me.nodedigital.olympicheroes.OlympicHeroes;
import me.nodedigital.olympicheroes.Variables;
import me.nodedigital.olympicheroes.player.OHPlayer;

public class DionysusListener implements Listener{

	private OlympicHeroes plugin;
	
	public DionysusListener(OlympicHeroes plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent e) {
		Player player = e.getPlayer();
		OHPlayer ohPlayer = new OHPlayer(player);
		
		if(e.getHand() == EquipmentSlot.HAND && e.getRightClicked() instanceof LivingEntity) {
			
			LivingEntity entity = (LivingEntity) e.getRightClicked();
			ItemStack item = player.getInventory().getItemInMainHand();
			
			if(item != null && item.getType() == Material.STICK) {
				
				if(!Cooldowns.dionysusPoisonCooldown.contains(player)) {
					boolean applied = false;
					
					if(ohPlayer.getLevel("Dionysus") >= 3) {
						
						int duration = 300;
						
						if(ohPlayer.getLevel("Dionysus") >= 4) {
							duration = 600;
						}
						entity.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, duration, 1));
						
						applied = true;
					}
					
					if(ohPlayer.getLevel("Dionsysus") >= 4) {
						
						int level = Math.min(ohPlayer.getLevel("Dionysus")-4, 1);
						
						entity.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, level));
					}
					
					if(applied) {
						player.sendMessage("You have bestowed harmful effects on " + entity.getName());
						Cooldowns.dionysusPoisonCooldown.add(player);
						Cooldowns.removeCooldown(plugin, Cooldowns.dionysusPoisonCooldown, player, Variables.DIONYSUS_POISON_COOLDOWN);
					}
					
				}else {
					player.sendMessage("That ability is on cooldown.");
				}
			}
		}
	}
	
}
