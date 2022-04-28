package me.lanzhi.bluestargame.register;

import me.lanzhi.bluestargame.listener.*;
import me.lanzhi.bluestargame.listener.randoms.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import static me.lanzhi.bluestargame.BluestarGame.plugin;

public class registerListeners
{
    private static PluginManager pluginManager;

    public static void registerListeners()
    {
        pluginManager=Bukkit.getPluginManager();
        registerlistener(new muteListener());
        registerlistener(new HealthFixListener());
        registerlistener(new superSpongeListener());
        registerlistener(new opSwordListener());
        registerlistener(new arrowListener());
        registerlistener(new breakBedrockListener());

        registerlistener(new moreMineralListener());
        registerlistener(new randChatColorListener());
        registerlistener(new randDamageListener());
        registerlistener(new randSheepColorListener());
        registerlistener(new respawnListener());
        registerlistener(new the24PointListener());
        registerlistener(new elevatorListener());
    }

    private static void registerlistener(Listener listener)
    {
        pluginManager.registerEvents(listener,plugin);
    }
}
