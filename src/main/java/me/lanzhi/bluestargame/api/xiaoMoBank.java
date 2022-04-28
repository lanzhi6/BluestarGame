package me.lanzhi.bluestargame.api;

import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static me.lanzhi.bluestargame.BluestarGame.BluestarDataFormat;
import static me.lanzhi.bluestargame.BluestarGame.Data;

public class xiaoMoBank
{
    public static long getBorrow(@NotNull OfflinePlayer player)
    {
        return getBorrow(player.getUniqueId());
    }

    public static long getBorrow(@NotNull UUID uuid)
    {
        return Data.getLong("bank.borrow."+uuid+".money");
    }

    public static long getSave(@NotNull OfflinePlayer player)
    {
        return getSave(player.getUniqueId());
    }

    public static long getSave(@NotNull UUID uuid)
    {
        return Data.getLong("bank.save."+uuid+".money");
    }

    public static Date getBorrowTime(@NotNull OfflinePlayer player)
    {
        return getBorrowTime(player.getUniqueId());
    }

    public static Date getSaveTime(@NotNull OfflinePlayer player)
    {
        return getSaveTime(player.getUniqueId());
    }

    public static @Nullable Date getBorrowTime(@NotNull UUID uuid)
    {
        try
        {
            return BluestarDataFormat.parse(Data.getString("bank.borrow."+uuid+".time"));
        }
        catch (Throwable e)
        {
            return null;
        }
    }

    public static @Nullable Date getSaveTime(@NotNull UUID uuid)
    {
        try
        {
            return BluestarDataFormat.parse(Data.getString("bank.save."+uuid+".time"));
        }
        catch (Throwable e)
        {
            return null;
        }
    }

    public static double getShoutRepay(@NotNull OfflinePlayer player)
    {
        return getShoutRepay(player.getUniqueId());
    }

    public static double getShoutRepay(@NotNull OfflinePlayer player,@NotNull Date to)
    {
        return getShoutRepay(player.getUniqueId(),to);
    }

    public static double getShoutRepay(@NotNull UUID uuid)
    {
        Date date=getBorrowTime(uuid);
        if (date==null)
        {
            return 0D;
        }
        return getShoutRepay(uuid,date);
    }

    public static double getShoutRepay(@NotNull UUID uuid,@NotNull Date to)
    {
        return getShoutRepay(getBorrow(uuid),to);
    }

    public static double getShoutGet(@NotNull OfflinePlayer player)
    {
        return getShoutGet(player.getUniqueId());
    }

    public static double getShoutGet(@NotNull OfflinePlayer player,@NotNull Date to)
    {
        return getShoutGet(player.getUniqueId(),to);
    }

    public static double getShoutGet(@NotNull UUID uuid)
    {
        Date date=getSaveTime(uuid);
        if (date==null)
        {
            return 0D;
        }
        return getShoutGet(uuid,date);
    }

    public static double getShoutGet(@NotNull UUID uuid,@NotNull Date to)
    {
        return getShoutGet(getSave(uuid),to);
    }

    public static double getShoutRepay(long money,@NotNull Date borrowDate,@NotNull Date to)
    {
        double borrowTimeMs=borrowDate.getTime();
        double toMs=to.getTime();
        double days=Math.ceil((toMs-borrowTimeMs)/86400000);
        return 0.01*days*money+money;
    }

    public static double getShoutRepay(long money,@NotNull Date borrowDate)
    {
        return getShoutRepay(money,borrowDate,Calendar.getInstance().getTime());
    }

    public static double getShoutGet(long money,@NotNull Date saveDate,@NotNull Date to)
    {
        double saveTimeMs=saveDate.getTime();
        double toMs=to.getTime();
        double days=Math.floor((toMs-saveTimeMs)/86400000);
        return 0.01*days*money+money;
    }

    public static double getShoutGet(long money,@NotNull Date saveDate)
    {
        return getShoutGet(money,saveDate,Calendar.getInstance().getTime());
    }
}
