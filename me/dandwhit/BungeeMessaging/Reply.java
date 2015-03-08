package me.dandwhit.BungeeMessaging;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Reply extends Command {

	public Reply() {
		super("reply", "bungee.reply", "r");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(!(BungeeMessage.lastMessage.containsKey(sender.getName()))) {
			sender.sendMessage(new TextComponent(ChatColor.RED + "You do not have anyone to reply to!"));
			return;
		}
		
		ProxiedPlayer target = ProxyServer.getInstance().getPlayer(BungeeMessage.lastMessage.get(sender.getName()));
		
		if(target == null) {
			sender.sendMessage(new TextComponent(ChatColor.RED + BungeeMessage.lastMessage.get(sender.getName()) + " is not online!"));
			return;
		}
		
        String message = "";
        for (int i = 0; i< args.length; i++) {
            message = message + args[i] + " ";
        }

        message = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', message.trim()));
        
        target.sendMessage(new TextComponent(ChatColor.GRAY + "You have recieved the following message from " + ChatColor.GRAY + sender.getName() + ":"));
        target.sendMessage(new TextComponent(message));
        sender.sendMessage(new TextComponent(ChatColor.GRAY + "You have sent the following message to " + ChatColor.GRAY + target.getName() + ":"));
        sender.sendMessage(new TextComponent(message));
        
        if (BungeeMessage.lastMessage.containsKey(sender.getName())) {
        	BungeeMessage.lastMessage.remove(sender.getName());
        }
        BungeeMessage.lastMessage.put(sender.getName(), target.getName());

        if (BungeeMessage.lastMessage.containsKey(target.getName())) {
        	BungeeMessage.lastMessage.remove(target.getName());
        }
        BungeeMessage.lastMessage.put(target.getName(), sender.getName());

	}

}
