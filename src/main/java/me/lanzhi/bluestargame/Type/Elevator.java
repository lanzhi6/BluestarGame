package me.lanzhi.bluestargame.Type;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static me.lanzhi.bluestargame.BluestarGame.Data;

public class Elevator implements ConfigurationSerializable
{
    public static ConfigurationSection elevators=Data.getConfigurationSection("elevators");
    static
    {
        if (elevators==null)
        {
            Data.set("elevators",new HashMap<>());
            elevators=Data.getConfigurationSection("elevators");
        }
    }
    private final long minX, maxX, minZ, maxZ, maxY, minY;

    public Elevator(long minX,long maxX,long minZ,long maxZ,long minY,long maxY)
    {
        this.minX=minX;
        this.maxX=maxX;
        this.minZ=minZ;
        this.maxZ=maxZ;
        this.minY=minY;
        this.maxY=maxY;
    }

    public long getMinX()
    {
        return minX;
    }

    public long getMaxX()
    {
        return maxX;
    }

    public long getMinZ()
    {
        return minZ;
    }

    public long getMaxZ()
    {
        return maxZ;
    }

    public long getMaxY()
    {
        return maxY;
    }

    public long getMinY()
    {
        return minY;
    }

    public Elevator(Map<String, Object> map)
    {
        this.minX=(int) map.get("minX");
        this.maxX=(int) map.get("maxX");
        this.minZ=(int) map.get("minZ");
        this.maxZ=(int) map.get("maxZ");
        this.minY=(int) map.get("minY");
        this.maxY=(int) map.get("maxY");
    }

    @NotNull
    @Override
    public Map<String, Object> serialize()
    {
        Map<String, Object> map=new HashMap<>();
        map.put("minY",getMinY());
        map.put("maxY",getMaxY());
        map.put("minX",getMinX());
        map.put("maxX",getMaxX());
        map.put("minZ",getMinZ());
        map.put("maxZ",getMaxZ());
        return map;
    }

    @Nullable
    public static Elevator getElevator(Location loc)
    {
        Set<String> keys=elevators.getKeys(false);
        for (String s: keys)
        {
            Elevator elevator=elevators.getObject(s,Elevator.class);
            if (elevator==null)
            {
                continue;
            }
            if (elevator.getMaxX()>=loc.getBlockX()&&elevator.getMinX()<=loc.getBlockX()&&elevator.getMaxZ()>=loc.getBlockZ()&&elevator.getMinZ()<=loc.getBlockZ()&&elevator.getMaxY()>=loc.getBlockY()&&elevator.getMinY()<=loc.getBlockY())
            {
                return elevator;
            }
        }
        return null;
    }
}
