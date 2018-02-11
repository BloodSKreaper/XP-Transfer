package me.chrisochs.xptransfer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.plugin.Plugin;

public class Messages {
	private HashMap<String, String> messages = new HashMap<String,String>();
	private Plugin plugin;
	
	public Messages(Plugin pl) {
		plugin = pl;
		initializeGerman();
		loadConfig();
		saveConfig();
		
	}
	
	private void initializeGerman() {
		messages.put("myxp-message", "§aDu hast momentan §6%points% Erfahrungspunkte §a(§6Level %level%§a) und benötigst §6%pointsToNextLevel% Erfahrungspunkte §azum nächsten Level.");
		messages.put("payxp-toofewarguments", "§cDu musst einen Empfänger und eine Anzahl angeben! §b/payxp <NAME> <ERFAHRUNGSPUNKTE>");
		messages.put("payxp-toomanyarguments", "§cDu hast zu viel Argumente angegeben! §b/payxp <NAME> <ERFAHRUNGSPUNKTE>");
		messages.put("payxp-playernotonline", "§cDer Empfänger §6%player% §cist nicht online!");
		messages.put("payxp-nonumber", "§cDeine Eingabe §6%input% §cist keine Ganzzahl!");
		messages.put("payxp-negativeornull", "§cDu musst mindestens einen Erfahrungspunkt überweisen!");
		messages.put("payxp-notenaughtexp", "§cDu hast nicht genug Erfahrungspunkte! Deine Punkte: §6%points%");
		messages.put("payxp-successfulsending", "§aDu hast §6%target% §aerfolgreich §6%points% Erfahrungspunkte §aüberwiesen.");
		messages.put("payxp-successfulreceiving", "§aDu hast von §6%sender% §6%points% Erfahrungspunkte §aerhalten.");		
	}
	
	public String getMessage(String s) {
		if(messages.containsKey(s)) {
			return messages.get(s);
		}else {
			return s;
		}
	}
	
	public void setMessage(String key, String value) {
		messages.put(key, value);
	}
	
	private void loadConfig() {
		if(plugin.getConfig().getKeys(false).size() > 0) {
			Set<String> rawpath = new HashSet<String>();
			for(String s :plugin.getConfig().getKeys(true)) {
				s = s.replace("messages", "");
				s = s.replace(".", "");
				if(s.length()>0) {
					rawpath.add(s);
				}
			}
			
			for (String key: rawpath) {
				messages.put(key, plugin.getConfig().getString("messages."+key));
			}
			
		}
	}
	private void saveConfig() {
		if(plugin.getConfig().getKeys(false).size() == 0) {
			for(String s : messages.keySet()) {
				plugin.getConfig().set("messages."+s, messages.get(s));
			}
			plugin.saveConfig();
		}
	}

}
