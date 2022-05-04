package me.lanzhi.bluestargame.managers;

import me.lanzhi.bluestargame.BluestarGamePlugin;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public final class RandomEventsChange extends BukkitRunnable
{
    private int on;
    private final BluestarGamePlugin plugin;
    private final RandomEventManger randomEventManger;
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
            on=((int) (Math.random()*12)+1);
            if (Bukkit.getServer().getOnlinePlayers().size()<=0&&on!=4)
            {
                randomEventManger.randomEvents=new RandomEventsChange(plugin,on);
                randomEventManger.task=randomEventManger.randomEvents.runTaskLaterAsynchronously(plugin,12000);
                return;
            }
            this.change(true);
            randomEventManger.randomEvents=new RandomEventsChange(plugin,on);
            randomEventManger.task=randomEventManger.randomEvents.runTaskLaterAsynchronously(plugin,12000);
        }
        else
        {
            this.change(false);
            on=0;
            randomEventManger.randomEvents=new RandomEventsChange(plugin,on);
            randomEventManger.task=randomEventManger.randomEvents.runTaskLaterAsynchronously(plugin,24000);
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
        if (on==1)
        {
            randomEventManger.randdamage(b);
        }
        else if (on==2)
        {
            randomEventManger.randchat(b);
        }
        else if (on==3)
        {
            randomEventManger.randsheep(b);
        }
        else if (on==4)
        {
            randomEventManger.the24(b);
        }
        else if (on==5)
        {
            randomEventManger.morediamond(b);
        }
        else if (on==6)
        {
            randomEventManger.morecoal(b);
        }
        else if (on==7)
        {
            randomEventManger.morecopper(b);
        }
        else if (on==8)
        {
            randomEventManger.moregold(b);
        }
        else if (on==9)
        {
            randomEventManger.moreemerald(b);
        }
        else if (on==10)
        {
            randomEventManger.morelapis(b);
        }
        else if (on==11)
        {
            randomEventManger.moreiron(b);
        }
        else if (on==12)
        {
            randomEventManger.respawn(b);
        }
    }

}


  