package com.banyulsescouts.enchanting.listeners;

import com.banyulsescouts.enchanting.Main;
import com.banyulsescouts.enchanting.util.EnchantHandler;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Telepathy implements Listener {

    @EventHandler
    public void onBlockMined(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (!item.hasItemMeta()) return;
        Block block = event.getBlock();

        if (item.getItemMeta().hasEnchant(Main.TELEPATHY)) {
            Collection<ItemStack> drops = block.getDrops();
            addItemsToPlayer(player, drops);
            event.setDropItems(false);
            block.getWorld().spawnParticle(Particle.REVERSE_PORTAL, block.getLocation().add(0.5,0.5,0.5), 15, 0.4, 0.4, 0.4,0.03);

            if (EnchantHandler.getEnchantmentLevel(item, Main.TELEPATHY) > 1) {
                player.giveExp(event.getExpToDrop());
                event.setExpToDrop(0);
            }
        }
    }

    @EventHandler
    public void onEntityKilled(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        Player player = entity.getKiller();
        if (player == null) return;
        ItemStack item = player.getInventory().getItemInMainHand();
        if (!item.hasItemMeta()) return;

        if (item.getItemMeta().hasEnchant(Main.TELEPATHY)) {
            List<ItemStack> drops = event.getDrops();
            addItemsToPlayer(player, drops);
            event.getDrops().clear();

            if (EnchantHandler.getEnchantmentLevel(item, Main.TELEPATHY) > 1) {
                player.giveExp(event.getDroppedExp());
                event.setDroppedExp(0);
            }
        }
    }

    public void addItemsToPlayer(Player player, Collection<ItemStack> items) {
        for (ItemStack item : items) {
            HashMap<Integer, ItemStack> missedItems = player.getInventory().addItem(item);
            if (!missedItems.isEmpty()) {
                for (Map.Entry<Integer, ItemStack> i : missedItems.entrySet()) {
                    player.getWorld().dropItem(player.getLocation(), i.getValue());
                }
            }
        }
    }
}
