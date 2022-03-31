package us.stellarsquad.staffmode.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import us.stellarsquad.staffmode.StellarCore;
import us.stellarsquad.staffmode.StellarSource;
import us.stellarsquad.staffmode.management.InventoriesManager;
import us.stellarsquad.staffmode.runnables.PlayerRunnables;

public class PersonalCommands extends StellarSource implements CommandExecutor {

    protected final StellarCore core;
    public PersonalCommands(StellarCore core){
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arg) {
        int args = arg.length;
        if (!(sender instanceof Player)){
            sender.sendMessage(c("&cNo tienes permiso para este comando, solo jugadores."));
            return true;
        }
        Player p = (Player) sender;
        if (!p.hasPermission("essentials.staff")){
            p.sendMessage(c("&cNo tienes permiso para este comando."));
            return true;
        }
        if (command.getName().equalsIgnoreCase("staff")){
            if (args == 0) {
                if (!staffmode.contains(p)) {
                    staffmode.add(p);
                    InventoriesManager.staffModeON(p);
                    p.sendMessage(c("&7Modo staff &aactivado"));
                    return true;
                }
                staffmode.remove(p);
                InventoriesManager.staffModeOFF(p);
                p.sendMessage(c("&7Modo staff &cdesactivado"));
            }
        }
        if (command.getName().equalsIgnoreCase("sfreeze")){
            if (args == 0){
                p.sendMessage(c("&cDebes especificar un jugador."));
                return true;
            }
            Player target = server.getPlayerExact(arg[0]);
            if (target == null){
                p.sendMessage(c("&cEste jugador no existe."));
                return true;
            }
            if (!frozen.contains(target.getUniqueId())) {
                frozen.add(target.getUniqueId());
                PlayerRunnables.frozenMessage(target);
                return true;
            }
            frozen.remove(target.getUniqueId());
            target.sendMessage(c("&8&m-&f &a¡Ya no estás siendo revisado!"));
            PlayerRunnables.removeFrozenMessage();
        }
        if (command.getName().equalsIgnoreCase("svanish")){
            if (args == 0){
                if (!vanish.contains(p)){
                    for (Player online : server.getOnlinePlayers()){
                        online.hidePlayer(p);
                    }
                    vanish.add(p);
                    p.sendMessage(c("&7Vanish: &aON"));
                    InventoriesManager.giveInvisibility(p);
                    return true;
                }
                for (Player online : server.getOnlinePlayers()){
                    online.showPlayer(p);
                }
                vanish.remove(p);
                p.removePotionEffect(PotionEffectType.INVISIBILITY);
                p.sendMessage(c("&7Vanish: &cOFF"));
            }
        }
        return false;
    }
}
