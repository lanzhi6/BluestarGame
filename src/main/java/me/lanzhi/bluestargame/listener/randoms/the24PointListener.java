package me.lanzhi.bluestargame.listener.randoms;

import me.dreamvoid.miraimc.api.MiraiMC;
import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.managers.RandomEventManger;
import me.lanzhi.bluestarqq.events.QQChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public final class the24PointListener implements Listener
{
    private final BluestarGamePlugin plugin;
    private final RandomEventManger randomEventManger;

    public the24PointListener(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
        randomEventManger=plugin.getBluestarGameManager().getRandomEventManger();
    }

    @EventHandler(ignoreCancelled=true, priority=EventPriority.LOWEST)
    public void onChatFor24(AsyncPlayerChatEvent event)
    {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                if (decide(event.getMessage()))
                {
                    org.bukkit.Bukkit.getServer()
                                     .broadcastMessage(plugin.getMessageHead()+ChatColor.YELLOW+event.getPlayer()
                                                                                                     .getName()+"答案正确!");
                    org.bukkit.Bukkit.getServer()
                                     .broadcastMessage(plugin.getMessageHead()+ChatColor.YELLOW+"获得1000!");
                    me.lanzhi.api.Bluestar.useCommand(org.bukkit.Bukkit.getConsoleSender(),
                                                      "eco give "+event.getPlayer().getName()+" 1000",
                                                      plugin);
                    randomEventManger.the24(false);
                }
            }
        }.runTaskAsynchronously(plugin);
    }

    @EventHandler(ignoreCancelled=true, priority=EventPriority.MONITOR)
    public void onQQChatFor24(QQChatEvent event)
    {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                if (decide(event.getMessage()))
                {
                    UUID uuid=MiraiMC.getBind(event.getSenderId());
                    if (uuid==null||"".equals(uuid))
                    {
                        event.getGroup()
                             .sendMessageMirai("[mirai:at:"+event.getSenderId()+"] 您可能在参与24点,且答案正确,但您还未绑定Minecraft账号,请绑定");
                        org.bukkit.Bukkit.getServer()
                                         .broadcastMessage(plugin.getMessageHead()+ChatColor.YELLOW+event.getSenderName()+"答案正确!");
                    }
                    else
                    {
                        org.bukkit.Bukkit.getServer()
                                         .broadcastMessage(plugin.getMessageHead()+ChatColor.YELLOW+Bukkit.getOfflinePlayer(
                                                 uuid).getName()+"答案正确!");
                        org.bukkit.Bukkit.getServer()
                                         .broadcastMessage(plugin.getMessageHead()+ChatColor.YELLOW+"获得1000!");
                        me.lanzhi.api.Bluestar.useCommand(org.bukkit.Bukkit.getConsoleSender(),
                                                          "eco give "+Bukkit.getOfflinePlayer(uuid).getName()+" 1000",
                                                          plugin);
                    }
                    randomEventManger.the24(false);
                }
            }
        }.runTaskAsynchronously(plugin);
    }

    private boolean decide(String message)
    {
        if (!randomEventManger.the24())
        {
            return false;
        }
        String[] ans=RandomEventManger.mathFor24Points.yunsuan(message);
        if (ans==null)
        {
            return false;
        }
        try
        {
            if ((int) Double.parseDouble(ans[0])!=24)
            {
                return false;
            }
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        int[] a=randomEventManger.fourNum;
        boolean fflag=true;
        for (int i=1;i<=4;i++)
        {
            boolean flag=false;
            for (int j=0;j<4;j++)
            {
                if ((ans[i].equals(a[j]+""))&&(a[j]!=-1))
                {
                    a[j]=-1;
                    flag=true;
                    break;
                }
            }
            if (!flag)
            {
                fflag=false;
                break;
            }
        }
        return fflag;
    }
}
