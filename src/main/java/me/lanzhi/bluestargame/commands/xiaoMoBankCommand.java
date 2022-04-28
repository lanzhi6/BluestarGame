package me.lanzhi.bluestargame.commands;

import me.dreamvoid.miraimc.api.MiraiMC;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static me.lanzhi.bluestargame.BluestarGame.*;
import static me.lanzhi.bluestargame.api.xiaoMoBank.*;

public class xiaoMoBankCommand implements CommandExecutor, TabExecutor
{
    private static final BaseComponent borrowGui;
    private static final BaseComponent saveGui;
    private static final BaseComponent borrow;
    private static final BaseComponent back;
    private static final BaseComponent save;

    static
    {
        BaseComponent cmp=new TextComponent(ChatColor.DARK_GRAY+"["+ChatColor.GREEN+"存款相关业务"+ChatColor.DARK_GRAY+"]");
        cmp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("点击进入存款业务")));
        cmp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/xmbank savegui"));
        saveGui=new TextComponent(messageHead+"    ");
        saveGui.addExtra(cmp);

        cmp=new TextComponent(ChatColor.DARK_GRAY+"["+ChatColor.RED+"贷款相关业务"+ChatColor.DARK_GRAY+"]");
        cmp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("点击进入贷款业务")));
        cmp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/xmbank borrowgui"));
        borrowGui=new TextComponent(messageHead+"    ");
        borrowGui.addExtra(cmp);

        cmp=new TextComponent(ChatColor.DARK_GRAY+"["+ChatColor.RED+"返回"+ChatColor.DARK_GRAY+"]");
        cmp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("点击返回上一页")));
        cmp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/xmbank"));
        back=new TextComponent(messageHead);
        back.addExtra(cmp);

        cmp=new TextComponent(ChatColor.DARK_GRAY+"["+ChatColor.RED+"贷款"+ChatColor.DARK_GRAY+"]");
        cmp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("点击进行贷款")));
        cmp.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,"/xmbank borrow "));
        borrow=new TextComponent(messageHead+"      ");
        borrow.addExtra(cmp);

        cmp=new TextComponent(ChatColor.DARK_GRAY+"["+ChatColor.GREEN+"存款"+ChatColor.DARK_GRAY+"]");
        cmp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("点击进行存款")));
        cmp.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,"/xmbank save "));
        save=new TextComponent(messageHead+"      ");
        save.addExtra(cmp);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,@NotNull String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage("此指令仅允许玩家输入");
            return true;
        }
        Player player=(Player) sender;
        if (args.length<1)
        {
            sender.sendMessage(" ");
            sender.sendMessage(" ");
            sender.sendMessage(" ");
            sender.sendMessage(" ");
            sender.sendMessage(" ");
            sender.sendMessage(" ");
            sender.sendMessage(" ");
            sender.sendMessage(" ");
            sender.sendMessage(messageHead+ChatColor.RED+"       [小末银行]");
            sender.sendMessage(messageHead+"---------------------");
            sender.sendMessage(messageHead);
            sender.sendMessage(messageHead);
            sender.sendMessage(messageHead+"你拥有的钱: "+BluestarNF.format(econ.getBalance(player)));
            sender.sendMessage(messageHead+"小末银行的业务:");
            sender.sendMessage(messageHead);
            sender.spigot().sendMessage(saveGui);
            sender.spigot().sendMessage(borrowGui);
            sender.sendMessage(messageHead);
            sender.sendMessage(messageHead);
            sender.sendMessage(messageHead+"---------------------");
            return false;
        }
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
                    money=Long.parseLong(args[1]);
                }
                catch (NumberFormatException e)
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"请输入正确的贷款金额");
                    return true;
                }
                if (MiraiMC.getBinding(player.getUniqueId().toString())==0)
                {
                    player.sendMessage(errorMessageHead+"贷款必须绑定qq");
                    player.sendMessage(errorMessageHead+"使用/bindqq绑定qq");
                    return true;
                }
                if (Data.getLong("bank.borrow."+player.getUniqueId()+".money")!=0)
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"您似乎有一笔金额为"+Data.getLong("bank.borrow."+player.getUniqueId()+".money")+"的贷款还未偿还,请向偿还再尝试贷款");
                    return true;
                }
                if (money>1000000)
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"贷款金额禁止大于100万哦");
                    return true;
                }
                econ.depositPlayer(player,money);
                Data.set("bank.borrow."+player.getUniqueId()+".money",money);
                Calendar calendar=Calendar.getInstance();
                Data.set("bank.borrow."+player.getUniqueId()+".time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
                player.chat("/xmbank borrowgui");
                break;
            }
            case "repay":
            {
                if (getBorrow(player)==0)
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"你没有需要偿还的贷款");
                    return true;
                }
                double repayMoney=getShoutRepay(player);
                if (!econ.has(player,repayMoney))
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"您没有钱偿还贷款,快去赚钱吧");
                    return true;
                }
                econ.withdrawPlayer(player,repayMoney);
                Data.set("bank.borrow."+player.getUniqueId(),null);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent(ChatColor.GREEN+"你成功还款: "+BluestarNF.format(repayMoney)));
                player.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
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
                    money=Long.parseLong(args[1]);
                }
                catch (NumberFormatException e)
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"请输入正确的存款金额");
                    return true;
                }
                if (Data.getLong("bank.save."+player.getUniqueId()+".money")!=0)
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"您似乎有一笔金额为"+Data.getLong("bank.save."+player.getUniqueId()+".money")+"的存款还未取出,请向取出再尝试存款");
                    return true;
                }
                if (!econ.has(player,money))
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"你没有那么多钱(别做白日梦了!)");
                    return true;
                }
                econ.withdrawPlayer(player,money);
                Data.set("bank.save."+player.getUniqueId()+".money",money);
                Calendar calendar=Calendar.getInstance();
                Data.set("bank.save."+player.getUniqueId()+".time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
                player.chat("/xmbank savegui");
                break;
            }
            case "get":
            {
                if (getSave(player)==0)
                {
                    sender.sendMessage(messageHead+ChatColor.RED+"您还没有存款哦,去努力赚钱吧");
                    return true;
                }
                double getMoney=getShoutGet(player);
                econ.depositPlayer(player,getMoney);
                Data.set("bank.save."+player.getUniqueId(),null);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent(ChatColor.GREEN+"你成功取出存款: "+BluestarNF.format(getMoney)));
                player.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
                break;
            }
            case "borrowgui":
            {
                if (getBorrow(player)!=0)
                {
                    long money=getBorrow(player);
                    double repayMoney=getShoutRepay(player);
                    BaseComponent cmp=new TextComponent(ChatColor.DARK_GRAY+"["+ChatColor.RED+"还款"+ChatColor.DARK_GRAY+"]");
                    cmp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("您需要还款"+BluestarNF.format(repayMoney))));
                    cmp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/cmdbag repay&gui"));
                    BaseComponent repay=new TextComponent(messageHead+"      ");
                    repay.addExtra(cmp);
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(messageHead+ChatColor.RED+"       [小末银行]");
                    sender.sendMessage(messageHead+"---------------------");
                    sender.spigot().sendMessage(back);
                    sender.sendMessage(messageHead+"你拥有的钱: "+BluestarNF.format(econ.getBalance(player)));
                    sender.sendMessage(messageHead);
                    sender.sendMessage(messageHead+"你在银行的贷款: "+money);
                    sender.sendMessage(messageHead);
                    sender.spigot().sendMessage(repay);
                    sender.sendMessage(messageHead);
                    sender.sendMessage(messageHead+ChatColor.BLUE+"还款后才可再次贷款");
                    sender.sendMessage(messageHead);
                    sender.sendMessage(messageHead+"---------------------");
                }
                else
                {
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(messageHead+ChatColor.RED+"       [小末银行]");
                    sender.sendMessage(messageHead+"---------------------");
                    sender.spigot().sendMessage(back);
                    sender.sendMessage(messageHead+"你拥有的钱: "+BluestarNF.format(econ.getBalance(player)));
                    sender.sendMessage(messageHead);
                    sender.sendMessage(messageHead+"你目前没有贷款(人生赢家)");
                    sender.sendMessage(messageHead);
                    sender.spigot().sendMessage(borrow);
                    sender.sendMessage(messageHead);
                    sender.sendMessage(messageHead+ChatColor.BLUE+"贷款后,利息为每天%1(现实天)");
                    sender.sendMessage(messageHead);
                    sender.sendMessage(messageHead+"---------------------");
                }
                break;
            }
            case "savegui":
            {
                if (getSave(player)!=0)
                {
                    long money=getSave(player);
                    double getMoney=getShoutGet(player);
                    BaseComponent cmp=new TextComponent(ChatColor.DARK_GRAY+"["+ChatColor.GREEN+"取款"+ChatColor.DARK_GRAY+"]");
                    cmp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("您将会取出"+BluestarNF.format(getMoney))));
                    cmp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/cmdbag get&gui"));
                    BaseComponent get=new TextComponent(messageHead+"      ");
                    get.addExtra(cmp);
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(messageHead+ChatColor.RED+"       [小末银行]");
                    sender.sendMessage(messageHead+"---------------------");
                    sender.spigot().sendMessage(back);
                    sender.sendMessage(messageHead+"你拥有的钱: "+BluestarNF.format(econ.getBalance(player)));
                    sender.sendMessage(messageHead);
                    sender.sendMessage(messageHead+"你在银行的存款: "+money);
                    sender.sendMessage(messageHead);
                    sender.spigot().sendMessage(get);
                    sender.sendMessage(messageHead);
                    sender.sendMessage(messageHead+ChatColor.BLUE+"取出后才可以再存款");
                    sender.sendMessage(messageHead);
                    sender.sendMessage(messageHead+"---------------------");
                }
                else
                {
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(messageHead+ChatColor.RED+"       [小末银行]");
                    sender.sendMessage(messageHead+"---------------------");
                    sender.spigot().sendMessage(back);
                    sender.sendMessage(messageHead+"你拥有的钱: "+BluestarNF.format(econ.getBalance(player)));
                    sender.sendMessage(messageHead);
                    sender.sendMessage(messageHead+"你目前没有存款(好穷qwq)");
                    sender.sendMessage(messageHead);
                    sender.spigot().sendMessage(save);
                    sender.sendMessage(messageHead);
                    sender.sendMessage(messageHead+ChatColor.BLUE+"存款后,利息为每天%0.5(现实天)");
                    sender.sendMessage(messageHead);
                    sender.sendMessage(messageHead+"---------------------");
                }
            }
            default:
            {
            }
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
