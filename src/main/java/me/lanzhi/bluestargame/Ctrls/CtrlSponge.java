package me.lanzhi.bluestargame.Ctrls;

import java.util.ArrayList;
import java.util.List;
import me.lanzhi.bluestarapi.Bluestar;
import me.lanzhi.bluestargame.BluestarGame;
import me.lanzhi.bluestargame.Type.superSponge;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CtrlSponge
{
    private List<superSponge> nextSponges = new ArrayList();
    public org.bukkit.scheduler.BukkitRunnable ctrlsponge = new org.bukkit.scheduler.BukkitRunnable()
    {
        @Override
        public void run()
        {
            List<superSponge> sponges=(List<superSponge>)BluestarGame.config.getConfig().getList("superSponges");
            if ((sponges == null) || (sponges.isEmpty()))
            {
                return;
            }
            CtrlSponge.this.nextSponges = new ArrayList();
            for (superSponge sponge : sponges)
            {
                Location loc = sponge.getLocation();
                int age = sponge.getAge();
                Bluestar.setBlock(loc, Material.AIR, sponge.getPlayer().getName());
                if (age > 1)
                {


                    CtrlSponge.this.decide(new Location(loc.getWorld(), loc.getX() + 1.0D, loc.getY(), loc.getZ()), age, sponge.getPlayer());
                    CtrlSponge.this.decide(new Location(loc.getWorld(), loc.getX() - 1.0D, loc.getY(), loc.getZ()), age, sponge.getPlayer());
                    CtrlSponge.this.decide(new Location(loc.getWorld(), loc.getX(), loc.getY() + 1.0D, loc.getZ()), age, sponge.getPlayer());
                    CtrlSponge.this.decide(new Location(loc.getWorld(), loc.getX(), loc.getY() - 1.0D, loc.getZ()), age, sponge.getPlayer());
                    CtrlSponge.this.decide(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() + 1.0D), age, sponge.getPlayer());
                    CtrlSponge.this.decide(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() - 1.0D), age, sponge.getPlayer());
                } }
            BluestarGame.config.getConfig().set("superSponges", CtrlSponge.this.nextSponges);
            BluestarGame.config.saveConfig();
            BluestarGame.config.reloadConfig();
        }
    };

    private void decide(Location loc, int age, Player player) {
        if (loc.getBlock().getType() == Material.WATER)
        {
            Bluestar.setBlock(loc, Material.END_STONE, player.getName());
            this.nextSponges.add(new superSponge(age - 1, loc, player));
        }
        else if (loc.getBlock().getType() == Material.LAVA)
        {
            Bluestar.setBlock(loc, Material.OBSIDIAN, player.getName());
            this.nextSponges.add(new superSponge(age - 1, loc, player));
        }
    }
}


  

