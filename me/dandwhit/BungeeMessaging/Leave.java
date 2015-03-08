package me.dandwhit.BungeeMessaging;

import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Leave implements Listener {
	
	@EventHandler
	public void onQuit (PlayerDisconnectEvent e) {
		String name = e.getPlayer().getName();
		if(BungeeMessage.lastMessage.containsKey(name)) {
			BungeeMessage.lastMessage.remove(name);
		}
	}

}
