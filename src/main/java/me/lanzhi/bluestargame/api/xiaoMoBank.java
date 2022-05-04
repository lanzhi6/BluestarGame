package me.lanzhi.bluestargame.api;

import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargameapi.api.XiaoMoBankInterface;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class xiaoMoBank implements XiaoMoBankInterface
{
    private final BluestarGamePlugin plugin;

    public xiaoMoBank(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
    }

    @Override
    public long getBorrow(@NotNull OfflinePlayer player)
    {
        return getBorrow(player.getUniqueId());
    }

    @Override
    public long getBorrow(@NotNull UUID uuid)
    {
        return plugin.getData().getLong("bank.borrow."+uuid+".money");
    }

    @Override
    public long getSave(@NotNull OfflinePlayer player)
    {
        return getSave(player.getUniqueId());
    }

    @Override
    public long getSave(@NotNull UUID uuid)
    {
        return plugin.getData().getLong("bank.save."+uuid+".money");
    }

    @Override
    public Date getBorrowTime(@NotNull OfflinePlayer player)
    {
        return getBorrowTime(player.getUniqueId());
    }

    @Override
    public Date getSaveTime(@NotNull OfflinePlayer player)
    {
        return getSaveTime(player.getUniqueId());
    }

    @Override
    @Nullable
    public Date getBorrowTime(@NotNull UUID uuid)
    {
        try
        {
            return plugin.getDateFormat().parse(plugin.getData().getString("bank.borrow."+uuid+".time"));
        }
        catch (Throwable e)
        {
            return null;
        }
    }

    @Override
    @Nullable
    public Date getSaveTime(@NotNull UUID uuid)
    {
        try
        {
            return plugin.getDateFormat().parse(plugin.getData().getString("bank.save."+uuid+".time"));
        }
        catch (Throwable e)
        {
            return null;
        }
    }

    @Override
    public double getShoutRepay(@NotNull OfflinePlayer player)
    {
        return getShoutRepay(player.getUniqueId());
    }

    @Override
    public double getShoutRepay(@NotNull OfflinePlayer player,@NotNull Date to)
    {
        return getShoutRepay(player.getUniqueId(),to);
    }

    @Override
    public double getShoutRepay(@NotNull UUID uuid)
    {
        Date date=getBorrowTime(uuid);
        if (date==null)
        {
            return 0D;
        }
        return getShoutRepay(uuid,date);
    }

    @Override
    public double getShoutRepay(@NotNull UUID uuid,@NotNull Date to)
    {
        return getShoutRepay(getBorrow(uuid),to);
    }

    @Override
    public double getShoutGet(@NotNull OfflinePlayer player)
    {
        return getShoutGet(player.getUniqueId());
    }

    @Override
    public double getShoutGet(@NotNull OfflinePlayer player,@NotNull Date to)
    {
        return getShoutGet(player.getUniqueId(),to);
    }

    @Override
    public double getShoutGet(@NotNull UUID uuid)
    {
        Date date=getSaveTime(uuid);
        if (date==null)
        {
            return 0D;
        }
        return getShoutGet(uuid,date);
    }

    @Override
    public double getShoutGet(@NotNull UUID uuid,@NotNull Date to)
    {
        return getShoutGet(getSave(uuid),to);
    }

    @Override
    public double getShoutRepay(long money,@NotNull Date borrowDate,@NotNull Date to)
    {
        double borrowTimeMs=borrowDate.getTime();
        double toMs=to.getTime();
        double days=Math.ceil((toMs-borrowTimeMs)/86400000);
        return 0.01*days*money+money;
    }

    @Override
    public double getShoutRepay(long money,@NotNull Date borrowDate)
    {
        return getShoutRepay(money,borrowDate,Calendar.getInstance().getTime());
    }

    @Override
    public double getShoutGet(long money,@NotNull Date saveDate,@NotNull Date to)
    {
        double saveTimeMs=saveDate.getTime();
        double toMs=to.getTime();
        double days=Math.floor((toMs-saveTimeMs)/86400000);
        return 0.01*days*money+money;
    }

    @Override
    public double getShoutGet(long money,@NotNull Date saveDate)
    {
        return getShoutGet(money,saveDate,Calendar.getInstance().getTime());
    }
}
