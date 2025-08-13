package com.example.newplayerwelcome.listeners;

import com.example.newplayerwelcome.NewPlayerWelcomePlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    
    private final NewPlayerWelcomePlugin plugin;
    
    public PlayerJoinListener(NewPlayerWelcomePlugin plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // 延遲執行，確保玩家完全載入
        plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
            // 發送歡迎消息
            plugin.getWelcomeManager().sendWelcomeMessage(event.getPlayer());
            
            // 檢查是否為首次加入
            if (isFirstJoin(event.getPlayer())) {
                // 給予新手裝備
                plugin.getStarterKitManager().giveStarterKit(event.getPlayer());
                
                // 保存加入記錄
                if (plugin.getConfigManager().isSaveJoinRecord()) {
                    saveJoinRecord(event.getPlayer());
                }
            }
        }, 20L); // 延遲1秒執行
    }
    
    private boolean isFirstJoin(org.bukkit.entity.Player player) {
        if (!plugin.getConfigManager().isOnlyFirstJoin()) {
            return true; // 如果設置為每次都給，則返回true
        }
        
        // 檢查玩家是否首次加入
        // 這裡可以擴展為檢查數據庫或文件記錄
        return !player.hasPlayedBefore();
    }
    
    private void saveJoinRecord(org.bukkit.entity.Player player) {
        // 這裡可以擴展為保存到數據庫或文件
        // 目前只是簡單的實現
        plugin.getLogger().info("玩家 " + player.getName() + " 首次加入服務器");
    }
}
