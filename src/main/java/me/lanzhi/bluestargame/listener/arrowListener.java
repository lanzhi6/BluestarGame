package me.lanzhi.bluestargame.listener;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.util.Vector;

public class arrowListener implements Listener
{
    @EventHandler
    public void entityArrow(EntityShootBowEvent event)
    {
        System.out.println(new NBTEntity(event.getProjectile()).getUUID("archer"));
        if (event.getBow()==null)
        {
            return;
        }
        NBTItem bow=new NBTItem(event.getBow());
        NBTCompound bluestarGame=bow.getCompound("BluestarGame");
        if (bluestarGame==null)
        {
            return;
        }
        String arrow=bluestarGame.getString("arrow");
        double v=bluestarGame.getDouble("arrowSpeed");
        v=(v!=0)?v:1;
        if (arrow==null)
        {
            return;
        }
        EntityType type=EntityType.fromName(arrow);
        if (type==null)
        {
            return;
        }
        Entity entity=event.getEntity().getWorld().spawnEntity(event.getProjectile().getLocation(),type,true);
        Vector vector=event.getProjectile().getVelocity().clone();
        vector.setX(vector.getX()*v).setY(vector.getY()*v).setZ(vector.getZ()*v);
        entity.setVelocity(vector);
        event.setProjectile(entity);
    }

    @EventHandler(priority=EventPriority.MONITOR)
    public void setArcher(EntityShootBowEvent event)
    {
        System.out.println(event.getProjectile().getUniqueId());
        NBTEntity entity=new NBTEntity(event.getProjectile());
        entity.setUUID("archer",event.getEntity().getUniqueId());
        System.out.println(event.getProjectile().getUniqueId());
    }
}
