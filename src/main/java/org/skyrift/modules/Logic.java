package org.skyrift.modules;
import cn.nukkit.Player;
import org.skyrift.Main;

import java.util.ArrayList;
import java.util.List;

public class Logic {
    Main main;
    public Logic(Main main) {
        this.main = main;
    }

    public void startGame(List<String> players) {
        // Clear queue
        List<Player> playerList = new ArrayList<Player>();

        for (int i = 0; i < players.size(); i++) {
            playerList.add(main.getServer().getPlayer(players.get(i)));
        }

        for (Player player : playerList) {
            player.sendMessage(player.getName());
        }
    }
}
