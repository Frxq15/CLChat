package me.frxq15.citylifechat.Managers;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class RadiusManager {
    public static ArrayList<Player> getNearbyPlayers(Player p, double range){
        ArrayList<Player> nearby = new ArrayList<Player>();
        for (Entity e : p.getNearbyEntities(range, range, range)){
            if (e instanceof Player){
                nearby.add((Player) e);
            }
        }
        return nearby;
    }
}
