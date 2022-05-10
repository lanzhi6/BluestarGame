package me.lanzhi.bluestargame.listener;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import de.tr7zw.nbtapi.NBTItem;
import me.lanzhi.bluestarapi.Api.RGBChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public final class effectListener implements Listener
{
    @EventHandler(priority=EventPriority.MONITOR)
    public void onEntityDamagePlayer(EntityDamageByEntityEvent event)
    {
        if (event.isCancelled()||!(event.getEntity() instanceof Player))
        {
            return;
        }
        PlayerInventory inventory=((Player) event.getEntity()).getInventory();
        setEffect(event.getEntity(),inventory.getItemInOffHand(),effectEventType.DamageByEntityToUs);
        setEffect(event.getEntity(),inventory.getItemInMainHand(),effectEventType.DamageByEntityToUs);
        setEffect(event.getEntity(),inventory.getBoots(),effectEventType.DamageByEntityToUs);
        setEffect(event.getEntity(),inventory.getChestplate(),effectEventType.DamageByEntityToUs);
        setEffect(event.getEntity(),inventory.getLeggings(),effectEventType.DamageByEntityToUs);
        setEffect(event.getEntity(),inventory.getHelmet(),effectEventType.DamageByEntityToUs);
        setEffect(event.getDamager(),inventory.getItemInOffHand(),effectEventType.DamageByEntityToIt);
        setEffect(event.getDamager(),inventory.getItemInMainHand(),effectEventType.DamageByEntityToIt);
        setEffect(event.getDamager(),inventory.getBoots(),effectEventType.DamageByEntityToIt);
        setEffect(event.getDamager(),inventory.getChestplate(),effectEventType.DamageByEntityToIt);
        setEffect(event.getDamager(),inventory.getLeggings(),effectEventType.DamageByEntityToIt);
        setEffect(event.getDamager(),inventory.getHelmet(),effectEventType.DamageByEntityToIt);
    }

    @EventHandler(priority=EventPriority.MONITOR)
    public void onPlayerDamageEntity(EntityDamageByEntityEvent event)
    {
        if (event.isCancelled()||!(event.getDamager() instanceof Player))
        {
            return;
        }
        PlayerInventory inventory=((Player) event.getDamager()).getInventory();

        setEffect(event.getEntity(),inventory.getItemInOffHand(),effectEventType.DamageEntityToIt);
        setEffect(event.getEntity(),inventory.getItemInMainHand(),effectEventType.DamageEntityToIt);
        setEffect(event.getEntity(),inventory.getBoots(),effectEventType.DamageEntityToIt);
        setEffect(event.getEntity(),inventory.getChestplate(),effectEventType.DamageEntityToIt);
        setEffect(event.getEntity(),inventory.getLeggings(),effectEventType.DamageEntityToIt);
        setEffect(event.getEntity(),inventory.getHelmet(),effectEventType.DamageEntityToIt);
        setEffect(event.getDamager(),inventory.getItemInOffHand(),effectEventType.DamageEntityToUs);
        setEffect(event.getDamager(),inventory.getItemInMainHand(),effectEventType.DamageEntityToUs);
        setEffect(event.getDamager(),inventory.getBoots(),effectEventType.DamageEntityToUs);
        setEffect(event.getDamager(),inventory.getChestplate(),effectEventType.DamageEntityToUs);
        setEffect(event.getDamager(),inventory.getLeggings(),effectEventType.DamageEntityToUs);
        setEffect(event.getDamager(),inventory.getHelmet(),effectEventType.DamageEntityToUs);
    }
    @EventHandler(priority=EventPriority.MONITOR)
    public void onPlayerDamage(EntityDamageEvent event)
    {
        if (event.isCancelled()||!(event.getEntity() instanceof Player)||event instanceof EntityDamageByEntityEvent)
        {
            return;
        }

        PlayerInventory inventory=((Player) event.getEntity()).getInventory();

        setEffect(event.getEntity(),inventory.getItemInOffHand(),effectEventType.DamageToUs);
        setEffect(event.getEntity(),inventory.getItemInMainHand(),effectEventType.DamageToUs);
        setEffect(event.getEntity(),inventory.getBoots(),effectEventType.DamageToUs);
        setEffect(event.getEntity(),inventory.getChestplate(),effectEventType.DamageToUs);
        setEffect(event.getEntity(),inventory.getLeggings(),effectEventType.DamageToUs);
        setEffect(event.getEntity(),inventory.getHelmet(),effectEventType.DamageToUs);
    }

    private void setEffect(Entity entity,ItemStack itemStack,effectEventType type)
    {
        if (itemStack==null||itemStack.getType().isAir())
        {
            return;
        }
        if (entity instanceof Projectile)
        {
            entity=Bukkit.getEntity(new NBTEntity(entity).getUUID("Owner"));
        }
        NBTCompound effect=new NBTItem(itemStack).addCompound("BluestarGame").addCompound(type.name());
        for (String i:effect.getKeys())
        {
            ((LivingEntity)entity).addPotionEffect(new PotionEffect(PotionEffectType.getByName(i),effect.addCompound(i).getInteger("time"),effect.addCompound(i).getInteger("s")));
        }
    }

    public enum effectEventType
    {
        /**
         * 非生物导致受伤给予自己
         */
        DamageToUs(1),
        /**
         * 被击中给予自己
         */
        DamageByEntityToUs(2),
        /**
         * 击中生物时给予自己
         */
        DamageEntityToUs(3),
        /**
         * 击中生物时给予对方
         */
        DamageEntityToIt(4),
        /**
         * 被生物击中给予对方
         */
        DamageByEntityToIt(5);
        private final int id;

        effectEventType(int id)
        {
            this.id=id;
        }

        public int getId()
        {
            return id;
        }

        public String getName()
        {
            switch (id)
            {
                case 1:
                {
                    return RGBChat.toColorCode("123456")+"非生物导致受伤时";
                }
                case 2:
                {
                    return RGBChat.toColorCode("523456")+"被生物击中受伤时";
                }
                case 3:
                {
                    return RGBChat.toColorCode("12f456")+"使生物受到伤害时";
                }
                case 4:
                {
                    return RGBChat.toColorCode("12f4c6")+"击中生物时给予它";
                }
                case 5:
                {
                    return RGBChat.toColorCode("c234f6")+"被击中时给予生物";
                }
                default:
                {
                    return "";
                }
            }
        }

        public static effectEventType getById(int id)
        {
            return byId.get(id);
        }
        public static effectEventType getByName(String name)
        {
            return byName.get(name.toLowerCase());
        }

        private final static Map<Integer,effectEventType>byId=new HashMap<>();
        private final static Map<String,effectEventType>byName=new HashMap<>();
        static
        {
            for (effectEventType i:values())
            {
                byId.put(i.id,i);
                byName.put(i.name().toLowerCase(),i);
            }
        }
    }
}
