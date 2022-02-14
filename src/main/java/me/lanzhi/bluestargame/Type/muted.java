package me.lanzhi.bluestargame.Type;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.util.NumberConversions;

import java.util.HashMap;
import java.util.Map;

public class muted implements ConfigurationSerializable
{
    Map<String,Object> map;

    public muted(Map<String,Object> map)
    {
        this.map = map;
    }
    @Override
    public Map<String,Object> serialize()
    {
        return map;
    }

    public boolean get(String name)
    {
        return Boolean.parseBoolean(map.get(name).toString());
    }
    public Map getall()
    {
        return map;
    }
    public void set(String name,boolean value)
    {
        map.put(name,value);
    }
}
