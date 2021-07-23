package com.banyulsescouts.enchanting.util;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EnchantmentWrapper extends Enchantment {
    private final String name;
    private final int maxLvl;
    private final List<Material> enchantables;
    private final List<Enchantment> conflicts;
    private final boolean cursed;

    public EnchantmentWrapper(String namespace, int lvl, List<Material> enchantables, List<Enchantment> conflicts, boolean cursed) {
        super(NamespacedKey.minecraft(namespace.toLowerCase().replace(" ", "_")));
        this.name = namespace;
        this.maxLvl = lvl;
        this.cursed = cursed;
        if (!enchantables.isEmpty()) this.enchantables = enchantables;
        else this.enchantables = null;
        if (conflicts == null) this.conflicts = null;
        else if (!conflicts.isEmpty()) this.conflicts = conflicts;
        else this.conflicts = null;
    }

    @Override
    public boolean canEnchantItem(ItemStack arg0) {
        if (enchantables.isEmpty()) return true;
        if (enchantables.contains(arg0.getType())) return true;
        else return false;
    }

    @Override
    public boolean conflictsWith(Enchantment arg0) {
        if (conflicts == null || conflicts.isEmpty()) return false;
        if (conflicts.contains(arg0)) return true;
        return false;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public int getMaxLevel() {
        return maxLvl;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public boolean isCursed() {
        return cursed;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }
}
