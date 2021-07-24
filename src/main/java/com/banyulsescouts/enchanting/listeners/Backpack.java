package com.banyulsescouts.enchanting.listeners;

import com.banyulsescouts.enchanting.Main;
import org.bukkit.block.EnderChest;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Backpack implements Listener {

    @EventHandler
    public void onStorageRightClick(PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) return;
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item == null) return;
        if (!(item instanceof EnderChest) || !(item instanceof ShulkerBox)) return;

        if (item.getItemMeta().hasEnchant(Main.BACKPACK)) {
            if (item instanceof ShulkerBox box) {
                Inventory inv = box.getInventory();
                player.openInventory(inv);
            }
            else if (item instanceof EnderChest chest) {
                player.openInventory(player.getEnderChest());
            }
        }
    }
}
