package me.lanzhi.bluestargame.Type;

import me.lanzhi.api.config.AutoSerializeInterface;
import me.lanzhi.api.config.SerializeAs;
import org.bukkit.Location;

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


  