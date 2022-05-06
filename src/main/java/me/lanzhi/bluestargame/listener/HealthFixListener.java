package me.lanzhi.bluestargame.listener;

import me.lanzhi.bluestarapi.Api.config.YamlFile;
import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.managers.RandomEventManger;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

public final class HealthFixListener implements Listener
{
    private final BluestarGamePlugin plugin;

    public HealthFixListener(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
    }

    @EventHandler(priority=EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player=event.getPlayer();
        player.setHealthScaled(false);
        YamlFile playerData=YamlFile.loadYamlFile(new File(plugin.getPlayerData(),event.getPlayer().getUniqueId()+".yml"));
        double health=playerData.getDouble("health");
        double maxHealth=playerData.getDouble("maxhealth");
        if (maxHealth!=0)
        {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
        }
        if (health!=0)
        {
            player.setHealth(health);
        }
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                player.setHealthScaled(false);
                if (maxHealth!=0)
                {
                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
                }
            }
        }.runTaskLater(plugin,70);
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        YamlFile playerData=YamlFile.loadYamlFile(new File(plugin.getPlayerData(),event.getPlayer().getUniqueId()+".yml"));
        double maxhealth=event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        if (plugin.getBluestarGameManager().getRandomEventManger().oneHealth())
        {
            maxhealth=plugin.getBluestarGameManager().getRandomEventManger().oneHealth_playerHealth.get(event.getPlayer());
        }
        playerData.set("health",Math.min(event.getPlayer().getHealth(),maxhealth));
        playerData.set("maxhealth",maxhealth);
        playerData.save();
        plugin.getPlayerMap().set(event.getPlayer().getName(),event.getPlayer().getUniqueId()+"");
    }
}