package me.lanzhi.bluestargame.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;

import static me.lanzhi.bluestargame.BluestarGame.*;

public class XiaoMoBank implements CommandExecutor, TabExecutor
{
    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,@NotNull String[] args)
    {
        if (args.length<1)
        {
            return false;
        }
        if (!(sender instanceof Player))
        {
            sender.sendMessage("此指令仅允许玩家输入");
            return true;
        }
        Player player=(Player)sender;
        switch (args[0])
        {
            case "borrow":
            {
                if (args.length<2)
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"请输入贷款金额");
                    return true;
                }
                long money;
                try
                {
                    money = Long.parseLong(args[1]);
                }
                catch (NumberFormatException e)
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"请输入正确的贷款金额");
                    return true;
                }
                if (Data.getLong("bank.borrow."+player.getUniqueId()+".money")!=0)
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"您似乎有一笔金额为"+
                            Data.getLong("bank.borrow."+player.getUniqueId()+".money")+
                            "的贷款还未偿还,请向偿还再尝试贷款");
                    return true;
                }
                econ.depositPlayer(player,money);
                Data.set("bank.borrow."+player.getUniqueId()+".money",money);
                Calendar calendar = Calendar.getInstance();
                Data.set("bank.borrow."+player.getUniqueId()+".time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
                sender.sendMessage(messageHead+ChatColor.GREEN+"您已成功贷款"+money);
                break;
            }
            case "repay":
            {
                long money=Data.getLong("bank.borrow."+player.getUniqueId()+".money");
                if (money==0)
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"你没有需要偿还的贷款");
                    return true;
                }
                Date borrowTime;
                try
                {
                    borrowTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(Data.getString("bank.borrow."+player.getUniqueId()+".time"));
                }
                catch (ParseException e)
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"出现错误,请重试或联系腐竹");
                    return true;
                }
                double borrowTimeMs=borrowTime.getTime();
                double nowTimeMs=Calendar.getInstance().getTime().getTime();
                double days=Math.ceil((nowTimeMs-borrowTimeMs)/86400000);
                double repayMoney=money+0.01*days*money;
                if (!econ.has(player,repayMoney))
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"您没有钱偿还贷款,快去赚钱吧");
                    return true;
                }
                econ.withdrawPlayer(player,repayMoney);
                Data.set("bank.borrow."+player.getUniqueId()+".money",0);
                sender.sendMessage(messageHead+ChatColor.GREEN+"你成功还款"+repayMoney);
                break;
            }
            case "save":
            {
                if (args.length<2)
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"请输入存款金额");
                    return true;
                }
                long money;
                try
                {
                    money = Long.parseLong(args[1]);
                }
                catch (NumberFormatException e)
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"请输入正确的存款金额");
                    return true;
                }
                if (Data.getLong("bank.save."+player.getUniqueId()+".money")!=0)
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"您似乎有一笔金额为"+
                            Data.getLong("bank.save."+player.getUniqueId()+".money")+
                            "的存款还未取出,请向取出再尝试存款");
                    return true;
                }
                if (!econ.has(player,money))
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"你没有那么多钱(别做白日梦了!)");
                    return true;
                }
                econ.withdrawPlayer(player,money);
                Data.set("bank.save."+player.getUniqueId()+".money",money);
                Calendar calendar = Calendar.getInstance();
                Data.set("bank.save."+player.getUniqueId()+".time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
                sender.sendMessage(messageHead+ChatColor.GREEN+"您已成功存款"+money);
                break;
            }
            case "get":
            {
                if (Data.getLong("bank.save."+player.getUniqueId()+".money")==0)
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"您还没有存款哦,去努力赚钱吧");
                    return true;
                }
                Date saveTime;
                try
                {
                    saveTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(Data.getString("bank.save."+player.getUniqueId()+".time"));
                }
                catch (ParseException e)
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"出现错误,请重试或联系腐竹");
                    return true;
                }
                double money=Data.getLong("bank.save."+player.getUniqueId()+".money");
                double saveTimeMs=saveTime.getTime();
                double nowTimeMs=Calendar.getInstance().getTime().getTime();
                double days=Math.floor((nowTimeMs-saveTimeMs)/86400000);
                double getMoney=money+0.01*days*money;
                econ.depositPlayer(player,getMoney);
                Data.set("bank.save."+player.getUniqueId()+".money",0);
                sender.sendMessage(messageHead+ChatColor.GREEN+"你成功取出存款"+getMoney);
                break;
            }
            default:
            { }
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,@NotNull String[] args)
    {
        if (args.length==1)
        {
            return Arrays.asList("borrow","repay","save","get");
        }
        else if (args.length==2&&("borrow".equals(args[0])||"save".equals(args[0])))
        {
            return Arrays.asList("<金额>");
        }
        return new ArrayList<>();
    }
}
