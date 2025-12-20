package com.zymekoh.bdcpvper;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.geysermc.floodgate.api.FloodgateApi;

public class CrystalListener implements Listener {
    
    private final Main plugin;
    private final FloodgateApi floodgateApi;
    
    public CrystalListener(Main plugin) {
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
     * Permite colocar cristales del End sin restricciones para Bedrock
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCrystalPlace(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        
        // Solo procesar si es jugador Bedrock
        if (!isBedrockPlayer(player)) {
            return;
        }
        
        // Verificar si está intentando colocar un cristal
        if (event.getItem() != null && event.getItem().getType() == Material.END_CRYSTAL) {
            Block clickedBlock = event.getClickedBlock();
            
            if (clickedBlock != null) {
                // Permitir colocación en obsidiana y bedrock
                Material blockType = clickedBlock.getType();
                if (blockType == Material.OBSIDIAN || blockType == Material.BEDROCK) {
                    // El evento se procesa normalmente, solo aseguramos que no haya delay
                    event.setCancelled(false);
                }
            }
        }
    }
    
    /**
     * Destrucción instantánea de cristales para jugadores Bedrock
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCrystalDamage(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();
        
        // Verificar que sea un cristal del End siendo atacado por un jugador
        if (!(damaged instanceof EnderCrystal)) {
            return;
        }
        
        if (!(damager instanceof Player)) {
            return;
        }
        
        Player player = (Player) damager;
        
        // Solo para jugadores Bedrock
        if (!isBedrockPlayer(player)) {
            return;
        }
        
        EnderCrystal crystal = (EnderCrystal) damaged;
        
        // Destruir el cristal instantáneamente
        crystal.remove();
        
        // Crear la explosión del cristal
        crystal.getWorld().createExplosion(
            crystal.getLocation(),
            6.0F, // Potencia de explosión
            false, // No romper bloques
            false  // No causar fuego
        );
        
        // Cancelar el evento original para evitar doble procesamiento
        event.setCancelled(true);
    }
}
