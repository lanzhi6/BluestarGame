package me.lanzhi.bluestargame.managers;

import me.lanzhi.bluestarapi.api.Bluestar;
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
            on=Bluestar.getMainManager().randomInt(13)+1;
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
            case 1 -> randomEventManger.randdamage(b);
            case 2 -> randomEventManger.randchat(b);
            case 3 -> randomEventManger.randsheep(b);
            case 4 -> randomEventManger.the24(b);
            case 5 -> randomEventManger.morediamond(b);
            case 6 -> randomEventManger.morecoal(b);
            case 7 -> randomEventManger.morecopper(b);
            case 8 -> randomEventManger.moregold(b);
            case 9 -> randomEventManger.moreemerald(b);
            case 10 -> randomEventManger.morelapis(b);
            case 11 -> randomEventManger.moreiron(b);
            case 12 -> randomEventManger.moreredstone(b);
            case 13 -> randomEventManger.oneHealth(b);
            default -> {}
        }
    }

}


  