package me.lanzhi.bluestargame.commands;

import java.util.ArrayList;
import java.util.List;
import me.lanzhi.bluestargame.Ctrls.CTRL;
import me.lanzhi.bluestargame.Ctrls.CtrlSponge;
import me.lanzhi.bluestargame.Type.superSponge;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import static me.lanzhi.bluestargame.BluestarGame.config;

public class maincommand implements CommandExecutor, TabExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (args.length != 2)
        {
            sender.sendMessage(ChatColor.RED + "格式错误!");
            return false;
        }
        if (args[0].equals("randdamage"))
        {
            if (args[1].equals("true"))
            {
                CTRL.randdamage(true);
                sender.sendMessage(ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.randdamage(false);
                sender.sendMessage(ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("randchat"))
        {
            if (args[1].equals("true"))
            {
                CTRL.randchat(true);
                sender.sendMessage(ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.randchat(false);
                sender.sendMessage(ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("randsheep"))
        {
            if (args[1].equals("true"))
            {
                CTRL.randsheep(true);
                sender.sendMessage(ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.randsheep(false);
                sender.sendMessage(ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("24"))
        {
            if (args[1].equals("true"))
            {
                CTRL.the24(true);
                sender.sendMessage(ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.the24(false);
                sender.sendMessage(ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("morediamond"))
        {
            if (args[1].equals("true"))
            {
                CTRL.morediamond(true);
                sender.sendMessage(ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.morediamond(false);
                sender.sendMessage(ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("morecoal"))
        {
            if (args[1].equals("true"))
            {
                CTRL.morecoal(true);
                sender.sendMessage(ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.morecoal(false);
                sender.sendMessage(ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("morecopper"))
        {
            if (args[1].equals("true"))
            {
                CTRL.morecopper(true);
                sender.sendMessage(ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.morecopper(false);
                sender.sendMessage(ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("moreiron"))
        {
            if (args[1].equals("true"))
            {
                CTRL.moreiron(true);
                sender.sendMessage(ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.moreiron(false);
                sender.sendMessage(ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("moregold"))
        {
            if (args[1].equals("true"))
            {
                CTRL.moregold(true);
                sender.sendMessage(ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.moregold(false);
                sender.sendMessage(ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("moreemerald"))
        {
            if (args[1].equals("true"))
            {
                CTRL.moreemerald(true);
                sender.sendMessage(ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.moreemerald(false);
                sender.sendMessage(ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("morelapis"))
        {
            if (args[1].equals("true"))
            {
                CTRL.morelapis(true);
                sender.sendMessage(ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.morelapis(false);
                sender.sendMessage(ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("respawn"))
        {
            if (args[1].equals("true"))
            {
                CTRL.respawn(true);
                sender.sendMessage(ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.respawn(false);
                sender.sendMessage(ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("all"))
        {
            if (args[1].equals("true"))
            {
                CTRL.all(true);
                sender.sendMessage(ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.all(false);
                sender.sendMessage(ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("auto"))
        {
            if (args[1].equals("true"))
            {
                CTRL.runAuto(true);
                sender.sendMessage(ChatColor.GREEN + "自动切换启动");
            }
            else
            {
                CTRL.runAuto(false);
                sender.sendMessage(ChatColor.GREEN + "自动切换关闭");
            }
            return true;
        }
        if (args[0].equals("spongeR"))
        {
            int r = 0;
            try
            {
                r = Integer.valueOf(args[1]).intValue();
            }
            catch (NumberFormatException e)
            {
                sender.sendMessage(ChatColor.RED + "错误!");
                return false;
            }
            if ((r > 32) || (r < 1))
            {
                sender.sendMessage(ChatColor.RED + "错误,范围应在1-32之间!");
                return false;
            }
            config.set("spongeR", Integer.valueOf(r));
            config.save();
            sender.sendMessage(ChatColor.GREEN + "设置成功");
            return true;
        }
        if (args[0].equals("newsponge")&&sender.hasPermission("bluestargame.lanzhi"))
        {
            int r;
            r=Integer.parseInt(args[1]);
            Location locc=((Player)(sender)).getLocation();
            Location loc=new Location(locc.getWorld(),locc.getBlockX(),locc.getBlockY(),locc.getBlockZ());
            CtrlSponge.add(new superSponge(r,loc,(Player)sender,true,true));
            return true;
        }
        if ("boom".equals(args[0])&&sender.hasPermission("bluestargame.lanzhi"))
        {
            Location location=((Player)sender).getLocation();
            try
            {
                location.getWorld().createExplosion(location,Integer.parseInt(args[1]),true,true);
            }
            catch (NumberFormatException e)
            {
                sender.sendMessage(ChatColor.RED + "错误!");
            }
            return true;
        }
        if ("health".equals(args[0])&&sender.hasPermission("bluestargame.lanzhi"))
        {
            Player player=(Player)sender;
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Long.parseLong(args[1]));
            player.setHealthScaled(false);
            return true;
        }
        if ("test".equals(args[0])&&sender.hasPermission("bluestargame.lanzhi"))
        {
            Player player=(Player)sender;
            player.setHealthScaled(!player.isHealthScaled());
            return true;
        }
        sender.sendMessage(ChatColor.RED + "格式错误!");
        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args)
    {
        if (args.length == 1)
        {
            List<String> tablist = new ArrayList();
            tablist.add("randdamage");
            tablist.add("randchat");
            tablist.add("randsheep");
            tablist.add("24");
            tablist.add("morediamond");
            tablist.add("morecoal");
            tablist.add("morecopper");
            tablist.add("moreiron");
            tablist.add("moregold");
            tablist.add("moreemerald");
            tablist.add("morelapis");
            tablist.add("respawn");
            tablist.add("all");
            tablist.add("auto");
            tablist.add("spongeR");
            if(sender.hasPermission("bluestargame.lanzhi"))
            {
                tablist.add("newsponge");
                tablist.add("boom");
                tablist.add("health");
                tablist.add("reload");
            }
            return tablist;
        }
        if(("spongeR".equals(args[0])||"newsponge".equals(args[0]))&&args.length==2)
        {
            List<String> tablist = new ArrayList();
            tablist.add("超级海绵吸水范围");
            return tablist;
        }
        if (args.length==2&&"boom".equals(args[0]))
        {
            List<String> tablist = new ArrayList();
            tablist.add("爆炸威力");
            return tablist;
        }
        if ((args.length == 2))
        {
            List<String> tablist = new ArrayList();
            tablist.add("true");
            tablist.add("false");
            return tablist;
        }
        List<String> tablist = new ArrayList();
        return tablist;
    }
}


