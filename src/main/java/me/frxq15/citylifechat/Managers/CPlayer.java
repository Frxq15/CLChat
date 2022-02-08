package me.frxq15.citylifechat.Managers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CPlayer {
    private final static Map<UUID, CPlayer> players = new HashMap<>();
    private final UUID uuid;
    private String chattype = "none";

    public CPlayer(UUID uuid) {
        this.uuid = uuid;
        players.put(uuid, this);
    }

    public void setChat(String type) {
        this.chattype = type;
    }
    public String getChatType() { return chattype; }

    public static CPlayer getPlayerData(UUID uuid) {
        if (!players.containsKey(uuid)) {
            CPlayer cPlayer = new CPlayer(uuid);
        }
        return players.get(uuid);
    }
    public static void removePlayerData(UUID uuid) { players.remove(uuid); }
    public static Map<UUID, CPlayer> getAllPlayerData() {
        return players;
    }
}
