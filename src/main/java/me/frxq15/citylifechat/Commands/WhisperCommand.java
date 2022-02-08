package me.frxq15.citylifechat.Commands;

import me.frxq15.citylifechat.CityLifeChat;
import me.frxq15.citylifechat.Managers.RadiusManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WhisperCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            CityLifeChat.log("This command cannot be executed from console.");
            return true;
        }
        Player p = (Player) sender;
        if(!p.hasPermission("clchat.whisper")) {
            p.sendMessage(CityLifeChat.formatMsg("NO_PERMISSION"));
            return true;
        }
        if(args.length == 0) {
            p.sendMessage(CityLifeChat.colourize("&cUsage: /whisper <text>"));
        }
        final String message = this.getFinalArg(args, 0);
        RadiusManager.getNearbyPlayers(p, CityLifeChat.getInstance().getConfig().getDouble("WHISPER_CHAT_RADIUS")).forEach(r -> {
            r.sendMessage(CityLifeChat.formatMsg("WHISPER_CHAT_FORMAT").replace("%player%", p.getName()).replace("%chat%", message));
        });
        p.sendMessage(CityLifeChat.formatMsg("WHISPER_CHAT_FORMAT").replace("%player%", p.getName()).replace("%chat%", message));
        return true;
    }
    private String getFinalArg(final String[] args, final int start) {
        final StringBuilder bldr = new StringBuilder();
        for (int i = start; i < args.length; ++i) {
            if (i != start) {
                bldr.append(" ");
            }
            bldr.append(args[i]);
        }
        return bldr.toString();
    }
}
