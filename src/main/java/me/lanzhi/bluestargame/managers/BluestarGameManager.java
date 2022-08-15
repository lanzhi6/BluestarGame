package me.lanzhi.bluestargame.managers;

import me.lanzhi.api.nbt.NBTCompound;
import me.lanzhi.api.nbt.NBTItem;
import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.Type.Elevator;
import me.lanzhi.bluestargame.api.BluestarGameApi;
import me.lanzhi.bluestargameapi.managers.BluestarGameManagerInterface;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public final class BluestarGameManager implements BluestarGameManagerInterface
{
    private final BluestarGamePlugin plugin;
    private final ConfigurationSection muted;
    private final ConfigurationSection elevators;
    private final BluestarGameApi api;
    private final RandomEventManger randomEventManger;
    private final SuperSpongeManager superSpongeManager;
    private final BukkitTask effectTask;

    public BluestarGameManager(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
        ConfigurationSection s=plugin.getData().getConfigurationSection("muted");
        muted=s==null?plugin.getData().createSection("muted"):s;
        s=plugin.getData().getConfigurationSection("elevators");
        elevators=s==null?plugin.getData().createSection("elevators"):s;
        this.api=new BluestarGameApi(plugin);
        this.randomEventManger=new RandomEventManger(plugin);
        this.superSpongeManager=new SuperSpongeManager(plugin);
        this.effectTask=new Effect().runTaskTimer(plugin,0,95);
    }

    @Override
    public BluestarGameApi getApi()
    {
        return api;
    }

    @Override
    public ConfigurationSection getMuted()
    {
        return muted;
    }

    @Override
    public ConfigurationSection getElevators()
    {
        return elevators;
    }

    @Nullable
    public Elevator getElevator(Location loc)
    {
        Set<String> keys=elevators.getKeys(false);
        for (String s: keys)
        {
            Elevator elevator=elevators.getObject(s,Elevator.class);
            if (elevator==null)
            {
                continue;
            }
            if (elevator.getWorld()
                        .equals(loc.getWorld())&&elevator.getMaxX()>=loc.getBlockX()&&elevator.getMinX()<=loc.getBlockX()&&elevator.getMaxZ()>=loc.getBlockZ()&&elevator.getMinZ()<=loc.getBlockZ()&&elevator.getMaxY()>=loc.getBlockY()&&elevator.getMinY()<=loc.getBlockY())
            {
                return elevator;
            }
        }
        return null;
    }

    @Override
    public RandomEventManger getRandomEventManger()
    {
        return randomEventManger;
    }

    @Override
    public SuperSpongeManager getSuperSpongeManager()
    {
        return superSpongeManager;
    }

    @Override
    public BluestarGamePlugin getPlugin()
    {
        return plugin;
    }

    public void stop()
    {
        effectTask.cancel();
        getRandomEventManger().end();
        getRandomEventManger().all(false);
    }

    public static class Effect extends BukkitRunnable
    {
        private static void set(Player player,ItemStack itemStack)
        {
            if (itemStack==null||itemStack.getType().isAir())
            {
                return;
            }
            NBTItem item=new NBTItem(itemStack);
            NBTCompound compound=item.addCompound("BluestarGame").addCompound("effect");
            for (String i: compound.getKeys())
            {
                PotionEffectType type=PotionEffectType.getByName(i);
                if (type==null)
                {
                    continue;
                }
                player.addPotionEffect(new PotionEffect(type,type.getId()==16?300:100,compound.getInteger(i)-1));
            }
        }

        @Override
        public void run()
        {
            for (Player player: Bukkit.getOnlinePlayers())
            {
                PlayerInventory inventory=player.getInventory();
                set(player,inventory.getItemInMainHand());
                set(player,inventory.getItemInOffHand());
                for (ItemStack itemStack: inventory.getArmorContents())
                {
                    set(player,itemStack);
                }
            }
        }
    }
}
