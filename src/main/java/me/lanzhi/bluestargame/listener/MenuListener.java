package me.lanzhi.bluestargame.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class MenuListener implements Listener
{
    @EventHandler
    public void onChangeHand(PlayerSwapHandItemsEvent event)
    {
        if (event.getPlayer().isSneaking())
        {
            event.setCancelled(true);
            event.getPlayer().chat("/bluestar");
        }
    }
}
