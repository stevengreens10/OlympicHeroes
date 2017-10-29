package me.NodeDigital.OlympicHeroes.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

import me.NodeDigital.OlympicHeroes.Cooldowns;
import me.NodeDigital.OlympicHeroes.OlympicHeroes;
import me.NodeDigital.OlympicHeroes.Variables;
import me.NodeDigital.OlympicHeroes.gods.GodData;
import me.NodeDigital.OlympicHeroes.item.OHItems;
import me.NodeDigital.OlympicHeroes.player.OHPlayer;

public class PlayerListener implements Listener{

	OlympicHeroes plugin;
	
	public PlayerListener(OlympicHeroes plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			
			// Make lightning do 1 more damage
			
			if(e.getCause() == DamageCause.LIGHTNING) {
				e.setDamage(e.getFinalDamage()+1);
			}
		}
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		
		boolean allowed = true;
		
		for(ItemStack it : OHItems.DISALLOWED_ITEMS) {
			if(OHItems.isItemSimilarTo(e.getCurrentItem(), it, false)) {
				allowed = false;
				break;
			}
		}
		
		if(!allowed) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void PlayerInteractEntity(PlayerInteractEntityEvent e) {
		
		Player player = e.getPlayer();
		
		if(e.getRightClicked() instanceof Villager) {
			Villager villager = (Villager) e.getRightClicked();
			if(villager.getCustomName() == ChatColor.AQUA + "Vault Manager") {
				player.sendMessage(ChatColor.AQUA + "That is a Vault Manager");
				
			}
		}
		
	}
	
	@EventHandler
	public void onKill(PlayerDeathEvent e) {
		Player player = e.getEntity();
		
		if(player.getKiller() instanceof Player) {
			Player killer = (Player) player.getKiller();
			OHPlayer ohPlayer = new OHPlayer(player);
			OHPlayer ohKiller = new OHPlayer(killer);
			
			boolean gotXP = false;
			
			for(String god : Variables.GODS) {
				if(!Cooldowns.killCooldownMap.containsKey(killer) || !Cooldowns.killCooldownMap.get(killer).contains(player)) {
					for(String oppGod : Variables.OPPOSING_GODS.get(god)) {
						if(ohKiller.getLevel(god) >= 3 && ohPlayer.getLevel(oppGod) >= 3) {
							
							gotXP = true;
							
							ohKiller.setXP(ohKiller.getXP(god)+100, god, false);
							ohPlayer.setXP(ohPlayer.getXP(oppGod)-70,oppGod,false);
							
							killer.sendMessage("You gained 100 XP for " + god);
							player.sendMessage("You lost 70 XP for " + oppGod);
						}
					}
				}
			}
			
			if(gotXP) {
				if(!Cooldowns.killCooldownMap.containsKey(killer)) {
					List<Player> playerList = new ArrayList<Player>();
					Cooldowns.killCooldownMap.put(killer, playerList);
				}else {
					Cooldowns.killCooldownMap.get(killer).add(player);
				}
				Cooldowns.removePlayerFromKillCooldown(plugin, killer, player, Variables.KILL_COOLDOWN);
			}
		}
	}
	
	@EventHandler
	public void onSwapItem(PlayerSwapHandItemsEvent e) {
		
		boolean allowed = true;
		
		for(ItemStack it : OHItems.DISALLOWED_ITEMS) {
			if(OHItems.isItemSimilarTo(e.getMainHandItem(), it, false) ||
			   OHItems.isItemSimilarTo(e.getOffHandItem(), it, false)) {
				allowed = false;
				break;
			}
		}
		
		if(!allowed) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		
		Player player = e.getEntity();
		
		for(int i = e.getDrops().size() - 1; i >= 0; i--) {
			ItemStack item = e.getDrops().get(i);
			boolean remove = false;
			
			for(ItemStack it : OHItems.DISALLOWED_ITEMS) {
				if(OHItems.isItemSimilarTo(item, it, false)) {
					remove = true;
					break;
				}
			}
			
			if(remove) {
				e.getDrops().remove(i);
				
				if(OHItems.isItemSimilarTo(item, OHItems.HERMES_ELYTRA, false)) {
					e.getDrops().add(GodData.hermesChestplateMap.get(player));
					GodData.hermesChestplateMap.remove(player);
				}else if(OHItems.isItemSimilarTo(item, OHItems.POSEIDON_BOOTS, false)) {
					e.getDrops().add(GodData.poseidonBootsMap.get(player));
					GodData.poseidonBootsMap.remove(player);
				}
			}
		}
	}
	
}
