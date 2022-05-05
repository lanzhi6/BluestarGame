package me.lanzhi.bluestargame.listener.randoms;

import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.managers.RandomEventManger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public final class respawnListener implements Listener
{
    private final BluestarGamePlugin plugin;
    private final RandomEventManger randomEventManger;

    public respawnListener(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
        randomEventManger=plugin.getBluestarGameManager().getRandomEventManger();
    }

    @EventHandler(priority=EventPriority.MONITOR)
    public void onEntiyDie(EntityDeathEvent event)
    {
        if (event.getEntity() instanceof Player)
        {
            ((Player) event.getEntity()).setHealthScaled(false);
        }
        if (event.getEntity() instanceof Player||!randomEventManger.respawn())
        {
            return;
        }
        event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(),event.getEntityType());
    }
}
