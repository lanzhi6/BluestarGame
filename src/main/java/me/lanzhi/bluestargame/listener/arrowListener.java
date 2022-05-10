package me.lanzhi.bluestargame.listener;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.util.Vector;

public final class arrowListener implements Listener
{
    public static double decide(double x,double y,double z)
    {
        x=Math.abs(x);
        y=Math.abs(y);
        z=Math.abs(z);
        return Math.min(10D/x,Math.min(10D/y,10D/z))-0.01D;
    }

    @EventHandler
    public void entityArrow(EntityShootBowEvent event)
    {
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
        v=(v>0)?v:1;
        if (arrow==null)
        {
            return;
        }
        EntityType type=EntityType.fromName(arrow);
        if (type==null)
        {
            return;
        }
        Vector vector=event.getProjectile().getVelocity().clone();
        if (EntityType.ARROW!=type)
        {
            Entity entity=event.getEntity().getWorld().spawnEntity(event.getProjectile().getLocation(),type,true);
            event.setProjectile(entity);
        }
        double vv=Math.min(v,decide(vector.getX(),vector.getY(),vector.getZ()));
        vector.setX(vector.getX()*vv).setY(vector.getY()*vv).setZ(vector.getZ()*vv);
        event.getProjectile().setVelocity(vector);
    }
}
