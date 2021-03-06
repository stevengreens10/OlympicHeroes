package me.nodedigital.olympicheroes.listeners.gods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import me.nodedigital.olympicheroes.Cooldowns;
import me.nodedigital.olympicheroes.OlympicHeroes;
import me.nodedigital.olympicheroes.Variables;
import me.nodedigital.olympicheroes.player.OHPlayer;

/**
 * Listener for events related to Demeter's abilities
 * @author Steven Green
 *
 */
public class DemeterListener implements Listener {

    private OlympicHeroes plugin;
    private List<Material> growableCrops = new ArrayList<Material>();

    /**
     * Initializes the listener
     * @param plugin Reference to the main plugin class
     */
    public DemeterListener(OlympicHeroes plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        growableCrops = Arrays.asList(new Material[] { Material.CROPS, Material.MELON_STEM, Material.BEETROOT_BLOCK,
                Material.CARROT, Material.POTATO, Material.PUMPKIN_STEM, Material.NETHER_WART_BLOCK, Material.CACTUS,
                Material.SUGAR_CANE_BLOCK });
    }

    /**
     * Event handler for a player interaction (clicking)
     * If a player devoted to Demeter right clicks with bonemeal, they can grow crops in a radius
     * @param e The player interaction event
     */
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        OHPlayer ohPlayer = new OHPlayer(player);

        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
                && e.getHand() == EquipmentSlot.HAND) {
            ItemStack item = e.getItem();
            if (item != null && item.getType() == Material.BONE) {
                if (ohPlayer.getLevel("Demeter") >= 3) {
                    if (!Cooldowns.demeterBonemealCooldown.contains(player)) {

                        int radius = Math.min(ohPlayer.getLevel("Demeter"), 4);

                        Location l = player.getLocation();
                        int xp = l.getBlockX();
                        int yp = l.getBlockY();
                        int zp = l.getBlockZ();

                        boolean grow = false;

                        for (int x = xp - radius; x <= xp + radius; x++) {
                            for (int y = yp - 1; y <= yp + 1; y++) {
                                for (int z = zp - radius; z <= zp + radius; z++) {
                                    Block b = new Location(player.getWorld(), x, y, z).getBlock();
                                    if (growableCrops.contains(b.getType())) {

                                        if (b.getType() == Material.SUGAR_CANE_BLOCK
                                                || b.getType() == Material.CACTUS) {
                                            Material type = b.getType();
                                            if (b.getLocation().clone().subtract(0, 1, 0).getBlock()
                                                    .getType() != type) {
                                                b.getWorld().playEffect(b.getLocation(), Effect.VILLAGER_PLANT_GROW,
                                                        10);
                                                for (int yb = 0; yb <= 2; yb++) {
                                                    Block above = new Location(b.getWorld(), x, y + yb, z).getBlock();
                                                    if (above == null || above.getType() == Material.AIR) {
                                                        above.setType(type);
                                                        grow = true;
                                                    }
                                                }
                                            }
                                        } else {
                                            byte newData = (b.getType() == Material.BEETROOT_BLOCK) ? (byte) 3
                                                    : (byte) 7;
                                            b.setData(newData);
                                            grow = true;
                                            b.getWorld().playEffect(b.getLocation(), Effect.VILLAGER_PLANT_GROW, 10);
                                        }
                                    }
                                }
                            }
                        }
                        if (grow) {
                            Cooldowns.demeterBonemealCooldown.add(player);
                            Cooldowns.removeCooldown(plugin, Cooldowns.demeterBonemealCooldown, player,
                                    Variables.DEMETER_BONEMEAL_COOLDOWN);
                        }
                    } else {
                        player.sendMessage("That ability is on cooldown.");
                    }
                }
            }
        }
    }

    /**
     * Event handler for a block being broken
     * If a player devoted to Demeter breaks a crop, there is a chance they will obtain double drops
     * depending on their devotion
     * @param e The block break event
     */
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        OHPlayer ohPlayer = new OHPlayer(player);
        Block b = e.getBlock();

        if (growableCrops.contains(b.getType())) {
            if (ohPlayer.getXP("Demeter") >= 1) {
                double chance = Math.min((double) ohPlayer.getXP("Demeter") / 10000, 0.8);

                if (Math.random() < chance
                        && b.getData() == ((b.getType() == Material.BEETROOT_BLOCK) ? (byte) 3 : (byte) 7)) {

                    e.setCancelled(true);

                    // TODO THIS IS BAD CODE, b.getDrops() doesn't give correct amounts of items, nor
                    // even seeds for breaking wheat

                    List<ItemStack> items = new ArrayList<ItemStack>(
                            b.getDrops(player.getInventory().getItemInMainHand()));

                    items.add(items.get(0));

                    if (b.getType() == Material.CROPS) {
                        items.add(new ItemStack(Material.SEEDS, 2));
                    }

                    for (ItemStack item : items) {
                        b.getWorld().dropItemNaturally(b.getLocation(), item);
                    }

                    b.setType(Material.AIR);

                }
            }
        }
    }

}