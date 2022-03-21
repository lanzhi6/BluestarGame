package me.lanzhi.bluestargame.commands;

import me.lanzhi.bluestargame.BluestarGame;
import me.lanzhi.bluestargame.Type.muted;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Set;

public class mutedCommand implements CommandExecutor, TabExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if("list".equals(args[0])&&args.length==1)
        {
            sender.sendMessage(ChatColor.GOLD + "禁言列表:");
            muted mute = (muted) BluestarGame.Data.get("muted");
            if(mute==null)
            {
                BluestarGame.Data.set("muted",new muted(new HashMap<>()));
                BluestarGame.Data.save();
            }
            mute=(muted) BluestarGame.Data.get("muted");
            HashMap all=(HashMap) mute.getall();
            for(String key:(Set<String>)all.keySet())
            {
                if(Boolean.parseBoolean(all.get(key).toString()))
                {
                    sender.sendMessage(ChatColor.RED + key);
                }
            }
            return true;
        }
        if(args.length!=3||!"set".equals(args[0]))
        {
            sender.sendMessage(ChatColor.RED + "格式错误!");
            return false;
        }
        muted mute = (muted) BluestarGame.Data.get("muted");
        mute.set(args[1],Boolean.parseBoolean(args[2]));
        sender.sendMessage(ChatColor.GREEN+"设置成功");
        BluestarGame.Data.set("muted",mute);
        BluestarGame.Data.save();
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args)
    {
        if(args.length==1)
        {
            List<String> tablist=new ArrayList<>();
            tablist.add("set");
            tablist.add("list");
            return tablist;
        }
        if(args.length==2&&!"list".equals(args[0]))
        {
            List<String> tablist=new ArrayList<>();
            tablist.add("玩家名");
            return tablist;
        }
        if(args.length==3&&!"list".equals(args[0]))
        {
            List<String> tablist=new ArrayList<>();
            tablist.add("true");
            tablist.add("false");
            return tablist;
        }
        return new ArrayList<String>();
    }
}
