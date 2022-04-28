package me.lanzhi.bluestargame.Ctrls;

import me.lanzhi.bluestarapi.Api.Bluestar;
import me.lanzhi.bluestargame.Type.SuperSponge;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class CtrlSponge
{
    private static List<SuperSponge> nextSponges = new ArrayList<>();
    private static List<SuperSponge> sponges = new ArrayList<>();
    public static org.bukkit.scheduler.BukkitRunnable ctrlsponge = new org.bukkit.scheduler.BukkitRunnable()
    {
        @Override
        public void run()
        {
            if ((sponges == null) || (sponges.isEmpty()))
            {
                return;
            }
            nextSponges = new ArrayList();
            for (SuperSponge sponge : sponges)
            {
                Location loc = sponge.getLocation();
                int age = sponge.getAge();
                Bluestar.setBlock(loc, Material.AIR, sponge.getPlayer());
                boolean iswater=sponge.getIswater();
                boolean islava=sponge.getIslava();
                if (age>1)
                {
                    decide(new Location(loc.getWorld(), loc.getX() + 1.0D, loc.getY(), loc.getZ()), age, sponge.getPlayer(),iswater,islava);
                    decide(new Location(loc.getWorld(), loc.getX() - 1.0D, loc.getY(), loc.getZ()), age, sponge.getPlayer(),iswater,islava);
                    decide(new Location(loc.getWorld(), loc.getX(), loc.getY() + 1.0D, loc.getZ()), age, sponge.getPlayer(),iswater,islava);
                    decide(new Location(loc.getWorld(), loc.getX(), loc.getY() - 1.0D, loc.getZ()), age, sponge.getPlayer(),iswater,islava);
                    decide(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() + 1.0D), age, sponge.getPlayer(),iswater,islava);
                    decide(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() - 1.0D), age, sponge.getPlayer(),iswater,islava);
                }
            }
            sponges=nextSponges;
        }
    };

    public static void add(SuperSponge sponge)
    {
        if (sponges==null)
        {
            sponges=new ArrayList<>();
        }
        sponges.add(sponge);
    }
    public static void set(List<SuperSponge> spongess){sponges=spongess;}
    public static List<SuperSponge> get(){return sponges;}

    private static void decide(Location loc, int age, String player,boolean iswater,boolean islava)
    {
        if (loc.getBlock().getType()==Material.WATER&&iswater)
        {
            Bluestar.setBlock(loc, Material.END_STONE, player);
            nextSponges.add(new SuperSponge(age-1,loc,player,islava,true));
        }
        if(loc.getBlock().getType()==Material.LAVA&&islava)
        {
            Bluestar.setBlock(loc, Material.OBSIDIAN, player);
            nextSponges.add(new SuperSponge(age-1,loc,player,true,iswater));
        }
    }
}


  

