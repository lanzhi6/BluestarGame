package me.lanzhi.bluestargame;

import de.tr7zw.nbtapi.NBTItem;
import me.lanzhi.bluestargame.Ctrls.CTRL;
import me.lanzhi.bluestargame.Type.superSponge;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.SheepDyeWoolEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class listener implements org.bukkit.event.Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityDamage(EntityDamageEvent event)
    {
        if(!CTRL.randdamage())
        {
            return;
        }
        Entity entity = event.getEntity();
        double hurt = (Math.random() - 0.3D) * event.getDamage() * 5.0D;
        entity.sendMessage(ChatColor.GREEN + "[BluestarGame]随机伤害");
        entity.sendMessage(ChatColor.YELLOW + "原伤害:" + String.format("%.2f", new Object[]{Double.valueOf(event.getDamage() / 5.0D)}) + ",随机伤害:" + String.format("%.2f", new Object[]{Double.valueOf(hurt / 5.0D)}));
        if (hurt > 0.0D)
        {
            event.setDamage(hurt);
        }
        else
        {
            event.setCancelled(true);
            ((Damageable) entity).setMaxHealth(Math.max(((Damageable) entity).getHealth() - hurt, ((Damageable) entity).getMaxHealth()));
            ((Damageable) entity).setHealth(((Damageable) entity).getHealth() - hurt);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onChatForRand(AsyncPlayerChatEvent event)
    {

        String message = event.getMessage();
        if (!CTRL.randchat())
        {
            return;
        }
        String[] chatColor={
                ChatColor.GREEN.toString(),
                ChatColor.YELLOW.toString(),
                ChatColor.RED.toString(),
                ChatColor.GOLD.toString(),
                ChatColor.AQUA.toString(),
                ChatColor.BLACK.toString(),
                ChatColor.BLUE.toString(),
                ChatColor.DARK_GRAY.toString(),
                ChatColor.DARK_PURPLE.toString(),
                ChatColor.DARK_AQUA.toString(),
                ChatColor.DARK_BLUE.toString(),
                ChatColor.DARK_GREEN.toString(),
                ChatColor.DARK_RED.toString(),
                ChatColor.GRAY.toString(),
                ChatColor.LIGHT_PURPLE.toString(),
                ChatColor.WHITE.toString()};
        int i = (int) (Math.random() * 17.0D);
        event.setMessage(chatColor[i] + message);
    }

    @EventHandler
    public void onChatFor24(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        if ((!CTRL.the24()) && (!CTRL.the24end))
        {
            return;
        }
        String[] ans = me.lanzhi.bluestargame.Ctrls.math.yunsuan(message);
        if (ans == null)
        {
            return;
        }
        if ((int) Double.parseDouble(ans[0]) != 24)
        {
            return;
        }
        int[] a = CTRL.fourNum;
        boolean fflag = true;
        for (int i = 1; i <= 4; i++) {
            boolean flag = false;
            for (int j = 0; j < 4; j++) {
                if ((ans[i].equals(a[j] + "")) && (a[j] != -1)) {
                    a[j] = -1;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                fflag = false;
                break;
            }
        }
        if (fflag)
        {
            org.bukkit.Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + "[BluestarGame]" + event.getPlayer().getName() + "答案正确!");
            org.bukkit.Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + "[BluestarGame]获得1000!");
            CTRL.the24end = true;
            me.lanzhi.bluestarapi.Bluestar.useCommand(org.bukkit.Bukkit.getConsoleSender(), "eco give " + event.getPlayer().getName() + " 1000");
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
     public void onSheepDyeWool(SheepDyeWoolEvent event) {
        if ((!CTRL.randsheep()) || (event.isCancelled()))
        {
            return;
        }
        DyeColor[] sheepColor={DyeColor.WHITE,
                DyeColor.ORANGE,
                DyeColor.MAGENTA,
                DyeColor.LIGHT_BLUE,
                DyeColor.YELLOW,
                DyeColor.LIME,
                DyeColor.PINK,
                DyeColor.GRAY,
                DyeColor.LIGHT_GRAY,
                DyeColor.CYAN,
                DyeColor.PURPLE,
                DyeColor.BLUE,
                DyeColor.BROWN,
                DyeColor.GREEN,
                DyeColor.RED,
                DyeColor.BLACK};
        int i=(int)(Math.random()*(sheepColor.length+1));
        event.setColor(sheepColor[i]);
        if (event.getPlayer() != null)
        {
            event.getPlayer().sendMessage(ChatColor.GREEN + "[BluestarGame]随机羊毛颜色");
            event.getPlayer().sendMessage(ChatColor.GREEN + "你的羊的颜色变成了:" + ChatColor.GOLD + sheepColor[i]);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onItemSpawn(ItemSpawnEvent event) {
        if ((!CTRL.randsheep()) || (event.isCancelled()))
        {
            return;
        }
        ItemStack item = event.getEntity().getItemStack();
        Material[] sheepColor = {Material.WHITE_WOOL, Material.ORANGE_WOOL, Material.MAGENTA_WOOL, Material.LIGHT_BLUE_WOOL, Material.YELLOW_WOOL, Material.LIME_WOOL, Material.PINK_WOOL, Material.GRAY_WOOL, Material.LIGHT_GRAY_WOOL, Material.CYAN_WOOL, Material.PURPLE_WOOL, Material.BLUE_WOOL, Material.BROWN_WOOL, Material.GREEN_WOOL, Material.RED_WOOL, Material.BLACK_WOOL};
        for (int i = 0; i <= 16; i++)
        {
            if (i == 16)
            {
                return;
            }
            if (item.getType().equals(sheepColor[i]))
            {
                break;
            }
        }
        int i = (int) (Math.random() * (sheepColor.length + 1));
        item.setType(sheepColor[i]);
        event.getEntity().setItemStack(item);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockDropItem(BlockDropItemEvent event) {
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

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerPlaceBlock(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();
        NBTItem nbtItem = new NBTItem(item);
        if (!nbtItem.getBoolean("BluestarGameSponge").booleanValue()) {
            return;
        }
        List<superSponge> sponges = (List<superSponge>) BluestarGame.config.getConfig().getList("superSponges");
        if (sponges != null) {
            sponges.add(new superSponge(BluestarGame.config.getConfig().getInt("spongeR"), event.getBlock().getLocation(), event.getPlayer()));
        } else {
            sponges = new java.util.ArrayList();
            sponges.add(new superSponge(BluestarGame.config.getConfig().getInt("spongeR"), event.getBlock().getLocation(), event.getPlayer()));
        }
        BluestarGame.config.getConfig().set("superSponges", sponges);
        BluestarGame.config.saveConfig();
        BluestarGame.config.reloadConfig();
    }
}