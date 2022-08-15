package me.lanzhi.bluestargame.listener.randoms;

import me.lanzhi.api.config.YamlFile;
import me.lanzhi.bluestargame.BluestarGamePlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;

public final class oneHealthListener implements Listener
{
    private final BluestarGamePlugin plugin;

    public oneHealthListener(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        if (!plugin.getBluestarGameManager().getRandomEventManger().oneHealth())
        {
            return;
        }
        double maxHealth=YamlFile.loadYamlFile(new File(plugin.getPlayerData(),event.getPlayer().getUniqueId()+".yml"))
                                 .getDouble("maxhealth");
        plugin.getBluestarGameManager().getRandomEventManger().oneHealth_playerHealth.put(event.getPlayer(),
                                                                                          maxHealth!=0?maxHealth:20);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        if (!plugin.getBluestarGameManager().getRandomEventManger().oneHealth())
        {
            return;
        }
        plugin.getBluestarGameManager().getRandomEventManger().oneHealth_playerHealth.remove(event.getPlayer());
    }
}
