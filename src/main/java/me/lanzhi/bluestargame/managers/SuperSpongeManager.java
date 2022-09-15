package me.lanzhi.bluestargame.managers;

import me.lanzhi.bluestargame.bluestarapi.Bluestar;
import me.lanzhi.bluestargame.bluestarapi.nbt.NBTCompound;
import me.lanzhi.bluestargame.bluestarapi.nbt.NBTItem;
import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.Type.SuperSponge;
import me.lanzhi.bluestargameapi.managers.ISuperSpongeManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public final class SuperSpongeManager implements ISuperSpongeManager
{
    private final BluestarGamePlugin plugin;
    private List<SuperSponge> nextSponges=new ArrayList<>();
    private List<SuperSponge> sponges=new ArrayList<>();
    public BukkitRunnable ctrlsponge=new BukkitRunnable()
    {
        @Override
        public void run()
        {
            if ((sponges==null)||(sponges.isEmpty()))
            {
                return;
            }
            nextSponges=new ArrayList();
            for (SuperSponge sponge: sponges)
            {
                Location loc=sponge.getLocation();
                int age=sponge.getAge();
                loc.getBlock().setType(Material.AIR);
                boolean iswater=sponge.getIswater();
                boolean islava=sponge.getIslava();
                if (age>1)
                {
                    decide(new Location(loc.getWorld(),loc.getX()+1.0D,loc.getY(),loc.getZ()),
                           age,
                           sponge.getPlayer(),
                           iswater,
                           islava);
                    decide(new Location(loc.getWorld(),loc.getX()-1.0D,loc.getY(),loc.getZ()),
                           age,
                           sponge.getPlayer(),
                           iswater,
                           islava);
                    decide(new Location(loc.getWorld(),loc.getX(),loc.getY()+1.0D,loc.getZ()),
                           age,
                           sponge.getPlayer(),
                           iswater,
                           islava);
                    decide(new Location(loc.getWorld(),loc.getX(),loc.getY()-1.0D,loc.getZ()),
                           age,
                           sponge.getPlayer(),
                           iswater,
                           islava);
                    decide(new Location(loc.getWorld(),loc.getX(),loc.getY(),loc.getZ()+1.0D),
                           age,
                           sponge.getPlayer(),
                           iswater,
                           islava);
                    decide(new Location(loc.getWorld(),loc.getX(),loc.getY(),loc.getZ()-1.0D),
                           age,
                           sponge.getPlayer(),
                           iswater,
                           islava);
                }
            }
            sponges=nextSponges;
        }
    };
    private NBTItem lavaSponge=null;
    private NBTItem usedLavaSponge=null;
    private NBTItem waterSponge=null;
    private NBTItem usedWaterSponge=null;

    public SuperSpongeManager(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
    }

    public void set(List<SuperSponge> spongess)
    {
        sponges=spongess;
    }

    public List<SuperSponge> get()
    {
        return sponges;
    }

    private void decide(Location loc,int age,String player,boolean iswater,boolean islava)
    {
        if (loc.getBlock().getType()==Material.WATER&&iswater)
        {
            Bluestar.getMainManager().CoreLogRemoval(player,loc,Material.WATER,loc.getBlock().getBlockData());
            loc.getBlock().setType(Material.END_STONE);
            nextSponges.add(new SuperSponge(age-1,loc,player,islava,true));
        }
        if (loc.getBlock().getType()==Material.LAVA&&islava)
        {
            Bluestar.getMainManager().CoreLogRemoval(player,loc,Material.LAVA,loc.getBlock().getBlockData());
            loc.getBlock().setType(Material.OBSIDIAN);
            nextSponges.add(new SuperSponge(age-1,loc,player,true,iswater));
        }
    }

    public void add(SuperSponge sponge)
    {
        if (sponges==null)
        {
            sponges=new ArrayList<>();
        }
        sponges.add(sponge);
    }

    @Override
    public NBTItem getWaterSponge()
    {
        if (waterSponge==null)
        {
            ItemStack itemStack=new ItemStack(Material.END_STONE);
            ItemMeta itemMeta=itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.GOLD+"超级海绵");
            List<String> list=new ArrayList<>();
            list.add(ChatColor.AQUA+"放在"+ChatColor.GOLD+"水"+ChatColor.AQUA+"中或"+ChatColor.GOLD+"水"+ChatColor.AQUA+"旁边");
            list.add(ChatColor.AQUA+"即可吸干附近"+plugin.getConfig().getInt("spongeR")+"格的"+ChatColor.GOLD+"水");
            list.add(ChatColor.RED+"放在没"+ChatColor.GOLD+"水"+ChatColor.RED+"的地方会直接变成"+ChatColor.GOLD+"湿超级海绵"+ChatColor.RED+"哦!");
            itemMeta.setLore(list);
            itemStack.setItemMeta(itemMeta);
            waterSponge=new NBTItem(itemStack);
            NBTCompound bluestargame=waterSponge.addCompound("BluestarGame");
            bluestargame.setInteger("lavaSponge",0);
            bluestargame.setInteger("waterSponge",1);
        }
        return waterSponge;
    }

    @Override
    public NBTItem getlavaSponge()
    {
        if (lavaSponge==null)
        {
            ItemStack itemStack=new ItemStack(Material.OBSIDIAN);
            ItemMeta itemMeta=itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.GOLD+"岩浆海绵");
            List<String> list=new ArrayList<>();
            list.add(ChatColor.AQUA+"放在"+ChatColor.GOLD+"岩浆"+ChatColor.AQUA+"中或"+ChatColor.GOLD+"岩浆"+ChatColor.AQUA+"旁边");
            list.add(ChatColor.AQUA+"即可吸干附近"+plugin.getConfig().getInt("spongeR")+"格的"+ChatColor.GOLD+"岩浆");
            list.add(ChatColor.RED+"放在没"+ChatColor.GOLD+"岩浆"+ChatColor.RED+"的地方会直接变成"+ChatColor.GOLD+"湿岩浆海绵"+ChatColor.RED+"哦!");
            itemMeta.setLore(list);
            itemStack.setItemMeta(itemMeta);
            lavaSponge=new NBTItem(itemStack);
            NBTCompound bluestargame=lavaSponge.addCompound("BluestarGame");
            bluestargame.setInteger("lavaSponge",1);
            bluestargame.setInteger("waterSponge",0);
        }
        return lavaSponge;
    }

    @Override
    public NBTItem getUsedWaterSponge()
    {
        if (usedWaterSponge==null)
        {
            ItemStack itemStack=new ItemStack(Material.PRISMARINE);
            ItemMeta itemMeta=itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.GOLD+"湿超级海绵");
            List<String> list=new ArrayList<>();
            list.add(ChatColor.AQUA+"湿超级海绵,可放入熔炉变回"+ChatColor.GOLD+"超级海绵");
            list.add(ChatColor.GOLD+"湿超级海绵"+ChatColor.RED+"无法放置或使用哦");
            itemMeta.setLore(list);
            itemStack.setItemMeta(itemMeta);
            usedWaterSponge=new NBTItem(itemStack);
            NBTCompound bluestargame=usedWaterSponge.addCompound("BluestarGame");
            bluestargame.setInteger("lavaSponge",0);
            bluestargame.setInteger("waterSponge",-1);
        }
        return usedWaterSponge;
    }

    @Override
    public NBTItem getUsedLavaSponge()
    {
        if (usedLavaSponge==null)
        {
            ItemStack itemStack=new ItemStack(Material.MAGMA_BLOCK);
            ItemMeta itemMeta=itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.GOLD+"湿岩浆海绵");
            List<String> list=new ArrayList<>();
            list.add(ChatColor.AQUA+"湿岩浆海绵,可放入熔炉变回"+ChatColor.GOLD+"湿岩浆海绵");
            list.add(ChatColor.GOLD+"湿岩浆海绵"+ChatColor.RED+"无法放置或使用哦");
            itemMeta.setLore(list);
            itemStack.setItemMeta(itemMeta);
            usedLavaSponge=new NBTItem(itemStack);
            NBTCompound bluestargame=usedLavaSponge.addCompound("BluestarGame");
            bluestargame.setInteger("lavaSponge",-1);
            bluestargame.setInteger("waterSponge",0);
        }
        return usedLavaSponge;
    }

    public List<SuperSponge> getSponges()
    {
        return sponges;
    }
}


  

