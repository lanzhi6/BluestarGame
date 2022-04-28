package me.lanzhi.bluestargame.listener;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.lanzhi.bluestargame.BluestarGame;
import me.lanzhi.bluestargame.Ctrls.CtrlSponge;
import me.lanzhi.bluestargame.Type.SuperSponge;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class superSpongeListener implements Listener
{
    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerPlaceBlock(BlockPlaceEvent event)
    {
        if (event.isCancelled())
        {
            return;
        }
        ItemStack item=event.getItemInHand();
        NBTCompound bluestar=new NBTItem(item).getCompound("BluestarGame");
        if (bluestar==null)
        {
            return;
        }
        if (bluestar.getInteger("waterSponge")==-1||bluestar.getInteger("lavaSponge")==-1)
        {
            event.setCancelled(true);
            return;
        }
        if (bluestar.getInteger("waterSponge")!=1&&bluestar.getInteger("lavaSponge")!=1)
        {
            return;
        }
        CtrlSponge.add(new SuperSponge(BluestarGame.config.getInt("spongeR"),event.getBlock().getLocation(),event.getPlayer().getName(),bluestar.getInteger("lavaSponge")==1,bluestar.getInteger("waterSponge")==1));
        if (bluestar.getInteger("lavaSponge")==1)
        {
            event.getPlayer().getInventory().addItem(SuperSponge.getUsedLavaSponge().getItem());
        }
        else
        {
            event.getPlayer().getInventory().addItem(SuperSponge.getUsedWaterSponge().getItem());
        }
    }
}
