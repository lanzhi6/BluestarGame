package me.lanzhi.bluestargame.register;

import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.commands.*;

public final class CommandRegister
{
    private final BluestarGamePlugin plugin;

    public CommandRegister(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
    }

    public void registerCommands()
    {
        plugin.getCommand("bluestargame").setExecutor(new BluestarGameCommand(plugin));
        plugin.getCommand("bluestargamelist").setExecutor(new BluestarGameListCommand(plugin));
        plugin.getCommand("muted").setExecutor(new MutedCommand(plugin));
        plugin.getCommand("chat").setExecutor(new ChatCommand(plugin));
        plugin.getCommand("bluestaritem").setExecutor(new BluestarItemCommand(plugin));
        plugin.getCommand("elevator").setExecutor(new ElevatorCommand(plugin));
        plugin.getCommand("xiaomobank").setExecutor(new XiaoMoBankCommand(plugin));
        plugin.getCommand("cmdbag").setExecutor(new CmdBagCommand());
        plugin.getCommand("setnick").setExecutor(new setNickCommand(plugin));
        plugin.getCommand("itemedit").setExecutor(new ItemEditCommand(plugin));
        plugin.getCommand("tpr").setExecutor(new RandomTPCommand(plugin));
    }
}
