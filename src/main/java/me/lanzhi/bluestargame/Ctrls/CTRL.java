package me.lanzhi.bluestargame.Ctrls;

import java.util.Arrays;
import me.lanzhi.bluestargame.BluestarGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitTask;

public class CTRL
{
    private static BukkitTask task = null;
    private static AutoCtrl autoCtrl = null;

    public static synchronized void runAuto(boolean b)
    {
        if (BluestarGame.config.getBoolean("auto") != b)
        {
            BluestarGame.config.set("auto", b);
            if (!b)
            {
                Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]随机事件自动切换已关闭!");
            }
            else
            {
                Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]随机事件自动切换已启用!");
            }
        }
        if ((b) && (task == null))
        {
            autoCtrl = new AutoCtrl();
            task = autoCtrl.thread.runTaskTimer(BluestarGame.getPlugin(BluestarGame.class), 0L, 12000L);
        }
        else if ((!b) && (task != null))
        {
            task.cancel();
            if (autoCtrl.on != 0)
            {
                autoCtrl.change(false);
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
        return BluestarGame.config.getBoolean("randdamage");
    }

    public static void randdamage(boolean b)
    {
        if (BluestarGame.config.getBoolean("randdamage") == b)
        {
            return;
        }
        BluestarGame.config.set("randdamage", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]随机伤害已启用!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]随机伤害已禁用!");
        }
    }

    public static boolean randchat()
    {
        return BluestarGame.config.getBoolean("randchat");
    }

    public static void randchat(boolean b)
    {
        if (BluestarGame.config.getBoolean("randchat") == b)
        {
            return;
        }
        BluestarGame.config.set("randchat", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]随机聊天颜色已启用!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]随机聊天颜色已禁用!");
        }
    }

    public static boolean randsheep()
    {
        return BluestarGame.config.getBoolean("randsheep");
    }

    public static void randsheep(boolean b)
    {
        if (BluestarGame.config.getBoolean("randsheep") == b)
        {
            return;
        }
        BluestarGame.config.set("randsheep", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]随机羊毛颜色已启用!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "对羊染色以及羊毛掉落物的颜色都会随机!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]随机羊毛颜色已禁用!");
        }
    }

    public static boolean the24end = false;
    public static int[] fourNum = new int[4];

    public static boolean the24()
    {
        return BluestarGame.config.getBoolean("the24");
    }

    public static void the24(boolean b)
    {
        if (BluestarGame.config.getBoolean("the24") == b)
        {
            return;
        }
        BluestarGame.config.set("the24", b);
        if (b)
        {
            the24end = false;
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]24点开始!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "4个数字为:");
            for (int i = 0; i <= 3; i++)
            {
                fourNum[i] = ((int)(Math.random() * 12.0D) + 1);
                Bukkit.getServer().broadcastMessage(ChatColor.GREEN.toString() + fourNum[i] + "");
            }
            Arrays.sort(fourNum);
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]24点已结束!");
        }
    }

    public static boolean morediamond()
    {
        return BluestarGame.config.getBoolean("morediamond");
    }

    public static void morediamond(boolean b)
    {
        if (BluestarGame.config.getBoolean("morediamond") == b)
        {
            return;
        }
        BluestarGame.config.set("morediamond", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多钻石已启用!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]挖钻石会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多钻石已禁用!");
        }
    }

    public static boolean morecoal()
    {
        return BluestarGame.config.getBoolean("morecoal");
    }

    public static void morecoal(boolean b)
    {
        if (BluestarGame.config.getBoolean("morecoal") == b)
        {
            return;
        }
        BluestarGame.config.set("morecoal", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多煤炭已启用!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]挖煤炭会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多煤炭已禁用!");
        }
    }

    public static boolean moreredstone()
    {
        return BluestarGame.config.getBoolean("moreredstone");
    }

    public static void moreredstone(boolean b)
    {
        if (BluestarGame.config.getBoolean("moreredstone") == b)
        {
            return;
        }
        BluestarGame.config.set("moreredstone", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多红石已启用!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]挖红石会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多红石已禁用!");
        }
    }

    public static boolean morecopper()
    {
        return BluestarGame.config.getBoolean("morecopper");
    }

    public static void morecopper(boolean b)
    {
        if (BluestarGame.config.getBoolean("morecopper") == b)
        {
            return;
        }
        BluestarGame.config.set("morecopper", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多粗铜已启用!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]挖粗铜会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多粗铜已禁用!");
        }
    }

    public static boolean moreiron()
    {
        return BluestarGame.config.getBoolean("moreiron");
    }

    public static void moreiron(boolean b)
    {
        if (BluestarGame.config.getBoolean("moreiron") == b)
        {
            return;
        }
        BluestarGame.config.set("moreiron", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多粗铁已启用!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]挖粗铁会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多粗铁已禁用!");
        }
    }

    public static boolean moregold()
    {
        return BluestarGame.config.getBoolean("moregold");
    }

    public static void moregold(boolean b)
    {
        if (BluestarGame.config.getBoolean("moregold") == b)
        {
            return;
        }
        BluestarGame.config.set("moregold", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多粗金已启用!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]挖粗金会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多粗金已禁用!");
        }
    }

    public static boolean moreemerald()
    {
        return BluestarGame.config.getBoolean("moreemerald");
    }

    public static void moreemerald(boolean b)
    {
        if (BluestarGame.config.getBoolean("moreemerald") == b)
        {
            return;
        }
        BluestarGame.config.set("moreemerald", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多绿宝石已启用!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]挖绿宝石会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多绿宝石已禁用!");
        }
    }

    public static boolean morelapis()
    {
        return BluestarGame.config.getBoolean("morelapis");
    }

    public static void morelapis(boolean b)
    {
        if (BluestarGame.config.getBoolean("morelapis") == b)
        {
            return;
        }
        BluestarGame.config.set("morelapis", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多青金石已启用!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]挖青金石会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多青金石已禁用!");
        }
    }

    public static boolean respawn()
    {
        return BluestarGame.config.getBoolean("respawn");
    }
    public static void respawn(boolean b)
    {
        if (BluestarGame.config.getBoolean("respawn") == b)
        {
            return;
        }
        BluestarGame.config.set("respawn", b);
        if (b)
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]复活已启用!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]生物死亡会直接复活哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]复活已禁用!");
        }
    }
}


  