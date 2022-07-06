package me.lanzhi.bluestargame.listener.randoms;

import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.managers.RandomEventManger;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.SheepDyeWoolEvent;
import org.bukkit.inventory.ItemStack;

public final class randSheepColorListener implements Listener
{
    private final BluestarGamePlugin plugin;
    private final RandomEventManger randomEventManger;

    public randSheepColorListener(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
        randomEventManger=plugin.getBluestarGameManager().getRandomEventManger();
    }

    @EventHandler(ignoreCancelled=true, priority=EventPriority.HIGH)
    public void onSheepDyeWool(SheepDyeWoolEvent event)
    {
        if ((!randomEventManger.randsheep()))
        {
            return;
        }
        DyeColor[] sheepColor={DyeColor.WHITE,DyeColor.ORANGE,DyeColor.MAGENTA,DyeColor.LIGHT_BLUE,DyeColor.YELLOW,DyeColor.LIME,DyeColor.PINK,DyeColor.GRAY,DyeColor.LIGHT_GRAY,DyeColor.CYAN,DyeColor.PURPLE,DyeColor.BLUE,DyeColor.BROWN,DyeColor.GREEN,DyeColor.RED,DyeColor.BLACK};
        int i=(int) (Math.random()*(sheepColor.length+1));
        event.setColor(sheepColor[i]);
        if (event.getPlayer()!=null)
        {
            event.getPlayer().sendMessage(ChatColor.GREEN+"[BluestarGame]随机羊毛颜色");
            event.getPlayer().sendMessage(ChatColor.GREEN+"你的羊的颜色变成了:"+ChatColor.GOLD+sheepColor[i]);
        }
    }

    @EventHandler(ignoreCancelled=true, priority=EventPriority.HIGH)
    public void onItemSpawn(ItemSpawnEvent event)
    {
        if ((!randomEventManger.randsheep()))
        {
            return;
        }
        ItemStack item=event.getEntity().getItemStack();
        Material[] sheepColor={Material.WHITE_WOOL,Material.ORANGE_WOOL,Material.MAGENTA_WOOL,Material.LIGHT_BLUE_WOOL,Material.YELLOW_WOOL,Material.LIME_WOOL,Material.PINK_WOOL,Material.GRAY_WOOL,Material.LIGHT_GRAY_WOOL,Material.CYAN_WOOL,Material.PURPLE_WOOL,Material.BLUE_WOOL,Material.BROWN_WOOL,Material.GREEN_WOOL,Material.RED_WOOL,Material.BLACK_WOOL};
        for (int i=0;i<=16;i++)
        {
            if (i==16)
            {
                return;
            }
            if (item.getType().equals(sheepColor[i]))
            {
                break;
            }
        }
        int i=(int) (Math.random()*(sheepColor.length+1));
        item.setType(sheepColor[i]);
        event.getEntity().setItemStack(item);
    }

}
