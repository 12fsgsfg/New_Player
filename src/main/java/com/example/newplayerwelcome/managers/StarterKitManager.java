package com.example.newplayerwelcome.managers;

import com.example.newplayerwelcome.NewPlayerWelcomePlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class StarterKitManager {
    
    private final NewPlayerWelcomePlugin plugin;
    private final NBTManager nbtManager;
    
    public StarterKitManager(NewPlayerWelcomePlugin plugin) {
        this.plugin = plugin;
        this.nbtManager = new NBTManager(plugin);
    }
    
    public void giveStarterKit(Player player) {
        if (!plugin.getConfigManager().isStarterKitEnabled()) {
            return;
        }
        
        // 檢查權限
        if (player.hasPermission("newplayerwelcome.bypass")) {
            return;
        }
        
        List<String> items = plugin.getConfigManager().getStarterKitItems();
        
        for (String itemString : items) {
            ItemStack item = parseItemString(itemString);
            if (item != null) {
                giveItemToPlayer(player, item);
            }
        }
        
        player.sendMessage(ChatColor.GREEN + "您已收到新手裝備！");
    }
    
    private ItemStack parseItemString(String itemString) {
        try {
            // 檢查是否包含 NBT 標籤
            if (itemString.contains(":name=") || itemString.contains(":lore=") || itemString.contains(":enchantments=")) {
                // 使用 NBT 管理器解析
                return nbtManager.parseItemWithNBT(itemString);
            } else {
                // 使用舊的解析方法
                return parseSimpleItemString(itemString);
            }
        } catch (Exception e) {
            plugin.getLogger().warning("無法解析物品字符串: " + itemString + " - " + e.getMessage());
            return null;
        }
    }
    
    private ItemStack parseSimpleItemString(String itemString) {
        try {
            String[] parts = itemString.split(":");
            if (parts.length < 1) {
                return null;
            }
            
            Material material = Material.valueOf(parts[0].toUpperCase());
            int amount = parts.length > 1 ? Integer.parseInt(parts[1]) : 1;
            int data = parts.length > 2 ? Integer.parseInt(parts[2]) : 0;
            
            ItemStack item = new ItemStack(material, amount, (short) data);
            
            // 設置物品名稱
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                String displayName = nbtManager.getDisplayName(material);
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
                item.setItemMeta(meta);
            }
            
            return item;
        } catch (Exception e) {
            plugin.getLogger().warning("無法解析簡單物品字符串: " + itemString + " - " + e.getMessage());
            return null;
        }
    }
    
    private void giveItemToPlayer(Player player, ItemStack item) {
        // 檢查背包是否有空間
        if (player.getInventory().firstEmpty() != -1) {
            player.getInventory().addItem(item);
        } else {
            // 如果背包滿了，丟到地上
            player.getWorld().dropItemNaturally(player.getLocation(), item);
            player.sendMessage(ChatColor.YELLOW + "您的背包已滿，部分物品已掉落在地上！");
        }
    }
}
