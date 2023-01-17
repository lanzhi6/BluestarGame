package me.lanzhi.bluestargame.listener.randoms;

import me.lanzhi.bluestarbot.api.BluestarBot;
import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.bluestarapi.Bluestar;
import me.lanzhi.bluestargame.bluestarapi.math.Calculator;
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

    @EventHandler(priority=EventPriority.LOWEST)
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
                                     .broadcastMessage(plugin.getMessageHead()+
                                                       ChatColor.YELLOW+
                                                       event.getPlayer().getName()+
                                                       "答案正确!");
                    org.bukkit.Bukkit.getServer()
                                     .broadcastMessage(plugin.getMessageHead()+ChatColor.YELLOW+"获得1000!");
                    Bluestar.getCommandManager()
                            .useCommand(Bukkit.getConsoleSender(),
                                        "eco give "+event.getPlayer().getName()+" 1000",
                                        plugin);
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
        Calculator.Result result=new Calculator().calculator(message);
        System.out.println(result);
        if (result.hasError()||result.getAnswer()!=24)
        {
            return false;
        }
        Number[] a=result.getItemInList().toArray(new Number[0]);
        if (a.length!=4)
        {
            return false;
        }
        for (int i=0;i<4;i++)
        {
            if (!(a[i] instanceof Double))
            {
                return false;
            }
            double x=(double) a[i];
            if (x!=randomEventManger.fourNum[i])
            {
                return false;
            }
        }
        return true;
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
                    UUID uuid=BluestarBot.getBind(event.getSenderId());
                    if (uuid==null)
                    {
                        event.getGroup()
                             .sendMessageCode("[mirai:at:"+
                                              event.getSenderId()+
                                              "] 您可能在参与24点,且答案正确,但您还未绑定Minecraft账号,请绑定");
                        org.bukkit.Bukkit.getServer()
                                         .broadcastMessage(plugin.getMessageHead()+
                                                           ChatColor.YELLOW+
                                                           event.getSenderName()+
                                                           "答案正确!");
                    }
                    else
                    {
                        org.bukkit.Bukkit.getServer()
                                         .broadcastMessage(plugin.getMessageHead()+
                                                           ChatColor.YELLOW+
                                                           Bukkit.getOfflinePlayer(uuid).getName()+
                                                           "答案正确!");
                        org.bukkit.Bukkit.getServer()
                                         .broadcastMessage(plugin.getMessageHead()+ChatColor.YELLOW+"获得1000!");
                        me.lanzhi.bluestargame.bluestarapi.Bluestar.useCommand(org.bukkit.Bukkit.getConsoleSender(),
                                                          "eco give "+Bukkit.getOfflinePlayer(uuid).getName()+" 1000",
                                                          plugin);
                    }
                    randomEventManger.the24(false);
                }
            }
        }.runTaskAsynchronously(plugin);
    }
}
