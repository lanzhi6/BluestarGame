package me.lanzhi.bluestargame.listener;

import me.lanzhi.bluestargame.bluestarapi.nbt.NBTCompound;
import me.lanzhi.bluestargame.bluestarapi.nbt.NBTItem;
import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.Type.SuperSponge;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public final class superSpongeListener implements Listener
{
    private final BluestarGamePlugin plugin;

    public superSpongeListener(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
    }

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
        plugin.getBluestarGameManager()
              .getSuperSpongeManager()
              .add(new SuperSponge(plugin.getConfig().getInt("spongeR"),
                                   event.getBlock().getLocation(),
                                   event.getPlayer().getName(),
                                   bluestar.getInteger("lavaSponge")==1,
                                   bluestar.getInteger("waterSponge")==1));
        if (bluestar.getInteger("lavaSponge")==1)
        {
            event.getPlayer()
                 .getInventory()
                 .addItem(plugin.getBluestarGameManager().getSuperSpongeManager().getUsedLavaSponge().getItem());
        }
        else
        {
            event.getPlayer()
                 .getInventory()
                 .addItem(plugin.getBluestarGameManager().getSuperSpongeManager().getUsedWaterSponge().getItem());
        }
    }
}
