package us.stellarsquad.staffmode.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import us.stellarsquad.staffmode.StellarCore;
import us.stellarsquad.staffmode.StellarSource;

public class PlayerListener extends StellarSource implements Listener {

    @EventHandler
    public void frozenMoves(PlayerMoveEvent e){
        if (frozen.contains(e.getPlayer().getUniqueId())){
            e.setTo(e.getFrom());
        }
    }
    @EventHandler
    public void staffModeDamageNo(EntityDamageEvent e){
        if (staffmode.contains((Player) e.getEntity())){
            if (e.getCause() != EntityDamageEvent.DamageCause.VOID){
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void staffModeNoDrop(PlayerDropItemEvent e){
        if (staffmode.contains(e.getPlayer())){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void whenFrozenQuits(PlayerQuitEvent e){
        if (frozen.contains(e.getPlayer().getUniqueId())){
            for (Player staff : server.getOnlinePlayers()){
                if (staff.hasPermission("essentials.staff")){
                    globalMessage("--------------");
                    globalMessage(c("&c&lALERTA: &eEl jugador &b")+e.getPlayer().getName()+c(" &ese ha desconectado mientras estaba siendo revisado."));
                    globalMessage("--------------");
                }
            }
        }
    }
}
