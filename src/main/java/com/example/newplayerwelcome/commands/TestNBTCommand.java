package com.example.newplayerwelcome.commands;

import com.example.newplayerwelcome.NewPlayerWelcomePlugin;
import com.example.newplayerwelcome.managers.NBTManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TestNBTCommand implements CommandExecutor {
    
    private final NewPlayerWelcomePlugin plugin;
    
    public TestNBTCommand(NewPlayerWelcomePlugin plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("newplayerwelcome.testnbt")) {
            sender.sendMessage(ChatColor.RED + "您沒有權限使用此命令！");
            return true;
        }
        
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "此命令只能由玩家使用！");
            return true;
        }
        
        Player player = (Player) sender;
        
        if (args.length == 0) {
            // 測試預設的 NBT 物品
            testDefaultNBTItems(player);
            return true;
        }
        
        if (args.length >= 1) {
            // 測試自定義 NBT 物品
            String itemString = String.join(" ", args);
            testCustomNBTItem(player, itemString);
            return true;
        }
        
        return true;
    }
    
    private void testDefaultNBTItems(Player player) {
        NBTManager nbtManager = plugin.getNBTManager();
        
        // 測試鑽石劍
        String diamondSwordString = "DIAMOND_SWORD:1:0:name=&b&l測試鑽石劍:lore=&7這是一把測試武器|&7帶有 NBT 標籤:enchantments=SHARPNESS:5,DURABILITY:3";
        ItemStack diamondSword = nbtManager.parseItemWithNBT(diamondSwordString);
        
        if (diamondSword != null) {
            player.getInventory().addItem(diamondSword);
            player.sendMessage(ChatColor.GREEN + "已給予測試鑽石劍！");
        }
        
        // 測試弓
        String bowString = "BOW:1:0:name=&a&l測試弓:lore=&7這是一把測試弓|&7帶有附魔:enchantments=POWER:3,INFINITY:1";
        ItemStack bow = nbtManager.parseItemWithNBT(bowString);
        
        if (bow != null) {
            player.getInventory().addItem(bow);
            player.sendMessage(ChatColor.GREEN + "已給予測試弓！");
        }
        
        player.sendMessage(ChatColor.YELLOW + "已測試預設 NBT 物品！");
    }
    
    private void testCustomNBTItem(Player player, String itemString) {
        NBTManager nbtManager = plugin.getNBTManager();
        
        ItemStack item = nbtManager.parseItemWithNBT(itemString);
        
        if (item != null) {
            player.getInventory().addItem(item);
            player.sendMessage(ChatColor.GREEN + "已給予自定義 NBT 物品！");
            player.sendMessage(ChatColor.GRAY + "物品: " + item.getType().name());
        } else {
            player.sendMessage(ChatColor.RED + "無法解析物品字符串！");
            player.sendMessage(ChatColor.YELLOW + "格式: 物品ID:數量:數據值:name=名稱:lore=描述:enchantments=附魔");
        }
    }
}
