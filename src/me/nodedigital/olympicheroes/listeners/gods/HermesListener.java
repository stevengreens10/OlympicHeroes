package me.nodedigital.olympicheroes.listeners.gods;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.nodedigital.olympicheroes.Cooldowns;
import me.nodedigital.olympicheroes.OlympicHeroes;
import me.nodedigital.olympicheroes.Variables;
import me.nodedigital.olympicheroes.gods.GodData;
import me.nodedigital.olympicheroes.item.OHItems;
import me.nodedigital.olympicheroes.player.OHPlayer;

public class HermesListener implements Listener {

    private OlympicHeroes plugin;

    public HermesListener(OlympicHeroes plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onShift(PlayerToggleSneakEvent e) {
        if (e.isSneaking()) {
            Player player = e.getPlayer();
            OHPlayer ohPlayer = new OHPlayer(player);

            // Gives jump lvl 15 for 4 seconds, once player jumps they will get an elytra.

            if (ohPlayer.getLevel("Hermes") >= 5 && !Cooldowns.hermesGlideCooldown.contains(player)) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 80, 14), true);
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        OHPlayer ohPlayer = new OHPlayer(player);

        // ON JUMP WITH HERMES JUMP BOOST
        if (player.getVelocity().getY() > 0
                && player.getLocation().add(0, -1, 0).getBlock().getType() == Material.AIR) {
            if (ohPlayer.getLevel("Hermes") >= 5) {
                boolean giveElytra = false;

                for (PotionEffect eff : player.getActivePotionEffects()) {
                    if (eff.getType().equals(PotionEffectType.JUMP)) {
                        if (eff.getAmplifier() == 14) {
                            giveElytra = true;
                        }
                    }
                }

                if (giveElytra) {
                    if (!OHItems.isItemSimilarTo(player.getInventory().getChestplate(), OHItems.hermesElytra, false)) {

                        Cooldowns.hermesGlideCooldown.add(player);
                        Cooldowns.removeCooldown(plugin, Cooldowns.hermesGlideCooldown, player,
                                Variables.HERMES_GLIDE_COOLDOWN);

                        GodData.hermesChestplateMap.put(player, player.getInventory().getChestplate());
                        player.getInventory().setChestplate(OHItems.hermesElytra);
                    }
                }
            }
        }

        // ON HERMES LAND FROM GLIDE

        if (player.getLocation().add(0, -1, 0).getBlock().getType() != Material.AIR) {
            if (ohPlayer.getLevel("Hermes") >= 5) {
                if (OHItems.isItemSimilarTo(player.getInventory().getChestplate(), OHItems.hermesElytra, false)) {
                    player.getInventory().setChestplate(GodData.hermesChestplateMap.get(player));
                    GodData.hermesChestplateMap.remove(player);
                }
            }
        }

    }

}
