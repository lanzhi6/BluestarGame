package me.lanzhi.bluestargame.register;

import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.listener.*;
import me.lanzhi.bluestargame.listener.randoms.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public final class ListenersRegister
{
    private final PluginManager pluginManager;
    private final BluestarGamePlugin plugin;

    public ListenersRegister(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
        this.pluginManager=Bukkit.getPluginManager();
    }

    public void registerListeners()
    {
        registerlistener(new muteListener(plugin));
        registerlistener(new HealthFixListener(plugin));
        registerlistener(new superSpongeListener(plugin));
        registerlistener(new opSwordListener(plugin));
        registerlistener(new arrowListener());
        registerlistener(new breakBedrockListener());
        registerlistener(new elevatorListener(plugin));
        registerlistener(new effectListener());
        registerlistener(new ChatColorListener());

        registerlistener(new moreMineralListener(plugin));
        registerlistener(new randChatColorListener(plugin));
        registerlistener(new randDamageListener(plugin));
        registerlistener(new randSheepColorListener(plugin));
        registerlistener(new respawnListener(plugin));
        registerlistener(new the24PointListener(plugin));
        registerlistener(new oneHealthListener(plugin));
    }

    private void registerlistener(Listener listener)
    {
        pluginManager.registerEvents(listener,plugin);
    }
}
