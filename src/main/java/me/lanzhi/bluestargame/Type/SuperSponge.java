package me.lanzhi.bluestargame.Type;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.lanzhi.bluestarapi.Api.config.AutoSerializeInterface;
import me.lanzhi.bluestarapi.Api.config.SerializeAs;
import me.lanzhi.bluestarapi.Api.config.SpecialSerialize;
import me.lanzhi.bluestargame.BluestarGamePlugin;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@SerializeAs("BluestarGame.SuperSponge")
public final class SuperSponge implements AutoSerializeInterface
{

    private final int age;
    private final Location location;
    private final String player;
    private final boolean islava;
    private final boolean iswater;

    public SuperSponge()
    {
        this(0,null,null,false,false);
    }

    public SuperSponge(int age,Location loc,String player,boolean islava,boolean iswater)
    {
        this.age=age;
        this.location=loc;
        this.player=player;
        this.islava=islava;
        this.iswater=iswater;
    }

    public int getAge()
    {
        return this.age;
    }

    public Location getLocation()
    {
        return this.location;
    }

    public String getPlayer()
    {
        return this.player;
    }

    public boolean getIslava()
    {
        return this.islava;
    }

    public boolean getIswater()
    {
        return this.iswater;
    }
}


  