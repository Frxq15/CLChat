package me.frxq15.citylifechat;

import me.frxq15.citylifechat.Commands.YellCommand;
import me.frxq15.citylifechat.Managers.ChatManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class CityLifeChat extends JavaPlugin {
    private static CityLifeChat instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        registry();

    }
    void registry() {
        getCommand("yell").setExecutor(new YellCommand());
        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            Bukkit.getPluginManager().registerEvents(new ChatManager(), this);
        }
    }

    @Override
    public void onDisable() {
    }
    public static CityLifeChat getInstance() { return instance; }
    public static String colourize(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
    public static String formatMsg(String input) { return ChatColor.translateAlternateColorCodes('&', getInstance().getConfig().getString(input)); }
    public static void log(String text) { Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA+"[CLChat] "+text);}
}
