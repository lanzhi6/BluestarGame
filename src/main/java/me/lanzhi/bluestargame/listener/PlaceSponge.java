package me.lanzhi.bluestargame.listener;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.lanzhi.bluestargame.BluestarGame;
import me.lanzhi.bluestargame.Ctrls.CtrlSponge;
import me.lanzhi.bluestargame.Type.superSponge;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class PlaceSponge implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerPlaceBlock(BlockPlaceEvent event)
    {
        ItemStack item = event.getItemInHand();
        NBTCompound bluestar = new NBTItem(item).getCompound("BluestarGame");
        if (bluestar==null)
        {
            return;
        }
        if (!bluestar.getBoolean("waterSponge")&&!bluestar.getBoolean("lavaSponge"))
        {
            return;
        }
        CtrlSponge.add(new superSponge(
                BluestarGame.config.getInt("spongeR"),
                event.getBlock().getLocation(), event.getPlayer(),
                bluestar.getBoolean("lavaSponge"),
                bluestar.getBoolean("waterSponge")
        ));
    }
}
