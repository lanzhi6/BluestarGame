package me.lanzhi.bluestargame.commands;

import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.bluestarapi.BluestarManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RandomTPCommand implements CommandExecutor, TabExecutor
{
    private final BluestarGamePlugin plugin;

    public RandomTPCommand(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,
                             @NotNull String[] args)
    {
        if (!(sender instanceof Player player))
        {
            sender.sendMessage(ChatColor.RED+"仅允许玩家输入!");
            return true;
        }
        String worldName=args.length>0?args[0]:"";
        var world=sender.getServer().getWorld(worldName);
        if (world==null)
        {
            world=player.getLocation().getWorld();
        }
        org.bukkit.World finalWorld=world;
        Bukkit.getScheduler().runTaskAsynchronously(plugin,()->
        {
            sender.sendMessage(plugin.getMessageHead()+"随机传送: 正在搜索目的地...");
            for (int cnt=0;cnt<10;cnt++)
            {
                var x=BluestarManager.randomInt(100000)-50000;
                var z=BluestarManager.randomInt(100000)-50000;
                sender.sendMessage(plugin.getMessageHead()+"随机传送: 尝试坐标: "+x+" "+z);
                //如果目的地没有被生成
                if (!finalWorld.isChunkGenerated(x>>4,z>>4))
                {
                    sender.sendMessage(plugin.getMessageHead()+"随机传送: 目的地未被生成,正在生成...请稍等...");
                    try
                    {
                        finalWorld.getChunkAtAsync(x>>4,z>>4).get();
                    }
                    catch (Throwable e)
                    {
                        sender.sendMessage(plugin.getMessageHead()+"随机传送: 生成失败!");
                        continue;
                    }
                }
                //获取世界维度
                var dimension=finalWorld.getEnvironment();
                Location res=null;
                if (dimension==World.Environment.NETHER)
                {
                    for (var y=128;y>0;y--)
                    {
                        var block0=finalWorld.getBlockAt(x,y,z);
                        var block1=finalWorld.getBlockAt(x,y+1,z);
                        var block2=finalWorld.getBlockAt(x,y+2,z);
                        if (block0.getType().isSolid()&&!block1.getType().isSolid()&&!block2.getType().isSolid())
                        {
                            sender.sendMessage(plugin.getMessageHead()+"随机传送: 找到安全位置: "+x+" "+y+" "+z);
                            res=new Location(finalWorld,x,y,z);
                        }
                    }
                }
                else
                {
                    res=finalWorld.getHighestBlockAt(x,z).getLocation();
                    if (!res.getBlock().getType().isSolid())
                    {
                        res=null;
                    }
                }
                if (res==null)
                {
                    sender.sendMessage(plugin.getMessageHead()+"随机传送: 未找到安全位置");
                    continue;
                }
                Location finalRes=res;
                Bukkit.getScheduler().runTask(plugin,()->
                {
                    player.teleport(finalRes.add(0.5,1,0.5));
                    sender.sendMessage(plugin.getMessageHead()+"随机传送: 传送成功!");
                });
                return;
            }
            sender.sendMessage(plugin.getMessageHead()+"随机传送: 未找到安全位置,请稍后再试!");
        });
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender,@NotNull Command command,
                                                @NotNull String label,@NotNull String[] args)
    {
        return sender.getServer().getWorlds().stream().map(WorldInfo::getName).toList();
    }
}
