package me.lanzhi.bluestargame.listener;

import me.lanzhi.bluestarapi.Api.config.YamlFile;
import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.managers.RandomEventManger;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

public final class HealthFixListener implements Listener
{
    private final BluestarGamePlugin plugin;
    private final RandomEventManger randomEventManger;

    public HealthFixListener(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
        randomEventManger=plugin.getBluestarGameManager().getRandomEventManger();
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerLoginEvent event)
    {
        Player player=event.getPlayer();
        player.setHealthScaled(false);
        YamlFile playerdata=new YamlFile(new File(plugin.getPlayerData(),player.getUniqueId()+".yml"));
        if (playerdata.getDouble("maxhealth")!=0)
        {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(playerdata.getDouble("maxhealth"));
        }
        if (playerdata.getDouble("health")!=0)
        {
            player.setHealth(playerdata.getDouble("health"));
        }
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                Player player=event.getPlayer();
                player.setHealthScaled(false);
                YamlFile playerdata=new YamlFile(new File(plugin.getPlayerData(),player.getUniqueId()+".yml"));
                if (playerdata.getDouble("maxhealth")!=0)
                {
                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(playerdata.getDouble("maxhealth"));
                }
                if (playerdata.getDouble("health")!=0)
                {
                    player.setHealth(playerdata.getDouble("health"));
                }
            }
        }.runTaskLater(plugin,70);
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        YamlFile playerData=new YamlFile(new File(plugin.getPlayerData(),event.getPlayer().getUniqueId()+".yml"));
        double maxhealth=event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        playerData.set("health",Math.min(event.getPlayer().getHealth(),maxhealth));
        playerData.set("maxhealth",maxhealth);
        playerData.save();
        plugin.getPlayerMap().set(event.getPlayer().getName(),event.getPlayer().getUniqueId()+"");
    }
}