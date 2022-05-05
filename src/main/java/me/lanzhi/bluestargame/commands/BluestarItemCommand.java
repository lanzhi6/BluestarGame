package me.lanzhi.bluestargame.commands;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.lanzhi.bluestargame.BluestarGamePlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class BluestarItemCommand implements CommandExecutor, TabExecutor
{
    private static List<String> entityType=new ArrayList<>();

    static
    {
        try
        {
            for (EntityType i: EntityType.values())
            {
                if (i.getName()==null)
                {
                    continue;
                }
                entityType.add(i.getName());
            }
        }
        catch (Throwable e)
        {
            System.out.println("错误在初始化");
        }
    }

    private final BluestarGamePlugin plugin;

    public BluestarItemCommand(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,@NotNull String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.RED+"此命令仅允许玩家输入!");
            return false;
        }
        if (args.length<1)
        {
            sender.sendMessage(ChatColor.RED+"格式错误!");
            return false;
        }
        Player player=(Player) sender;
        if ("watersponge".equals(args[0]))
        {
            int cnt=1;
            if (args.length>1)
            {
                try
                {
                    cnt=Integer.parseInt(args[1]);
                }
                catch (NumberFormatException e)
                {
                }
            }
            ItemStack itemStack=plugin.getBluestarGameManager().getSuperSpongeManager().getWaterSponge().getItem().clone();
            itemStack.setAmount(cnt);
            player.getInventory().addItem(itemStack);
            player.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已为给予您 \"超级海绵\" ×"+cnt);
            return true;
        }
        if ("lavasponge".equals(args[0]))
        {
            int cnt=1;
            if (args.length>1)
            {
                try
                {
                    cnt=Integer.parseInt(args[1]);
                }
                catch (NumberFormatException e)
                {
                }
            }
            ItemStack itemStack=plugin.getBluestarGameManager().getSuperSpongeManager().getlavaSponge().getItem().clone();
            itemStack.setAmount(cnt);
            player.getInventory().addItem(itemStack);
            player.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已为给予您 \"岩浆海绵\" ×"+cnt);
            return true;
        }
        if ("usedwatersponge".equals(args[0]))
        {
            int cnt=1;
            if (args.length>1)
            {
                try
                {
                    cnt=Integer.parseInt(args[1]);
                }
                catch (NumberFormatException e)
                {
                }
            }
            ItemStack itemStack=plugin.getBluestarGameManager().getSuperSpongeManager().getUsedWaterSponge().getItem().clone();
            itemStack.setAmount(cnt);
            player.getInventory().addItem(itemStack);
            player.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已为给予您 \"湿超级海绵\" ×"+cnt);
            return true;
        }
        if ("usedlavasponge".equals(args[0]))
        {
            int cnt=1;
            if (args.length>1)
            {
                try
                {
                    cnt=Integer.parseInt(args[1]);
                }
                catch (NumberFormatException e)
                {
                }
            }
            ItemStack itemStack=plugin.getBluestarGameManager().getSuperSpongeManager().getUsedLavaSponge().getItem().clone();
            itemStack.setAmount(cnt);
            player.getInventory().addItem(itemStack);
            player.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已为给予您 \"湿岩浆海绵\" ×"+cnt);
            return true;
        }
        if ("sword".equals(args[0]))
        {
            if (player.getInventory().getItemInMainHand().getType().isAir())
            {
                player.sendMessage(plugin.getMessageHead()+ChatColor.RED+"请手持任意物品");
                return false;
            }
            NBTItem item=new NBTItem(player.getInventory().getItemInMainHand());
            item.addCompound("BluestarGame").setBoolean("sword",true);
            player.getInventory().setItemInMainHand(item.getItem());
            player.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已为您手持的物品添加\"op剑\"属性");
            return true;
        }
        if ("bow".equals(args[0]))
        {
            if (args.length<2)
            {
                sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"请输入生物类型");
            }
            int cnt=1;
            double v=1;
            if (args.length>=3)
            {
                try
                {
                    v=Double.parseDouble(args[2]);
                }
                catch (NumberFormatException e)
                {
                    sender.sendMessage(plugin.getErrorMessageHead()+"错误,力量不合法");
                    return true;
                }
                if (v<=0D)
                {
                    sender.sendMessage(plugin.getErrorMessageHead()+"错误,力量不合法");
                    return true;
                }
                if (args.length>=4)
                {
                    try
                    {
                        cnt=Integer.parseInt(args[3]);
                    }
                    catch (NumberFormatException e)
                    {
                        sender.sendMessage(plugin.getErrorMessageHead()+"错误,数量不合法");
                        return true;
                    }
                    if (cnt<=0)
                    {
                        sender.sendMessage(plugin.getErrorMessageHead()+"错误,数量不合法");
                        return true;
                    }
                }
            }
            ItemStack boww=new ItemStack(Material.BOW,cnt);
            ItemMeta itemMeta=boww.getItemMeta();
            itemMeta.setLore(Arrays.asList(ChatColor.AQUA+"箭: "+args[1],ChatColor.LIGHT_PURPLE+"力量: "+plugin.getNumberFormat().format(v)));
            boww.setItemMeta(itemMeta);
            NBTItem bow=new NBTItem(boww);
            NBTCompound bluestarGame=bow.addCompound("BluestarGame");
            bluestarGame.setString("arrow",args[1]);
            bluestarGame.setDouble("arrowSpeed",v);
            player.getInventory().addItem(bow.getItem());
            return true;
        }
        sender.sendMessage(ChatColor.RED+"格式错误!");
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,@NotNull String[] args)
    {
        if (args.length==1)
        {
            return Arrays.asList("watersponge","lavasponge","usedwatersponge","usedlavasponge","sword","bow");
        }
        if ("bow".equals(args[0]))
        {
            if (args.length==2)
            {
                return entityType;
            }
            if (args.length==3)
            {
                return Arrays.asList("力量","发射速度=原速度*力量");
            }
            if (args.length==4)
            {
                return Collections.singletonList("数量");
            }
            return Collections.emptyList();
        }
        if (args.length==2)
        {
            return Collections.singletonList("数量");
        }
        return Collections.emptyList();
    }
}
