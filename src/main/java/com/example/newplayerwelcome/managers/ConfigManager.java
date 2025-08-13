package com.example.newplayerwelcome.managers;

import com.example.newplayerwelcome.NewPlayerWelcomePlugin;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ConfigManager {
    
    private final NewPlayerWelcomePlugin plugin;
    private FileConfiguration config;
    
    public ConfigManager(NewPlayerWelcomePlugin plugin) {
        this.plugin = plugin;
    }
    
    public void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        config = plugin.getConfig();
    }
    
    public boolean isWelcomeEnabled() {
        return config.getBoolean("welcome.enabled", true);
    }
    
    public List<String> getWelcomeMessages() {
        return config.getStringList("welcome.messages");
    }
    
    public boolean isBroadcastEnabled() {
        return config.getBoolean("welcome.broadcast", true);
    }
    
    public boolean isPrivateMessageEnabled() {
        return config.getBoolean("welcome.private_message", true);
    }
    
    public boolean isStarterKitEnabled() {
        return config.getBoolean("starter_kit.enabled", true);
    }
    
    public List<String> getStarterKitItems() {
        return config.getStringList("starter_kit.items");
    }
    
    public boolean isOnlyFirstJoin() {
        return config.getBoolean("first_join.only_first_join", true);
    }
    
    public boolean isSaveJoinRecord() {
        return config.getBoolean("first_join.save_join_record", true);
    }
    
    // NBT 相關配置方法
    public boolean isNBTEnabled() {
        return config.getBoolean("nbt.enabled", true);
    }
    
    public boolean isEnchantmentsEnabled() {
        return config.getBoolean("nbt.enchantments", true);
    }
    
    public boolean isNamingEnabled() {
        return config.getBoolean("nbt.naming", true);
    }
    
    public boolean isLoreEnabled() {
        return config.getBoolean("nbt.lore", true);
    }
    
    public void reloadConfig() {
        plugin.reloadConfig();
        config = plugin.getConfig();
    }
}
