package me.lanzhi.bluestargame.commands;

import me.lanzhi.bluestarapi.api.RGBColor;
import me.lanzhi.bluestargame.BluestarGamePlugin;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
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

public class setNickCommand implements CommandExecutor, TabExecutor
{
    private final BluestarGamePlugin plugin;
    private final Chat chat;
    private final Economy economy;

    public setNickCommand(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
        chat=plugin.getChat();
        economy=plugin.getEcon();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,@NotNull String[] args)
    {
        if (!(sender instanceof Player player))
        {
            sender.sendMessage(plugin.getErrorMessageHead()+"此指令仅允许玩家输入!");
            return true;
        }
        List<Player> players=plugin.getBluestarGameManager().getSettingNickPlayer();
        if (players.remove(player))
        {
            player.sendMessage(plugin.getMessageHead()+"已取消设置昵称");
            return true;
        }
        if (!economy.has(player,1000))
        {
            player.sendMessage(plugin.getErrorMessageHead()+"错误,你的钱不足1000");
            return true;
        }
        player.sendMessage(plugin.getMessageHead()+"请在聊天区直接发送昵称,支持颜色");
        player.sendMessage(
                plugin.getMessageHead()+"支持颜色符号:§1&1§2&2§3&3§4&4§5&5§6&6§7&7§8&8§9&9§0&0§a&a§b&b§c&c§d&d§e&e§f&f"+ChatColor.GOLD+",支持RGB:"+RGBColor.setColor(
                        "#098765")+"#098765");
        player.sendMessage(plugin.getErrorMessageHead()+"设置昵称将花费1000,在聊天区发送需要的昵称后立即生效,概不退款。再次设置昵称可以覆盖,需要重新付款");
        player.sendMessage(plugin.getErrorMessageHead()+"若想取消此操作,请再次输入指令");
        players.add(player);
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender,@NotNull Command command,@NotNull String s,@NotNull String[] strings)
    {
        return Collections.emptyList();
    }
}
