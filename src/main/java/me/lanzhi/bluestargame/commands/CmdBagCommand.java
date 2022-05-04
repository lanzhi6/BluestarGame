package me.lanzhi.bluestargame.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CmdBagCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,@NotNull String[] args)
    {
        if (args.length>0)
        {
            switch (args[0])
            {
                case "get&gui":
                {
                    if (sender instanceof Player)
                    {
                        ((Player) sender).chat("/xmbank get");
                        try
                        {
                            Thread.sleep(1);
                        }
                        catch (Throwable e)
                        {
                            throw new RuntimeException(e);
                        }
                        ((Player) sender).chat("/xmbank savegui");
                    }
                    break;
                }
                case "repay&gui":
                {
                    ((Player) sender).chat("/xmbank repay ");
                    try
                    {
                        Thread.sleep(1);
                    }
                    catch (Throwable e)
                    {
                        throw new RuntimeException(e);
                    }
                    ((Player) sender).chat("/xmbank borrowgui");
                    break;
                }
                default:
                {
                }
            }
        }
        return false;
    }
}
