package me.lanzhi.bluestargame.managers;

import me.lanzhi.bluestargame.BluestarGamePlugin;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public final class RandomEventsChange extends BukkitRunnable
{
    private final BluestarGamePlugin plugin;
    private final RandomEventManger randomEventManger;
    private int on;

    public RandomEventsChange(BluestarGamePlugin plugin,int on)
    {
        this.plugin=plugin;
        this.on=on;
        this.randomEventManger=plugin.getBluestarGameManager().getRandomEventManger();
    }

    @Override
    public void run()
    {
        if (!plugin.getConfig().getBoolean("auto"))
        {
            if (on!=0)
            {
                this.change(false);
                on=0;
            }
            return;
        }
        if (on==0)
        {
            on=((int) (Math.random()*13)+1);
            if (Bukkit.getServer().getOnlinePlayers().size()<=0&&on!=4)
            {
                randomEventManger.randomEvents=new RandomEventsChange(plugin,on);
                randomEventManger.randomEventChangeTask=randomEventManger.randomEvents.runTaskLaterAsynchronously(plugin,12000);
                return;
            }
            this.change(true);
            randomEventManger.randomEvents=new RandomEventsChange(plugin,on);
            randomEventManger.randomEventChangeTask=randomEventManger.randomEvents.runTaskLaterAsynchronously(plugin,12000);
        }
        else
        {
            this.change(false);
            on=0;
            randomEventManger.randomEvents=new RandomEventsChange(plugin,on);
            randomEventManger.randomEventChangeTask=randomEventManger.randomEvents.runTaskLaterAsynchronously(plugin,24000);
        }
    }

    public void stop()
    {
        if (on!=0)
        {
            change(false);
        }
    }

    public synchronized void change(boolean b)
    {
        switch (on)
        {
            case 1:
            {
                randomEventManger.randdamage(b);
                return;
            }
            case 2:
            {
                randomEventManger.randchat(b);
                return;
            }
            case 3:
            {
                randomEventManger.randsheep(b);
                return;
            }
            case 4:
            {
                randomEventManger.the24(b);
                return;
            }
            case 5:
            {
                randomEventManger.morediamond(b);
                return;
            }
            case 6:
            {
                randomEventManger.morecoal(b);
                return;
            }
            case 7:
            {
                randomEventManger.morecopper(b);
                return;
            }
            case 8:
            {
                randomEventManger.moregold(b);
                return;
            }
            case 9:
            {
                randomEventManger.moreemerald(b);
                return;
            }
            case 10:
            {
                randomEventManger.morelapis(b);
                return;
            }
            case 11:
            {
                randomEventManger.moreiron(b);
                return;
            }
            case 12:
            {
                randomEventManger.respawn(b);
                return;
            }
            case 13:
            {
                randomEventManger.oneHealth(b);
                return;
            }
            default:{

            }
        }
    }

}


  