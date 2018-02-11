package me.chrisochs.xptransfer;

import org.bukkit.plugin.java.JavaPlugin;

import me.chrisochs.xptransfer.commands.MyXpCommand;
import me.chrisochs.xptransfer.commands.PayXpCommand;

public class Main extends JavaPlugin{
	public static Messages messages;
	
	public void onEnable() {
		loadMessages();
		registerCommands();
		
	}
	
	public void registerCommands() {
		getCommand("myxp").setExecutor(new MyXpCommand());
		getCommand("payxp").setExecutor(new PayXpCommand(this));
	}
	
	public void loadMessages() {
		messages = new Messages(this);
		
	}

}
