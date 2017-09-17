package me.TehGoldyLockz.OlympicHeroes.command;

import org.bukkit.Bukkit;
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
			
			if(args.length == 0) {
				player.sendMessage("---------------------");
				for(String god : Variables.GODS) {
					player.sendMessage(god + " : " + ohPlayer.getXP(god) + " XP : " + "Lvl " + ohPlayer.getLevel(god));
				}
			}else if(args.length >= 2) {
				if(args[0].equalsIgnoreCase("reset")) {
					String playerName = args[1];
					Player p = Bukkit.getServer().getPlayer(playerName);
					if(p != null) {
						OHPlayer ohP = new OHPlayer(p);
						for(String god : Variables.GODS) {
							ohP.setXP(0, god);
						}
						player.sendMessage(playerName + "'s XP has been reset!");
					}else {
						player.sendMessage("Player does not exist!");
					}
				}else if(args[0].equalsIgnoreCase("view")) {
					String playerName = args[1];
					Player p = Bukkit.getServer().getPlayer(playerName);
					OHPlayer ohP = new OHPlayer(p);
					player.sendMessage("---------------------");
					for(String god : Variables.GODS) {
						player.sendMessage(god + " : " + ohP.getXP(god) + " XP : " + "Lvl " + ohP.getLevel(god));
					}
				}
			}
		}
		return false;
	}

}
