package org.skyrift;

import cn.nukkit.plugin.PluginBase;
import org.skyrift.commands.test;
import org.skyrift.events.PlayerJoin;
import org.skyrift.commands.Queue;
import org.skyrift.modules.Logic;

public class Main extends PluginBase {
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        this.getServer().getCommandMap().register("skyrift", new test());
        this.getServer().getCommandMap().register("queue", new Queue(this));
    }
}