package com.zymekoh.bdcpvper;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.geysermc.floodgate.api.FloodgateApi;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CrystalListener implements Listener {
    
    private final Main plugin;
    private final FloodgateApi floodgateApi;
    private final Set<UUID> fastPlaceCooldown;
    
    public CrystalListener(Main plugin) {
        this.plugin = plugin;
        this.floodgateApi = FloodgateApi.getInstance();
        this.fastPlaceCooldown = new HashSet<>();
    }
    
    /**
     * Detecta si un jugador es de Bedrock
     */
    private boolean isBedrockPlayer(Player player) {
        return floodgateApi.isFloodgatePlayer(player.getUniqueId());
    }
    
    /**
     * Colocación ULTRA RÁPIDA de cristales para Bedrock
     * - Sin cooldown de colocación
     * - Spawn instantáneo
     * - Sin validaciones restrictivas
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onCrystalPlace(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        
        // Solo procesar si es jugador Bedrock
        if (!isBedrockPlayer(player)) {
            return;
        }
        
        // Solo click derecho
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        
        // Verificar si tiene un cristal en la mano
        if (event.getItem() == null || event.getItem().getType() != Material.END_CRYSTAL) {
            return;
        }
        
        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null) {
            return;
        }
        
        Material blockType = clickedBlock.getType();
        
        // Solo en obsidiana o bedrock
        if (blockType != Material.OBSIDIAN && blockType != Material.BEDROCK) {
            return;
        }
        
        // ULTRA FAST: Sin cooldown de colocación
        UUID playerId = player.getUniqueId();
        
        // Cancelar el evento original para manejarlo nosotros
        event.setCancelled(true);
        
        // Ubicación del cristal (encima del bloque)
        Location crystalLoc = clickedBlock.getLocation().add(0.5, 1.0, 0.5);
        World world = crystalLoc.getWorld();
        
        if (world == null) {
            return;
        }
        
        // Verificar espacio (2 bloques de altura)
        Block space1 = world.getBlockAt(clickedBlock.getLocation().add(0, 1, 0));
        Block space2 = world.getBlockAt(clickedBlock.getLocation().add(0, 2, 0));
        
        if (!space1.getType().isAir() || !space2.getType().isAir()) {
            return;
        }
        
        // SPAWN INSTANTÁNEO del cristal
        EnderCrystal crystal = (EnderCrystal) world.spawnEntity(crystalLoc, EntityType.END_CRYSTAL);
        
        // Optimizaciones para velocidad
        crystal.setShowingBottom(false); // Sin base para mejor rendimiento
        crystal.setInvulnerable(false);  // Puede ser destruido
        
        // Remover 1 cristal del inventario
        if (event.getItem().getAmount() > 1) {
            event.getItem().setAmount(event.getItem().getAmount() - 1);
        } else {
            player.getInventory().setItemInMainHand(null);
        }
        
        // Log de debug (opcional, comentar si quieres menos logs)
        // plugin.getLogger().info(player.getName() + " (Bedrock) colocó cristal ULTRA RÁPIDO en " + crystalLoc);
    }
    
    /**
     * Destrucción ULTRA INSTANTÁNEA de cristales para jugadores Bedrock
     * - 0ms de delay
     * - Explosión inmediata
     * - Sin lag de animación
     */
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void onCrystalDamage(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();
        
        // Solo cristales del End
        if (!(damaged instanceof EnderCrystal)) {
            return;
        }
        
        // Solo jugadores
        if (!(damager instanceof Player)) {
            return;
        }
        
        Player player = (Player) damager;
        
        // Solo para jugadores Bedrock
        if (!isBedrockPlayer(player)) {
            return;
        }
        
        EnderCrystal crystal = (EnderCrystal) damaged;
        Location crystalLoc = crystal.getLocation();
        World world = crystal.getWorld();
        
        // CANCELAR el evento original inmediatamente
        event.setCancelled(true);
        
        // REMOVER el cristal INSTANTÁNEAMENTE (sin animación)
        crystal.remove();
        
        // EXPLOSIÓN ULTRA RÁPIDA
        // Ejecutar en el próximo tick para máxima velocidad
        new BukkitRunnable() {
            @Override
            public void run() {
                // Explosión potente e instantánea
                world.createExplosion(
                    crystalLoc,
                    6.0F,    // Potencia (vanilla)
                    false,   // No romper bloques
                    false    // No causar fuego
                );
                
                // Empuje adicional para efecto visual rápido
                for (Entity nearby : world.getNearbyEntities(crystalLoc, 6.0, 6.0, 6.0)) {
                    if (nearby instanceof Player) {
                        Player nearbyPlayer = (Player) nearby;
                        Vector direction = nearbyPlayer.getLocation().toVector()
                            .subtract(crystalLoc.toVector())
                            .normalize()
                            .multiply(1.5); // Empuje más fuerte
                        
                        nearbyPlayer.setVelocity(direction);
                    }
                }
            }
        }.runTask(plugin);
        
        // Log de debug (opcional)
        // plugin.getLogger().info(player.getName() + " (Bedrock) destruyó cristal ULTRA RÁPIDO");
    }
    
    /**
     * Optimización adicional: Detección temprana de hits a cristales
     * Esto hace que la destrucción sea AÚN MÁS RÁPIDA
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onCrystalHit(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof EnderCrystal)) {
            return;
        }
        
        if (!(event.getDamager() instanceof Player)) {
            return;
        }
        
        Player player = (Player) event.getDamager();
        
        if (!isBedrockPlayer(player)) {
            return;
        }
        
        // Pre-cancelar para procesamiento ultra rápido
        event.setDamage(0);
    }
}
