package com.zymekoh.bdcpvper;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.geysermc.floodgate.api.FloodgateApi;

public class PlayerConnectionListener implements Listener {
    
    private final Main plugin;
    private final FloodgateApi floodgateApi;
    
    public PlayerConnectionListener(Main plugin) {
        this.plugin = plugin;
        this.floodgateApi = FloodgateApi.getInstance();
    }
    
    /**
     * Detecta cuando un jugador Bedrock se conecta al servidor
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        
        // Verificar si es jugador de Bedrock
        if (floodgateApi.isFloodgatePlayer(player.getUniqueId())) {
            // Log en consola
            plugin.getLogger().info("========================================");
            plugin.getLogger().info("JUGADOR BEDROCK DETECTADO:");
            plugin.getLogger().info("  Nombre: " + player.getName());
            plugin.getLogger().info("  UUID: " + player.getUniqueId().toString());
            plugin.getLogger().info("  Compatible: SI - BDcpvper ACTIVO");
            plugin.getLogger().info("  Velocidad Crystal PvP: ULTRA RAPIDA");
            plugin.getLogger().info("========================================");
            
            // Mensaje opcional al jugador (puedes comentar estas líneas si no quieres)
            player.sendMessage("§a§l[BDcpvper] §7¡Bienvenido jugador de Bedrock!");
            player.sendMessage("§a§l[BDcpvper] §7Crystal PvP optimizado: §e§lULTRA RAPIDO");
        }
    }
}
