package com.zymekoh.bdcpvper;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.geysermc.floodgate.api.FloodgateApi;

public class AnchorListener implements Listener {
    
    private final Main plugin;
    private final FloodgateApi floodgateApi;
    
    public AnchorListener(Main plugin) {
        this.plugin = plugin;
        this.floodgateApi = FloodgateApi.getInstance();
    }
    
    /**
     * Detecta si un jugador es de Bedrock
     */
    private boolean isBedrockPlayer(Player player) {
        return floodgateApi.isFloodgatePlayer(player.getUniqueId());
    }
    
    /**
     * Permite colocar Respawn Anchors sin delay para Bedrock
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAnchorPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        
        // Solo procesar si es jugador Bedrock y está colocando un Respawn Anchor
        if (!isBedrockPlayer(player)) {
            return;
        }
        
        if (block.getType() == Material.RESPAWN_ANCHOR) {
            // Permitir colocación sin restricciones
            event.setCancelled(false);
            
            // Log opcional para debug
            plugin.getLogger().info(player.getName() + " (Bedrock) colocó un Respawn Anchor en " 
                + block.getLocation().toString());
        }
    }
    
    /**
     * Destrucción instantánea de Respawn Anchors para jugadores Bedrock
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAnchorBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        
        // Solo procesar si es jugador Bedrock y está rompiendo un Respawn Anchor
        if (!isBedrockPlayer(player)) {
            return;
        }
        
        if (block.getType() == Material.RESPAWN_ANCHOR) {
            // Permitir destrucción instantánea
            event.setCancelled(false);
            
            // Forzar la destrucción inmediata del bloque
            block.setType(Material.AIR);
            
            // Dropear el item
            block.getWorld().dropItemNaturally(block.getLocation(), 
                new ItemStack(Material.RESPAWN_ANCHOR));
            
            // Log opcional para debug
            plugin.getLogger().info(player.getName() + " (Bedrock) rompió un Respawn Anchor en " 
                + block.getLocation().toString());
        }
    }
}
