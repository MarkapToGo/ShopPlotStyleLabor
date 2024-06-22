package de.stylelabor.dev.shopplotstylelabor;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin logger
        getLogger().info("####################################");
        getLogger().info("##     StyleLabor Shop Plots      ##");
        getLogger().info("##      coded by MarkapStyle      ##");
        getLogger().info("##     << dc.stylelabor.de >>     ##");
        getLogger().info("####################################");

        // Plugin startup logic
        StyleLaborShopsCommand styleLaborShopsCommand = new StyleLaborShopsCommand();
        Objects.requireNonNull(this.getCommand("stylelaborshops")).setExecutor(styleLaborShopsCommand);
        Objects.requireNonNull(this.getCommand("stylelaborshops")).setTabCompleter(styleLaborShopsCommand);

        // Register the listener
        getServer().getPluginManager().registerEvents(new SetupToolListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}