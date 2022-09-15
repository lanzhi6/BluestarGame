package me.lanzhi.bluestargame.listener;

import me.lanzhi.bluestargame.bluestarapi.GradientColor;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public final class ChatColorListener implements Listener
{
    @EventHandler(ignoreCancelled=true, priority=EventPriority.LOWEST)
    public void onPlyaerChat(AsyncPlayerChatEvent event)
    {
        run(event);
    }

    @EventHandler(ignoreCancelled=true, priority=EventPriority.LOWEST)
    public void onPlyaerChat(AsyncPlayerChatPreviewEvent event)
    {
        run(event);
    }

    @EventHandler

    public void run(AsyncPlayerChatEvent event)
    {
        boolean canUseColor=event.getPlayer().hasPermission("bluestargame.color");
        if (canUseColor)
        {
            event.setMessage(GradientColor.setColor(event.getMessage()));
        }
        else
        {
            event.setMessage(ChatColor.stripColor(GradientColor.setColor(event.getMessage())));
        }
    }
}
