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
        
        boolean allowColoursInMessages = BungeeMessage.allowColoursInMessages;
        
        String senderTopLine = BungeeMessage.senderTopLine;
        String senderBottomLine = BungeeMessage.senderBottomLine;
        
        String receiverTopLine = BungeeMessage.receiverTopLine;
        String receiverBottomLine = BungeeMessage.receiverBottomLine;        
        
        senderTopLine = senderTopLine.replace("%sender%", target.getName());
        senderTopLine = senderTopLine.replace("%reciever%", sender.getName());
        senderBottomLine = senderBottomLine.replace("%message%", message);
        
        receiverTopLine = receiverTopLine.replace("%sender%", target.getName());
        receiverTopLine = receiverTopLine.replace("%reciever%", sender.getName());
        receiverBottomLine = receiverBottomLine.replace("%message%", message);
        
        if (allowColoursInMessages == true) {
        	ChatColor.translateAlternateColorCodes('&', senderTopLine);
        	ChatColor.translateAlternateColorCodes('&', senderBottomLine);
        	
        	ChatColor.translateAlternateColorCodes('&', receiverTopLine);
        	ChatColor.translateAlternateColorCodes('&', receiverBottomLine);
        } else {
        	ChatColor.translateAlternateColorCodes('&', senderTopLine.trim());
        	ChatColor.translateAlternateColorCodes('&', senderBottomLine.trim());
        	
        	ChatColor.translateAlternateColorCodes('&', receiverTopLine.trim());
        	ChatColor.translateAlternateColorCodes('&', receiverBottomLine.trim());
        }
       
        target.sendMessage(new TextComponent(receiverTopLine));
        target.sendMessage(new TextComponent(receiverBottomLine));
        sender.sendMessage(new TextComponent(senderTopLine));
        sender.sendMessage(new TextComponent(senderBottomLine));
        
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
