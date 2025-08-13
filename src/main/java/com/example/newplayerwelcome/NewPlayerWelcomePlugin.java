package com.example.newplayerwelcome;

import com.example.newplayerwelcome.commands.TestNBTCommand;
import com.example.newplayerwelcome.commands.WelcomeCommand;
import com.example.newplayerwelcome.listeners.PlayerJoinListener;
import com.example.newplayerwelcome.managers.ConfigManager;
import com.example.newplayerwelcome.managers.NBTManager;
import com.example.newplayerwelcome.managers.StarterKitManager;
import com.example.newplayerwelcome.managers.WelcomeManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NewPlayerWelcomePlugin extends JavaPlugin {
    
    private static NewPlayerWelcomePlugin instance;
    private ConfigManager configManager;
    private WelcomeManager welcomeManager;
    private StarterKitManager starterKitManager;
    private NBTManager nbtManager;
    
    @Override
    public void onEnable() {
        instance = this;
        
        // 初始化配置管理器
        configManager = new ConfigManager(this);
        configManager.loadConfig();
        
        // 初始化 NBT 管理器
        nbtManager = new NBTManager(this);
        
        // 初始化歡迎管理器
        welcomeManager = new WelcomeManager(this);
        
        // 初始化新手裝備管理器
        starterKitManager = new StarterKitManager(this);
        
        // 註冊事件監聽器
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        
        // 註冊命令
        getCommand("welcome").setExecutor(new WelcomeCommand(this));
        getCommand("testnbt").setExecutor(new TestNBTCommand(this));
        
        getLogger().info("新玩家歡迎插件已啟用！");
    }
    
    @Override
    public void onDisable() {
        getLogger().info("新玩家歡迎插件已停用！");
    }
    
    public static NewPlayerWelcomePlugin getInstance() {
        return instance;
    }
    
    public ConfigManager getConfigManager() {
        return configManager;
    }
    
    public WelcomeManager getWelcomeManager() {
        return welcomeManager;
    }
    
    public StarterKitManager getStarterKitManager() {
        return starterKitManager;
    }
    
    public NBTManager getNBTManager() {
        return nbtManager;
    }
}
