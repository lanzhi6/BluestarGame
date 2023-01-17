package me.lanzhi.bluestargame.listener;

import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.bluestarapi.nbt.NBTCompound;
import me.lanzhi.bluestargame.bluestarapi.nbt.NBTEntity;
import me.lanzhi.bluestargame.bluestarapi.nbt.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public final class opSwordListener implements Listener
{
    private final BluestarGamePlugin plugin;

    public opSwordListener(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerDamage(EntityDamageEvent event)
    {
        if (!(event.getEntity() instanceof LivingEntity entity)||event instanceof EntityDamageByEntityEvent)
        {
            return;
        }
        if (!hasOpSword(entity))
        {
            return;
        }
        event.setCancelled(true);
        try
        {
            entity.setHealth(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        }
        catch (Throwable ignored)
        {
        }
    }

    private static boolean hasOpSword(Entity e)
    {
        if (!(e instanceof LivingEntity entity))
        {
            return false;
        }
        boolean flag=false;
        if (entity.getEquipment()==null)
        {
            return false;
        }
        if (!entity.getEquipment().getItemInMainHand().getType().isAir())
        {
            NBTItem item=new NBTItem(entity.getEquipment().getItemInMainHand());
            NBTCompound bluestargame=item.getCompound("BluestarGame");
            if (bluestargame!=null&&bluestargame.getBoolean("sword"))
            {
                flag=true;
            }
        }
        if (!entity.getEquipment().getItemInOffHand().getType().isAir()&&!flag)
        {
            NBTItem item=new NBTItem(entity.getEquipment().getItemInOffHand());
            NBTCompound bluestargame=item.getCompound("BluestarGame");
            if (bluestargame!=null&&bluestargame.getBoolean("sword"))
            {
                flag=true;
            }
        }
        return flag;
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerDamage1(EntityDamageByEntityEvent event)
    {
        if (!hasOpSword(event.getEntity()))
        {
            return;
        }
        if (hasOpSword(event.getDamager()))
        {
            return;
        }
        if (event.getDamager() instanceof Projectile projectile&&hasOpSword(getOwner(projectile)))
        {
            return;
        }

        event.setCancelled(true);
        if (event.getDamager() instanceof Damageable)
        {
            ((Damageable) event.getDamager()).damage(Integer.MAX_VALUE,event.getEntity());
            return;
        }
        if (event.getDamager() instanceof Projectile projectile)
        {
            Entity entity1=getOwner(projectile);
            if (!(entity1 instanceof Damageable))
            {
                return;
            }
            ((Damageable) entity1).damage(Integer.MAX_VALUE,event.getEntity());
        }
    }

    private static Entity getOwner(Entity entity)
    {
        UUID uuid=new NBTEntity(entity).getUUID("Owner");
        if (uuid==null)
        {
            return null;
        }
        return Bukkit.getEntity(uuid);
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onEntityDamageByPlayer(EntityDamageByEntityEvent event)
    {
        if (!(event.getDamager() instanceof LivingEntity entity))
        {
            return;
        }
        if (event.isCancelled())
        {
            return;
        }
        if (!hasOpSword(entity))
        {
            return;
        }
        Integer ss=null;
        event.setDamage(Integer.MAX_VALUE);
        if (event.getEntity() instanceof Player)
        {
            PlayerInventory inventory=((Player) event.getEntity()).getInventory();
            inventory.setChestplate(new ItemStack(Material.AIR));
            inventory.setHelmet(new ItemStack(Material.AIR));
            inventory.setBoots(new ItemStack(Material.AIR));
            inventory.setLeggings(new ItemStack(Material.AIR));
        }
        Damageable entity1=(Damageable) event.getEntity();
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                if (entity1.getHealth()!=0)
                {
                    entity1.setHealth(0);
                }
            }
        }.runTaskLater(plugin,1);
    }
}
