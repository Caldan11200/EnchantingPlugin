package com.banyulsescouts.enchanting;

import com.banyulsescouts.enchanting.listeners.AutoSmelt;
import com.banyulsescouts.enchanting.listeners.Backpack;
import com.banyulsescouts.enchanting.listeners.Telepathy;
import com.banyulsescouts.enchanting.listeners.Yeet;
import com.banyulsescouts.enchanting.util.AnvilCrafting;
import com.banyulsescouts.enchanting.util.EnchantHandler;
import com.banyulsescouts.enchanting.util.EnchantmentWrapper;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Main extends JavaPlugin {

    public static List<Enchantment> registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList());

    public static final Enchantment YEET = new EnchantmentWrapper("Yeet", 5, EnchantHandler.combineMaterialLists(
            EnchantHandler.materialList("AXES"),
            EnchantHandler.materialList("RODS"),
            EnchantHandler.materialList("SWORDS")
    ), null, false);
    public static final Enchantment TELEPATHY = new EnchantmentWrapper("Telepathy", 2, EnchantHandler.combineMaterialLists(
            EnchantHandler.materialList("AXES"),
            EnchantHandler.materialList("BOW"),
            EnchantHandler.materialList("CROSSBOW"),
            EnchantHandler.materialList("PICKAXES"),
            EnchantHandler.materialList("SHOVELS"),
            EnchantHandler.materialList("SWORDS"),
            EnchantHandler.materialList("TRIDENT")
    ), EnchantHandler.enchantmentsList(Main.AUTO_SMELT), false);
    public static final Enchantment SHIMMER = new EnchantmentWrapper("Shimmer", 1, EnchantHandler.combineMaterialLists(
            EnchantHandler.materialList("ALL")
    ), EnchantHandler.allEnchantments(), false);
    public static final Enchantment AUTO_SMELT = new EnchantmentWrapper("Auto Smelt", 1, EnchantHandler.combineMaterialLists(
            EnchantHandler.materialList("AXES"),
            EnchantHandler.materialList("PICKAXES"),
            EnchantHandler.materialList("SHOVELS")
    ), EnchantHandler.enchantmentsList(Enchantment.LOOT_BONUS_BLOCKS, Enchantment.SILK_TOUCH, Main.TELEPATHY), false);
    public static final Enchantment BACKPACK = new EnchantmentWrapper("Backpack", 1, EnchantHandler.combineMaterialLists(
            EnchantHandler.materialList("SHULKER_BOXES"),
            EnchantHandler.materialList("ENDER_CHEST")
    ), null, false);

    @Override
    public void onEnable() {
        if (!registered.contains(YEET)) registerEnchantment(YEET);
        if (!registered.contains(TELEPATHY)) registerEnchantment(TELEPATHY);
        if (!registered.contains(SHIMMER)) registerEnchantment(SHIMMER);
        if (!registered.contains(AUTO_SMELT)) registerEnchantment(AUTO_SMELT);
        if (!registered.contains(BACKPACK)) registerEnchantment(BACKPACK);

        Bukkit.getPluginManager().registerEvents(new Yeet(), this);
        Bukkit.getPluginManager().registerEvents(new Telepathy(), this);
        Bukkit.getPluginManager().registerEvents(new AutoSmelt(), this);
        Bukkit.getPluginManager().registerEvents(new Backpack(), this);

        Bukkit.getPluginManager().registerEvents(new AnvilCrafting(), this);
    }

    @Override
    public void onDisable() {

    }

    public static void registerEnchantment(Enchantment enchantment) {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
