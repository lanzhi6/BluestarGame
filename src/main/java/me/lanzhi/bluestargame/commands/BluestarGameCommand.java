package me.lanzhi.bluestargame.commands;

import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.Type.SuperSponge;
import me.lanzhi.bluestargame.managers.RandomEventManger;
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
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class BluestarGameCommand implements CommandExecutor, TabExecutor
{
    private final BluestarGamePlugin plugin;
    private final RandomEventManger randomEventManger;

    public BluestarGameCommand(@NotNull BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
        randomEventManger=plugin.getBluestarGameManager().getRandomEventManger();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,@NotNull String[] args)
    {
        if ("reload".equals(args[0])&&sender.hasPermission("bluestargame.lanzhi"))
        {
            return true;
        }
        if ("maxhealth".equals(args[0])&&sender.hasPermission("bluestargame.lanzhi"))
        {
            if (args.length==3)
            {
                Player player=Bukkit.getPlayer(args[1]);
                if (player==null)
                {
                    sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"未找到玩家");
                    return false;
                }
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Double.parseDouble(args[2]));
            }
            else if (args.length==2&&sender instanceof Player)
            {
                Player player=(Player) sender;
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Double.parseDouble(args[1]));
            }
            else
            {
                sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"格式错误!");
                return false;
            }
            sender.sendMessage("设置成功");
            return true;
        }
        if ("break".equals(args[0]))
        {
            if (!(sender instanceof Player))
            {
                sender.sendMessage(plugin.getErrorMessageHead()+"此指令仅允许玩家输入");
                return true;
            }
            if (args.length!=4)
            {
                sender.sendMessage(plugin.getErrorMessageHead()+"格式错误");
                return true;
            }
            long x, y, z;
            try
            {
                x=Long.parseLong(args[1]);
                y=Long.parseLong(args[2]);
                z=Long.parseLong(args[3]);
            }
            catch (NumberFormatException e)
            {
                sender.sendMessage(plugin.getErrorMessageHead()+"坐标无效");
                return true;
            }
            Location location=new Location(((Player) sender).getLocation().getWorld(),x,y,z);
            ((Player) sender).breakBlock(location.getBlock());
            return true;
        }
        if (args.length!=2)
        {
            sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"格式错误!");
            return false;
        }
        if (args[0].equals("onehealth"))
        {
            if (args[1].equals("true"))
            {
                randomEventManger.oneHealth(true);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为true");
            }
            else
            {
                randomEventManger.oneHealth(false);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为false");
            }
            return true;
        }
        if (args[0].equals("randdamage"))
        {
            if (args[1].equals("true"))
            {
                randomEventManger.randdamage(true);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为true");
            }
            else
            {
                randomEventManger.randdamage(false);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为false");
            }
            return true;
        }
        if (args[0].equals("randchat"))
        {
            if (args[1].equals("true"))
            {
                randomEventManger.randchat(true);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为true");
            }
            else
            {
                randomEventManger.randchat(false);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为false");
            }
            return true;
        }
        if (args[0].equals("randsheep"))
        {
            if (args[1].equals("true"))
            {
                randomEventManger.randsheep(true);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为true");
            }
            else
            {
                randomEventManger.randsheep(false);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为false");
            }
            return true;
        }
        if (args[0].equals("24"))
        {
            if (args[1].equals("true"))
            {
                randomEventManger.the24(true);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为true");
            }
            else
            {
                randomEventManger.the24(false);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为false");
            }
            return true;
        }
        if (args[0].equals("morediamond"))
        {
            if (args[1].equals("true"))
            {
                randomEventManger.morediamond(true);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为true");
            }
            else
            {
                randomEventManger.morediamond(false);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为false");
            }
            return true;
        }
        if (args[0].equals("morecoal"))
        {
            if (args[1].equals("true"))
            {
                randomEventManger.morecoal(true);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为true");
            }
            else
            {
                randomEventManger.morecoal(false);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为false");
            }
            return true;
        }
        if (args[0].equals("morecopper"))
        {
            if (args[1].equals("true"))
            {
                randomEventManger.morecopper(true);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为true");
            }
            else
            {
                randomEventManger.morecopper(false);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为false");
            }
            return true;
        }
        if (args[0].equals("moreiron"))
        {
            if (args[1].equals("true"))
            {
                randomEventManger.moreiron(true);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为true");
            }
            else
            {
                randomEventManger.moreiron(false);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为false");
            }
            return true;
        }
        if (args[0].equals("moregold"))
        {
            if (args[1].equals("true"))
            {
                randomEventManger.moregold(true);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为true");
            }
            else
            {
                randomEventManger.moregold(false);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为false");
            }
            return true;
        }
        if (args[0].equals("moreemerald"))
        {
            if (args[1].equals("true"))
            {
                randomEventManger.moreemerald(true);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为true");
            }
            else
            {
                randomEventManger.moreemerald(false);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为false");
            }
            return true;
        }
        if (args[0].equals("morelapis"))
        {
            if (args[1].equals("true"))
            {
                randomEventManger.morelapis(true);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为true");
            }
            else
            {
                randomEventManger.morelapis(false);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为false");
            }
            return true;
        }
        if (args[0].equals("respawn"))
        {
            if (args[1].equals("true"))
            {
                randomEventManger.respawn(true);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为true");
            }
            else
            {
                randomEventManger.respawn(false);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为false");
            }
            return true;
        }
        if (args[0].equals("all"))
        {
            if (args[1].equals("true"))
            {
                randomEventManger.all(true);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为true");
            }
            else
            {
                randomEventManger.all(false);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已设置为false");
            }
            return true;
        }
        if (args[0].equals("auto"))
        {
            if (args[1].equals("true"))
            {
                randomEventManger.runAuto(true);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"自动切换启动");
            }
            else
            {
                randomEventManger.runAuto(false);
                sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"自动切换关闭");
            }
            return true;
        }
        if (args[0].equals("spongeR"))
        {
            int r=0;
            try
            {
                r=Integer.valueOf(args[1]).intValue();
            }
            catch (NumberFormatException e)
            {
                sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"错误!");
                return false;
            }
            if ((r>32)||(r<1))
            {
                sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"错误,范围应在1-32之间!");
                return false;
            }
            plugin.getConfig().set("spongeR",r);
            plugin.getConfig().save();
            sender.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"设置成功");
            return true;
        }
        if (args[0].equals("newsponge")&&sender.hasPermission("bluestargame.lanzhi"))
        {
            if (!(sender instanceof Player))
            {
                return false;
            }
            int r;
            r=Integer.parseInt(args[1]);
            Location locc=((Player) (sender)).getLocation();
            Location loc=new Location(locc.getWorld(),locc.getBlockX(),locc.getBlockY(),locc.getBlockZ());
            plugin.getBluestarGameManager().getSuperSpongeManager().add(
                    new SuperSponge(r,loc,sender.getName(),true,true));
            return true;
        }
        if ("makeboom".equals(args[0]))
        {
            Location location=((Player) sender).getLocation();
            long r;
            try
            {
                r=Integer.parseInt(args[1]);
            }
            catch (NumberFormatException e)
            {
                return false;
            }
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
                sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"错误!");
                return false;
            }
            if (r>50||r<0)
            {
                sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"错误,范围应在0-50之间");
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
        sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"格式错误!");
        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender,Command command,String alias,String[] args)
    {
        if (args.length==1)
        {
            List<String> tablist=new ArrayList<>(
                    Arrays.asList("randdamage","randchat","randsheep","24","morediamond","morecoal","morecopper",
                                  "moreiron","moregold","moreemerald","morelapis","respawn","all","auto","spongeR",
                                  "boom","onehealth"
                                 ));
            if (sender.hasPermission("bluestargame.lanzhi"))
            {
                tablist.add("newsponge");
                tablist.add("maxhealth");
                tablist.add("reload");
                tablist.add("break");
            }
            return tablist;
        }
        if (("spongeR".equals(args[0])||"newsponge".equals(args[0]))&&args.length==2)
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
        if ((args.length==2))
        {
            return Arrays.asList("true","false");
        }
        return new ArrayList<>();
    }
}


