package com.banyulsescouts.enchanting.listeners;

import com.banyulsescouts.enchanting.Main;
import com.banyulsescouts.enchanting.util.Cooldown;
import com.banyulsescouts.enchanting.util.EnchantHandler;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Yeet implements Listener {

    @EventHandler
    public void onItemRightClick(PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) return;
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        if (item == null) return;
        if (!item.hasItemMeta()) return;

        if (item.getItemMeta().hasEnchant(Main.YEET)) {
            int timer = 10 - EnchantHandler.getEnchantmentLevel(item, Main.YEET);
            if (timer < 5) timer = 5;
            Cooldown cooldown = new Cooldown(player.getUniqueId(), "yeetCooldown", timer);

            if (Cooldown.isInCooldown(player.getUniqueId(), "yeetCooldown")) {
                int timeLeft = cooldown.getSeconds(player.getUniqueId(), "yeetCooldown");
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Yeet: "+timeLeft+" seconds remaining"));
            }
            else {
                player.setVelocity(player.getLocation().getDirection().multiply((EnchantHandler.getEnchantmentLevel(item, Main.YEET))));
                player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SHOOT, 0.2f, 1.2f);
                cooldown.start();
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            if (!(event.getEntity() instanceof Player)) return;
            Player player = (Player) event.getEntity();
            if (Cooldown.isInCooldown(player.getUniqueId(), "yeetCooldown")) event.setCancelled(true);
        }
    }
}
