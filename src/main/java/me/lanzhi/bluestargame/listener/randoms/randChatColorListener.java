package me.lanzhi.bluestargame.listener.randoms;

import me.arasple.mc.trchat.api.event.TrChatEvent;
import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.bluestarapi.player.chat.GradientColor;
import me.lanzhi.bluestargame.bluestarapi.player.chat.RGBColor;
import me.lanzhi.bluestargame.managers.RandomEventManger;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public final class randChatColorListener implements Listener
{
    private final RandomEventManger randomEventManger;

    public randChatColorListener(BluestarGamePlugin plugin)
    {
        randomEventManger=plugin.getBluestarGameManager().getRandomEventManger();
    }

    @EventHandler(ignoreCancelled=true, priority=EventPriority.HIGH)
    public void onChatForRand(TrChatEvent event)
    {
        String message=event.getMessage();
        if (!randomEventManger.randchat())
        {
            return;
        }
        if (!message.equals(ChatColor.stripColor(message)))
        {
            return;
        }
        event.setMessage(GradientColor.colorText(RGBColor.random(),RGBColor.random(),message));
    }
}
