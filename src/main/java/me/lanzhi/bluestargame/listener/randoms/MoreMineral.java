package me.lanzhi.bluestargame.listener.randoms;

import me.lanzhi.bluestargame.Ctrls.CTRL;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class MoreMineral implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockDropItem(BlockDropItemEvent event)
    {
        if (CTRL.morediamond())
        {
            if ((event.getBlockState().getType() != Material.DIAMOND_ORE) && (event.getBlockState().getType() != Material.DEEPSLATE_DIAMOND_ORE))
            {
                return;
            }
            boolean flag = false;
            List<Item> items = event.getItems();
            for (int i = 0; i <= items.size(); i++)
            {
                if (items.get(i).getItemStack().getType() == Material.DIAMOND)
                {
                    flag = true;
                    break;
                }
            }
            if (!flag)
            {
                return;
            }
            event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
        }
        if (CTRL.morecoal())
        {
            if ((event.getBlockState().getType() != Material.COAL_ORE) && (event.getBlockState().getType() != Material.DEEPSLATE_COAL_ORE))
            {
                return;
            }
            boolean flag = false;
            List<Item> items = event.getItems();
            for (int i = 0; i <= items.size(); i++)
            {
                if (items.get(i).getItemStack().getType() == Material.COAL)
                {
                    flag = true;
                    break;
                }
            }
            if (!flag)
            {
                return;
            }
            event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.COAL));
        }
        if (CTRL.moreredstone())
        {
            if ((event.getBlockState().getType() != Material.REDSTONE_ORE)&&
                    (event.getBlockState().getType() != Material.DEEPSLATE_REDSTONE_ORE))
            {
                return;
            }
            boolean flag = false;
            List<Item> items = event.getItems();
            for (int i = 0; i <= items.size(); i++)
            {
                if (items.get(i).getItemStack().getType() == Material.REDSTONE)
                {
                    flag = true;
                    break;
                }
            }
            if (!flag)
            {
                return;
            }
            event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.REDSTONE));
        }
        if (CTRL.morecopper())
        {
            if ((event.getBlockState().getType() != Material.COPPER_ORE)&&
                    (event.getBlockState().getType() != Material.DEEPSLATE_COPPER_ORE))
            {
                return;
            }
            boolean flag = false;
            List<Item> items = event.getItems();
            for (int i = 0; i <= items.size(); i++)
            {
                if (items.get(i).getItemStack().getType() == Material.RAW_COPPER)
                {
                    flag = true;
                    break;
                }
            }
            if (!flag)
            {
                return;
            }
            event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.RAW_COPPER));
        }
        if (CTRL.moregold()) {
            if ((event.getBlockState().getType() != Material.GOLD_ORE) &&
                    (event.getBlockState().getType() != Material.DEEPSLATE_GOLD_ORE)) {
                return;
            }
            boolean flag = false;
            List<Item> items = event.getItems();
            for (int i = 0; i <= items.size(); i++) {
                if (items.get(i).getItemStack().getType() == Material.RAW_GOLD) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return;
            }
            event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.RAW_GOLD));
        }
        if (CTRL.moreemerald()) {
            if ((event.getBlockState().getType() != Material.EMERALD_ORE) &&
                    (event.getBlockState().getType() != Material.DEEPSLATE_EMERALD_ORE)) {
                return;
            }
            boolean flag = false;
            List<Item> items = event.getItems();
            for (int i = 0; i <= items.size(); i++) {
                if (items.get(i).getItemStack().getType() == Material.EMERALD) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return;
            }
            event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.EMERALD));
        }
        if (CTRL.morelapis()) {
            if ((event.getBlockState().getType() != Material.LAPIS_ORE) &&
                    (event.getBlockState().getType() != Material.DEEPSLATE_LAPIS_ORE)) {
                return;
            }
            boolean flag = false;
            List<Item> items = event.getItems();
            for (int i = 0; i <= items.size(); i++) {
                if (items.get(i).getItemStack().getType() == Material.LAPIS_LAZULI) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return;
            }
            event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.LAPIS_LAZULI));
        }
        if (CTRL.moreiron()) {
            if ((event.getBlockState().getType() != Material.IRON_ORE) &&
                    (event.getBlockState().getType() != Material.DEEPSLATE_IRON_ORE)) {
                return;
            }
            boolean flag = false;
            List<Item> items = event.getItems();
            for (int i = 0; i <= items.size(); i++) {
                if ((items.get(i)).getItemStack().getType() == Material.RAW_IRON) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return;
            }
            event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.RAW_IRON));
        }
    }
}
