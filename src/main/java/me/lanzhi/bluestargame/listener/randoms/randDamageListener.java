package me.lanzhi.bluestargame.listener.randoms;

import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.managers.RandomEventManger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public final class randDamageListener implements Listener
{
    private final BluestarGamePlugin plugin;
    private final RandomEventManger randomEventManger;
    EntityDamageEvent lastEvent=null;

    public randDamageListener(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
        randomEventManger=plugin.getBluestarGameManager().getRandomEventManger();
    }

    @EventHandler(ignoreCancelled=true,priority=EventPriority.HIGHEST)
    synchronized public void onEntityDamage(EntityDamageEvent event)
    {
        if (event.equals(lastEvent))
        {
            System.out.println("yes");
            return;
        }
        lastEvent=event;
        if (!randomEventManger.randdamage())
        {
            return;
        }
        Entity entity=event.getEntity();
        double hurt=(Math.random()-0.3D)*event.getDamage()*5.0D;
        entity.sendMessage(plugin.getMessageHead()+"随机伤害");
        entity.sendMessage(ChatColor.YELLOW+"原伤害:"+event.getDamage()+",随机伤害:"+String.format("%.2f",hurt));
        if (hurt>0.0D)
        {
            event.setDamage(hurt);
        }
        else if (entity instanceof Damageable)
        {
            event.setCancelled(true);
            ((Damageable) entity).setHealth(Double.min(((Damageable) entity).getHealth()-hurt,((Attributable) entity).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()));
        }
        else
        {
            Bukkit.getLogger().warning("错误!生物类型为"+entity.getType());
        }
    }
}
