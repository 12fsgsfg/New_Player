package com.example.newplayerwelcome.commands;

import com.example.newplayerwelcome.NewPlayerWelcomePlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WelcomeCommand implements CommandExecutor {
    
    private final NewPlayerWelcomePlugin plugin;
    
    public WelcomeCommand(NewPlayerWelcomePlugin plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("newplayerwelcome.welcome")) {
            sender.sendMessage(ChatColor.RED + "您沒有權限使用此命令！");
            return true;
        }
        
        if (args.length == 0) {
            // 沒有參數，發送給自己
            if (sender instanceof Player) {
                Player player = (Player) sender;
                plugin.getWelcomeManager().sendWelcomeMessageToPlayer(player);
                sender.sendMessage(ChatColor.GREEN + "已向您發送歡迎消息！");
            } else {
                sender.sendMessage(ChatColor.RED + "此命令只能由玩家使用！");
            }
            return true;
        }
        
        if (args.length == 1) {
            // 有一個參數，發送給指定玩家
            String targetName = args[0];
            Player target = plugin.getServer().getPlayer(targetName);
            
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "找不到玩家: " + targetName);
                return true;
            }
            
            plugin.getWelcomeManager().sendWelcomeMessageToPlayer(target);
            sender.sendMessage(ChatColor.GREEN + "已向 " + targetName + " 發送歡迎消息！");
            return true;
        }
        
        // 參數過多
        sender.sendMessage(ChatColor.RED + "用法: /welcome [玩家名稱]");
        return true;
    }
}
