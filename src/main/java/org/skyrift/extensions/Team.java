package org.skyrift.extensions;

import cn.nukkit.Player;

import java.util.ArrayList;
import java.util.List;

import org.skyrift.Main;

public class Team {
    private final String name;
    private final String colorCode;
    private Main main;
    private List<String> players;

    public Team(String name, String colorCode, Main main) {
        this.name = name;
        this.colorCode = colorCode;
        this.main = main;
        this.players = new ArrayList<String>();
    }

    public void addPlayer(Player player) {
        players.add(player.getName());

        player.setNameTag(player.getName() + " [" + this.name + "]");
        player.getServer().broadcastMessage(player.getName() + " joined the team.");
        player.getServer().broadcastMessage(player.getNameTag());

    }

    public void removePlayer(Player player) {
        players.remove(player.getName());

        player.setNameTag(player.getName());
    }

    public void removeAllPlayers() {
        // Reset nameTags
        for (int i = 0, size = players.size(); i < size; i++) {
            Player player = main.getServer().getPlayer(players.get(i));
            if (player != null) {
                player.setNameTag(player.getName());
            }
        }

        players.clear();  // Clear the player list
    }

}
