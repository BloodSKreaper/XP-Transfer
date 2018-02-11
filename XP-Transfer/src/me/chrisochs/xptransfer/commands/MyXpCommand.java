package me.chrisochs.xptransfer.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.chrisochs.xptransfer.Main;
import me.chrisochs.xptransfer.XPHandler;
import net.md_5.bungee.api.ChatColor;

public class MyXpCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			//§aDu hast momentan §6%points% Erfahrungspunkte §a(§6Level %level%§a) und benötigst §6%pointsToNextLevel% Erfahrungspunkte §azum nächsten Level.
			int points = XPHandler.getTotalExperience(p);
			int level = p.getLevel();
			int needed = XPHandler.getExpUntilNextLevel(p);
			String message = Main.messages.getMessage("myxp-message");
			message = message.replace("%points%", String.valueOf(points));
			message = message.replace("%level%", String.valueOf(level));
			message = message.replace("%pointsToNextLevel%", String.valueOf(needed));
			ChatColor.translateAlternateColorCodes('&', message);
			p.sendMessage(message);
		}
		// TODO Auto-generated method stub
		return true;
	}


}
