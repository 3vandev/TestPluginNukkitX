package org.skyrift.modules;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.skyrift.Main;
import org.skyrift.modules.Logic;

// Simple json database for matchmaking
public class MatchmakingQueue {
    private final Config queueConfig;
    private Logic logic;

    public MatchmakingQueue(Main main) {
        queueConfig = new Config(new File("plugins/skyrift/queue.yml"), Config.YAML);

        if(!queueConfig.exists("queue")) {
            queueConfig.set("queue", new ArrayList<String>());
            queueConfig.save();
        }

        this.logic = main.getLogic();
    }

    public void addPlayer(Player player) {
        String name = player.getName();

        List<String> queue = queueConfig.getStringList("queue");
        queue.add(name);

        queueConfig.set("queue", queue);

        // Game start
        if(queue.size() >= 1) {
            this.logic.startGame(queue);
            this.clearQueue();
        }
    }

    public void removePlayer(Player player) {
        String name = player.getName();

        List<String> queue = queueConfig.getStringList("queue");
        queue.remove(name);

        queueConfig.set("queue", queue);
    }

    // When the game starts a new queue will need to be initialised
    public void clearQueue() {
        queueConfig.set("queue", new ArrayList<String>());
    }

    public boolean isPlayerInQueue(Player player) {
        String name = player.getName();
        List<String> queue = queueConfig.getStringList("queue");

        return queue.contains(name);
    }

    public List<String> getQueue() {
        return queueConfig.getStringList("queue");
    }
}
