package me.lanzhi.bluestargame.listener;

import me.lanzhi.api.Bluestar;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageAbortEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public final class breakBedrockListener implements Listener
{
    private static final ItemStack bedrock=new ItemStack(Material.BEDROCK);
    public static Map<Player, AbstractMap.SimpleEntry<Date, Block>> playerList=new HashMap<>();
    public static Set<Player> playerSet=new HashSet<>();
    public static BukkitRunnable breakBedrock=new BukkitRunnable()
    {
        @Override
        public void run()
        {
            playerSet=new HashSet<>();
            for (Player i: playerList.keySet())
            {
                AbstractMap.SimpleEntry<Date, Block> value=playerList.get(i);
                if (Calendar.getInstance().getTime().getTime()-value.getKey().getTime()>=30000)
                {
                    playerSet.add(i);
                    BlockBreakEvent event=new BlockBreakEvent(value.getValue(),i);
                    Bukkit.getPluginManager().callEvent(event);
                    if (event.isCancelled())
                    {
                        continue;
                    }
                    Bluestar.setBlock(value.getValue().getLocation(),Material.AIR,i.getName());
                    value.getValue().getWorld().dropItem(value.getValue().getLocation(),bedrock);
                }
            }
            for (Player i: playerSet)
            {
                playerList.remove(i);
            }
        }
    };

    @EventHandler(ignoreCancelled=true, priority=EventPriority.MONITOR)
    public void onPlayerDamageBlock(BlockDamageEvent event)
    {
        if (!event.getBlock().getType().equals(Material.BEDROCK))
        {
            return;
        }
        if (!event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_PICKAXE))
        {
            return;
        }
        playerList.put(event.getPlayer(),
                       new AbstractMap.SimpleEntry<>(Calendar.getInstance().getTime(),event.getBlock()));
    }

    @EventHandler(ignoreCancelled=true, priority=EventPriority.MONITOR)
    public void onPlayerDamageBlock(BlockDamageAbortEvent event)
    {
        playerList.remove(event.getPlayer());
    }
}
