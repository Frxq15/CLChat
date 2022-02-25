package me.frxq15.citylifechat.Managers;

import me.clip.placeholderapi.PlaceholderAPI;
import me.frxq15.citylifechat.CityLifeChat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatManager implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        e.setCancelled(true);
        String message = CityLifeChat.formatMsg("LOCAL_CHAT_FORMAT")
                .replace("%player%", p.getName()).replace("%chat%", e.getMessage());
        RadiusManager.getNearbyPlayers(p, CityLifeChat.getInstance().getConfig().getDouble("GLOBAL_CHAT_RADIUS")).forEach(r -> {
            String pmessage = PlaceholderAPI.setPlaceholders(p, message);
            Bukkit.broadcastMessage(pmessage);
        });
    }
}
