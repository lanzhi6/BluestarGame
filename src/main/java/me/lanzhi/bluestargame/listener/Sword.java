package me.lanzhi.bluestargame.listener;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.lanzhi.bluestargame.BluestarGame;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
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
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDamage(EntityDamageEvent event)
    {
        if(!(event.getEntity() instanceof Player))
        {
            return;
        }
        Player player = (Player)event.getEntity();
        boolean flag = false;
        if (!player.getInventory().getItemInMainHand().getType().isAir())
        {
            NBTItem item=new NBTItem(player.getInventory().getItemInMainHand());
            NBTCompound bluestargame=item.getCompound("BluestarGame");
            if(bluestargame!=null&&bluestargame.getBoolean("sword"))
            {
                flag = true;
            }
        }
        if (!player.getInventory().getItemInOffHand().getType().isAir()&&!flag)
        {
            NBTItem item=new NBTItem(player.getInventory().getItemInOffHand());
            NBTCompound bluestargame=item.getCompound("BluestarGame");
            if(bluestargame!=null&&bluestargame.getBoolean("sword"))
            {
                flag = true;
            }
        }
        if (!flag)
        {
            return;
        }
        event.setCancelled(true);
        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByPlayer(EntityDamageByEntityEvent event)
    {
        if(!(event.getDamager() instanceof Player)){return;}
        Player player = (Player)event.getDamager();
        boolean flag = false;
        if (!player.getInventory().getItemInMainHand().getType().isAir())
        {
            NBTItem item=new NBTItem(player.getInventory().getItemInMainHand());
            NBTCompound bluestargame=item.getCompound("BluestarGame");
            if(bluestargame!=null&&bluestargame.getBoolean("sword"))
            {
                flag = true;
            }
        }
        if (!player.getInventory().getItemInOffHand().getType().isAir()&&!flag)
        {
            NBTItem item=new NBTItem(player.getInventory().getItemInOffHand());
            NBTCompound bluestargame=item.getCompound("BluestarGame");
            if(bluestargame!=null&&bluestargame.getBoolean("sword"))
            {
                flag = true;
            }
        }
        if (!flag)
        {
            return;
        }
        event.setDamage(Integer.MAX_VALUE);
        if (event.getEntity() instanceof Player)
        {
            PlayerInventory inventory=((Player)event.getEntity()).getInventory();
            inventory.setChestplate(new ItemStack(Material.AIR));
            inventory.setHelmet(new ItemStack(Material.AIR));
            inventory.setBoots(new ItemStack(Material.AIR));
            inventory.setLeggings(new ItemStack(Material.AIR));
        }
        Damageable entity=(Damageable)event.getEntity();
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                if (entity.getHealth()!=0)
                {
                    entity.setHealth(0);
                }
            }
        }.runTaskLater(BluestarGame.plugin,1);
    }
}
