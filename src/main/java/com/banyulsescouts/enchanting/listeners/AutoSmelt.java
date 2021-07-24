package com.banyulsescouts.enchanting.listeners;

import com.banyulsescouts.enchanting.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import java.util.Iterator;

public class AutoSmelt implements Listener {

    @EventHandler
    public void onBlockMined(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (!item.hasItemMeta()) return;
        Block block = event.getBlock();

        if (item.getItemMeta().hasEnchant(Main.AUTO_SMELT)) {
            boolean smelted = false;
            for (ItemStack blockItem : block.getDrops()) {
                ItemStack result = blockItem;
                Iterator<Recipe> iter = Bukkit.recipeIterator();
                while (iter.hasNext()) {
                    Recipe recipe = iter.next();
                    if (!(recipe instanceof FurnaceRecipe)) continue;
                    if (((FurnaceRecipe) recipe).getInput().getType() != blockItem.getType()) continue;
                    result = recipe.getResult();
                    result.setAmount(blockItem.getAmount());
                    smelted = true;
                    break;
                }
                result.setAmount(1);
                block.getWorld().dropItemNaturally(block.getLocation(), result);
            }
            block.setType(Material.AIR);
            if (smelted) block.getWorld().spawnParticle(Particle.FLAME, block.getLocation().add(0.5, 0.5, 0.5), 15, 0.4, 0.4, 0.4, 0.03);
        }
    }
}
