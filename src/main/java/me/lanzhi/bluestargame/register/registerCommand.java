package me.lanzhi.bluestargame.register;

import me.lanzhi.bluestargame.commands.*;

import static me.lanzhi.bluestargame.BluestarGame.plugin;

public class registerCommand
{
    public static void registerCommands()
    {
        plugin.getCommand("bluestargame").setExecutor(new me.lanzhi.bluestargame.commands.maincommand());
        plugin.getCommand("bluestargamelist").setExecutor(new bsgamelist());
        plugin.getCommand("muted").setExecutor(new mutedCommand());
        plugin.getCommand("chat").setExecutor(new chat());
        plugin.getCommand("bluestaritem").setExecutor(new bluestaritem());
        plugin.getCommand("elevator").setExecutor(new elevatorCommand());
        plugin.getCommand("xiaomobank").setExecutor(new xiaoMoBankCommand());
        plugin.getCommand("cmdbag").setExecutor(new commandBag());
    }
}
