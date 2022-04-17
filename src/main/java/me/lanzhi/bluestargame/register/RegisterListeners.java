package me.lanzhi.bluestargame.register;

import me.lanzhi.bluestargame.listener.*;
import me.lanzhi.bluestargame.listener.randoms.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import static me.lanzhi.bluestargame.BluestarGame.plugin;

public class RegisterListeners
{
    private static PluginManager pluginManager;

    public static void registerListeners()
    {
        pluginManager=Bukkit.getPluginManager();
        registerlistener(new DoMute());
        registerlistener(new HealthFix());
        registerlistener(new PlaceSponge());
        registerlistener(new Sword());

        registerlistener(new MoreMineral());
        registerlistener(new RandChatColor());
        registerlistener(new RandDamage());
        registerlistener(new RandSheepColor());
        registerlistener(new Respawn());
        registerlistener(new The24Point());
        registerlistener(new ElevatorListener());
    }

    private static void registerlistener(Listener listener)
    {
        pluginManager.registerEvents(listener,plugin);
    }
}
