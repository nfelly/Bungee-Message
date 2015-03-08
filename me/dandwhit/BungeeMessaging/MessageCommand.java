package me.dandwhit.BungeeMessaging;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class MessageCommand extends Command {

	public MessageCommand() {
		super("msg", "bungee.message", "m", "message");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		
		if(args.length < 2) {
			sender.sendMessage(new TextComponent(ChatColor.RED + "Usage: /msg <player> <message>"));
			return;
		}
		
        ProxiedPlayer p = ProxyServer.getInstance().getPlayer(args[0]);
        
		if(p == null) {
			sender.sendMessage(new TextComponent(ChatColor.RED + "Sorry but I can't find the player " + args[0] + "!"));
			return;
		}
		
        StringBuilder msgBuilder = new StringBuilder();
        
        for (int i = 1; i < args.length; i++) {
                msgBuilder.append(args[i]).append(" ");
        }
        
        String msg = ChatColor.translateAlternateColorCodes('&', msgBuilder.toString().trim());
        
        p.sendMessage(new TextComponent(ChatColor.GRAY + "You have recieved the following message from " + ChatColor.GRAY + sender.getName() + ":"));
        p.sendMessage(new TextComponent(msg));
        sender.sendMessage(new TextComponent(ChatColor.GRAY + "You have sent the following message to " + ChatColor.GRAY + p.getName() + ":"));
        sender.sendMessage(new TextComponent(msg));
        
        if (BungeeMessage.lastMessage.containsKey(sender.getName())) {
        	BungeeMessage.lastMessage.remove(sender.getName());
        }
        BungeeMessage.lastMessage.put(sender.getName(), p.getName());

        if (BungeeMessage.lastMessage.containsKey(p.getName())) {
        	BungeeMessage.lastMessage.remove(p.getName());
        }
        BungeeMessage.lastMessage.put(p.getName(), sender.getName());

    }

	}
