package me.lanzhi.bluestargame.managers;

import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargameapi.managers.RandomEventMangerInterface;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class RandomEventManger implements RandomEventMangerInterface
{
    private final BluestarGamePlugin plugin;
    public int[] fourNum=new int[4];
    public BukkitTask randomEventChangeTask=null;
    public BukkitTask oneHealthTask=null;
    public HashMap<Player, Double> oneHealth_playerHealth=new HashMap<>();
    public RandomEventsChange randomEvents;

    public RandomEventManger(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
    }

    @Override
    public synchronized void runAuto(boolean b)
    {
        if (plugin.getConfig().getBoolean("auto")!=b)
        {
            plugin.getConfig().set("auto",b);
            if (!b)
            {
                Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"随机事件自动切换已关闭!");
            }
            else
            {
                Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"随机事件自动切换已启用!");
            }
        }
        if ((b)&&(randomEventChangeTask==null))
        {
            randomEvents=new RandomEventsChange(plugin,0);
            randomEventChangeTask=randomEvents.runTaskAsynchronously(plugin);
        }
        else if ((!b)&&(randomEventChangeTask!=null))
        {
            randomEventChangeTask.cancel();
            randomEvents.stop();
            randomEventChangeTask=null;
        }
    }

    @Override
    public void end()
    {
        if (randomEventChangeTask!=null)
        {
            randomEventChangeTask.cancel();
        }
    }

    @Override
    public void all(boolean b)
    {
        randchat(b);
        randdamage(b);
        randsheep(b);
        the24(b);
        morecoal(b);
        moreiron(b);
        morecopper(b);
        moregold(b);
        morelapis(b);
        morediamond(b);
        moreemerald(b);
        moreredstone(b);
        respawn(b);
        oneHealth(b);
    }

    @Override
    public boolean randdamage()
    {
        return plugin.getData().getBoolean("randdamage");
    }

    @Override
    public void randdamage(boolean b)
    {
        if (plugin.getData().getBoolean("randdamage")==b)
        {
            return;
        }
        plugin.getData().set("randdamage",b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"随机伤害已启用!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"随机伤害已禁用!");
        }
    }

    @Override
    public boolean randchat()
    {
        return plugin.getData().getBoolean("randchat");
    }

    @Override
    public void randchat(boolean b)
    {
        if (plugin.getData().getBoolean("randchat")==b)
        {
            return;
        }
        plugin.getData().set("randchat",b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"随机聊天颜色已启用!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"随机聊天颜色已禁用!");
        }
    }

    @Override
    public boolean randsheep()
    {
        return plugin.getData().getBoolean("randsheep");
    }

    @Override
    public void randsheep(boolean b)
    {
        if (plugin.getData().getBoolean("randsheep")==b)
        {
            return;
        }
        plugin.getData().set("randsheep",b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"随机羊毛颜色已启用!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD+"对羊染色以及羊毛掉落物的颜色都会随机!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"随机羊毛颜色已禁用!");
        }
    }

    @Override
    public boolean the24()
    {
        return plugin.getData().getBoolean("the24");
    }

    @Override
    public void the24(boolean b)
    {
        if (plugin.getData().getBoolean("the24")==b)
        {
            return;
        }
        plugin.getData().set("the24",b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"24点开始!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD+"4个数字为:");
            StringBuilder nums=new StringBuilder();
            nums.append(ChatColor.GREEN);
            for (int i=0;i<=3;i++)
            {
                fourNum[i]=((int) (Math.random()*12.0D)+1);
                if (i==3)
                {
                    nums.append(fourNum[i]+"");
                }
                else
                {
                    nums.append(fourNum[i]+",");
                }
            }
            Bukkit.getServer().broadcastMessage(nums.toString());
            Arrays.sort(fourNum);
        }
        else
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"24点已结束!");
        }
    }

    @Override
    public boolean morediamond()
    {
        return plugin.getData().getBoolean("morediamond");
    }

    @Override
    public void morediamond(boolean b)
    {
        if (plugin.getData().getBoolean("morediamond")==b)
        {
            return;
        }
        plugin.getData().set("morediamond",b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"更多钻石已启用!");
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"挖钻石会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"更多钻石已禁用!");
        }
    }

    @Override
    public boolean morecoal()
    {
        return plugin.getData().getBoolean("morecoal");
    }

    @Override
    public void morecoal(boolean b)
    {
        if (plugin.getData().getBoolean("morecoal")==b)
        {
            return;
        }
        plugin.getData().set("morecoal",b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"更多煤炭已启用!");
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"挖煤炭会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"更多煤炭已禁用!");
        }
    }

    @Override
    public boolean moreredstone()
    {
        return plugin.getData().getBoolean("moreredstone");
    }

    @Override
    public void moreredstone(boolean b)
    {
        if (plugin.getData().getBoolean("moreredstone")==b)
        {
            return;
        }
        plugin.getData().set("moreredstone",b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"更多红石已启用!");
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"挖红石会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"更多红石已禁用!");
        }
    }

    @Override
    public boolean morecopper()
    {
        return plugin.getData().getBoolean("morecopper");
    }

    @Override
    public void morecopper(boolean b)
    {
        if (plugin.getData().getBoolean("morecopper")==b)
        {
            return;
        }
        plugin.getData().set("morecopper",b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"更多粗铜已启用!");
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"挖粗铜会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"更多粗铜已禁用!");
        }
    }

    @Override
    public boolean moreiron()
    {
        return plugin.getData().getBoolean("moreiron");
    }

    @Override
    public void moreiron(boolean b)
    {
        if (plugin.getData().getBoolean("moreiron")==b)
        {
            return;
        }
        plugin.getData().set("moreiron",b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"更多粗铁已启用!");
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"挖粗铁会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"更多粗铁已禁用!");
        }
    }

    @Override
    public boolean moregold()
    {
        return plugin.getData().getBoolean("moregold");
    }

    @Override
    public void moregold(boolean b)
    {
        if (plugin.getData().getBoolean("moregold")==b)
        {
            return;
        }
        plugin.getData().set("moregold",b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"更多粗金已启用!");
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"挖粗金会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"更多粗金已禁用!");
        }
    }

    @Override
    public boolean moreemerald()
    {
        return plugin.getData().getBoolean("moreemerald");
    }

    @Override
    public void moreemerald(boolean b)
    {
        if (plugin.getData().getBoolean("moreemerald")==b)
        {
            return;
        }
        plugin.getData().set("moreemerald",b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"更多绿宝石已启用!");
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"挖绿宝石会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"更多绿宝石已禁用!");
        }
    }

    @Override
    public boolean morelapis()
    {
        return plugin.getData().getBoolean("morelapis");
    }

    @Override
    public void morelapis(boolean b)
    {
        if (plugin.getData().getBoolean("morelapis")==b)
        {
            return;
        }
        plugin.getData().set("morelapis",b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"更多青金石已启用!");
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"挖青金石会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"更多青金石已禁用!");
        }
    }

    @Override
    public boolean respawn()
    {
        return plugin.getData().getBoolean("respawn");
    }

    @Override
    public void respawn(boolean b)
    {
        if (plugin.getData().getBoolean("respawn")==b)
        {
            return;
        }
        plugin.getData().set("respawn",b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"复活已启用!");
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"生物死亡会直接复活哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(plugin.getMessageHead()+"复活已禁用!");
        }
    }

    @Override
    public boolean oneHealth()
    {
        return plugin.getData().getBoolean("oneHealth");
    }

    @Override
    public void oneHealth(boolean b)
    {
        if (b==oneHealth())
        {
            return;
        }
        plugin.getData().set("oneHealth",b);
        if (b)
        {
            for (Player player: Bukkit.getOnlinePlayers())
            {
                oneHealth_playerHealth.put(player,player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1);
                player.setHealth(Math.min(player.getHealth(),1D));
                oneHealthTask=new BukkitRunnable()
                {
                    @Override
                    public void run()
                    {
                        for (Player player: oneHealth_playerHealth.keySet())
                        {
                            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1);
                        }
                    }
                }.runTaskTimer(plugin,0,1);
            }
            Bukkit.broadcastMessage(plugin.getMessageHead()+"一滴血生存已启用!");
        }
        else
        {
            oneHealthTask.cancel();
            for (Map.Entry<Player, Double> i: oneHealth_playerHealth.entrySet())
            {
                i.getKey().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(i.getValue());
            }
            oneHealth_playerHealth=new HashMap<>();
            Bukkit.broadcastMessage(plugin.getMessageHead()+"一滴血生存已禁用!");
        }
    }

    public static class mathFor24Points
    {

        public static String[] yunsuan(String str)
        {
            int cntnum=0;
            String[] ans=new String[5];
            String s="";
            char[] a=new char[100];
            String[] jieguo=new String[100];
            int top=-1;
            int j=0;
            for (int i=0;i<str.length();i++)
            {
                if ("0123456789.".indexOf(str.charAt(i))>=0)
                {
                    s="";
                    for (;(i<str.length())&&("0123456789.".indexOf(str.charAt(i))>=0);i++)
                    {
                        s=s+str.charAt(i);
                    }
                    i--;
                    jieguo[j]=s;
                    j++;
                    cntnum++;
                    if (cntnum>4)
                    {
                        return null;
                    }
                    ans[cntnum]=s;
                }
                else if ("(".indexOf(str.charAt(i))>=0)
                {
                    top++;
                    a[top]=str.charAt(i);
                }
                else if (")".indexOf(str.charAt(i))>=0)
                {


                    while (a[top]!='(')
                    {
                        jieguo[j]=(a[top]+"");
                        j++;
                        top--;
                    }


                    top--;


                }
                else if ("*/×÷".indexOf(str.charAt(i))>=0)
                {
                    if (top==-1)
                    {
                        top++;
                        a[top]=str.charAt(i);


                    }
                    else if ("*/×÷".indexOf(a[top])>=0)
                    {
                        jieguo[j]=(a[top]+"");
                        j++;
                        a[top]=str.charAt(i);
                    }
                    else if ("(".indexOf(a[top])>=0)
                    {
                        top++;
                        a[top]=str.charAt(i);
                    }
                    else if ("+-".indexOf(a[top])>=0)
                    {
                        top++;
                        a[top]=str.charAt(i);
                    }

                }
                else if ("+-".indexOf(str.charAt(i))>=0)
                {
                    if (top==-1)
                    {
                        top++;
                        a[top]=str.charAt(i);


                    }
                    else if ("*/×÷".indexOf(a[top])>=0)
                    {
                        jieguo[j]=(a[top]+"");
                        j++;
                        a[top]=str.charAt(i);
                    }
                    else if ("(".indexOf(a[top])>=0)
                    {
                        top++;
                        a[top]=str.charAt(i);
                    }
                    else if ("+-".indexOf(a[top])>=0)
                    {
                        jieguo[j]=(a[top]+"");
                        j++;
                        a[top]=str.charAt(i);
                    }

                }
                else
                {
                    return null;
                }
            }
            while (top!=-1)
            {
                jieguo[j]=(a[top]+"");
                j++;
                top--;
            }
            ans[0]=Result(jieguo);
            return ans;
        }

        public static String Result(String[] str)
        {
            if (str==null)
            {
                return "-1";
            }
            String[] Result=new String[100];
            int Top=-1;
            for (int i=0;str[i]!=null;i++)
            {
                if ("+-*/×÷".indexOf(str[i])<0)
                {
                    Top++;
                    Result[Top]=str[i];
                }
                if ("+-*/×÷".indexOf(str[i])>=0)
                {

                    double x=Double.parseDouble(Result[Top]);
                    Top--;
                    double y=Double.parseDouble(Result[Top]);
                    Top--;
                    if ("-".indexOf(str[i])>=0)
                    {
                        double n=y-x;
                        Top++;
                        Result[Top]=String.valueOf(n);
                    }
                    if ("+".indexOf(str[i])>=0)
                    {
                        double n=y+x;
                        Top++;
                        Result[Top]=String.valueOf(n);
                    }
                    if ("*×".indexOf(str[i])>=0)
                    {
                        double n=y*x;
                        Top++;
                        Result[Top]=String.valueOf(n);
                    }
                    if ("/÷".indexOf(str[i])>=0)
                    {
                        if (x==0.0D)
                        {
                            String s="ERROR";
                            return s;
                        }


                        double n=y/x;
                        Top++;
                        Result[Top]=String.valueOf(n);
                    }
                }
            }
            return Result[Top];
        }
    }
}


  