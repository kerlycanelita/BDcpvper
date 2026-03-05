package com.zymekoh.bdcpvper;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.geysermc.floodgate.api.FloodgateApi;

public class CrystalListener implements Listener {

    private final Main plugin;
    private final FloodgateApi floodgateApi;

    public CrystalListener(Main plugin) {
        this.plugin = plugin;
        this.floodgateApi = FloodgateApi.getInstance();
    }

    private boolean isBedrockPlayer(Player player) {
        return floodgateApi.isFloodgatePlayer(player.getUniqueId());
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onCrystalPlace(PlayerInteractEvent event) {
        if (!plugin.getConfig().getBoolean("crystals.ultra-fast-place", true)) {
            return;
        }

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        ItemStack item = event.getItem();
        if (item == null || item.getType() != Material.END_CRYSTAL) {
            return;
        }

        Player player = event.getPlayer();
        if (!isBedrockPlayer(player)) {
            return;
        }

        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null) {
            return;
        }

        Material clickedType = clickedBlock.getType();
        if (clickedType != Material.OBSIDIAN && clickedType != Material.BEDROCK) {
            return;
        }

        Block firstAirBlock = clickedBlock.getRelative(BlockFace.UP);
        Block secondAirBlock = firstAirBlock.getRelative(BlockFace.UP);
        if (!firstAirBlock.getType().isAir() || !secondAirBlock.getType().isAir()) {
            return;
        }

        event.setCancelled(true);

        Location crystalLocation = clickedBlock.getLocation().add(0.5D, 1.0D, 0.5D);
        World world = clickedBlock.getWorld();

        EnderCrystal crystal = (EnderCrystal) world.spawnEntity(crystalLocation, EntityType.END_CRYSTAL);
        crystal.setShowingBottom(plugin.getConfig().getBoolean("crystals.show-crystal-base", false));
        crystal.setInvulnerable(false);

        consumeCrystalFromUsedHand(event, player);

        if (plugin.getConfig().getBoolean("logs.crystal-actions", false)) {
            plugin.getLogger().info(player.getName() + " placed an End Crystal at " + formatLocation(crystalLocation));
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onCrystalDamage(EntityDamageByEntityEvent event) {
        if (!plugin.getConfig().getBoolean("crystals.ultra-fast-break", true)) {
            return;
        }

        if (!(event.getEntity() instanceof EnderCrystal crystal)) {
            return;
        }

        if (!(event.getDamager() instanceof Player player)) {
            return;
        }

        if (!isBedrockPlayer(player)) {
            return;
        }

        event.setCancelled(true);

        Location crystalLocation = crystal.getLocation();
        World world = crystal.getWorld();
        crystal.remove();

        if (plugin.getConfig().getBoolean("crystals.explosion-enabled", true)) {
            float power = (float) plugin.getConfig().getDouble("crystals.explosion-power", 6.0D);
            boolean breaksBlocks = plugin.getConfig().getBoolean("crystals.explosion-breaks-blocks", false);
            boolean causesFire = plugin.getConfig().getBoolean("crystals.explosion-causes-fire", false);
            world.createExplosion(crystalLocation, Math.max(0.0F, power), causesFire, breaksBlocks, player);
        }

        if (plugin.getConfig().getBoolean("logs.crystal-actions", false)) {
            plugin.getLogger().info(player.getName() + " broke an End Crystal at " + formatLocation(crystalLocation));
        }
    }

    private void consumeCrystalFromUsedHand(PlayerInteractEvent event, Player player) {
        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        ItemStack item = event.getItem();
        if (item == null) {
            return;
        }

        int remaining = item.getAmount() - 1;
        if (remaining > 0) {
            item.setAmount(remaining);
            return;
        }

        EquipmentSlot hand = event.getHand();
        if (hand == EquipmentSlot.OFF_HAND) {
            player.getInventory().setItemInOffHand(null);
            return;
        }

        player.getInventory().setItemInMainHand(null);
    }

    private String formatLocation(Location location) {
        return location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ();
    }
}
