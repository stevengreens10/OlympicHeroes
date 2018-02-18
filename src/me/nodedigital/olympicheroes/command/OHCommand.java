package me.nodedigital.olympicheroes.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nodedigital.olympicheroes.Variables;
import me.nodedigital.olympicheroes.player.OHPlayer;

public class OHCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            OHPlayer ohPlayer = new OHPlayer(player);

            boolean successfulCommand = false;

            if (args.length == 0) {
                player.sendMessage("---------------------");
                for (String god : Variables.GODS) {
                    player.sendMessage(god + " : " + ohPlayer.getXP(god) + " XP : " + "Lvl " + ohPlayer.getLevel(god));
                }
                successfulCommand = true;
            } else if (args.length >= 3) {
                if (args[0].equalsIgnoreCase("set")) {
                    successfulCommand = true;
                    if (player.hasPermission("oh.admin")) {
                        String playerName = args[1];
                        Player p = Bukkit.getServer().getPlayer(playerName);
                        if (p != null) {
                            String godName = args[2];
                            godName = godName.toLowerCase();
                            godName = godName.substring(0, 1).toUpperCase() + godName.substring(1);
                            boolean validGod = false;
                            for (String god : Variables.GODS) {
                                if (god.equalsIgnoreCase(godName)) {
                                    validGod = true;
                                }
                            }

                            if (validGod) {
                                try {
                                    int xp = Integer.parseInt(args[3]);
                                    new OHPlayer(p).setXP(xp, godName, true);
                                    player.sendMessage(
                                            "You set " + playerName + "'s XP for " + godName + " to " + xp + ".");
                                } catch (Exception e) {
                                    player.sendMessage("The XP amount must be a valid number!");
                                }
                            } else {
                                player.sendMessage(godName + " is not a valid god!");
                            }
                        } else {
                            player.sendMessage("Player does not exist!");
                        }
                    } else {
                        player.sendMessage("You do not have permission!");
                    }
                }
            } else if (args.length >= 2) {
                if (args[0].equalsIgnoreCase("reset")) {
                    successfulCommand = true;
                    if (player.hasPermission("oh.admin")) {
                        String playerName = args[1];
                        Player p = Bukkit.getServer().getPlayer(playerName);
                        if (p != null) {
                            OHPlayer ohP = new OHPlayer(p);
                            for (String god : Variables.GODS) {
                                ohP.setXP(0, god, true);
                            }
                            player.sendMessage(playerName + "'s XP has been reset!");
                        } else {
                            player.sendMessage("Player does not exist!");
                        }
                    } else {
                        player.sendMessage("You do not have permission!");
                    }
                } else if (args[0].equalsIgnoreCase("view")) {
                    successfulCommand = true;
                    if (player.hasPermission("oh.admin")) {
                        String playerName = args[1];
                        Player p = Bukkit.getServer().getPlayer(playerName);
                        if (p != null) {
                            OHPlayer ohP = new OHPlayer(p);
                            player.sendMessage("---------" + p.getName() + "---------");
                            for (String god : Variables.GODS) {
                                player.sendMessage(
                                        god + " : " + ohP.getXP(god) + " XP : " + "Lvl " + ohP.getLevel(god));
                            }
                        } else {
                            player.sendMessage("Player does not exist!");
                        }
                    } else {
                        player.sendMessage("You do not have permission!");
                    }
                }
            } else if (args.length == 1) {

                String arg = args[0];

                String god = "";

                for (String g : Variables.GODS) {
                    if (arg.equalsIgnoreCase(g)) {
                        god = g;
                        break;
                    }
                }

                if (god.length() >= 1) {
                    successfulCommand = true;
                    god = god.toLowerCase();
                    god = god.replaceFirst(".", "" + Character.toUpperCase(god.charAt(0)));
                    player.sendMessage("-----" + god + "-----");
                    for (int i = 1; i < Variables.GOD_INFO.get(god).length; i++) {
                        player.sendMessage(Variables.GOD_INFO.get(god)[i]);
                    }
                }
            }

            if (!successfulCommand) {
                if (player.hasPermission("oh.admin")) {
                    for (String s : Variables.ADMIN_HELP_MESSAGE) {
                        player.sendMessage(s);
                    }
                } else {
                    for (String s : Variables.HELP_MESSAGE) {
                        player.sendMessage(s);
                    }
                }
            }

        }
        return false;
    }

}
