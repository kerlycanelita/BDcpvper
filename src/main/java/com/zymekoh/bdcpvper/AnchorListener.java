package com.zymekoh.bdcpvper;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
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
     * Colocación ULTRA RÁPIDA de Respawn Anchors para Bedrock
     * - Sin delay de colocación
     * - Spawn instantáneo del bloque
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onAnchorPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        
        // Solo procesar si es jugador Bedrock y está colocando un Respawn Anchor
        if (!isBedrockPlayer(player)) {
            return;
        }
        
        if (block.getType() != Material.RESPAWN_ANCHOR) {
            return;
        }
        
        // ULTRA FAST: Permitir colocación sin restricciones
        event.setCancelled(false);
        
        // Confirmar colocación con sonido instantáneo
        new BukkitRunnable() {
            @Override
            public void run() {
                Location loc = block.getLocation();
                loc.getWorld().playSound(loc, Sound.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, 1.0F, 1.0F);
            }
        }.runTask(plugin);
        
        // Log de debug (opcional)
        // plugin.getLogger().info(player.getName() + " (Bedrock) colocó Anchor ULTRA RÁPIDO");
    }
    
    /**
     * Destrucción ULTRA INSTANTÁNEA de Respawn Anchors para jugadores Bedrock
     * - 0ms de delay
     * - Rompimiento instantáneo
     * - Drop inmediato
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onAnchorBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        
        // Solo procesar si es jugador Bedrock y está rompiendo un Respawn Anchor
        if (!isBedrockPlayer(player)) {
            return;
        }
        
        if (block.getType() != Material.RESPAWN_ANCHOR) {
            return;
        }
        
        // CANCELAR el evento original para manejarlo nosotros
        event.setCancelled(true);
        
        Location blockLoc = block.getLocation();
        
        // DESTRUCCIÓN INSTANTÁNEA
        block.setType(Material.AIR, false); // false = sin física, más rápido
        
        // DROP INSTANTÁNEO del item
        new BukkitRunnable() {
            @Override
            public void run() {
                blockLoc.getWorld().dropItemNaturally(
                    blockLoc.add(0.5, 0.5, 0.5), 
                    new ItemStack(Material.RESPAWN_ANCHOR)
                );
                
                // Sonido de ruptura
                blockLoc.getWorld().playSound(
                    blockLoc, 
                    Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 
                    1.0F, 
                    1.0F
                );
            }
        }.runTask(plugin);
        
        // Dar experiencia instantánea (como vanilla)
        player.giveExp(3);
        
        // Log de debug (opcional)
        // plugin.getLogger().info(player.getName() + " (Bedrock) rompió Anchor ULTRA RÁPIDO");
    }
}
