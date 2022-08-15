package me.lanzhi.bluestargame.listener;

import me.lanzhi.api.Bluestar;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.jetbrains.annotations.NotNull;

public final class CompostListener implements Listener
{
    @EventHandler(ignoreCancelled=true)
    public void onPlayerSneak(@NotNull PlayerToggleSneakEvent event)
    {
        if (event.isSneaking())
        {
            Player player=event.getPlayer();
            Location location=player.getLocation();
            if (location.getBlock().getType()!=Material.COMPOSTER)
            {
                return;
            }
            Levelled blockData=(Levelled) location.getBlock().getBlockData();
            if (blockData.getLevel()<blockData.getMaximumLevel()&&Bluestar.randomInt(10)==0)
            {
                blockData.setLevel(blockData.getLevel()+1);
                location.getBlock().setBlockData(blockData);
            }
        }
    }
}
