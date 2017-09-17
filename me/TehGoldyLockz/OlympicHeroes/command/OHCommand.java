package me.TehGoldyLockz.OlympicHeroes.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.TehGoldyLockz.OlympicHeroes.Variables;
import me.TehGoldyLockz.OlympicHeroes.player.OHPlayer;

public class OHCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			OHPlayer ohPlayer = new OHPlayer(player);
			player.sendMessage("---------------------");
			for(String god : Variables.GODS) {
				player.sendMessage(god + " : " + ohPlayer.getXP(god) + " XP : " + "Lvl " + ohPlayer.getLevel(god));
			}
		}
		return false;
	}

}
