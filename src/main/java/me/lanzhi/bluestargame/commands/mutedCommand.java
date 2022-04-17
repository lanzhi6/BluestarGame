package me.lanzhi.bluestargame.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.*;

import static me.lanzhi.bluestargame.BluestarGame.*;

public class mutedCommand implements CommandExecutor, TabExecutor
{
    public static ConfigurationSection muted;

    static
    {
        muted=Data.getConfigurationSection("muted");
        if (muted==null)
        {
            Data.set("muted",new HashMap<>());
            muted=Data.getConfigurationSection("muted");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender,Command command,String label,String[] args)
    {
        if (args.length<1)
        {
            sender.sendMessage(messageHead+ChatColor.RED+"格式错误");
            return false;
        }
        switch (args[0])
        {
            case "list":
            {
                if (muted==null)
                {
                    sender.sendMessage(messageHead+"没人被禁言");
                    return true;
                }
                sender.sendMessage(messageHead+"禁言列表:");
                Set<String> mutedPlayers=muted.getKeys(false);
                Date date=Calendar.getInstance().getTime();
                for (String i: mutedPlayers)
                {
                    String time=muted.getString(i);
                    Date mutedtime=null;
                    if (time!=null)
                    {
                        try
                        {
                            mutedtime=BluestarDataFormat.parse(time);
                        }
                        catch (Throwable e)
                        {
                        }
                    }
                    if (mutedtime==null||mutedtime.after(date))
                    {
                        sender.sendMessage("玩家: "+Bukkit.getOfflinePlayer(UUID.fromString(i)).getName()+" 截止日期:"+(mutedtime==null?"永远":time));
                    }
                    else
                    {
                        muted.set(i,null);
                    }
                }
                return true;
            }
            case "set":
            {
                if (args.length<2)
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"请输入玩家名");
                    return true;
                }
                if (args.length<3)
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"请输入设置为 禁言或取消");
                    return true;
                }
                Player player=Bukkit.getPlayer(args[1]);
                if (player==null)
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"未找到玩家,请确认玩家已上线");
                    return true;
                }
                if (!Boolean.parseBoolean(args[2]))
                {
                    muted.set(player.getUniqueId().toString(),null);
                    sender.sendMessage(messageHead+ChatColor.GREEN+"取消禁言成功!");
                    return true;
                }
                Date mutedTo=Calendar.getInstance().getTime();
                if (args.length>=4)
                {
                    if (args[3].length()<2)
                    {
                        sender.sendMessage(messageHead+"时间格式错误");
                        return true;
                    }
                    args[3]=args[3].toLowerCase();
                    char end=args[3].charAt(args[3].length()-1);
                    args[3]=args[3].substring(0,args[3].length()-1);
                    long num;
                    try
                    {
                        num=Long.parseLong(args[3]);
                    }
                    catch (NumberFormatException e)
                    {
                        sender.sendMessage(messageHead+"时间格式错误");
                        return true;
                    }
                    switch (end)
                    {
                        case 's':
                        {
                            mutedTo.setTime(mutedTo.getTime()+num*1000L);
                            break;
                        }
                        case 'm':
                        {
                            mutedTo.setTime(mutedTo.getTime()+num*60000L);
                            break;
                        }
                        case 'h':
                        {
                            mutedTo.setTime(mutedTo.getTime()+num*3600000L);
                            break;
                        }
                        case 'd':
                        {
                            mutedTo.setTime(mutedTo.getTime()+num*86400000L);
                            break;
                        }
                        default:
                        {
                            sender.sendMessage(messageHead+ChatColor.RED+"时间单位错误");
                            return true;
                        }
                    }
                    muted.set(player.getUniqueId().toString(),BluestarDataFormat.format(mutedTo));
                }
                else
                {
                    muted.set(player.getUniqueId().toString(),"---");
                }
                sender.sendMessage(messageHead+ChatColor.GREEN+"设置禁言成功!");
                return true;
            }
            default:
            {
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender,Command command,String alias,String[] args)
    {
        if (args.length==1)
        {
            List<String> tablist=new ArrayList<>();
            tablist.add("set");
            tablist.add("list");
            return tablist;
        }
        if (args.length==2&&"set".equals(args[0]))
        {
            return null;
        }
        if (args.length==3&&"set".equals(args[0]))
        {
            List<String> tablist=new ArrayList<>();
            tablist.add("true");
            tablist.add("false");
            return tablist;
        }
        if (args.length==4&&"set".equals(args[0]))
        {
            if (args[3].endsWith("s")||args[3].endsWith("m")||args[3].endsWith("h")||args[3].endsWith("d"))
            {
                return Collections.singletonList(args[3]);
            }
            return Arrays.asList(args[3]+"s",args[3]+"m",args[3]+"h",args[3]+"d");
        }
        return new ArrayList<>();
    }
}
