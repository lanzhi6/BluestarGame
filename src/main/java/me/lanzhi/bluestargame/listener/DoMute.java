package me.lanzhi.bluestargame.listener;

import me.lanzhi.bluestargame.commands.mutedCommand;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Calendar;
import java.util.Date;

import static me.lanzhi.bluestargame.BluestarGame.BluestarDataFormat;
import static me.lanzhi.bluestargame.BluestarGame.messageHead;

public class DoMute implements Listener
{
    @EventHandler
    public void onChatForMuted(AsyncPlayerChatEvent event)
    {
        if (event.isCancelled())
        {
            return;
        }
        if (mutedCommand.muted.getKeys(false).contains(event.getPlayer().getUniqueId().toString()))
        {
            boolean isMuted=false;
            Date muted;
            try
            {
                muted=BluestarDataFormat.parse(mutedCommand.muted.getString(event.getPlayer().getUniqueId().toString()));
            }
            catch (Throwable e)
            {
                isMuted=true;
                muted=Calendar.getInstance().getTime();
            }
            if (isMuted||muted.after(Calendar.getInstance().getTime()))
            {
                event.getPlayer().sendMessage(messageHead+ChatColor.RED+"你已被禁言,有疑问请联系管理员");
                event.setCancelled(true);
            }
            else
            {
                mutedCommand.muted.set(event.getPlayer().getUniqueId().toString(),null);
            }
        }
    }
}
