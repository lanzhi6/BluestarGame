package me.lanzhi.bluestargame.listener;

import me.lanzhi.bluestarapi.Api.YamlFile;
import me.lanzhi.bluestargame.BluestarGame;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.UUID;

import static me.lanzhi.bluestargame.BluestarGame.PlayerMap;
import static me.lanzhi.bluestargame.BluestarGame.plugin;

public class HealthFix implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerLoginEvent event)
    {
        Player player=event.getPlayer();
        player.setHealthScaled(false);
        YamlFile playerdata=new YamlFile(new File(BluestarGame.PlayerData,player.getUniqueId()+".yml"));
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
                Player player =event.getPlayer();
                player.setHealthScaled(false);
                YamlFile playerdata=new YamlFile(new File(BluestarGame.PlayerData,player.getUniqueId()+".yml"));
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

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        YamlFile playerData=new YamlFile(new File(BluestarGame.PlayerData,event.getPlayer().getUniqueId()+".yml"));
        playerData.set("health",event.getPlayer().getHealth());
        playerData.set("maxhealth",event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        playerData.save();
        PlayerMap.set(event.getPlayer().getName(),event.getPlayer().getUniqueId()+"");
    }
}