package me.lanzhi.bluestargame.listener;

import io.papermc.paper.event.player.PlayerNameEntityEvent;
import me.lanzhi.bluestargame.bluestarapi.player.chat.RGBColor;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public final class EasterEggListener implements Listener
{
    @EventHandler(ignoreCancelled=true)
    public void onPlayerNameEntity(PlayerNameEntityEvent event)
    {
        if (event.getName() instanceof TextComponent name)
        {
            if (name.content().toLowerCase().contains("bluestar"))
            {
                event.getPlayer()
                     .sendMessage(RGBColor.random()+"你好!恭喜你发现了一个隐藏的"+RGBColor.random()+"彩"+RGBColor.random()+"蛋!");
            }
        }
    }
}
