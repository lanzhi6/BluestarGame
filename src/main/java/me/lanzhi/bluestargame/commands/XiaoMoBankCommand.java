package me.lanzhi.bluestargame.commands;

import me.dreamvoid.miraimc.api.MiraiMC;
import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.api.xiaoMoBank;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class XiaoMoBankCommand implements CommandExecutor, TabExecutor
{
    private final BaseComponent borrowGui;
    private final BaseComponent saveGui;
    private final BaseComponent borrow;
    private final BaseComponent back;
    private final BaseComponent save;
    private final BluestarGamePlugin plugin;
    private final xiaoMoBank bank;

    public XiaoMoBankCommand(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
        BaseComponent cmp=new TextComponent(ChatColor.DARK_GRAY+"["+ChatColor.GREEN+"存款相关业务"+ChatColor.DARK_GRAY+"]");
        cmp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("点击进入存款业务")));
        cmp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/xmbank savegui"));
        saveGui=new TextComponent(plugin.getMessageHead()+"    ");
        saveGui.addExtra(cmp);

        cmp=new TextComponent(ChatColor.DARK_GRAY+"["+ChatColor.RED+"贷款相关业务"+ChatColor.DARK_GRAY+"]");
        cmp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("点击进入贷款业务")));
        cmp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/xmbank borrowgui"));
        borrowGui=new TextComponent(plugin.getMessageHead()+"    ");
        borrowGui.addExtra(cmp);

        cmp=new TextComponent(ChatColor.DARK_GRAY+"["+ChatColor.RED+"返回"+ChatColor.DARK_GRAY+"]");
        cmp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("点击返回上一页")));
        cmp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/xmbank"));
        back=new TextComponent(plugin.getMessageHead());
        back.addExtra(cmp);

        cmp=new TextComponent(ChatColor.DARK_GRAY+"["+ChatColor.RED+"贷款"+ChatColor.DARK_GRAY+"]");
        cmp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("点击进行贷款")));
        cmp.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,"/xmbank borrow "));
        borrow=new TextComponent(plugin.getMessageHead()+"      ");
        borrow.addExtra(cmp);

        cmp=new TextComponent(ChatColor.DARK_GRAY+"["+ChatColor.GREEN+"存款"+ChatColor.DARK_GRAY+"]");
        cmp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("点击进行存款")));
        cmp.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,"/xmbank save "));
        save=new TextComponent(plugin.getMessageHead()+"      ");
        save.addExtra(cmp);
        
        bank=plugin.getBluestarGameManager().getApi().getXiaoMoBank();
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
            sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"       [小末银行]");
            sender.sendMessage(plugin.getMessageHead()+"---------------------");
            sender.sendMessage(plugin.getMessageHead());
            sender.sendMessage(plugin.getMessageHead());
            sender.sendMessage(plugin.getMessageHead()+"你拥有的钱: "+plugin.getNumberFormat().format(plugin.getEcon().getBalance(player)));
            sender.sendMessage(plugin.getMessageHead()+"小末银行的业务:");
            sender.sendMessage(plugin.getMessageHead());
            sender.spigot().sendMessage(saveGui);
            sender.spigot().sendMessage(borrowGui);
            sender.sendMessage(plugin.getMessageHead());
            sender.sendMessage(plugin.getMessageHead());
            sender.sendMessage(plugin.getMessageHead()+"---------------------");
            return false;
        }
        switch (args[0])
        {
            case "borrow":
            {
                if (args.length<2)
                {
                    sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"请输入贷款金额");
                    return true;
                }
                long money;
                try
                {
                    money=Long.parseLong(args[1]);
                }
                catch (NumberFormatException e)
                {
                    sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"请输入正确的贷款金额");
                    return true;
                }
                if (MiraiMC.getBinding(player.getUniqueId().toString())==0)
                {
                    player.sendMessage(plugin.getErrorMessageHead()+"贷款必须绑定qq");
                    player.sendMessage(plugin.getErrorMessageHead()+"使用/bindqq绑定qq");
                    return true;
                }
                if (plugin.getData().getLong("bank.borrow."+player.getUniqueId()+".money")!=0)
                {
                    sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"您似乎有一笔金额为"+plugin.getData().getLong("bank.borrow."+player.getUniqueId()+".money")+"的贷款还未偿还,请向偿还再尝试贷款");
                    return true;
                }
                if (money>1000000)
                {
                    sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"贷款金额禁止大于100万哦");
                    return true;
                }
                plugin.getEcon().depositPlayer(player,money);
                plugin.getData().set("bank.borrow."+player.getUniqueId()+".money",money);
                Calendar calendar=Calendar.getInstance();
                plugin.getData().set("bank.borrow."+player.getUniqueId()+".time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
                player.chat("/xmbank borrowgui");
                break;
            }
            case "repay":
            {
                if (bank.getBorrow(player)==0)
                {
                    sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"你没有需要偿还的贷款");
                    return true;
                }
                double repayMoney=bank.getShoutRepay(player);
                if (!plugin.getEcon().has(player,repayMoney))
                {
                    sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"您没有钱偿还贷款,快去赚钱吧");
                    return true;
                }
                plugin.getEcon().withdrawPlayer(player,repayMoney);
                plugin.getData().set("bank.borrow."+player.getUniqueId(),null);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent(ChatColor.GREEN+"你成功还款: "+plugin.getNumberFormat().format(repayMoney)));
                player.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
                break;
            }
            case "save":
            {
                if (args.length<2)
                {
                    sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"请输入存款金额");
                    return true;
                }
                long money;
                try
                {
                    money=Long.parseLong(args[1]);
                }
                catch (NumberFormatException e)
                {
                    sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"请输入正确的存款金额");
                    return true;
                }
                if (plugin.getData().getLong("bank.save."+player.getUniqueId()+".money")!=0)
                {
                    sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"您似乎有一笔金额为"+plugin.getData().getLong("bank.save."+player.getUniqueId()+".money")+"的存款还未取出,请向取出再尝试存款");
                    return true;
                }
                if (!plugin.getEcon().has(player,money))
                {
                    sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"你没有那么多钱(别做白日梦了!)");
                    return true;
                }
                plugin.getEcon().withdrawPlayer(player,money);
                plugin.getData().set("bank.save."+player.getUniqueId()+".money",money);
                Calendar calendar=Calendar.getInstance();
                plugin.getData().set("bank.save."+player.getUniqueId()+".time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
                player.chat("/xmbank savegui");
                break;
            }
            case "get":
            {
                if (bank.getSave(player)==0)
                {
                    sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"您还没有存款哦,去努力赚钱吧");
                    return true;
                }
                double getMoney=bank.getShoutGet(player);
                plugin.getEcon().depositPlayer(player,getMoney);
                plugin.getData().set("bank.save."+player.getUniqueId(),null);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent(ChatColor.GREEN+"你成功取出存款: "+plugin.getNumberFormat().format(getMoney)));
                player.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
                break;
            }
            case "borrowgui":
            {
                if (bank.getBorrow(player)!=0)
                {
                    long money=bank.getBorrow(player);
                    double repayMoney=bank.getShoutRepay(player);
                    BaseComponent cmp=new TextComponent(ChatColor.DARK_GRAY+"["+ChatColor.RED+"还款"+ChatColor.DARK_GRAY+"]");
                    cmp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("您需要还款"+plugin.getNumberFormat().format(repayMoney))));
                    cmp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/cmdbag repay&gui"));
                    BaseComponent repay=new TextComponent(plugin.getMessageHead()+"      ");
                    repay.addExtra(cmp);
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"       [小末银行]");
                    sender.sendMessage(plugin.getMessageHead()+"---------------------");
                    sender.spigot().sendMessage(back);
                    sender.sendMessage(plugin.getMessageHead()+"你拥有的钱: "+plugin.getNumberFormat().format(plugin.getEcon().getBalance(player)));
                    sender.sendMessage(plugin.getMessageHead());
                    sender.sendMessage(plugin.getMessageHead()+"你在银行的贷款: "+money);
                    sender.sendMessage(plugin.getMessageHead());
                    sender.spigot().sendMessage(repay);
                    sender.sendMessage(plugin.getMessageHead());
                    sender.sendMessage(plugin.getMessageHead()+ChatColor.BLUE+"还款后才可再次贷款");
                    sender.sendMessage(plugin.getMessageHead());
                    sender.sendMessage(plugin.getMessageHead()+"---------------------");
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
                    sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"       [小末银行]");
                    sender.sendMessage(plugin.getMessageHead()+"---------------------");
                    sender.spigot().sendMessage(back);
                    sender.sendMessage(plugin.getMessageHead()+"你拥有的钱: "+plugin.getNumberFormat().format(plugin.getEcon().getBalance(player)));
                    sender.sendMessage(plugin.getMessageHead());
                    sender.sendMessage(plugin.getMessageHead()+"你目前没有贷款(人生赢家)");
                    sender.sendMessage(plugin.getMessageHead());
                    sender.spigot().sendMessage(borrow);
                    sender.sendMessage(plugin.getMessageHead());
                    sender.sendMessage(plugin.getMessageHead()+ChatColor.BLUE+"贷款后,利息为每天%1(现实天)");
                    sender.sendMessage(plugin.getMessageHead());
                    sender.sendMessage(plugin.getMessageHead()+"---------------------");
                }
                break;
            }
            case "savegui":
            {
                if (bank.getSave(player)!=0)
                {
                    long money=bank.getSave(player);
                    double getMoney=bank.getShoutGet(player);
                    BaseComponent cmp=new TextComponent(ChatColor.DARK_GRAY+"["+ChatColor.GREEN+"取款"+ChatColor.DARK_GRAY+"]");
                    cmp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("您将会取出"+plugin.getNumberFormat().format(getMoney))));
                    cmp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/cmdbag get&gui"));
                    BaseComponent get=new TextComponent(plugin.getMessageHead()+"      ");
                    get.addExtra(cmp);
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"       [小末银行]");
                    sender.sendMessage(plugin.getMessageHead()+"---------------------");
                    sender.spigot().sendMessage(back);
                    sender.sendMessage(plugin.getMessageHead()+"你拥有的钱: "+plugin.getNumberFormat().format(plugin.getEcon().getBalance(player)));
                    sender.sendMessage(plugin.getMessageHead());
                    sender.sendMessage(plugin.getMessageHead()+"你在银行的存款: "+money);
                    sender.sendMessage(plugin.getMessageHead());
                    sender.spigot().sendMessage(get);
                    sender.sendMessage(plugin.getMessageHead());
                    sender.sendMessage(plugin.getMessageHead()+ChatColor.BLUE+"取出后才可以再存款");
                    sender.sendMessage(plugin.getMessageHead());
                    sender.sendMessage(plugin.getMessageHead()+"---------------------");
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
                    sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"       [小末银行]");
                    sender.sendMessage(plugin.getMessageHead()+"---------------------");
                    sender.spigot().sendMessage(back);
                    sender.sendMessage(plugin.getMessageHead()+"你拥有的钱: "+plugin.getNumberFormat().format(plugin.getEcon().getBalance(player)));
                    sender.sendMessage(plugin.getMessageHead());
                    sender.sendMessage(plugin.getMessageHead()+"你目前没有存款(好穷qwq)");
                    sender.sendMessage(plugin.getMessageHead());
                    sender.spigot().sendMessage(save);
                    sender.sendMessage(plugin.getMessageHead());
                    sender.sendMessage(plugin.getMessageHead()+ChatColor.BLUE+"存款后,利息为每天%0.5(现实天)");
                    sender.sendMessage(plugin.getMessageHead());
                    sender.sendMessage(plugin.getMessageHead()+"---------------------");
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
