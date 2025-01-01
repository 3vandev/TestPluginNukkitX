package org.skyrift.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Level;
import cn.nukkit.utils.LevelException;

public class test extends Command {
    public test() {
        super("goto", "teleports the player to a specfic world" , "/goto <world_name>");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if(!(sender instanceof Player)) { return false; }

        Player player = (Player) sender;

        if(args.length != 1) {
            player.sendMessage("Incorrect usage: /tptoworld <world_name>");
            return false;
        }

        String worldName = args[0];

        player.getServer().loadLevel(worldName);
        Level gameWorld = player.getServer().getLevelByName(worldName);

        if(gameWorld == null) {
            player.sendMessage("The game world does not exist.");

            return false;
        }

        try {
            player.teleport(gameWorld.getSpawnLocation());
        }
        catch(LevelException e) {
            player.sendMessage("There was an error");
            e.printStackTrace();
        }

        return true;
    }
}
