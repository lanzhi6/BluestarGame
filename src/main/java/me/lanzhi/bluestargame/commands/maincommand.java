package me.lanzhi.bluestargame.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import me.lanzhi.bluestargame.BluestarGame;
import me.lanzhi.bluestargame.Ctrls.CTRL;
import me.lanzhi.bluestargame.Ctrls.CtrlSponge;
import me.lanzhi.bluestargame.Type.superSponge;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import static me.lanzhi.bluestargame.BluestarGame.*;

public class maincommand implements CommandExecutor, TabExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if ("maxhealth".equals(args[0])&&sender.hasPermission("bluestargame.lanzhi"))
        {
            if (args.length==3)
            {
                Player player=Bukkit.getPlayer(args[1]);
                if (player==null)
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"未找到玩家");
                    return false;
                }
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Double.parseDouble(args[2]));
            }
            else if (args.length==2&&sender instanceof Player)
            {
                Player player=(Player)sender;
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Double.parseDouble(args[1]));
            }
            else
            {
                sender.sendMessage(messageHead+ChatColor.RED + "格式错误!");
                return false;
            }
            sender.sendMessage("设置成功");
            return true;
        }
        if (args.length != 2)
        {
            sender.sendMessage(messageHead+ChatColor.RED + "格式错误!");
            return false;
        }
        if (args[0].equals("randdamage"))
        {
            if (args[1].equals("true"))
            {
                CTRL.randdamage(true);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.randdamage(false);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("randchat"))
        {
            if (args[1].equals("true"))
            {
                CTRL.randchat(true);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.randchat(false);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("randsheep"))
        {
            if (args[1].equals("true"))
            {
                CTRL.randsheep(true);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.randsheep(false);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("24"))
        {
            if (args[1].equals("true"))
            {
                CTRL.the24(true);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.the24(false);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("morediamond"))
        {
            if (args[1].equals("true"))
            {
                CTRL.morediamond(true);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.morediamond(false);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("morecoal"))
        {
            if (args[1].equals("true"))
            {
                CTRL.morecoal(true);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.morecoal(false);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("morecopper"))
        {
            if (args[1].equals("true"))
            {
                CTRL.morecopper(true);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.morecopper(false);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("moreiron"))
        {
            if (args[1].equals("true"))
            {
                CTRL.moreiron(true);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.moreiron(false);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("moregold"))
        {
            if (args[1].equals("true"))
            {
                CTRL.moregold(true);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.moregold(false);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("moreemerald"))
        {
            if (args[1].equals("true"))
            {
                CTRL.moreemerald(true);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.moreemerald(false);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("morelapis"))
        {
            if (args[1].equals("true"))
            {
                CTRL.morelapis(true);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.morelapis(false);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("respawn"))
        {
            if (args[1].equals("true"))
            {
                CTRL.respawn(true);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.respawn(false);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("all"))
        {
            if (args[1].equals("true"))
            {
                CTRL.all(true);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为true");
            }
            else
            {
                CTRL.all(false);
                sender.sendMessage(messageHead+ChatColor.GREEN + "已设置为false");
            }
            return true;
        }
        if (args[0].equals("auto"))
        {
            if (args[1].equals("true"))
            {
                CTRL.runAuto(true);
                sender.sendMessage(messageHead+ChatColor.GREEN + "自动切换启动");
            }
            else
            {
                CTRL.runAuto(false);
                sender.sendMessage(messageHead+ChatColor.GREEN + "自动切换关闭");
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
                sender.sendMessage(messageHead+ChatColor.RED + "错误!");
                return false;
            }
            if ((r > 32) || (r < 1))
            {
                sender.sendMessage(messageHead+ChatColor.RED + "错误,范围应在1-32之间!");
                return false;
            }
            BluestarGame.config.set("spongeR",r);
            BluestarGame.config.save();
            sender.sendMessage(messageHead+ChatColor.GREEN + "设置成功");
            return true;
        }
        if (args[0].equals("newsponge")&&sender.hasPermission("bluestargame.lanzhi"))
        {
            if (!(sender instanceof Player)){return false;}
            int r;
            r=Integer.parseInt(args[1]);
            Location locc=((Player)(sender)).getLocation();
            Location loc=new Location(locc.getWorld(),locc.getBlockX(),locc.getBlockY(),locc.getBlockZ());
            CtrlSponge.add(new superSponge(r,loc,sender.getName(),true,true));
            return true;
        }
        if ("makeboom".equals(args[0]))
        {
            Location location=((Player)sender).getLocation();
            long r;
            try
            {
                r=Integer.parseInt(args[1]);
            }
            catch (NumberFormatException e)
            {return false;}
            if (r>50||r<0)
            {
                return false;
            }
            location.getWorld().createExplosion(location,Integer.parseInt(args[1]),true,true);
            return true;
        }
        if ("boom".equals(args[0]))
        {
            long r;
            try
            {
                r=Integer.parseInt(args[1]);
            }
            catch (NumberFormatException e)
            {
                sender.sendMessage(messageHead+ChatColor.RED + "错误!");
                return false;
            }
            if (r>50||r<0)
            {
                sender.sendMessage(messageHead+ChatColor.RED + "错误,范围应在0-50之间");
            }
            TextComponent component=new TextComponent("[点击爆炸]");
            component.setColor(net.md_5.bungee.api.ChatColor.RED);
            component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("爆炸范围:"+r)));
            component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/bsgame makeboom "+r));
            TextComponent component1=new TextComponent("爆炸:");
            component1.setColor(net.md_5.bungee.api.ChatColor.GOLD);
            sender.sendMessage(ChatColor.GOLD+"--------------");
            sender.spigot().sendMessage(component1,component);
            sender.sendMessage(ChatColor.GOLD+"--------------");
            return true;
        }
        sender.sendMessage(messageHead+ChatColor.RED + "格式错误!");
        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args)
    {
        if (args.length == 1)
        {
            List<String> tablist = new ArrayList<>();
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
            tablist.add("boom");
            if(sender.hasPermission("bluestargame.lanzhi"))
            {
                tablist.add("newsponge");
                tablist.add("maxhealth");
                tablist.add("reload");
            }
            return tablist;
        }
        if(("spongeR".equals(args[0])||"newsponge".equals(args[0]))&&args.length==2)
        {
            return Collections.singletonList("超级海绵吸水范围");
        }
        if (args.length==2&&"boom".equals(args[0]))
        {
            return Collections.singletonList("爆炸威力");
        }
        if (args.length==2&&"maxhealth".equals(args[0]))
        {
            return null;
        }
        if (args.length==3&&"maxhealth".equals(args[0]))
        {
            return Collections.singletonList("血量");
        }
        if ((args.length == 2))
        {
            return Arrays.asList("true","false");
        }
        return new ArrayList<>();
    }
}


