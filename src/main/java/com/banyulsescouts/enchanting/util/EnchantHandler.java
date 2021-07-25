package com.banyulsescouts.enchanting.util;

import com.banyulsescouts.enchanting.Main;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EnchantHandler {

    @SafeVarargs
    public static List<Material> combineMaterialLists(List<Material>... lists) {
        List<Material> result = new ArrayList<>();
        for (List<Material> list : lists) {
            result.addAll(list);
        }
        return result;
    }

    public static List<Enchantment> enchantmentsList(Enchantment... enchants) {
        return new ArrayList<>(Arrays.asList(enchants));
    }

    public static List<Material> materialList(String type) {
        List<Material> result = new ArrayList<>();
        switch (type.toUpperCase()) {
            case "RODS":
                result.add(Material.BLAZE_ROD);
                result.add(Material.BONE);
                result.add(Material.END_ROD);
                result.add(Material.STICK);
                return result;
            case "SWORDS":
                result.add(Material.WOODEN_SWORD);
                result.add(Material.STONE_SWORD);
                result.add(Material.IRON_SWORD);
                result.add(Material.GOLDEN_SWORD);
                result.add(Material.DIAMOND_SWORD);
                result.add(Material.NETHERITE_SWORD);
                return result;
            case "AXES":
                result.add(Material.WOODEN_AXE);
                result.add(Material.STONE_AXE);
                result.add(Material.IRON_AXE);
                result.add(Material.GOLDEN_AXE);
                result.add(Material.DIAMOND_AXE);
                result.add(Material.NETHERITE_AXE);
                return result;
            case "PICKAXES":
                result.add(Material.WOODEN_PICKAXE);
                result.add(Material.STONE_PICKAXE);
                result.add(Material.IRON_PICKAXE);
                result.add(Material.GOLDEN_PICKAXE);
                result.add(Material.DIAMOND_PICKAXE);
                result.add(Material.NETHERITE_PICKAXE);
                return result;
            case "SHOVELS":
                result.add(Material.WOODEN_SHOVEL);
                result.add(Material.STONE_SHOVEL);
                result.add(Material.IRON_SHOVEL);
                result.add(Material.GOLDEN_SHOVEL);
                result.add(Material.DIAMOND_SHOVEL);
                result.add(Material.NETHERITE_SHOVEL);
                return result;
            case "BOW":
                result.add(Material.BOW);
                return result;
            case "CROSSBOW":
                result.add(Material.CROSSBOW);
                return result;
            case "TRIDENT":
                result.add(Material.TRIDENT);
                return result;
            case "ALL":
                result.add(Material.AIR);
                return result;
            case "ENDER_CHEST":
                result.add(Material.ENDER_CHEST);
                return result;
            case "SHULKER_BOXES":
                result.add(Material.SHULKER_BOX);
                result.add(Material.BLACK_SHULKER_BOX);
                result.add(Material.BLUE_SHULKER_BOX);
                result.add(Material.BROWN_SHULKER_BOX);
                result.add(Material.CYAN_SHULKER_BOX);
                result.add(Material.GRAY_SHULKER_BOX);
                result.add(Material.GREEN_SHULKER_BOX);
                result.add(Material.LIGHT_BLUE_SHULKER_BOX);
                result.add(Material.LIGHT_GRAY_SHULKER_BOX);
                result.add(Material.LIME_SHULKER_BOX);
                result.add(Material.MAGENTA_SHULKER_BOX);
                result.add(Material.ORANGE_SHULKER_BOX);
                result.add(Material.PINK_SHULKER_BOX);
                result.add(Material.PURPLE_SHULKER_BOX);
                result.add(Material.RED_SHULKER_BOX);
                result.add(Material.WHITE_SHULKER_BOX);
                result.add(Material.YELLOW_SHULKER_BOX);
                return result;
            default: return null;
        }
    }

    public static List<Enchantment> allEnchantments() {
        List<Enchantment> result = new ArrayList<>();
        result.addAll(Arrays.asList(Enchantment.values()));
        result.addAll(Main.registered);
        return result;
    }

    public static int getEnchantmentLevel(ItemStack item, Enchantment enchant) {
        Map<Enchantment, Integer> enchantments = item.getEnchantments();
        for (Enchantment enchantment : enchantments.keySet()) {
            if (enchantment.equals(enchant)) return enchantments.get(enchantment);
        }
        return 0;
    }

    public static String romanNumeral(int num) {
        switch (num) {
            case 1: return "I";
            case 2: return "II";
            case 3: return "III";
            case 4: return "IV";
            case 5: return "V";
            case 6: return "VI";
            case 7: return "VII";
            case 8: return "VIII";
            case 9: return "IX";
            case 10: return "X";
            default: return ""+num;
        }
    }
}
