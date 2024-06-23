package de.stylelabor.dev.shopplotstylelabor;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SetupToolListener implements Listener {

    private static final Logger LOGGER = Logger.getLogger(SetupToolListener.class.getName());

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        // Check if the player is an operator and is holding the setup tool
        if (player.isOp() && itemInHand.getType() == Material.DIAMOND_AXE && itemInHand.getItemMeta() != null && "§6Setup Tool".equals(itemInHand.getItemMeta().getDisplayName())) {
            switch (event.getAction()) {
                case LEFT_CLICK_AIR:
                case LEFT_CLICK_BLOCK:
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.sendMessage("§aPos 1 set");
                            savePosition(player.getLocation(), "Pos1");
                        }
                    }.runTaskLater(Main.getPlugin(Main.class), 10L);
                    event.setCancelled(true);
                    break;
                case RIGHT_CLICK_AIR:
                case RIGHT_CLICK_BLOCK:
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.sendMessage("§aPos 2 set");
                            savePosition(player.getLocation(), "Pos2");
                        }
                    }.runTaskLater(Main.getPlugin(Main.class), 10L);
                    event.setCancelled(true);
                    break;
            }
        }
    }

    private void savePosition(Location location, String posName) {
        File dataFolder = Main.getPlugin(Main.class).getDataFolder();
        if (!dataFolder.exists()) {
            boolean success = dataFolder.mkdir();
            if (!success) {
                LOGGER.log(Level.SEVERE, "Failed to create data directory");
                return;
            }
        }
        File file = new File(dataFolder, "positions.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        config.set(posName + ".x", location.getX());
        config.set(posName + ".y", location.getY());
        config.set(posName + ".z", location.getZ());

        try {
            config.save(file);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An error occurred while saving the position", e);
        }
    }
}