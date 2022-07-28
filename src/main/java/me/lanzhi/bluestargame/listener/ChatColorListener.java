package me.lanzhi.bluestargame.listener;

import me.lanzhi.bluestarapi.api.GradientColor;
import me.lanzhi.bluestarapi.api.RGBColor;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.regex.Pattern;

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
