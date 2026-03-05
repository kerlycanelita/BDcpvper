package com.zymekoh.bdcpvper;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.geysermc.floodgate.api.FloodgateApi;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class PlayerConnectionListener implements Listener {

    private final Main plugin;
    private final FloodgateApi floodgateApi;

    public PlayerConnectionListener(Main plugin) {
        this.plugin = plugin;
        this.floodgateApi = FloodgateApi.getInstance();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!floodgateApi.isFloodgatePlayer(player.getUniqueId())) {
            return;
        }

        if (plugin.getConfig().getBoolean("logs.player-join", true)) {
            plugin.getLogger().info("========================================");
            plugin.getLogger().info("BEDROCK PLAYER DETECTED: " + player.getName());
            plugin.getLogger().info("UUID: " + player.getUniqueId());
            plugin.getLogger().info("========================================");
        }

        if (plugin.getConfig().getBoolean("messages.welcome-message", true)) {
            for (String line : plugin.getConfig().getStringList("messages.welcome-text")) {
                player.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(line));
            }
        }
    }
}
