package me.frxq15.citylifechat.Commands;

import me.clip.placeholderapi.PlaceholderAPI;
import me.frxq15.citylifechat.CityLifeChat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class globalCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            CityLifeChat.log("This command cannot be executed from console.");
            return true;
        }
        Player p = (Player) sender;
        if(!p.hasPermission("clchat.global")) {
            p.sendMessage(CityLifeChat.formatMsg("NO_PERMISSION"));
            return true;
        }
        if(args.length == 0) {
            p.sendMessage(CityLifeChat.colourize("&cUsage: /global <text>"));
        }
        final String message = this.getFinalArg(args, 0);
        String pmessage = PlaceholderAPI.setPlaceholders(p, CityLifeChat.formatMsg("GLOBAL_CHAT_FORMAT").replace("%player%", p.getName()).replace("%chat%", message));
        Bukkit.broadcastMessage(CityLifeChat.colourize(pmessage));
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
