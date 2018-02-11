package me.chrisochs.xptransfer.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.chrisochs.xptransfer.Main;
import me.chrisochs.xptransfer.XPHandler;
import net.md_5.bungee.api.ChatColor;

public class PayXpCommand implements CommandExecutor{
	private Plugin plugin;
	
	public PayXpCommand(Plugin p) {
		plugin = p;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
		if(args.length < 2 ) {
			String message = Main.messages.getMessage("payxp-toofewarguments");
			message = ChatColor.translateAlternateColorCodes('&', message);
			p.sendMessage(message);
		}else {
			if(args.length > 2) {
				String message = Main.messages.getMessage("payxp-toomanyarguments");
				message = ChatColor.translateAlternateColorCodes('&', message);
				p.sendMessage(message);				
			}else {
				if(plugin.getServer().getPlayer(args[0])==null) {
					String message = Main.messages.getMessage("payxp-playernotonline");
					message = ChatColor.translateAlternateColorCodes('&', message);
					message = message.replaceAll("%player%", args[0]);
					p.sendMessage(message);
				}else {
					if(!isInteger(args[1])) {
						String message = Main.messages.getMessage("payxp-nonumber");
						message = ChatColor.translateAlternateColorCodes('&', message);
						message = message.replaceAll("%input%", args[1]);
						p.sendMessage(message);
					}else {
						//Wenn Spieler online und zweite Eingabe Ganzzahl
						Integer exp = Integer.parseInt(args[1]);
						Player target = plugin.getServer().getPlayer(args[0]);
						if(exp <= 0) {
							String message = Main.messages.getMessage("payxp-negativeornull");
							message = ChatColor.translateAlternateColorCodes('&', message);
							p.sendMessage(message);
						}else {
							//exp > 0 && Player online
							if(p.getPlayer().getTotalExperience()<exp) {
								String message = Main.messages.getMessage("payxp-notenaughtexp");
								message = ChatColor.translateAlternateColorCodes('&', message);
								message = message.replace("%points%", String.valueOf(XPHandler.getTotalExperience(p)));
								p.sendMessage(message);								
							}else {
								String sendermessage = Main.messages.getMessage("payxp-successfulsending");
								sendermessage = ChatColor.translateAlternateColorCodes('&', sendermessage);
								sendermessage = sendermessage.replace("%target%", target.getName());
								sendermessage = sendermessage.replace("%points%", String.valueOf(exp));
								p.sendMessage(sendermessage);

								String targetmessage = Main.messages.getMessage("payxp-successfulreceiving");
								targetmessage = ChatColor.translateAlternateColorCodes('&', targetmessage);
								targetmessage = targetmessage.replace("%sender%", p.getName());
								targetmessage = targetmessage.replace("%points%", String.valueOf(exp));
								target.sendMessage(targetmessage);
								
								XPHandler.setTotalExperience(p, XPHandler.getTotalExperience(p)-exp);
								XPHandler.setTotalExperience(target, XPHandler.getTotalExperience(target)+exp);
							}
						}
					}
			}
		}
		}
	}
		return true;
	}
	   public static boolean isInteger(String s) {
		      boolean isValidInteger = false;
		      try
		      {
		         Integer.parseInt(s);
		 
		         // s is a valid integer
		 
		         isValidInteger = true;
		      }
		      catch (NumberFormatException ex)
		      {
		         // s is not an integer
		      }
		 
		      return isValidInteger;
		   }
		}



