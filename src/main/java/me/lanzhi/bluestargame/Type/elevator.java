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

public class elevator implements ConfigurationSerializable
{
    private final long minX,maxX,minZ,maxZ,maxY,minY;
    public elevator(long minX,long maxX,long minZ,long maxZ,long minY,long maxY)
    {
        this.minX = minX;
        this.maxX = maxX;
        this.minZ = minZ;
        this.maxZ = maxZ;
        this.minY = minY;
        this.maxY = maxY;
    }
    public long getMinX() {
        return minX;
    }
    public long getMaxX() {
        return maxX;
    }
    public long getMinZ() {
        return minZ;
    }
    public long getMaxZ() {
        return maxZ;
    }
    public long getMaxY() {
        return maxY;
    }
    public long getMinY() {
        return minY;
    }

    public elevator(Map<String,Object> map)
    {
        this.minX = (int)map.get("minX");
        this.maxX = (int)map.get("maxX");
        this.minZ = (int)map.get("minZ");
        this.maxZ = (int)map.get("maxZ");
        this.minY = (int)map.get("minY");
        this.maxY = (int)map.get("maxY");
    }
    @NotNull
    @Override
    public Map<String,Object> serialize()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("minY",getMinY());
        map.put("maxY",getMaxY());
        map.put("minX",getMinX());
        map.put("maxX",getMaxX());
        map.put("minZ",getMinZ());
        map.put("maxZ",getMaxZ());
        return map;
    }
    @Nullable
    public static elevator getElevator(Location loc)
    {
        Public test=((Public)Data.get("elevators"));
        if(test==null)return null;
        Map<String,Object> elevators=(test.map);
        if (elevators==null)
        {
            return null;
        }
        Set<String> keys=elevators.keySet();
        for (String s:keys)
        {
            if (!(elevators.get(s) instanceof elevator))continue;
            elevator ss=(elevator) elevators.get(s);
            if (ss.getMaxX()>=loc.getBlockX()&&ss.getMinX()<=loc.getBlockX()&&
                    ss.getMaxZ()>=loc.getBlockZ()&&ss.getMinZ()<=loc.getBlockZ()&&
                    ss.getMaxY()>=loc.getBlockY()&&ss.getMinY()<=loc.getBlockY())
            {
                return ss;
            }
        }
        return null;
    }
}
