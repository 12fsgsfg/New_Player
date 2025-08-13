package com.example.newplayerwelcome.managers;

import com.example.newplayerwelcome.NewPlayerWelcomePlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NBTManager {
    
    private final NewPlayerWelcomePlugin plugin;
    
    public NBTManager(NewPlayerWelcomePlugin plugin) {
        this.plugin = plugin;
    }
    
    /**
     * 解析物品字符串並創建帶有 NBT 標籤的物品
     * 格式：物品ID:數量:數據值:name=名稱:lore=描述1|描述2:enchantments=附魔1:等級1,附魔2:等級2
     */
    public ItemStack parseItemWithNBT(String itemString) {
        try {
            String[] parts = itemString.split(":");
            if (parts.length < 1) {
                return null;
            }
            
            // 解析基本物品信息
            Material material = Material.valueOf(parts[0].toUpperCase());
            int amount = parts.length > 1 ? Integer.parseInt(parts[1]) : 1;
            int data = parts.length > 2 ? Integer.parseInt(parts[2]) : 0;
            
            ItemStack item = new ItemStack(material, amount, (short) data);
            
            // 解析 NBT 標籤
            if (parts.length > 3) {
                item = applyNBTToItem(item, parts);
            }
            
            return item;
        } catch (Exception e) {
            plugin.getLogger().warning("無法解析物品字符串: " + itemString + " - " + e.getMessage());
            return null;
        }
    }
    
    /**
     * 將 NBT 標籤應用到物品上
     */
    private ItemStack applyNBTToItem(ItemStack item, String[] parts) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return item;
        }
        
        // 檢查是否啟用 NBT 功能
        if (!plugin.getConfigManager().isNBTEnabled()) {
            return item;
        }
        
        // 解析 NBT 標籤
        for (int i = 3; i < parts.length; i++) {
            String part = parts[i];
            if (part.contains("=")) {
                String[] keyValue = part.split("=", 2);
                String key = keyValue[0].toLowerCase();
                String value = keyValue[1];
                
                switch (key) {
                    case "name":
                        if (plugin.getConfigManager().isNamingEnabled()) {
                            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', value));
                        }
                        break;
                    case "lore":
                        if (plugin.getConfigManager().isLoreEnabled()) {
                            meta.setLore(parseLore(value));
                        }
                        break;
                    case "enchantments":
                        if (plugin.getConfigManager().isEnchantmentsEnabled()) {
                            applyEnchantments(meta, value);
                        }
                        break;
                }
            }
        }
        
        item.setItemMeta(meta);
        return item;
    }
    
    /**
     * 解析描述文本
     */
    private List<String> parseLore(String loreString) {
        List<String> lore = new ArrayList<>();
        String[] lines = loreString.split("\\|");
        
        for (String line : lines) {
            lore.add(ChatColor.translateAlternateColorCodes('&', line));
        }
        
        return lore;
    }
    
    /**
     * 應用附魔
     */
    private void applyEnchantments(ItemMeta meta, String enchantmentsString) {
        String[] enchantments = enchantmentsString.split(",");
        
        for (String enchantment : enchantments) {
            if (enchantment.contains(":")) {
                String[] parts = enchantment.split(":", 2);
                String enchantmentName = parts[0].toUpperCase();
                int level = Integer.parseInt(parts[1]);
                
                try {
                    Enchantment enchant = Enchantment.getByName(enchantmentName);
                    if (enchant != null) {
                        meta.addEnchant(enchant, level, true);
                    } else {
                        plugin.getLogger().warning("未知的附魔: " + enchantmentName);
                    }
                } catch (Exception e) {
                    plugin.getLogger().warning("無法應用附魔 " + enchantmentName + ": " + e.getMessage());
                }
            }
        }
    }
    
    /**
     * 獲取物品的顯示名稱
     */
    public String getDisplayName(Material material) {
        switch (material) {
            case DIAMOND_SWORD:
                return "&b鑽石劍";
            case BOW:
                return "&b弓";
            case ARROW:
                return "&7箭矢";
            case DIAMOND_HELMET:
                return "&b鑽石頭盔";
            case DIAMOND_CHESTPLATE:
                return "&b鑽石胸甲";
            case DIAMOND_LEGGINGS:
                return "&b鑽石護腿";
            case DIAMOND_BOOTS:
                return "&b鑽石靴子";
            case DIAMOND_PICKAXE:
                return "&b鑽石鎬";
            case DIAMOND_AXE:
                return "&b鑽石斧";
            case DIAMOND_SHOVEL:
                return "&b鑽石鏟";
            case COOKED_BEEF:
                return "&6熟牛肉";
            case GOLDEN_APPLE:
                return "&6金蘋果";
            case TORCH:
                return "&e火把";
            case STONE:
                return "&7石頭";
            case OAK_LOG:
                return "&6橡木原木";
            case IRON_INGOT:
                return "&7鐵錠";
            case GOLD_INGOT:
                return "&6金錠";
            case DIAMOND:
                return "&b鑽石";
            case EMERALD:
                return "&a綠寶石";
            default:
                return material.name().toLowerCase().replace("_", " ");
        }
    }
    
    /**
     * 創建預設的附魔配置
     */
    public Map<Enchantment, Integer> getDefaultEnchantments(Material material) {
        Map<Enchantment, Integer> enchantments = new HashMap<>();
        
        switch (material) {
            case DIAMOND_SWORD:
                enchantments.put(Enchantment.DAMAGE_ALL, 5);
                enchantments.put(Enchantment.DURABILITY, 3);
                enchantments.put(Enchantment.LOOT_BONUS_MOBS, 2);
                break;
            case BOW:
                enchantments.put(Enchantment.ARROW_DAMAGE, 3);
                enchantments.put(Enchantment.ARROW_INFINITE, 1);
                enchantments.put(Enchantment.DURABILITY, 2);
                break;
            case DIAMOND_HELMET:
            case DIAMOND_CHESTPLATE:
            case DIAMOND_LEGGINGS:
                enchantments.put(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                enchantments.put(Enchantment.DURABILITY, 3);
                enchantments.put(Enchantment.THORNS, 2);
                break;
            case DIAMOND_BOOTS:
                enchantments.put(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                enchantments.put(Enchantment.DURABILITY, 3);
                enchantments.put(Enchantment.PROTECTION_FALL, 4);
                break;
            case DIAMOND_PICKAXE:
                enchantments.put(Enchantment.DIG_SPEED, 5);
                enchantments.put(Enchantment.DURABILITY, 3);
                enchantments.put(Enchantment.SILK_TOUCH, 1);
                break;
            case DIAMOND_AXE:
                enchantments.put(Enchantment.DIG_SPEED, 5);
                enchantments.put(Enchantment.DURABILITY, 3);
                enchantments.put(Enchantment.LOOT_BONUS_MOBS, 2);
                break;
            case DIAMOND_SHOVEL:
                enchantments.put(Enchantment.DIG_SPEED, 5);
                enchantments.put(Enchantment.DURABILITY, 3);
                enchantments.put(Enchantment.SILK_TOUCH, 1);
                break;
        }
        
        return enchantments;
    }
}
