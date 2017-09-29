package me.TehGoldyLockz.OlympicHeroes.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Arrow.PickupStatus;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.TehGoldyLockz.OlympicHeroes.Cooldowns;
import me.TehGoldyLockz.OlympicHeroes.OlympicHeroes;
import me.TehGoldyLockz.OlympicHeroes.Variables;
import me.TehGoldyLockz.OlympicHeroes.player.OHPlayer;

public class PlayerListener implements Listener{

	OlympicHeroes plugin;
	
	public PlayerListener(OlympicHeroes plugin) {
		this.plugin = plugin;
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
			
			if(e.getCause() == DamageCause.ENTITY_ATTACK) {
				if(ohPlayer.getLevel("Aphrodite") >= 5) {
					if(!Cooldowns.resCooldown.contains(player.getPlayer())) {
						Cooldowns.resCooldown.add(player.getPlayer());
						player.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 1));
						
						OlympicHeroes.removeCooldown(plugin, Cooldowns.resCooldown, player.getPlayer(), Variables.RES_COOLDOWN);
					}
				}
			}
		}
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
	
	@EventHandler
	public void onShootEntity(ProjectileHitEvent e) {
		if(e.getEntity().getShooter() instanceof Player) {
			Player player = (Player) e.getEntity().getShooter();
			OHPlayer ohPlayer = new OHPlayer(player);
			if(ohPlayer.getLevel("Apollo") >= 2) {
				if(e.getHitEntity() != null) {
					e.getHitEntity().setGlowing(true);
					
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

						@Override
						public void run() {
							if(e.getHitEntity().isDead() == false) {
								e.getHitEntity().setGlowing(false);
							}
						}
						
					}, 200L);
				}
			}
			
			if(ohPlayer.getLevel("Apollo") >= 3) {
				long time = player.getWorld().getTime();
				if(time <= 13500 || time >= 23000) {
					e.getHitEntity().setFireTicks(300);
				}
			}
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
		//HANDLE ZEUS ABILITY
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR && e.getHand() == EquipmentSlot.HAND) {
			ItemStack item = e.getItem();
			
			if(item.getType() == Material.DIAMOND_SWORD || item.getType() == Material.GOLD_SWORD ||
			   item.getType() == Material.IRON_SWORD || item.getType() == Material.STONE_SWORD ||
			   item.getType() == Material.WOOD_SWORD) {
				OHPlayer ohPlayer = new OHPlayer(e.getPlayer());
				if(ohPlayer.getLevel("Zeus") >= 5) {
					
					if(!Cooldowns.lightningCooldown.contains(e.getPlayer())) {
						Block block = e.getPlayer().getTargetBlock(null, 100);
						Location l = block.getLocation();
						e.getPlayer().getWorld().strikeLightning(l);
						Cooldowns.lightningCooldown.add(e.getPlayer());
						OlympicHeroes.removeCooldown(plugin, Cooldowns.lightningCooldown, e.getPlayer(), Variables.LIGHTNING_COOLDOWN);
					}else {
						e.getPlayer().sendMessage("That ability is on cooldown.");
					}
				}
			}
		}
		
		// HANDLE ARES ABILITY
		
		if( (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && e.getHand() == EquipmentSlot.HAND) {
			
			ItemStack item = e.getItem();
			if(item.getType() == Material.DIAMOND_AXE || item.getType() == Material.GOLD_AXE ||
			   item.getType() == Material.IRON_AXE || item.getType() == Material.STONE_AXE ||
			   item.getType() == Material.WOOD_AXE) {
				OHPlayer ohPlayer = new OHPlayer(e.getPlayer());
				if(ohPlayer.getLevel("Ares") >= 5) {
					
					if(!Cooldowns.rageCooldown.contains(e.getPlayer())) {
						Cooldowns.rageCooldown.add(e.getPlayer());
						e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 3), true);
						e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 100, 1));
						
						OlympicHeroes.removeCooldown(plugin, Cooldowns.rageCooldown, e.getPlayer(), Variables.RAGE_COOLDOWN);
					}else {
						e.getPlayer().sendMessage("That ability is on cooldown.");
					}
				}
			}
		}
	}
	
	
	@EventHandler
	public void PlayerInteractEntity(PlayerInteractEntityEvent e) {
		if(e.getRightClicked() instanceof Villager) {
			Villager villager = (Villager) e.getRightClicked();
			if(villager.getCustomName() == ChatColor.AQUA + "Vault Manager") {
				e.getPlayer().sendMessage(ChatColor.AQUA + "That is a Vault Manager");
				
			}
		}
	}
}
