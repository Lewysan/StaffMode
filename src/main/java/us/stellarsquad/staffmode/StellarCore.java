package us.stellarsquad.staffmode;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import us.stellarsquad.staffmode.commands.PersonalCommands;
import us.stellarsquad.staffmode.listener.PlayerListener;
import us.stellarsquad.staffmode.management.ConfigManager;

public final class StellarCore extends JavaPlugin {

    public static StellarCore core;
    public static ConfigManager settings = ConfigManager.getInstance();
    private final PluginManager pm = Bukkit.getServer().getPluginManager();

    @Override
    public void onEnable() {
        core = this;
        this.registerConfigManager();
        this.registerCommands();
        this.registerListeners();
    }

    private void registerListeners() {
        this.pm.registerEvents(new PlayerListener(), this);
    }
    private void registerConfigManager(){
        settings.setup(this);
    }
    private void registerCommands(){
        this.getCommand("staff").setExecutor(new PersonalCommands(this));
        this.getCommand("sfreeze").setExecutor(new PersonalCommands(this));
        this.getCommand("svanish").setExecutor(new PersonalCommands(this));
    }
}
