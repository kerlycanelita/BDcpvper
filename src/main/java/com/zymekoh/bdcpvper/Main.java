package com.zymekoh.bdcpvper;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    
    @Override
    public void onEnable() {
        // Verificar si Floodgate está instalado
        if (getServer().getPluginManager().getPlugin("floodgate") == null) {
            getLogger().severe("============================================");
            getLogger().severe("FLOODGATE NO ENCONTRADO!");
            getLogger().severe("Este plugin requiere Floodgate para funcionar");
            getLogger().severe("Descarga: https://geysermc.org/download");
            getLogger().severe("============================================");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        
        // Registrar listeners
        getServer().getPluginManager().registerEvents(new CrystalListener(this), this);
        getServer().getPluginManager().registerEvents(new AnchorListener(this), this);
        
        getLogger().info("============================================");
        getLogger().info("BDcpvper activado correctamente!");
        getLogger().info("Jugadores Bedrock pueden usar:");
        getLogger().info("- Cristales del End sin delay");
        getLogger().info("- Respawn Anchors sin delay");
        getLogger().info("============================================");
    }

    @Override
    public void onDisable() {
        getLogger().info("BDcpvper desactivado!");
    }
}
