package com.banyulsescouts.enchanting;

import com.banyulsescouts.enchanting.util.EnchantHandler;
import com.banyulsescouts.enchanting.util.EnchantmentWrapper;
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
    ), null, false);
    public static final Enchantment SHIMMER = new EnchantmentWrapper("Shimmer", 1, EnchantHandler.combineMaterialLists(
            EnchantHandler.materialList("ALL")
    ), EnchantHandler.allEnchantments(), false);

    @Override
    public void onEnable() {
        if (!registered.contains(YEET)) registerEnchantment(YEET);
        if (!registered.contains(TELEPATHY)) registerEnchantment(TELEPATHY);
        if (!registered.contains(SHIMMER)) registerEnchantment(SHIMMER);

        //Bukkit.getPluginManager().registerEvents(new Yeet(), this);
        //Bukkit.getPluginManager().registerEvents(new Telepathy(), this);
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
