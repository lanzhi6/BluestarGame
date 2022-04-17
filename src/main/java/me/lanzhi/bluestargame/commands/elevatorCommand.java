package me.lanzhi.bluestargame.commands;

import me.lanzhi.bluestargame.Type.Elevator;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static me.lanzhi.bluestargame.BluestarGame.messageHead;
import static me.lanzhi.bluestargame.Type.Elevator.elevators;

public class elevatorCommand implements CommandExecutor, TabExecutor
{
    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,@NotNull String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage(messageHead+ChatColor.RED+"此命令仅允许玩家输入!");
            return false;
        }
        if (args.length==0)
        {
            sender.sendMessage(messageHead+ChatColor.RED+"格式错误");
            return false;
        }
        if (args[0].equals("add"))
        {
            if (args.length!=8)
            {
                sender.sendMessage(messageHead+ChatColor.RED+"格式错误!");
                return false;
            }
            Elevator elevator;
            try
            {
                elevator=new Elevator(
                Integer.parseInt(args[2]),
                Integer.parseInt(args[3]),
                Integer.parseInt(args[4]),
                Integer.parseInt(args[5]),
                Integer.parseInt(args[6]),
                Integer.parseInt(args[7]));
            }
            catch (NumberFormatException e)
            {
                sender.sendMessage(messageHead+ChatColor.RED+"错误!X,Z,Y应均为整数");
                return true;
            }
            elevators.set(args[1],elevator);
            sender.sendMessage(ChatColor.RED+"添加成功");
            return true;
        }
        if (args[0].equals("list"))
        {
            sender.sendMessage(messageHead+ChatColor.GOLD+"电梯列表:");
            for (String s:elevators.getKeys(false))
            {
                Elevator elevator=elevators.getObject(s,Elevator.class);
                if (elevator==null)
                {
                    continue;
                }
                sender.sendMessage(ChatColor.WHITE+s+" -- X:("+elevator.getMinX()+"~"+elevator.getMaxX()+"),Z:("+elevator.getMinZ()+"~"+elevator.getMaxZ()+"),Y:("+elevator.getMinY()+"~"+elevator.getMaxY()+")");
            }
            return true;
        }
        if (args[0].equals("remove"))
        {
            if (args.length<2)
            {
                sender.sendMessage(messageHead+ChatColor.RED+"格式错误");
                return false;
            }
            elevators.set(args[1],null);
            sender.sendMessage(messageHead+ChatColor.RED+"已删除");
            return true;
        }

        sender.sendMessage(messageHead+ChatColor.RED+"格式错误");
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,@NotNull String[] args)
    {
        if (args.length==1)
        {
            return Arrays.asList("add","remove","list");
        }
        if (args[0].equals("add"))
        {
            if (args.length==2)
            {
                return Arrays.asList("电梯名称");
            }
            if (args.length==3)
            {
                return Arrays.asList("minX",((Player) sender).getLocation().getBlockX()+"");
            }
            if (args.length==4)
            {
                return Arrays.asList("maxX",((Player) sender).getLocation().getBlockX()+"");
            }
            if (args.length==5)
            {
                return Arrays.asList("minZ",((Player) sender).getLocation().getBlockZ()+"");
            }
            if (args.length==6)
            {
                return Arrays.asList("maxZ",((Player) sender).getLocation().getBlockZ()+"");
            }
            if (args.length==7)
            {
                return Arrays.asList("minY",((Player) sender).getLocation().getBlockY()+"");
            }
            if (args.length==8)
            {
                return Arrays.asList("maxY",((Player) sender).getLocation().getBlockY()+"");
            }
        }
        if (args[0].equals("remove"))
        {
            if (args.length==2)
            {
                return Collections.singletonList("电梯名称");
            }
        }
        return null;
    }
}
