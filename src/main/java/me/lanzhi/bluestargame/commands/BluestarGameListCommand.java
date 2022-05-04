package me.lanzhi.bluestargame.commands;

import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.managers.RandomEventManger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BluestarGameListCommand implements CommandExecutor
{
    private final BluestarGamePlugin plugin;
    private final RandomEventManger randomEventManger;
    public BluestarGameListCommand(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
        randomEventManger=plugin.getBluestarGameManager().getRandomEventManger();
    }
    @Override
    public boolean onCommand(CommandSender sender,Command command,String label,String[] args)
    {
        sender.sendMessage(plugin.getMessageHead());
        if (randomEventManger.randchat())
        {
            sender.sendMessage(ChatColor.GOLD+"随机聊天:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD+"随机聊天:关闭");
        }

        if (randomEventManger.randsheep())
        {
            sender.sendMessage(ChatColor.GOLD+"随机羊毛颜色:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD+"随机羊毛颜色:关闭");
        }

        if (randomEventManger.randdamage())
        {
            sender.sendMessage(ChatColor.GOLD+"随机伤害:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD+"随机伤害:关闭");
        }

        if (randomEventManger.the24())
        {
            sender.sendMessage(ChatColor.GOLD+"24点:开启");
            sender.sendMessage(ChatColor.GOLD+"  数字为:"+randomEventManger.fourNum[0]+","+randomEventManger.fourNum[1]+","+randomEventManger.fourNum[2]+","+randomEventManger.fourNum[3]+",");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD+"24点:关闭");
        }

        if (randomEventManger.morediamond())
        {
            sender.sendMessage(ChatColor.GOLD+"更多钻石:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD+"更多钻石:关闭");
        }

        if (randomEventManger.morecoal())
        {
            sender.sendMessage(ChatColor.GOLD+"更多煤炭:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD+"更多煤炭:关闭");
        }

        if (randomEventManger.morecopper())
        {
            sender.sendMessage(ChatColor.GOLD+"更多粗铜:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD+"更多粗铜:关闭");
        }

        if (randomEventManger.moreredstone())
        {
            sender.sendMessage(ChatColor.GOLD+"更多红石:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD+"更多红石:关闭");
        }

        if (randomEventManger.moregold())
        {
            sender.sendMessage(ChatColor.GOLD+"更多粗金:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD+"更多粗金:关闭");
        }

        if (randomEventManger.moreemerald())
        {
            sender.sendMessage(ChatColor.GOLD+"更多绿宝石:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD+"更多绿宝石:关闭");
        }

        if (randomEventManger.morelapis())
        {
            sender.sendMessage(ChatColor.GOLD+"更多青金石:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD+"更多青金石:关闭");
        }

        if (randomEventManger.moreiron())
        {
            sender.sendMessage(ChatColor.GOLD+"更多粗铁:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD+"更多粗铁:关闭");
        }

        if (randomEventManger.respawn())
        {
            sender.sendMessage(ChatColor.GOLD+"复活:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD+"复活:关闭");
        }
        return true;
    }
}


