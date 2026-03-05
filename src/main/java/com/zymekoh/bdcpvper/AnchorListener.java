package com.zymekoh.bdcpvper;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
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

    private boolean isBedrockPlayer(Player player) {
        return floodgateApi.isFloodgatePlayer(player.getUniqueId());
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onAnchorPlace(BlockPlaceEvent event) {
        if (!plugin.getConfig().getBoolean("anchors.ultra-fast-place", true)) {
            return;
        }

        if (event.getBlockPlaced().getType() != Material.RESPAWN_ANCHOR) {
            return;
        }

        Player player = event.getPlayer();
        if (!isBedrockPlayer(player)) {
            return;
        }

        if (plugin.getConfig().getBoolean("logs.anchor-actions", false)) {
            plugin.getLogger().info(player.getName() + " placed a Respawn Anchor at " + formatLocation(event.getBlockPlaced().getLocation()));
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onAnchorBreak(BlockBreakEvent event) {
        if (!plugin.getConfig().getBoolean("anchors.ultra-fast-break", true)) {
            return;
        }

        Block block = event.getBlock();
        if (block.getType() != Material.RESPAWN_ANCHOR) {
            return;
        }

        Player player = event.getPlayer();
        if (!isBedrockPlayer(player)) {
            return;
        }

        GameMode gameMode = player.getGameMode();
        if (gameMode == GameMode.ADVENTURE || gameMode == GameMode.SPECTATOR) {
            return;
        }

        event.setCancelled(true);

        World world = block.getWorld();
        Location blockLocation = block.getLocation();
        block.setType(Material.AIR, false);

        boolean shouldDrop = plugin.getConfig().getBoolean("anchors.drop-item", true) && gameMode != GameMode.CREATIVE;
        if (shouldDrop) {
            world.dropItemNaturally(blockLocation.clone().add(0.5D, 0.5D, 0.5D), new ItemStack(Material.RESPAWN_ANCHOR));
        }

        boolean shouldGiveExperience = plugin.getConfig().getBoolean("anchors.give-experience", true)
            && gameMode != GameMode.CREATIVE;
        if (shouldGiveExperience) {
            int expAmount = Math.max(0, plugin.getConfig().getInt("anchors.experience-amount", 3));
            if (expAmount > 0) {
                player.giveExp(expAmount);
            }
        }

        world.playSound(blockLocation, Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1.0F, 1.0F);

        if (plugin.getConfig().getBoolean("logs.anchor-actions", false)) {
            plugin.getLogger().info(player.getName() + " broke a Respawn Anchor at " + formatLocation(blockLocation));
        }
    }

    private String formatLocation(Location location) {
        return location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ();
    }
}
