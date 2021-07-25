package com.banyulsescouts.enchanting.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnvilCrafting implements Listener {

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        if (event.getInventory().getItem(0) != null && event.getInventory().getItem(1) != null) {
            ItemStack result = new ItemStack(event.getInventory().getItem(0));

            if (event.getInventory().getItem(1).getType() == Material.ENCHANTED_BOOK) {
                EnchantmentStorageMeta bookmeta = (EnchantmentStorageMeta) event.getInventory().getItem(1).getItemMeta();
                Map<Enchantment, Integer> enchantments = bookmeta.getStoredEnchants();
                List<String> lore = new ArrayList<>();
                for (Map.Entry<Enchantment, Integer> enchants : enchantments.entrySet()) {
                    if (!enchants.getKey().canEnchantItem(event.getInventory().getItem(0))) {
                        event.setResult(null);
                        return;
                    }
                    if (enchants.getKey() instanceof EnchantmentWrapper custom) lore.add(ChatColor.GRAY+custom.getName()+" "+EnchantHandler.romanNumeral(enchants.getValue()));
                }
                if (event.getInventory().getRepairCost() < 1) event.getInventory().setRepairCost(1);
                if (!lore.isEmpty()) {
                    ItemMeta meta = result.getItemMeta();
                    if (meta != null) meta.setLore(lore);
                    result.setItemMeta(meta);
                }
                result.addUnsafeEnchantments(enchantments);
                event.setResult(result);
            }
        }
    }
}
