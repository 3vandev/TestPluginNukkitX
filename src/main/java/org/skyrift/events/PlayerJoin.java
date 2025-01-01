package org.skyrift.events;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import org.skyrift.Main;

public class PlayerJoin implements Listener {
    private Main plugin;

    public PlayerJoin(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
       Player player = event.getPlayer();

       player.sendMessage("Welcome to SKYRIFT!");
    }
}
