package me.lanzhi.bluestargame.commands;

import me.lanzhi.bluestargame.Ctrls.CTRL;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static me.lanzhi.bluestargame.BluestarGame.*;

public class bsgamelist implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        sender.sendMessage(messageHead);
        if (CTRL.randchat())
        {
            sender.sendMessage(ChatColor.GOLD + "随机聊天:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD + "随机聊天:关闭");
        }

        if (CTRL.randsheep())
        {
            sender.sendMessage(ChatColor.GOLD + "随机羊毛颜色:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD + "随机羊毛颜色:关闭");
        }

        if (CTRL.randdamage())
        {
            sender.sendMessage(ChatColor.GOLD + "随机伤害:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD + "随机伤害:关闭");
        }

        if (CTRL.the24())
        {
            sender.sendMessage(ChatColor.GOLD + "24点:开启");
            sender.sendMessage(ChatColor.GOLD + "  数字为:" + CTRL.fourNum[0] + "," + CTRL.fourNum[1] + "," + CTRL.fourNum[2] + "," + CTRL.fourNum[3] + ",");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD + "24点:关闭");
        }

        if (CTRL.morediamond())
        {
            sender.sendMessage(ChatColor.GOLD + "更多钻石:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD + "更多钻石:关闭");
        }

        if (CTRL.morecoal())
        {
            sender.sendMessage(ChatColor.GOLD + "更多煤炭:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD + "更多煤炭:关闭");
        }

        if (CTRL.morecopper())
        {
            sender.sendMessage(ChatColor.GOLD + "更多粗铜:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD + "更多粗铜:关闭");
        }

        if (CTRL.moreredstone())
        {
            sender.sendMessage(ChatColor.GOLD + "更多红石:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD + "更多红石:关闭");
        }

        if (CTRL.moregold())
        {
            sender.sendMessage(ChatColor.GOLD + "更多粗金:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD + "更多粗金:关闭");
        }

        if (CTRL.moreemerald())
        {
            sender.sendMessage(ChatColor.GOLD + "更多绿宝石:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD + "更多绿宝石:关闭");
        }

        if (CTRL.morelapis())
        {
            sender.sendMessage(ChatColor.GOLD + "更多青金石:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD + "更多青金石:关闭");
        }

        if (CTRL.moreiron())
        {
            sender.sendMessage(ChatColor.GOLD + "更多粗铁:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD + "更多粗铁:关闭");
        }

        if (CTRL.respawn())
        {
            sender.sendMessage(ChatColor.GOLD + "复活:开启");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD + "复活:关闭");
        }
        return true;
    }
}


