package me.lanzhi.bluestargame.Ctrls;

import me.lanzhi.bluestargame.BluestarGame;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoCtrl
{
    public int on = 0;
    public BukkitRunnable thread = new BukkitRunnable()
    {
        @Override
        public void run()
        {
            if(Bukkit.getServer().getOnlinePlayers().size()<=0)
            {
                return;
            }
            if (!BluestarGame.config.getConfig().getBoolean("auto"))
            {
                if (AutoCtrl.this.on != 0)
                {
                    AutoCtrl.this.change(false);
                    AutoCtrl.this.on = 0;
                }
                return;
            }
            if (AutoCtrl.this.on == 0)
            {
                AutoCtrl.this.on = ((int)(Math.random() * 12) + 1);
                AutoCtrl.this.change(true);
            }
            else
            {
                AutoCtrl.this.change(false);
                AutoCtrl.this.on = 0;
            }
        }
    };

    public void change(boolean b)
    {
        if (this.on == 1)
        {
            CTRL.randdamage(b);
        }
        else if (this.on == 2)
        {
            CTRL.randchat(b);
        }
        else if (this.on == 3)
        {
            CTRL.randsheep(b);
        }
        else if (this.on == 4)
        {
            CTRL.the24(b);
        }
        else if (this.on == 5)
        {
            CTRL.morediamond(b);
        }
        else if (this.on == 6)
        {
            CTRL.morecoal(b);
        }
        else if (this.on == 7)
        {
            CTRL.morecopper(b);
        }
        else if (this.on == 8)
        {
            CTRL.moregold(b);
        }
        else if (this.on == 9)
        {
            CTRL.moreemerald(b);
        }
        else if (this.on == 10)
        {
            CTRL.morelapis(b);
        }
        else if (this.on == 11)
        {
            CTRL.moreiron(b);
        }
        else if (this.on == 12)
        {
            CTRL.respawn(b);
        }
    }
}


  