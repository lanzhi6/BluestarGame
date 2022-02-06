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

public final class superSponge implements ConfigurationSerializable {
    private static NBTItem superSponge = null;
    private final int age;
    private final Location location;
    private final Player player;

    public superSponge(Map<String, Object> map) {
        this.age = ((Integer) map.get("age")).intValue();
        this.location = ((Location) map.get("location"));
        this.player = Bukkit.getPlayer((String) map.get("player"));
    }

    public superSponge(int age, Location loc, Player player) {
        this.age = age;
        this.location = loc;
        this.player = player;
    }

    public static NBTItem getSuperSponge() {
        if (superSponge == null) {
            superSponge = new NBTItem(new ItemStack(Material.END_STONE));
            superSponge.setBoolean("BluestarGameSponge", Boolean.valueOf(true));
            NBTCompound display = superSponge.addCompound("display");
            display.setString("Name", "\"" + ChatColor.GOLD + "超级海绵\"");
            List<String> list = display.getStringList("Lore");
            list.add("\"" + ChatColor.AQUA + "放在 水/岩浆 中或 水/岩浆 旁边\"");
            list.add("\"" + ChatColor.AQUA + "即可吸干附近" + BluestarGame.config.getConfig().getInt("spongeR") + "格的 水/岩浆\"");
            list.add("\"" + ChatColor.RED + "放在没 水/岩浆 的地方会直接消失哦!\"");
        }
        return superSponge;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap();
        map.put("age", Integer.valueOf(this.age));
        map.put("location", this.location);
        map.put("player", this.player.getName());
        return map;
    }

    public int getAge() {
        return this.age;
    }

    public Location getLocation() {
        return this.location;
    }

    public Player getPlayer() {
        return this.player;
    }
}


  