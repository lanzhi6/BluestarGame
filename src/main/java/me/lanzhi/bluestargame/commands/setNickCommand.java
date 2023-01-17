package me.lanzhi.bluestargame.commands;

import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.bluestarapi.player.chat.GradientColor;
import me.lanzhi.bluestargame.bluestarapi.player.chat.RGBColor;
import me.lanzhi.bluestargame.bluestarapi.player.input.PlayerChatInput;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public final class setNickCommand implements CommandExecutor, TabExecutor
{
    private final BluestarGamePlugin plugin;
    private final Chat chat;
    private final Economy economy;
    private final PlayerChatInput.Builder<String> setNickInput;

    public setNickCommand(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
        chat=plugin.getChat();
        economy=plugin.getEcon();

        setNickInput=PlayerChatInput.builder();
        setNickInput.sendValueMessage(ChatColor.GREEN+"请在聊天区域输入昵称,输入"+ChatColor.RED+"cancel"+ChatColor.GREEN+"取消")
                    .setValue((player1,s)->s)
                    .onCancel(p->p.sendMessage(plugin.getMessageHead()+"你已取消设置昵称"))
                    .plugin(plugin)
                    .onFinish(this::setNick);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,@NotNull String[] args)
    {
        if (!(sender instanceof Player player))
        {
            sender.sendMessage(plugin.getErrorMessageHead()+"此指令仅允许玩家输入!");
            return true;
        }
        if (PlayerChatInput.isInputing(player.getUniqueId()))
        {
            player.sendMessage(plugin.getErrorMessageHead()+"您正在进行其他输入,请完成后再试");
            return true;
        }
        if (!economy.has(player,1000))
        {
            player.sendMessage(plugin.getErrorMessageHead()+"错误,你的钱不足1000");
            return true;
        }
        player.sendMessage(plugin.getMessageHead()+"请在 聊天中 直接发送昵称,支持颜色");
        player.sendMessage(plugin.getMessageHead()+"支持颜色符号:§1&1§2&2§3&3§4&4§5&5§6&6§7&7§8&8§9&9§0&0§a&a§b&b§c&c§d&d§e&e§f&f"+ChatColor.GOLD+",支持RGB:"+RGBColor.setColor(
                "#098765")+"#098765"+ChatColor.GOLD+"支持渐变色: #abcdef-654321<"+GradientColor.colorText("abcdef",
                                                                                                          "654321",
                                                                                                          "需要染色的文本")+ChatColor.GOLD+">");
        player.sendMessage(plugin.getErrorMessageHead()+"设置昵称将花费1000,在聊天区发送需要的昵称后立即生效,概不退款。再次设置昵称可以覆盖,需要重新付款");

        setNickInput.open(player);
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender,@NotNull Command command,@NotNull String s,@NotNull String[] strings)
    {
        return Collections.emptyList();
    }

    private void setNick(Player player,String nick)
    {
        if (!plugin.getEcon().has(player,1000))
        {
            player.sendMessage(plugin.getErrorMessageHead()+"你的钱不足1000");
            return;
        }
        if (plugin.getEcon().withdrawPlayer(player,1000).type!=EconomyResponse.ResponseType.SUCCESS)
        {
            player.sendMessage(plugin.getErrorMessageHead()+"出现错误,请重试,或通知腐竹");
            return;
        }
        if (nick.indexOf(' ')>=0)
        {
            player.sendMessage(plugin.getErrorMessageHead()+"错误!,名称中不能饱和空格");
            return;
        }
        if (ChatColor.stripColor(nick).length()>10)
        {
            player.sendMessage(plugin.getErrorMessageHead()+"错误.昵称长度(去除颜色符号后)不应超过10");
            return;
        }
        chat.setPlayerPrefix(player,nick);
        player.sendMessage(plugin.getMessageHead()+"设置成功");
    }
}
