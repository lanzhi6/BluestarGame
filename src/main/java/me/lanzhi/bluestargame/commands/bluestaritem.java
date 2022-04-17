package me.lanzhi.bluestargame.commands;

import de.tr7zw.nbtapi.NBTItem;
import me.lanzhi.bluestargame.Type.superSponge;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static me.lanzhi.bluestargame.BluestarGame.messageHead;

public class bluestaritem implements CommandExecutor, TabExecutor
{
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
            ItemStack itemStack=superSponge.getWaterSponge().getItem().clone();
            itemStack.setAmount(cnt);
            player.getInventory().addItem(itemStack);
            player.sendMessage(messageHead+ChatColor.GREEN+"已为给予您 \"超级海绵\" ×"+cnt);
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
            ItemStack itemStack=superSponge.getlavaSponge().getItem().clone();
            itemStack.setAmount(cnt);
            player.getInventory().addItem(itemStack);
            player.sendMessage(messageHead+ChatColor.GREEN+"已为给予您 \"岩浆海绵\" ×"+cnt);
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
            ItemStack itemStack=superSponge.getUsedWaterSponge().getItem().clone();
            itemStack.setAmount(cnt);
            player.getInventory().addItem(itemStack);
            player.sendMessage(messageHead+ChatColor.GREEN+"已为给予您 \"湿超级海绵\" ×"+cnt);
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
            ItemStack itemStack=superSponge.getUsedLavaSponge().getItem().clone();
            itemStack.setAmount(cnt);
            player.getInventory().addItem(itemStack);
            player.sendMessage(messageHead+ChatColor.GREEN+"已为给予您 \"湿岩浆海绵\" ×"+cnt);
            return true;
        }
        if ("sword".equals(args[0]))
        {
            if (player.getInventory().getItemInMainHand().getType().isAir())
            {
                player.sendMessage(messageHead+ChatColor.RED+"请手持任意物品");
                return false;
            }
            NBTItem item=new NBTItem(player.getInventory().getItemInMainHand());
            item.addCompound("BluestarGame").setBoolean("sword",true);
            player.getInventory().setItemInMainHand(item.getItem());
            player.sendMessage(messageHead+ChatColor.GREEN+"已为您手持的物品添加\"op剑\"属性");
            return true;
        }
        sender.sendMessage(ChatColor.RED+"格式错误!");
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,@NotNull String[] args)
    {
        List<String> tablist=new ArrayList<>();
        tablist.add("watersponge");
        tablist.add("lavasponge");
        tablist.add("usedwatersponge");
        tablist.add("usedlavasponge");
        tablist.add("sword");
        return tablist;
    }
}
