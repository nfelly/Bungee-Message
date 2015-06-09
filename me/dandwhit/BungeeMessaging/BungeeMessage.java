package me.dandwhit.BungeeMessaging;


import me.dandwhit.BungeeMessaging.MessageCommand;
import me.dandwhit.BungeeMessaging.Reply;
import me.dandwhit.BungeeMessaging.Leave;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

public class BungeeMessage extends Plugin {

	public static HashMap<String, String> lastMessage = new HashMap<String, String>();

    @SuppressWarnings("unused")
	public void onEnable() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new MessageCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Reply());
        ProxyServer.getInstance().getPluginManager().registerListener(this, new Leave());
        ProxyServer.getInstance().getPluginManager().registerListener(this, new NonCommandAlias());
        
        if (!getDataFolder().exists())
            getDataFolder().mkdir();

        File file = new File(getDataFolder(), "config.yml");

        if (!file.exists()) {
            try {
				Files.copy(getResourceAsStream("config.yml"), file.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
        try {
        	Configuration configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }

    public static boolean allowColoursInMessages = jline.internal.Configuration.getBoolean("settings.global.allowColoursInMessages", true);
    
    public static String senderTopLine = jline.internal.Configuration.getString("settings.sender.topMessage", "&a%sender% &7-> &a%reciever%:");
    public static String senderBottomLine = jline.internal.Configuration.getString("settings.sender.bottomMessage", "%message%");
    
    public static String receiverTopLine = jline.internal.Configuration.getString("settings.receiver.topMessage", "&a%sender% &7-> &a%reciever%:");
    public static String receiverBottomLine = jline.internal.Configuration.getString("settings.receiver.bottomMessage", "%message%");

}
