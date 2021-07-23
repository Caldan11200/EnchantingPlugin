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

    public static String getRomanNumeral(int num) {
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
            case 11: return "XI";
            case 12: return "XII";
            case 13: return "XIII";
            case 14: return "XIV";
            case 15: return "XV";
            default: return ""+num;
        }
    }

    public static int getEnchantmentLevel(ItemStack item, Enchantment enchant) {
        Map<Enchantment, Integer> enchantments = item.getEnchantments();

        for (Enchantment enchantment : enchantments.keySet()) {
            if (enchantment.equals(enchant)) return enchantments.get(enchantment);
        }

        return 0;
    }

    public static List<Material> combineMaterialLists(List<Material>... lists) {
        List<Material> result = new ArrayList<>();
        for (List<Material> list : lists) {
            for (Material material : list) {
                result.add(material);
            }
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
            default: return null;
        }
    }

    public static List<Enchantment> allEnchantments() {
        List<Enchantment> result = new ArrayList<>();
        result.addAll(Arrays.asList(Enchantment.values()));
        result.addAll(Main.registered);
        return result;
    }
}
