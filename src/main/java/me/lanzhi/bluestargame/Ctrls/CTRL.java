package me.lanzhi.bluestargame.Ctrls;

import me.lanzhi.bluestargame.BluestarGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitTask;

import java.util.Arrays;

import static me.lanzhi.bluestargame.BluestarGame.messageHead;

public class CTRL
{
    public static BukkitTask task = null;

    public static synchronized void runAuto(boolean b)
    {
        if (BluestarGame.config.getBoolean("auto") != b)
        {
            BluestarGame.config.set("auto", b);
            if (!b)
            {
                Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"随机事件自动切换已关闭!");
            }
            else
            {
                Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"随机事件自动切换已启用!");
            }
        }
        if ((b) && (task == null))
        {
            task = (new AutoCtrl()).runTaskAsynchronously(BluestarGame.plugin);
        }
        else if ((!b) && (task != null))
        {
            task.cancel();
            if (AutoCtrl.on != 0)
            {
                AutoCtrl.change(false);
            }
            task = null;
        }
    }

    public static void theend()
    {
        if (task != null)
        {
            task.cancel();
        }
    }

    public static void all(boolean b)
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
    }

    public static boolean randdamage()
    {
        return BluestarGame.Data.getBoolean("randdamage");
    }

    public static void randdamage(boolean b)
    {
        if (BluestarGame.Data.getBoolean("randdamage") == b)
        {
            return;
        }
        BluestarGame.Data.set("randdamage", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"随机伤害已启用!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"随机伤害已禁用!");
        }
    }

    public static boolean randchat()
    {
        return BluestarGame.Data.getBoolean("randchat");
    }

    public static void randchat(boolean b)
    {
        if (BluestarGame.Data.getBoolean("randchat") == b)
        {
            return;
        }
        BluestarGame.Data.set("randchat", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"随机聊天颜色已启用!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"随机聊天颜色已禁用!");
        }
    }

    public static boolean randsheep()
    {
        return BluestarGame.Data.getBoolean("randsheep");
    }

    public static void randsheep(boolean b)
    {
        if (BluestarGame.Data.getBoolean("randsheep") == b)
        {
            return;
        }
        BluestarGame.Data.set("randsheep", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"随机羊毛颜色已启用!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "对羊染色以及羊毛掉落物的颜色都会随机!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"随机羊毛颜色已禁用!");
        }
    }

    public static int[] fourNum = new int[4];

    public static boolean the24()
    {
        return BluestarGame.Data.getBoolean("the24");
    }

    public static void the24(boolean b)
    {
        if (BluestarGame.Data.getBoolean("the24") == b)
        {
            return;
        }
        BluestarGame.Data.set("the24", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"24点开始!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "4个数字为:");
            StringBuilder nums=new StringBuilder();
            nums.append(ChatColor.GREEN);
            for (int i = 0; i <= 3; i++)
            {
                fourNum[i] = ((int)(Math.random() * 12.0D) + 1);
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
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"24点已结束!");
        }
    }

    public static boolean morediamond()
    {
        return BluestarGame.Data.getBoolean("morediamond");
    }

    public static void morediamond(boolean b)
    {
        if (BluestarGame.Data.getBoolean("morediamond") == b)
        {
            return;
        }
        BluestarGame.Data.set("morediamond", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"更多钻石已启用!");
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"挖钻石会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"更多钻石已禁用!");
        }
    }

    public static boolean morecoal()
    {
        return BluestarGame.Data.getBoolean("morecoal");
    }

    public static void morecoal(boolean b)
    {
        if (BluestarGame.Data.getBoolean("morecoal") == b)
        {
            return;
        }
        BluestarGame.Data.set("morecoal", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"更多煤炭已启用!");
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"挖煤炭会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"更多煤炭已禁用!");
        }
    }

    public static boolean moreredstone()
    {
        return BluestarGame.Data.getBoolean("moreredstone");
    }

    public static void moreredstone(boolean b)
    {
        if (BluestarGame.Data.getBoolean("moreredstone") == b)
        {
            return;
        }
        BluestarGame.Data.set("moreredstone", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"更多红石已启用!");
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"挖红石会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"更多红石已禁用!");
        }
    }

    public static boolean morecopper()
    {
        return BluestarGame.Data.getBoolean("morecopper");
    }

    public static void morecopper(boolean b)
    {
        if (BluestarGame.Data.getBoolean("morecopper") == b)
        {
            return;
        }
        BluestarGame.Data.set("morecopper", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"更多粗铜已启用!");
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"挖粗铜会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"更多粗铜已禁用!");
        }
    }

    public static boolean moreiron()
    {
        return BluestarGame.Data.getBoolean("moreiron");
    }

    public static void moreiron(boolean b)
    {
        if (BluestarGame.Data.getBoolean("moreiron") == b)
        {
            return;
        }
        BluestarGame.Data.set("moreiron", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"更多粗铁已启用!");
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"挖粗铁会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"更多粗铁已禁用!");
        }
    }

    public static boolean moregold()
    {
        return BluestarGame.Data.getBoolean("moregold");
    }

    public static void moregold(boolean b)
    {
        if (BluestarGame.Data.getBoolean("moregold") == b)
        {
            return;
        }
        BluestarGame.Data.set("moregold", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"更多粗金已启用!");
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"挖粗金会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"更多粗金已禁用!");
        }
    }

    public static boolean moreemerald()
    {
        return BluestarGame.Data.getBoolean("moreemerald");
    }

    public static void moreemerald(boolean b)
    {
        if (BluestarGame.Data.getBoolean("moreemerald") == b)
        {
            return;
        }
        BluestarGame.Data.set("moreemerald", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"更多绿宝石已启用!");
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"挖绿宝石会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"更多绿宝石已禁用!");
        }
    }

    public static boolean morelapis()
    {
        return BluestarGame.Data.getBoolean("morelapis");
    }

    public static void morelapis(boolean b)
    {
        if (BluestarGame.Data.getBoolean("morelapis") == b)
        {
            return;
        }
        BluestarGame.Data.set("morelapis", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"更多青金石已启用!");
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"挖青金石会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"更多青金石已禁用!");
        }
    }

    public static boolean respawn()
    {
        return BluestarGame.Data.getBoolean("respawn");
    }
    public static void respawn(boolean b)
    {
        if (BluestarGame.Data.getBoolean("respawn") == b)
        {
            return;
        }
        BluestarGame.Data.set("respawn", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"复活已启用!");
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"生物死亡会直接复活哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(messageHead+ChatColor.GOLD+"复活已禁用!");
        }
    }
}


  