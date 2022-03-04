package me.lanzhi.bluestargame.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class chat implements CommandExecutor, TabExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (args.length < 2)
        {
            sender.sendMessage(ChatColor.RED+"错误!");
            return false;
        }
        Player player= Bukkit.getPlayer(args[0]);
        if (player == null)
        {
            sender.sendMessage(ChatColor.RED+"玩家不存在");
        }
        StringBuilder builder = new StringBuilder();
        for (int i=1;i<args.length;i++)
        {
            builder.append(args[i]+" ");
        }
        player.chat(PlaceholderAPI.setPlaceholders(player, builder.toString()));
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args)
    {
        if (args.length >= 2)
        {
            List<String> tablist=new ArrayList<>();
            tablist.add("message");
            return tablist;
        }
        return null;
    }
}
