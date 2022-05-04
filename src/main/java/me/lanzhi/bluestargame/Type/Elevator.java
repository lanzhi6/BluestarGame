package me.lanzhi.bluestargame.Type;

import me.lanzhi.bluestarapi.Api.config.AutoSerializeInterface;
import me.lanzhi.bluestarapi.Api.config.SerializeAs;
import me.lanzhi.bluestarapi.Api.config.SpecialSerialize;
import org.bukkit.Bukkit;
import org.bukkit.World;

@SerializeAs("BluestarGame.Elevator")
final public class Elevator implements AutoSerializeInterface
{
    final long minX;
    final long maxX;
    final long minZ;
    final long maxZ;
    final long maxY;
    final long minY;
    @SpecialSerialize(serialize="serializeWorld", deserialize="deserializeWorld")
    final World world;

    public Elevator()
    {
        minX=minZ=minY=maxX=maxZ=maxY=0;
        world=null;
    }

    public Elevator(World world,long minX,long maxX,long minZ,long maxZ,long minY,long maxY)
    {
        this.world=world;
        this.minX=Math.min(minX,maxX);
        this.maxX=Math.max(minX,maxX);
        this.minZ=Math.min(minZ,maxZ);
        this.maxZ=Math.max(minZ,maxZ);
        this.minY=Math.min(minY,maxY);
        this.maxY=Math.max(minY,maxY);
    }

    public static String serializeWorld(World world)
    {
        return world.getName();
    }

    public static World deserializeWorld(String world)
    {
        return Bukkit.getWorld(world);
    }

    public World getWorld()
    {
        return world;
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
}
