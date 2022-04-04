package me.lanzhi.bluestargame.Type;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.lanzhi.bluestargame.BluestarGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class superSponge implements ConfigurationSerializable
{
    private static NBTItem lavaSponge = null;
    private static NBTItem usedLavaSponge = null;
    private static NBTItem waterSponge = null;
    private static NBTItem usedWaterSponge = null;

    private final int age;
    private final Location location;
    private final String player;
    private boolean islava=false;
    private boolean iswater=false;

    public superSponge(Map<String, Object> map)
    {
        this.age = (int)map.get("age");
        this.location = ((Location) map.get("location"));
        this.player = (String) map.get("player");
        this.islava = (boolean)map.get("islava");
        this.iswater = (boolean)map.get("iswater");
    }

    public superSponge(int age, Location loc, String player,boolean islava,boolean iswater)
    {
        this.age = age;
        this.location = loc;
        this.player = player;
        this.islava = islava;
        this.iswater = iswater;
    }

    public static NBTItem getSuperSponge()
    {
        if (waterSponge == null)
        {
            ItemStack itemStack=new ItemStack(Material.END_STONE);
            ItemMeta itemMeta=itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.GOLD+"超级海绵");
            List<String> list=new ArrayList<>();
            list.add(ChatColor.AQUA+"放在"+ChatColor.GOLD+"水"+ChatColor.AQUA+"中或"+ChatColor.GOLD+"水"+ChatColor.AQUA+"旁边");
            list.add(ChatColor.AQUA+"即可吸干附近"+BluestarGame.config.getInt("spongeR")+"格的"+ChatColor.GOLD+"水");
            list.add(ChatColor.RED+"放在没"+ChatColor.GOLD+"水"+ChatColor.RED+"的地方会直接变成"+ChatColor.GOLD+"湿超级海绵"+ChatColor.RED+"哦!");
            itemMeta.setLore(list);
            itemStack.setItemMeta(itemMeta);
            waterSponge=new NBTItem(itemStack);
            NBTCompound bluestargame = waterSponge.addCompound("BluestarGame");
            bluestargame.setInteger("lavaSponge",0);
            bluestargame.setInteger("waterSponge",1);
        }
        return waterSponge;
    }
    public static NBTItem getlavaSponge()
    {
        if (lavaSponge == null)
        {
            ItemStack itemStack=new ItemStack(Material.OBSIDIAN);
            ItemMeta itemMeta=itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.GOLD+"岩浆海绵");
            List<String> list=new ArrayList<>();
            list.add(ChatColor.AQUA+"放在"+ChatColor.GOLD+"岩浆"+ChatColor.AQUA+"中或"+ChatColor.GOLD+"岩浆"+ChatColor.AQUA+"旁边");
            list.add(ChatColor.AQUA+"即可吸干附近"+BluestarGame.config.getInt("spongeR")+"格的"+ChatColor.GOLD+"岩浆");
            list.add(ChatColor.RED+"放在没"+ChatColor.GOLD+"岩浆"+ChatColor.RED+"的地方会直接变成"+ChatColor.GOLD+"湿岩浆海绵"+ChatColor.RED+"哦!");
            itemMeta.setLore(list);
            itemStack.setItemMeta(itemMeta);
            lavaSponge = new NBTItem(itemStack);
            NBTCompound bluestargame = lavaSponge.addCompound("BluestarGame");
            bluestargame.setInteger("lavaSponge",1);
            bluestargame.setInteger("waterSponge",0);
        }
        return lavaSponge;
    }
    public static NBTItem getUsedSuperSponge()
    {
        if (usedWaterSponge == null)
        {
            ItemStack itemStack=new ItemStack(Material.PRISMARINE);
            ItemMeta itemMeta=itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.GOLD+"湿超级海绵");
            List<String> list=new ArrayList<>();
            list.add(ChatColor.AQUA+"湿超级海绵,可放入熔炉变回"+ChatColor.GOLD+"超级海绵");
            list.add(ChatColor.GOLD+"湿超级海绵"+ChatColor.RED+"无法放置或使用哦");
            itemMeta.setLore(list);
            itemStack.setItemMeta(itemMeta);
            usedWaterSponge = new NBTItem(itemStack);
            NBTCompound bluestargame = usedWaterSponge.addCompound("BluestarGame");
            bluestargame.setInteger("lavaSponge",0);
            bluestargame.setInteger("waterSponge",-1);
        }
        return usedWaterSponge;
    }
    public static NBTItem getUsedLavaSponge()
    {
        if (usedLavaSponge == null)
        {
            ItemStack itemStack=new ItemStack(Material.MAGMA_BLOCK);
            ItemMeta itemMeta=itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.GOLD+"湿岩浆海绵");
            List<String> list=new ArrayList<>();
            list.add(ChatColor.AQUA+"湿岩浆海绵,可放入熔炉变回"+ChatColor.GOLD+"湿岩浆海绵");
            list.add(ChatColor.GOLD+"湿岩浆海绵"+ChatColor.RED+"无法放置或使用哦");
            itemMeta.setLore(list);
            itemStack.setItemMeta(itemMeta);
            usedLavaSponge = new NBTItem(itemStack);
            NBTCompound bluestargame = usedLavaSponge.addCompound("BluestarGame");
            bluestargame.setInteger("lavaSponge",-1);
            bluestargame.setInteger("waterSponge",0);
        }
        return usedLavaSponge;
    }

    @Override
    public Map<String, Object> serialize()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("age",this.age);
        map.put("location", this.location);
        map.put("player", this.player);
        map.put("islava", this.islava);
        map.put("iswater", this.iswater);
        return map;
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


  