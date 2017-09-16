package me.TehGoldyLockz.OlympicHeroes.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.TehGoldyLockz.OlympicHeroes.item.OHItems;

public class OHCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			player.getInventory().addItem(OHItems.LONG_BOW);
			//player.getInventory().addItem(OHItems.LAMINATED_LONGBOW);
			player.getInventory().addItem(OHItems.HERMES_BOOTS);
			//player.getInventory().addItem(OHItems.LAMINATED_RECURVE);
			player.getInventory().addItem(OHItems.LAMINATED_STICK);
			
			
		}
		return false;
	}

}
