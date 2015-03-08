package me.dandwhit.BungeeMessaging;


import me.dandwhit.BungeeMessaging.MessageCommand;
import me.dandwhit.BungeeMessaging.Reply;
import me.dandwhit.BungeeMessaging.Leave;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.HashMap;

public class BungeeMessage extends Plugin {

	public static HashMap<String, String> lastMessage = new HashMap<String, String>();

    public void onEnable() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new MessageCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Reply());
        ProxyServer.getInstance().getPluginManager().registerListener(this, new Leave());
        ProxyServer.getInstance().getPluginManager().registerListener(this, new NonCommandAlias());
    }


}
