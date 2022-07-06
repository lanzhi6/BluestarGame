package me.lanzhi.bluestargame.listener;

import me.lanzhi.bluestarapi.api.RGBColor;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.regex.Pattern;

public final class ChatColorListener implements Listener
{
    private Pattern hex=Pattern.compile("#([0-9A-Fa-fK-Ok-oRr]{6})");

    @EventHandler(ignoreCancelled=true, priority=EventPriority.LOWEST)
    public void onPlyaerChat(AsyncPlayerChatEvent event)
    {
        if (event.isCancelled())
        {
            return;
        }
        boolean canUseColor=event.getPlayer().hasPermission("bluestargame.color");
        if (canUseColor)
        {
            event.setMessage(RGBColor.setColor(event.getMessage()));
        }
        else
        {
            event.setMessage(ChatColor.stripColor(RGBColor.setColor(event.getMessage())));
        }
    }
}
