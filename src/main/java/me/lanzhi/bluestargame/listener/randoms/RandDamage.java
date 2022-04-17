package me.lanzhi.bluestargame.listener.randoms;

import me.lanzhi.bluestargame.Ctrls.CTRL;
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

public class RandDamage implements Listener
{

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onEntityDamage(EntityDamageEvent event)
    {
        if (!CTRL.randdamage()||event.isCancelled())
        {
            return;
        }
        Entity entity=event.getEntity();
        double hurt=(Math.random()-0.3D)*event.getDamage()*5.0D;
        entity.sendMessage(ChatColor.GREEN+"[BluestarGame]随机伤害");
        entity.sendMessage(ChatColor.YELLOW+"原伤害:"+event.getDamage()+",随机伤害:"+hurt);
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
