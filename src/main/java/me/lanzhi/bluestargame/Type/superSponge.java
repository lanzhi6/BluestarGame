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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class superSponge implements ConfigurationSerializable
{
    private static NBTItem lavaSponge = null;
    private static NBTItem waterSponge = null;

    private final int age;
    private final Location location;
    private final Player player;
    private boolean islava=false;
    private boolean iswater=false;

    public superSponge(Map<String, Object> map)
    {
        this.age = (int)map.get("age");
        this.location = ((Location) map.get("location"));
        this.player = Bukkit.getPlayer((String) map.get("player"));
        this.islava = (boolean)map.get("islava");
        this.iswater = (boolean)map.get("iswater");
    }

    public superSponge(int age, Location loc, Player player,boolean islava,boolean iswater)
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
            waterSponge = new NBTItem(new ItemStack(Material.END_STONE));
            NBTCompound bluestargame = waterSponge.addCompound("BluestarGame");
            bluestargame.setBoolean("lavaSponge",false);
            bluestargame.setBoolean("waterSponge",true);
            NBTCompound display = waterSponge.addCompound("display");
            display.setString("Name", "\"" + ChatColor.GOLD + "超级海绵(吸水版)\"");
            List<String> list = display.getStringList("Lore");
            list.add("\"" + ChatColor.AQUA + "放在 水/岩浆 中或 水/岩浆 旁边\"");
            list.add("\"" + ChatColor.AQUA + "即可吸干附近" + BluestarGame.config.getInt("spongeR") + "格的 水/岩浆\"");
            list.add("\"" + ChatColor.RED + "放在没 水/岩浆 的地方会直接消失哦!\"");
        }
        return waterSponge;
    }
    public static NBTItem getlavaSponge()
    {
        if (lavaSponge == null)
        {
            lavaSponge = new NBTItem(new ItemStack(Material.OBSIDIAN));
            NBTCompound bluestargame = lavaSponge.addCompound("BluestarGame");
            bluestargame.setBoolean("lavaSponge",true);
            bluestargame.setBoolean("waterSponge",false);
            NBTCompound display = lavaSponge.addCompound("display");
            display.setString("Name", "\"" + ChatColor.GOLD + "超级海绵(吸岩浆版)\"");
            List<String> list = display.getStringList("Lore");
            list.add("\"" + ChatColor.AQUA + "放在 水/岩浆 中或 水/岩浆 旁边\"");
            list.add("\"" + ChatColor.AQUA + "即可吸干附近" + BluestarGame.config.getInt("spongeR") + "格的 水/岩浆\"");
            list.add("\"" + ChatColor.RED + "放在没 水/岩浆 的地方会直接消失哦!\"");
        }
        return lavaSponge;
    }

    @Override
    public Map<String, Object> serialize()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("age",this.age);
        map.put("location", this.location);
        map.put("player", this.player.getName());
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

    public Player getPlayer()
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


  