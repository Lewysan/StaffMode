package us.stellarsquad.staffmode;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.UUID;

public class StellarSource {

    public static Server server = Bukkit.getServer();
    public static BukkitScheduler sh = Bukkit.getServer().getScheduler();
    public static ArrayList<Player> staffmode = new ArrayList<>();
    public static ArrayList<Player> vanish = new ArrayList<>();
    public static ArrayList<UUID> frozen = new ArrayList<>();

    public static String c(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
    public void globalMessage(String msg){
        server.broadcastMessage(c(msg));
    }
}
