package me.lanzhi.bluestargame.listener.randoms;

import me.lanzhi.bluestargame.BluestarGame;
import me.lanzhi.bluestargame.Ctrls.CTRL;
import me.lanzhi.bluestarqq.events.QQChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;
import me.dreamvoid.miraimc.api.MiraiMC;

import java.util.UUID;

import static me.lanzhi.bluestargame.BluestarGame.*;

public class The24Point implements Listener
{
    @EventHandler
    public void onChatFor24(AsyncPlayerChatEvent event)
    {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                if (decide(event.getMessage()))
                {
                    org.bukkit.Bukkit.getServer().broadcastMessage(messageHead+ChatColor.YELLOW + event.getPlayer().getName() + "答案正确!");
                    org.bukkit.Bukkit.getServer().broadcastMessage(messageHead+ChatColor.YELLOW + "获得1000!");
                    me.lanzhi.bluestarapi.Api.Bluestar.useCommand(org.bukkit.Bukkit.getConsoleSender(), "eco give "+event.getPlayer().getName()+" 1000", BluestarGame.plugin);
                    CTRL.the24(false);
                }
            }
        }.runTaskAsynchronously(BluestarGame.plugin);
    }

    @EventHandler
    public void onQQChatFor24(QQChatEvent event)
    {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                if (decide(event.getMessage()))
                {
                    String uuid=MiraiMC.getBinding(event.getSenderId());
                    if (uuid==null||"".equals(uuid))
                    {
                        event.getGroup().sendMessageMirai("[mirai:at:"+event.getSenderId()+"] 您可能在参与24点,且答案正确,但您还未绑定Minecraft账号,请绑定");
                        org.bukkit.Bukkit.getServer().broadcastMessage(messageHead+ChatColor.YELLOW + event.getSenderName() + "答案正确!");
                    }
                    else
                    {
                        org.bukkit.Bukkit.getServer().broadcastMessage(messageHead+ChatColor.YELLOW + Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName() + "答案正确!");
                        org.bukkit.Bukkit.getServer().broadcastMessage(messageHead+ChatColor.YELLOW + "获得1000!");
                        me.lanzhi.bluestarapi.Api.Bluestar.useCommand(org.bukkit.Bukkit.getConsoleSender(), "eco give "+Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName()+" 1000", BluestarGame.plugin);
                    }
                    CTRL.the24(false);
                }
            }
        }.runTaskAsynchronously(BluestarGame.plugin);
    }

    private boolean decide(String message)
    {
        if (!CTRL.the24())
        {
            return false;
        }
        String[] ans = me.lanzhi.bluestargame.Ctrls.math.yunsuan(message);
        if (ans == null)
        {
            return false;
        }
        try
        {
            if ((int) Double.parseDouble(ans[0]) != 24)
            {
                return false;
            }
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        int[] a = CTRL.fourNum;
        boolean fflag = true;
        for (int i = 1; i <= 4; i++)
        {
            boolean flag = false;
            for (int j = 0; j < 4; j++)
            {
                if ((ans[i].equals(a[j] + "")) && (a[j] != -1))
                {
                    a[j] = -1;
                    flag = true;
                    break;
                }
            }
            if (!flag)
            {
                fflag = false;
                break;
            }
        }
        return fflag;
    }
}
