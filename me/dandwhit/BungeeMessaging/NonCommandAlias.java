package me.dandwhit.BungeeMessaging;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class NonCommandAlias implements Listener {
	
	@EventHandler
	public void onChat (ChatEvent e) {
		ProxiedPlayer p = (ProxiedPlayer) e.getSender();
		String m = e.getMessage();
		if(m.startsWith("@")) {
			if(p.hasPermission("bungee.message")) {
				e.setCancelled(true);
				p.chat(m.replaceFirst("@", "/msg "));
			}
		}
	}

}
