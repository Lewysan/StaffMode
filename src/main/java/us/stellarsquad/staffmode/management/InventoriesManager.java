package us.stellarsquad.staffmode.management;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import us.stellarsquad.staffmode.StellarSource;

import java.util.ArrayList;
import java.util.List;

public class InventoriesManager extends StellarSource {
    // directamente borra su inventario y se le da el nuevo
    // luego saved inventory
    public static void staffModeON(Player p){
        giveInvisibility(p);
        p.setFoodLevel(20);
        p.setHealth(20);
        p.setGameMode(GameMode.CREATIVE);
        p.getInventory().clear();
        ItemStack wand = new ItemStack(Material.WOOD_AXE, 1);
        ItemMeta wandm = wand.getItemMeta();
        wandm.setDisplayName(c("&cWorldEdit"));
        List<String> lore = new ArrayList<>();
        lore.add(c("&7Hacha magica?"));
        wandm.setLore(lore);
        wand.setItemMeta(wandm);

        ItemStack stone = new ItemStack(Material.STONE, 1);
        ItemMeta stonem = stone.getItemMeta();
        stonem.setDisplayName(c("&ePiedra"));
        List<String> lores = new ArrayList<>();
        lores.add(c("&7Utilidad de todo constructor"));
        stonem.setLore(lores);
        stone.setItemMeta(stonem);

        ItemStack pico = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        ItemMeta picometa = pico.getItemMeta();
        picometa.setDisplayName(c("&dSuper-Pickaxe"));
        List<String> lorep = new ArrayList<>();
        lorep.add(c("&7Utiliza para hacer area 5 XD"));
        picometa.setLore(lorep);
        pico.setItemMeta(picometa);

        ItemStack compass = new ItemStack(Material.COMPASS, 1);
        ItemMeta metacompass = compass.getItemMeta();
        metacompass.setDisplayName(c("&bTeletransportador"));
        List<String> lorecomp = new ArrayList<>();
        lorecomp.add(c("&7(Click Izquierdo)"));
        metacompass.setLore(lorecomp);
        compass.setItemMeta(metacompass);

        p.getInventory().setItem(0, wand);
        p.getInventory().setItem(1, pico);
        p.getInventory().setItem(7, compass);
        p.getInventory().setItem(8, stone);
    }

    public static void giveInvisibility(Player p) {
        PotionEffect invisible = new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 3);
        p.addPotionEffect(invisible);
    }

    public static void staffModeOFF(Player p) {
        p.removePotionEffect(PotionEffectType.INVISIBILITY);
        p.getInventory().clear();
        p.setGameMode(GameMode.SURVIVAL);
    }
}
