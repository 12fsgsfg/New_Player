package com.example.newplayerwelcome.managers;

import com.example.newplayerwelcome.NewPlayerWelcomePlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class WelcomeManager {
    
    private final NewPlayerWelcomePlugin plugin;
    
    public WelcomeManager(NewPlayerWelcomePlugin plugin) {
        this.plugin = plugin;
    }
    
    public void sendWelcomeMessage(Player player) {
        if (!plugin.getConfigManager().isWelcomeEnabled()) {
            return;
        }
        
        List<String> messages = plugin.getConfigManager().getWelcomeMessages();
        
        for (String message : messages) {
            String formattedMessage = ChatColor.translateAlternateColorCodes('&', 
                message.replace("%player%", player.getName()));
            
            // 發送私人消息
            if (plugin.getConfigManager().isPrivateMessageEnabled()) {
                player.sendMessage(formattedMessage);
            }
            
            // 廣播到全服
            if (plugin.getConfigManager().isBroadcastEnabled()) {
                Bukkit.broadcastMessage(formattedMessage);
            }
        }
    }
    
    public void sendWelcomeMessageToPlayer(Player player) {
        if (!plugin.getConfigManager().isWelcomeEnabled()) {
            return;
        }
        
        List<String> messages = plugin.getConfigManager().getWelcomeMessages();
        
        for (String message : messages) {
            String formattedMessage = ChatColor.translateAlternateColorCodes('&', 
                message.replace("%player%", player.getName()));
            player.sendMessage(formattedMessage);
        }
    }
}
