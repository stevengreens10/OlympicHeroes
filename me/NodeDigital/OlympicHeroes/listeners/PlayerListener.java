package me.NodeDigital.OlympicHeroes.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Arrow.PickupStatus;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
					if(!Cooldowns.aphroditeResCooldown.contains(player.getPlayer())) {
						Cooldowns.aphroditeResCooldown.add(player.getPlayer());
						player.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 1));
						
						Cooldowns.removeCooldown(plugin, Cooldowns.aphroditeResCooldown, player.getPlayer(), Variables.APHRODITE_RES_COOLDOWN);
					}
				}
			}
			
			if(e.getCause() == DamageCause.LIGHTNING) {
				e.setDamage(e.getFinalDamage()+1);
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
				if(e.getHitEntity() != null && ( time <= 13500 || time >= 23000 )) {
					e.getHitEntity().setFireTicks(300);
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
		Player player = e.getPlayer();
		
		if((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && e.getHand() == EquipmentSlot.HAND) {
			ItemStack item = e.getItem();
			if((item != null) && (item.getType() == Material.DIAMOND_SWORD || item.getType() == Material.GOLD_SWORD ||
			   item.getType() == Material.IRON_SWORD || item.getType() == Material.STONE_SWORD ||
			   item.getType() == Material.WOOD_SWORD) ) {
				OHPlayer ohPlayer = new OHPlayer(player);
				
				if(e.getAction() == Action.RIGHT_CLICK_AIR && ohPlayer.getLevel("Zeus") >= 5) {
					
					//HANDLE ZEUS ABILITY
					if(!Cooldowns.zeusLightningCooldown.contains(player)) {
						Block block = player.getTargetBlock(null, 100);
						Location l = block.getLocation();
						e.getPlayer().getWorld().strikeLightning(l);
						Cooldowns.zeusLightningCooldown.add(player);
						Cooldowns.removeCooldown(plugin, Cooldowns.zeusLightningCooldown, player, Variables.ZEUS_LIGHTNING_COOLDOWN);
					}else {
						e.getPlayer().sendMessage("That ability is on cooldown.");
					}
				}
				
				if(ohPlayer.getLevel("Hades") >= 5) {
					
					//HANDLE HADES ABILITY
					
					if(!Cooldowns.hadesSpawnCooldown.contains(player)) {
						Cooldowns.hadesSpawnCooldown.add(player);
						Cooldowns.removeCooldown(plugin, Cooldowns.hadesSpawnCooldown, player, Variables.HADES_SPAWN_COOLDOWN);
						Location loc = player.getLocation();
						int x = loc.getBlockX();
						int y = loc.getBlockY();
						int z = loc.getBlockZ();
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), 
								String.format("mm mobs spawn HadesSkeles 1 %s,%d,%d,%d",
								player.getWorld().getName(), x, y, z));
					}else {
						e.getPlayer().sendMessage("That ability is on cooldown.");
					}
				}
				
				if(ohPlayer.getLevel("Poseidon") >= 5 ) {
					
					//HANDLE POSEIDON ABILITY
					
					if(!Cooldowns.poseidonSurgeCooldown.contains(player)) {
						int radius = 4;
						int playerX = player.getLocation().getBlockX();
						int playerY = player.getLocation().getBlockY();
						int playerZ = player.getLocation().getBlockZ();
						
						Cooldowns.poseidonSurgeCooldown.add(player);
						Cooldowns.removeCooldown(plugin, Cooldowns.poseidonSurgeCooldown, player, Variables.POSEIDON_SURGE_COOLDOWN);
						
						List<Block> blocksToReplace = new ArrayList<Block>();
							
						for(int x = playerX - radius; x <= playerX + radius; x++) {
							for(int z = playerZ - radius; z <= playerZ + radius; z++) {
								Block block = player.getLocation().getWorld().getBlockAt(x, playerY, z);
								if(block.getType() == Material.AIR || block.getType() == Material.DOUBLE_PLANT ||
								   block.getType() == Material.LONG_GRASS || block.getType() == Material.YELLOW_FLOWER ||
								   block.getType() == Material.RED_ROSE) {
									blocksToReplace.add(block);
								}
							}
						}
						
						for(Block b : blocksToReplace) {
							b.setType(Material.WATER);
							b.setData((byte) 8);
						}
					}else {
						e.getPlayer().sendMessage("That ability is on cooldown.");
					}
				}
			}
		}
		
		// HANDLE ARES ABILITY
		
		if( (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && e.getHand() == EquipmentSlot.HAND) {
			
			ItemStack item = e.getItem();
			if((item != null) && (item.getType() == Material.DIAMOND_AXE || item.getType() == Material.GOLD_AXE ||
			   item.getType() == Material.IRON_AXE || item.getType() == Material.STONE_AXE ||
			   item.getType() == Material.WOOD_AXE) ) {
				OHPlayer ohPlayer = new OHPlayer(e.getPlayer());
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
				e.getPlayer().sendMessage(ChatColor.AQUA + "That is a Vault Manager");
				
			}
		}else if(e.getHand() == EquipmentSlot.HAND && e.getRightClicked() instanceof LivingEntity) {
			OHPlayer ohPlayer = new OHPlayer(player);
			LivingEntity entity = (LivingEntity) e.getRightClicked();
			ItemStack item = player.getInventory().getItemInMainHand();
			if(item != null && item.getType() == Material.STICK) {
				
				if(!Cooldowns.heraBestowCooldown.contains(player)) {
					
					boolean applied = false;
					
					if(ohPlayer.getLevel("Hera") >= 3) {
						entity.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1));
						applied = true;
					}
					if(ohPlayer.getLevel("Hera") >= 5) {
						entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 1));
					}
					
					if(applied) {
						player.sendMessage("You have bestowed wellness on " + entity.getName());
						Cooldowns.heraBestowCooldown.add(player);
						Cooldowns.removeCooldown(plugin, Cooldowns.heraBestowCooldown, player, Variables.HERA_BESTOW_COOLDOWN);
					}
				}else {
					player.sendMessage("That ability is on cooldown.");
				}
				
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
	
	@EventHandler
	public void onShift(PlayerToggleSneakEvent e) {
		if(e.isSneaking()) {
			Player player = e.getPlayer();
			OHPlayer ohPlayer = new OHPlayer(player);
			
			// HERMES GLIDE ABILITY
			
			if(ohPlayer.getLevel("Hermes") >= 5 && !Cooldowns.hermesGlideCooldown.contains(player)) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,80,14), true);
			}
		}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		OHPlayer ohPlayer = new OHPlayer(player);
		
		
		// ON JUMP WITH HERMES JUMP BOOST
		if(player.getVelocity().getY() > 0 && player.getLocation().add(0, -1, 0).getBlock().getType() == Material.AIR) {
			if(ohPlayer.getLevel("Hermes") >= 5) {
				boolean giveElytra = false;
				
				for(PotionEffect eff : player.getActivePotionEffects()) {
					if(eff.getType().equals(PotionEffectType.JUMP)) {
						if(eff.getAmplifier() == 14) {
							giveElytra = true;
						}
					}
				}
				
				if(giveElytra) {
					if(!OHItems.isItemSimilarTo(player.getInventory().getChestplate(), OHItems.HERMES_ELYTRA, false)) {
						
						Cooldowns.hermesGlideCooldown.add(player);
						Cooldowns.removeCooldown(plugin, Cooldowns.hermesGlideCooldown, player, Variables.HERMES_GLIDE_COOLDOWN);
						
						GodData.hermesChestplateMap.put(player,player.getInventory().getChestplate());
						player.getInventory().setChestplate(OHItems.HERMES_ELYTRA);
					}
				}
			}
		}
		
		// ON HERMES LAND FROM GLIDE
		
		if(player.getLocation().add(0,-1,0).getBlock().getType() != Material.AIR) {
			if(ohPlayer.getLevel("Hermes") >= 5) {
				if(OHItems.isItemSimilarTo(player.getInventory().getChestplate(), OHItems.HERMES_ELYTRA, false)) {
					player.getInventory().setChestplate(GodData.hermesChestplateMap.get(player));
					GodData.hermesChestplateMap.remove(player);
				}
			}
		}
		
		// CHECK IF POSEIDON IS IN WATER
		
		if(ohPlayer.getXP("Poseidon")> 0) {
			if(player.getLocation().getBlock().getType() == Material.WATER || player.getLocation().getBlock().getType() == Material.STATIONARY_WATER) {
				if(!OHItems.isItemSimilarTo(player.getInventory().getBoots(), OHItems.POSEIDON_BOOTS, false)) {
					GodData.poseidonBootsMap.put(player, player.getInventory().getBoots());
					player.getInventory().setBoots(OHItems.POSEIDON_BOOTS);
					player.getInventory().getBoots().addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, Math.min(ohPlayer.getLevel("Poseidon")+1, 3));
				}
			}else {
				if(OHItems.isItemSimilarTo(player.getInventory().getBoots(), OHItems.POSEIDON_BOOTS, false)) {
					player.getInventory().setBoots(GodData.poseidonBootsMap.get(player));
					GodData.poseidonBootsMap.remove(player);
				}
			}
		}
	}
}
