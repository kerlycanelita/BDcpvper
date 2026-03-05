package com.zymekoh.bdcpvper;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        if (getServer().getPluginManager().getPlugin("floodgate") == null) {
            getLogger().severe("============================================");
            getLogger().severe("FLOODGATE NOT FOUND!");
            getLogger().severe("This plugin requires Floodgate to work.");
            getLogger().severe("Download: https://geysermc.org/download");
            getLogger().severe("============================================");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getServer().getPluginManager().registerEvents(new PlayerConnectionListener(this), this);
        getServer().getPluginManager().registerEvents(new CrystalListener(this), this);
        getServer().getPluginManager().registerEvents(new AnchorListener(this), this);

        getLogger().info("============================================");
        getLogger().info("BDcpvper enabled successfully (v" + getPluginMeta().getVersion() + ")");
        getLogger().info("Bedrock compatibility active for end crystals and respawn anchors.");
        getLogger().info("============================================");
    }

    @Override
    public void onDisable() {
        getLogger().info("BDcpvper disabled.");
    }
}
