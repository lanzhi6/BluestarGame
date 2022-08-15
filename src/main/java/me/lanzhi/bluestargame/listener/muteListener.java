package me.lanzhi.bluestargame.listener;

import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.managers.RandomEventManger;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Calendar;
import java.util.Date;

public final class muteListener implements Listener
{
    private final BluestarGamePlugin plugin;
    private final RandomEventManger randomEventManger;

    public muteListener(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
        randomEventManger=plugin.getBluestarGameManager().getRandomEventManger();
    }

    @EventHandler
    public void onChatForMuted(AsyncPlayerChatEvent event)
    {
        if (event.isCancelled())
        {
            return;
        }
        if (plugin.getBluestarGameManager()
                  .getMuted()
                  .getKeys(false)
                  .contains(event.getPlayer().getUniqueId().toString()))
        {
            boolean isMuted=false;
            Date muted;
            try
            {
                muted=plugin.getDateFormat()
                            .parse(plugin.getBluestarGameManager()
                                         .getMuted()
                                         .getString(event.getPlayer().getUniqueId().toString()));
            }
            catch (Throwable e)
            {
                isMuted=true;
                muted=Calendar.getInstance().getTime();
            }
            if (isMuted||muted.after(Calendar.getInstance().getTime()))
            {
                event.getPlayer().sendMessage(plugin.getMessageHead()+ChatColor.RED+"你已被禁言,有疑问请联系管理员");
                event.setCancelled(true);
            }
            else
            {
                plugin.getBluestarGameManager().getMuted().set(event.getPlayer().getUniqueId().toString(),null);
            }
        }
    }
}
