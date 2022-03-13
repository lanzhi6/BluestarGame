package me.lanzhi.bluestargame.listener.randoms;

import me.lanzhi.bluestargame.Ctrls.CTRL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class Respawn implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntiyDie(EntityDeathEvent event)
    {
        if (event.getEntity() instanceof Player)
        {
            ((Player) event.getEntity()).setHealthScaled(false);
        }
        if (event.getEntity() instanceof Player||!CTRL.respawn())
        {
            return;
        }
        event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(),event.getEntityType());
    }
}
