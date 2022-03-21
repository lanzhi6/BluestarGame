package me.lanzhi.bluestargame.commands;

import me.lanzhi.bluestargame.Type.Public;
import me.lanzhi.bluestargame.Type.elevator;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static me.lanzhi.bluestargame.BluestarGame.Data;

public class elevatorCommand implements CommandExecutor, TabExecutor
{

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.RED+"此命令仅允许玩家输入!");
            return false;
        }
        if (args.length==0){sender.sendMessage(ChatColor.RED+"格式错误");return false;}
        if (args[0].equals("add"))
        {
            if (args.length!=8)
            {
                sender.sendMessage(ChatColor.RED+"格式错误!");
                return false;
            }
            Map<String,Object> ele=new HashMap<>();
            try
            {
                ele.put("minX",Integer.parseInt(args[2]));
                ele.put("maxX",Integer.parseInt(args[3]));
                ele.put("minZ",Integer.parseInt(args[4]));
                ele.put("maxZ",Integer.parseInt(args[5]));
                ele.put("minY",Integer.parseInt(args[6]));
                ele.put("maxY",Integer.parseInt(args[7]));
            }
            catch (NumberFormatException e)
            {
                sender.sendMessage(ChatColor.RED+"错误!X,Z,Y应均为整数");
                return false;
            }
            Public test=(Public) Data.get("elevators");
            if (test==null)test=new Public(null);
            test.map.put(args[1],new elevator(ele));
            Data.set("elevators",test);
            sender.sendMessage(ChatColor.RED+"添加成功");
            return true;
        }
        if (args[0].equals("list"))
        {
            Public test=(Public) Data.get("elevators");
            if (test==null){sender.sendMessage(ChatColor.RED+"没有电梯");return false;}
            Set<String>elevators=test.map.keySet();
            sender.sendMessage(ChatColor.GOLD+"电梯列表:");
            for (String s:elevators)
            {
                Object object=test.map.get(s);
                if (object instanceof elevator)
                {
                    elevator ele=(elevator)object;
                    sender.sendMessage(ChatColor.WHITE+s+" -- X:("+ele.getMinX()+"~"+ele.getMaxX()+"),Z:("+ele.getMinZ()+"~"+ele.getMaxZ()+"),Y:("+ele.getMinY()+"~"+ele.getMaxY()+")");
                }
            }
            return true;
        }
        if (args[0].equals("remove"))
        {
            if(args.length<2)
            {
                sender.sendMessage(ChatColor.RED+"格式错误");return false;
            }
            Public test=(Public) Data.get("elevators");
            if (test==null)test=new Public(null);
            test.map.remove(args[1]);
            Data.set("elevators",test);
            sender.sendMessage(ChatColor.RED+"已删除");
            return true;
        }

        sender.sendMessage(ChatColor.RED+"格式错误");return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
    {
        if (args.length == 1)
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
                return Arrays.asList("minX",((Player)sender).getLocation().getBlockX()+"");
            }
            if (args.length==4)
            {
                return Arrays.asList("maxX",((Player)sender).getLocation().getBlockX()+"");
            }
            if (args.length==5)
            {
                return Arrays.asList("minZ",((Player)sender).getLocation().getBlockZ()+"");
            }
            if (args.length==6)
            {
                return Arrays.asList("maxZ",((Player)sender).getLocation().getBlockZ()+"");
            }
            if (args.length==7)
            {
                return Arrays.asList("minY",((Player)sender).getLocation().getBlockY()+"");
            }
            if (args.length==8)
            {
                return Arrays.asList("maxY",((Player)sender).getLocation().getBlockY()+"");
            }
        }
        if (args[0].equals("remove"))
        {
            if (args.length==2)
            {
                return Arrays.asList("电梯名称");
            }
        }
        return null;
    }
}
