package me.lanzhi.bluestargame.Type;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Public implements ConfigurationSerializable
{
    public Map<String, Object>map;
    public Public(Map<String,Object> map)
    {
        this.map = map;
        if (map == null)this.map = new HashMap<>();
    }
    @NotNull
    @Override
    public Map<String,Object> serialize()
    {
        return map;
    }
}
