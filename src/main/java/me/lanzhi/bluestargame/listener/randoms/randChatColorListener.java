package me.lanzhi.bluestargame.listener.randoms;

import me.lanzhi.bluestargame.bluestarapi.GradientColor;
import me.lanzhi.bluestargame.bluestarapi.RGBColor;
import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.managers.RandomEventManger;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public final class randChatColorListener implements Listener
{
    private final RandomEventManger randomEventManger;

    public randChatColorListener(BluestarGamePlugin plugin)
    {
        randomEventManger=plugin.getBluestarGameManager().getRandomEventManger();
    }

    @EventHandler
    public void onChatForRand(AsyncPlayerChatEvent event)
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
        //event.setMessage(new RGBColor(Math.abs(ThreadLocalRandom.current().nextInt()%0xffffff))+message);
    }
}
