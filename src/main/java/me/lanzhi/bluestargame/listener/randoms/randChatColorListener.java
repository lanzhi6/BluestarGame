package me.lanzhi.bluestargame.listener.randoms;

import me.lanzhi.bluestarapi.Api.RGBChat;
import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.listener.ChatColorListener;
import me.lanzhi.bluestargame.managers.RandomEventManger;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.concurrent.ThreadLocalRandom;

public final class randChatColorListener implements Listener
{
    private RandomEventManger randomEventManger;

    public randChatColorListener(BluestarGamePlugin plugin)
    {
        randomEventManger=plugin.getBluestarGameManager().getRandomEventManger();
    }

    @EventHandler(priority=EventPriority.LOW)
    public void onChatForRand(AsyncPlayerChatEvent event)
    {

        String message=event.getMessage();
        if (!randomEventManger.randchat())
        {
            return;
        }
        event.setMessage(new RGBChat(Math.abs(ThreadLocalRandom.current().nextInt()%0xffffff))+message);
    }
}
