package us.stellarsquad.staffmode.runnables;

import org.bukkit.entity.Player;
import us.stellarsquad.staffmode.StellarSource;

import static us.stellarsquad.staffmode.StellarCore.core;

public class PlayerRunnables extends StellarSource {

    public static int taskFrozen;

    public static void frozenMessage(Player p){
        taskFrozen = sh.scheduleSyncRepeatingTask(core, () -> {
            if (!frozen.contains(p.getUniqueId())) return;
            p.sendMessage(c("&8&m------------------------------"));
            p.sendMessage(c("&eEstás siendo revisado por un staff, no te desconectes.."));
            p.sendMessage(c("&8&m------------------------------"));
        },0,300);
    }
    public static void removeFrozenMessage(){
        sh.cancelTask(taskFrozen);
    }
}
