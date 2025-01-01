package org.skyrift.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Level;
import cn.nukkit.utils.LevelException;
import cn.nukkit.utils.TextFormat;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import org.skyrift.Main;
import org.w3c.dom.Text;
import org.skyrift.modules.MatchmakingQueue;

public class Queue extends Command {
    private MatchmakingQueue queue;

    public Queue(Main main) {
        super("queue", "Join/leave the queue" , "/queue <action>");

        queue = new MatchmakingQueue(main);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if(!(sender instanceof Player)) { return false; }

        Player player = (Player) sender;

        if(args.length != 1) {
            player.sendMessage("Incorrect usage: /queue join || leave");
            return false;
        }

        String action = args[0];

        if(action.equals("join")) {
            if(queue.isPlayerInQueue(player)) {
                player.sendMessage("You are already in the queue!");
                return false;
            }
            player.getServer().broadcastMessage(TextFormat.GREEN + player.getNameTag() + TextFormat.YELLOW + " has joined the queue");

            queue.addPlayer(player);
        }
        else if(action.equals("leave")) {
            if(!queue.isPlayerInQueue(player)) {
                player.sendMessage("You are already not in the queue!");
                return false;
            }

            player.getServer().broadcastMessage(TextFormat.RED + player.getNameTag() + TextFormat.YELLOW + " has left the queue");

            queue.removePlayer(player);
        }
        else {
            player.sendMessage("Unknown action: " + action);
            return false;
        }

        player.getServer().broadcastMessage(queue.getQueue().toString());

//        player.getServer().loadLevel(worldName);
//        Level gameWorld = player.getServer().getLevelByName(worldName);
//
//        if(gameWorld == null) {
//            player.sendMessage("The game world does not exist.");
//
//            return false;
//        }
//
//        try {
//            player.teleport(gameWorld.getSpawnLocation());
//        }
//        catch(LevelException e) {
//            player.sendMessage("There was an error");
//            e.printStackTrace();
//        }

        return true;
    }
}
