package me.lanzhi.bluestargame.listener;

import me.lanzhi.bluestargame.BluestarGame;
import me.lanzhi.bluestargame.Type.muted;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static me.lanzhi.bluestargame.BluestarGame.*;

public class DoMute implements Listener
{
    @EventHandler
    public void onChatForMuted(AsyncPlayerChatEvent event)
    {
        if(event.isCancelled())
        {
            return;
        }
        try
        {
            if(((muted) BluestarGame.Data.get("muted")).get(event.getPlayer().getName()))
            {
                event.getPlayer().sendMessage(messageHead+ChatColor.RED+"你已被禁言,有疑问请联系管理员");
                event.setCancelled(true);
            }
        }
        catch(NullPointerException e)
        {}
    }
}
