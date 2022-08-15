package me.lanzhi.bluestargame.listener;

import me.lanzhi.api.GradientColor;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public final class ChatColorListener implements Listener
{
    @EventHandler(ignoreCancelled=true, priority=EventPriority.LOWEST)
    public void onPlyaerChat(AsyncPlayerChatEvent event)
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
