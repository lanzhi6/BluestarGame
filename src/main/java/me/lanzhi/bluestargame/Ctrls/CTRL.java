package me.lanzhi.bluestargame.Ctrls;

import java.util.Arrays;
import me.lanzhi.bluestargame.BluestarGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public class CTRL
{
    private static BukkitTask task = null;
    private static AutoCtrl autoCtrl = null;

    public static synchronized void runAuto(boolean b) {
        if (BluestarGame.config.getConfig().getBoolean("auto") != b)
        {
            BluestarGame.config.getConfig().set("auto", Boolean.valueOf(b));
            BluestarGame.config.saveConfig();
            BluestarGame.config.reloadConfig();
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

    public static void theend() {
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
    }

    public static boolean randdamage()
    {
        return BluestarGame.config.getConfig().getBoolean("randdamage");
    }

    public static void randdamage(boolean b) {
        if (BluestarGame.config.getConfig().getBoolean("randdamage") == b)
        {
            return;
        }
        BluestarGame.config.getConfig().set("randdamage", Boolean.valueOf(b));
        if (b)
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]随机伤害已启用!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]随机伤害已禁用!");
        }
        BluestarGame.config.saveConfig();
        BluestarGame.config.reloadConfig();
    }

    public static boolean randchat()
    {
        return BluestarGame.config.getConfig().getBoolean("randchat");
    }

    public static void randchat(boolean b) {
        if (BluestarGame.config.getConfig().getBoolean("randchat") == b)
        {
            return;
        }
        BluestarGame.config.getConfig().set("randchat", Boolean.valueOf(b));
        if (b)
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]随机聊天颜色已启用!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]随机聊天颜色已禁用!");
        }
        BluestarGame.config.saveConfig();
        BluestarGame.config.reloadConfig();
    }

    public static boolean randsheep()
    {
        return BluestarGame.config.getConfig().getBoolean("randsheep");
    }

    public static void randsheep(boolean b) {
        if (BluestarGame.config.getConfig().getBoolean("randsheep") == b)
        {
            return;
        }
        BluestarGame.config.getConfig().set("randsheep", Boolean.valueOf(b));
        if (b)
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]随机羊毛颜色已启用!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "对羊染色以及羊毛掉落物的颜色都会随机!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]随机羊毛颜色已禁用!");
        }
        BluestarGame.config.saveConfig();
        BluestarGame.config.reloadConfig();
    }

    public static boolean the24end = false;
    public static int[] fourNum = new int[4];

    public static boolean the24() {
        return BluestarGame.config.getConfig().getBoolean("the24");
    }

    public static void the24(boolean b) {
        if (BluestarGame.config.getConfig().getBoolean("the24") == b)
        {
            return;
        }
        BluestarGame.config.getConfig().set("the24", Boolean.valueOf(b));
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
        BluestarGame.config.saveConfig();
        BluestarGame.config.reloadConfig();
    }

    public static boolean morediamond()
    {
        return BluestarGame.config.getConfig().getBoolean("morediamond");
    }

    public static void morediamond(boolean b) {
        if (BluestarGame.config.getConfig().getBoolean("morediamond") == b)
        {
            return;
        }
        BluestarGame.config.getConfig().set("morediamond", Boolean.valueOf(b));
        if (b)
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多钻石已启用!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]挖钻石会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多钻石已禁用!");
        }
        BluestarGame.config.saveConfig();
        BluestarGame.config.reloadConfig();
    }

    public static boolean morecoal()
    {
        return BluestarGame.config.getConfig().getBoolean("morecoal");
    }

    public static void morecoal(boolean b) {
        if (BluestarGame.config.getConfig().getBoolean("morecoal") == b)
        {
            return;
        }
        BluestarGame.config.getConfig().set("morecoal", Boolean.valueOf(b));
        if (b)
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多煤炭已启用!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]挖煤炭会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多煤炭已禁用!");
        }
        BluestarGame.config.saveConfig();
        BluestarGame.config.reloadConfig();
    }

    public static boolean moreredstone()
    {
        return BluestarGame.config.getConfig().getBoolean("moreredstone");
    }

    public static void moreredstone(boolean b) {
        if (BluestarGame.config.getConfig().getBoolean("moreredstone") == b)
        {
            return;
        }
        BluestarGame.config.getConfig().set("moreredstone", Boolean.valueOf(b));
        if (b)
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多红石已启用!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]挖红石会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多红石已禁用!");
        }
        BluestarGame.config.saveConfig();
        BluestarGame.config.reloadConfig();
    }

    public static boolean morecopper()
    {
        return BluestarGame.config.getConfig().getBoolean("morecopper");
    }

    public static void morecopper(boolean b) {
        if (BluestarGame.config.getConfig().getBoolean("morecopper") == b)
        {
            return;
        }
        BluestarGame.config.getConfig().set("morecopper", Boolean.valueOf(b));
        if (b)
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多粗铜已启用!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]挖粗铜会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多粗铜已禁用!");
        }
        BluestarGame.config.saveConfig();
        BluestarGame.config.reloadConfig();
    }

    public static boolean moreiron()
    {
        return BluestarGame.config.getConfig().getBoolean("moreiron");
    }

    public static void moreiron(boolean b) {
        if (BluestarGame.config.getConfig().getBoolean("moreiron") == b)
        {
            return;
        }
        BluestarGame.config.getConfig().set("moreiron", Boolean.valueOf(b));
        if (b)
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多粗铁已启用!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]挖粗铁会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多粗铁已禁用!");
        }
        BluestarGame.config.saveConfig();
        BluestarGame.config.reloadConfig();
    }

    public static boolean moregold()
    {
        return BluestarGame.config.getConfig().getBoolean("moregold");
    }

    public static void moregold(boolean b) {
        if (BluestarGame.config.getConfig().getBoolean("moregold") == b)
        {
            return;
        }
        BluestarGame.config.getConfig().set("moregold", Boolean.valueOf(b));
        if (b)
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多粗金已启用!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]挖粗金会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多粗金已禁用!");
        }
        BluestarGame.config.saveConfig();
        BluestarGame.config.reloadConfig();
    }

    public static boolean moreemerald()
    {
        return BluestarGame.config.getConfig().getBoolean("moreemerald");
    }

    public static void moreemerald(boolean b) {
        if (BluestarGame.config.getConfig().getBoolean("moreemerald") == b)
        {
            return;
        }
        BluestarGame.config.getConfig().set("moreemerald", Boolean.valueOf(b));
        if (b)
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多绿宝石已启用!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]挖绿宝石会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多绿宝石已禁用!");
        }
        BluestarGame.config.saveConfig();
        BluestarGame.config.reloadConfig();
    }

    public static boolean morelapis()
    {
        return BluestarGame.config.getConfig().getBoolean("morelapis");
    }

    public static void morelapis(boolean b) {
        if (BluestarGame.config.getConfig().getBoolean("morelapis") == b)
        {
            return;
        }
        BluestarGame.config.getConfig().set("morelapis", Boolean.valueOf(b));
        if (b)
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多青金石已启用!");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]挖青金石会多掉一个哦!");
        }
        else
        {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[BluestarGame]更多青金石已禁用!");
        }
        BluestarGame.config.saveConfig();
        BluestarGame.config.reloadConfig();
    }
}


  