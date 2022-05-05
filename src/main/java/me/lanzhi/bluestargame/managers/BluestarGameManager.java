package me.lanzhi.bluestargame.managers;

import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.Type.Elevator;
import me.lanzhi.bluestargame.api.BluestarGameApi;
import me.lanzhi.bluestargameapi.managers.BluestarGameManagerInterface;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
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
            if (elevator.getWorld().equals(loc.getWorld())&&elevator.getMaxX()>=loc.getBlockX()&&elevator.getMinX()<=loc.getBlockX()&&elevator.getMaxZ()>=loc.getBlockZ()&&elevator.getMinZ()<=loc.getBlockZ()&&elevator.getMaxY()>=loc.getBlockY()&&elevator.getMinY()<=loc.getBlockY())
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
}
