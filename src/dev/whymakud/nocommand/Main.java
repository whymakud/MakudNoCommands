package dev.whymakud.nocommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements Listener {
	   public static Main instance;
	   public static FileConfiguration cfg;
	   public static ConsoleCommandSender console = Bukkit.getConsoleSender();
	
	public void onEnable() {
		
		console.sendMessage(ChatColor.translateAlternateColorCodes('&', new String(new byte[]{38, 55, 91, 38, 98, 77, 97, 107, 117, 100, 78, 111, 67, 111, 109, 109, 97, 110, 100, 115, 38, 55, 93, 32, 38, 102, 98, 121, 32, 38, 99, 119, 104, 121, 109, 97, 107, 117, 100, 46, 103, 105, 116, 104, 117, 98, 46, 105, 111, 32, 38, 102, 101, 110, 97, 98, 108, 101, 100, 33})));
		getServer().getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
	}	
	
	@EventHandler
    public void disableConsoleCmd(final ServerCommandEvent e) {
		getConfig().getStringList("cmd-blocker.commands").forEach((msg) -> {
		      if (e.getCommand().toString().startsWith(msg)) {
		    	  console.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("cmd-blocker.message")));
		         e.setCancelled(true);
		      }
			});
	}

	@EventHandler
	public void disableChatCmd(PlayerCommandPreprocessEvent e) {
		getConfig().getStringList("cmd-blocker.commands").forEach((msg) -> {
	      if (e.getMessage().startsWith(msg)) {
	    	  e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("cmd-blocker.message")));
	         e.setCancelled(true);
	      }
		});
	}
	
	@EventHandler
	public void disableTabComplete(TabCompleteEvent e) {
		if (getConfig().getBoolean("anti-tab")) {
			e.setCancelled(true);
		}
	}
}