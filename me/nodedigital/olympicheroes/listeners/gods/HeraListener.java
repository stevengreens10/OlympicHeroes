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

public class HeraListener implements Listener {

    private OlympicHeroes plugin;

    public HeraListener(OlympicHeroes plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();
        OHPlayer ohPlayer = new OHPlayer(player);

        if (e.getHand() == EquipmentSlot.HAND && e.getRightClicked() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) e.getRightClicked();
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item != null && item.getType() == Material.STICK) {

                if (!Cooldowns.heraBestowCooldown.contains(player)) {

                    boolean applied = false;

                    if (ohPlayer.getLevel("Hera") >= 3) {
                        entity.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1));
                        applied = true;
                    }
                    if (ohPlayer.getLevel("Hera") >= 5) {
                        entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 1));
                    }

                    if (applied) {
                        player.sendMessage("You have bestowed wellness on " + entity.getName());
                        Cooldowns.heraBestowCooldown.add(player);
                        Cooldowns.removeCooldown(plugin, Cooldowns.heraBestowCooldown, player,
                                Variables.HERA_BESTOW_COOLDOWN);
                    }
                } else {
                    player.sendMessage("That ability is on cooldown.");
                }
            }
        }
    }

}
