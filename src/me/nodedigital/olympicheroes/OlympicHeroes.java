package me.nodedigital.olympicheroes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.nodedigital.olympicheroes.block.ChangedBlock;
import me.nodedigital.olympicheroes.command.OHCommand;
import me.nodedigital.olympicheroes.item.OHItems;
import me.nodedigital.olympicheroes.listeners.Listeners;
import me.nodedigital.olympicheroes.multiblock.MultiBlocks;
import me.nodedigital.olympicheroes.tasks.EffectsTask;

public class OlympicHeroes extends JavaPlugin {

    public static Config mbConfig;
    public static List<List<ChangedBlock>> changedBlocksList = new ArrayList<List<ChangedBlock>>();

    public void onEnable() {
        Bukkit.getLogger().info("Olympic Heroes is startin up :D");
        Variables.setup();
        mbConfig = new Config("storage/mb.yml");
        MultiBlocks.load();

        Listeners.initializeListeners(this);

        getCommand("oh").setExecutor(new OHCommand());

        OHItems.constructItems();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new EffectsTask(), 0L, 10L);
    }

    public static void regenerateTerrain(OlympicHeroes plugin, List<ChangedBlock> blocks, long ticks) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @SuppressWarnings("deprecation")
            public void run() {
                for (ChangedBlock cb : blocks) {
                    cb.getLocation().getBlock().setType(cb.getOldType());
                    cb.getLocation().getBlock().setData(cb.getOldData());
                }
                changedBlocksList.remove(blocks);
            }
        }, ticks);
    }

}
