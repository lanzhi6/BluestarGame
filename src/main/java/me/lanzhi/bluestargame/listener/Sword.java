package me.lanzhi.bluestargame.listener;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.lanzhi.bluestargame.BluestarGame;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

public class Sword implements Listener
{
    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerDamage(EntityDamageEvent event)
    {
        if (!(event.getEntity() instanceof LivingEntity)||event.isCancelled())
        {
            return;
        }
        LivingEntity entity=(LivingEntity) event.getEntity();
        if (entity.getEquipment()==null)
        {
            return;
        }
        boolean flag=false;
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
        if (!flag)
        {
            return;
        }
        event.setCancelled(true);
        try
        {
            entity.setHealth(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        }
        catch (Throwable e)
        {
        }
        if (event instanceof EntityDamageByEntityEvent)
        {
            EntityDamageByEntityEvent event1=(EntityDamageByEntityEvent) event;
            if (event1.getDamager() instanceof Damageable)
            {
                ((Damageable) event1.getDamager()).damage(Integer.MAX_VALUE,entity);
            }
        }
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onEntityDamageByPlayer(EntityDamageByEntityEvent event)
    {
        if (!(event.getDamager() instanceof LivingEntity))
        {
            return;
        }
        if (event.isCancelled())
        {
            return;
        }
        LivingEntity entity=(LivingEntity) event.getDamager();
        if (entity.getEquipment()==null)
        {
            return;
        }
        Integer ss=null;
        boolean flag=false;
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
        if (!flag)
        {
            return;
        }
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
        }.runTaskLater(BluestarGame.plugin,1);
    }
}
