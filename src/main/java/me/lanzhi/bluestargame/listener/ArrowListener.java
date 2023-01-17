package me.lanzhi.bluestargame.listener;

import me.lanzhi.bluestargame.bluestarapi.nbt.NBTCompound;
import me.lanzhi.bluestargame.bluestarapi.nbt.NBTItem;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.util.Vector;

public final class ArrowListener implements Listener
{
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
        Vector vector=test(event.getProjectile().getVelocity(),event.getEntity().getLocation().getDirection());
        if (EntityType.ARROW!=type)
        {
            Entity entity=event.getEntity().getWorld().spawnEntity(event.getProjectile().getLocation(),type,true);
            if (entity instanceof Projectile)
            {
                ((Projectile) entity).setShooter(event.getEntity());
            }
            event.setProjectile(entity);
        }
        event.getProjectile().setVelocity(vector.multiply(v));
    }

    private static Vector test(Vector speed,Vector direction)
    {
        double norm=direction.length();
        double x=direction.getX()/norm;
        double y=direction.getY()/norm;
        double z=direction.getZ()/norm;
        double a=speed.getX();
        double b=speed.getY();
        double c=speed.getZ();
        double t=(a*x+b*y+c*z)/(x*x+y*y+z*z);
        return new Vector(x*t,y*t,z*t);
    }
}
