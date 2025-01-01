package org.skyrift.modules;
import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.math.Vector3;
import org.skyrift.Main;
import org.skyrift.extensions.Team;

import java.util.ArrayList;
import java.util.List;

public class Logic {
    private static List<Player> playersInGame = new ArrayList<>();
    private static Main main;

    public static void setMain(Main main) {
        Logic.main = main;
    }

    // Check if the player is in the game
    public static boolean isPlayerInGame(Player player) {
        return playersInGame.contains(player);
    }

    // Teams
    private static final Team blue = new Team("blue", "1", main);
    private static final Team red = new Team("red", "2", main);

    private static void addPlayersToTeams() {
        // Clear previous teams
        blue.removeAllPlayers();
        red.removeAllPlayers();

        for (int i = 0; i < playersInGame.size(); i++) {
            if (i % 2 == 0) {
                // Even index, add to blue team
                blue.addPlayer(playersInGame.get(i));
            } else {
                // Odd index, add to red team
                red.addPlayer(playersInGame.get(i));
            }
        }
    }

    public static void startGame(List<String> players, Main main) {
        List<Player> playerList = new ArrayList<Player>();

        setMain(main);

        for (int i = 0; i < players.size(); i++) {
            playerList.add(main.getServer().getPlayer(players.get(i)));
        }

        playersInGame = playerList;

        main.getServer().loadLevel("game");
        Level game = main.getServer().getLevelByName("game");

        if(game == null) { return; }

        game.setSpawnLocation(new Vector3(0, 10, 0));



        // Add half of the players to each team

        for (Player player : playerList) {
            player.sendMessage("Game is now starting.");

            player.teleport(game.getSpawnLocation());
        }

        addPlayersToTeams();
    }
}
