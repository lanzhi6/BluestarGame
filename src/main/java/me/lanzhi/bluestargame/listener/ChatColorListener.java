package me.lanzhi.bluestargame.listener;

import me.lanzhi.bluestarapi.Api.*;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ChatColorListener implements Listener
{
    private Pattern hex=Pattern.compile("#([0-9A-Fa-fK-Ok-oRr]{6})");
    @EventHandler(priority=EventPriority.LOWEST)
    public void onPlyaerChat(AsyncPlayerChatEvent event)
    {
        if (event.isCancelled())
        {
            return;
        }
        boolean canUseColor=event.getPlayer().hasPermission("bluestargame.color");
        if (canUseColor)
        {
            event.setMessage(RGBChat.setColor(event.getMessage()));
        }
        else
        {
            event.setMessage(ChatColor.stripColor(RGBChat.setColor(event.getMessage())));
        }
    }
}
