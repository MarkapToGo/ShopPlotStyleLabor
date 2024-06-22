package de.stylelabor.dev.shopplotstylelabor;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SetupCommandHandler {

    public void handleSetup(Player player) {
        ItemStack setupTool = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta meta = setupTool.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§6Setup Tool");
            meta.setUnbreakable(true);
            setupTool.setItemMeta(meta);
        }

        if (player.getInventory().contains(setupTool)) {
            player.getInventory().remove(setupTool);
            player.sendMessage("§cSetup mode turned off.");
        } else {
            player.getInventory().addItem(setupTool);
            player.sendMessage("§aSetup mode turned on.");
        }
    }
}