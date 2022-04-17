package me.lanzhi.bluestargame.listener.randoms;

import me.lanzhi.bluestargame.Ctrls.CTRL;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class RandChatColor implements Listener
{

    @EventHandler(priority=EventPriority.LOW)
    public void onChatForRand(AsyncPlayerChatEvent event)
    {

        String message=event.getMessage();
        if (!CTRL.randchat())
        {
            return;
        }
        String[] chatColor={ChatColor.GREEN.toString(),ChatColor.YELLOW.toString(),ChatColor.RED.toString(),ChatColor.GOLD.toString(),ChatColor.AQUA.toString(),ChatColor.BLACK.toString(),ChatColor.BLUE.toString(),ChatColor.DARK_GRAY.toString(),ChatColor.DARK_PURPLE.toString(),ChatColor.DARK_AQUA.toString(),ChatColor.DARK_BLUE.toString(),ChatColor.DARK_GREEN.toString(),ChatColor.DARK_RED.toString(),ChatColor.GRAY.toString(),ChatColor.LIGHT_PURPLE.toString(),ChatColor.WHITE.toString()};
        int i=(int) (Math.random()*17.0D);
        event.setMessage(chatColor[i]+message);
    }
}
