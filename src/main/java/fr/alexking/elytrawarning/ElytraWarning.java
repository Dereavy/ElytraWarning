package fr.alexking.elytrawarning;

import fr.alexking.elytrawarning.events.PlayerItemDamage;
import fr.alexking.elytrawarning.events.PlayerMove;
import org.bukkit.plugin.java.JavaPlugin;

public final class ElytraWarning extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
    // Register Config:
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new PlayerItemDamage(), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
