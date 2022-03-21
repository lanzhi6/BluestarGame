package me.lanzhi.bluestargame.Ctrls;

import me.lanzhi.bluestargame.BluestarGame;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoCtrl extends BukkitRunnable
{
    public static int on = 0;
    @Override
    public void run()
    {
        if (!BluestarGame.config.getBoolean("auto"))
        {
            if (on != 0)
            {
                AutoCtrl.change(false);
                on = 0;
            }
            return;
        }
        if (on == 0)
        {
            on = ((int)(Math.random() * 12) + 1);
            if(Bukkit.getServer().getOnlinePlayers().size()<=0&&on!=4)
            {
                CTRL.task=(new AutoCtrl()).runTaskLaterAsynchronously(BluestarGame.plugin,12000);
                return;
            }
            AutoCtrl.change(true);
            CTRL.task=(new AutoCtrl()).runTaskLaterAsynchronously(BluestarGame.plugin,12000);
        }
        else
        {
            AutoCtrl.change(false);
            on=0;
            CTRL.task=(new AutoCtrl()).runTaskLaterAsynchronously(BluestarGame.plugin,24000);
        }
    }
    public static synchronized void change(boolean b)
    {
        if (on == 1)
        {
            CTRL.randdamage(b);
        }
        else if (on == 2)
        {
            CTRL.randchat(b);
        }
        else if (on == 3)
        {
            CTRL.randsheep(b);
        }
        else if (on == 4)
        {
            CTRL.the24(b);
        }
        else if (on == 5)
        {
            CTRL.morediamond(b);
        }
        else if (on == 6)
        {
            CTRL.morecoal(b);
        }
        else if (on == 7)
        {
            CTRL.morecopper(b);
        }
        else if (on == 8)
        {
            CTRL.moregold(b);
        }
        else if (on == 9)
        {
            CTRL.moreemerald(b);
        }
        else if (on == 10)
        {
            CTRL.morelapis(b);
        }
        else if (on == 11)
        {
            CTRL.moreiron(b);
        }
        else if (on == 12)
        {
            CTRL.respawn(b);
        }
    }

}


  