package me.lanzhi.bluestargame.listener;

import me.lanzhi.bluestarapi.api.GradientColor;
import me.lanzhi.bluestarapi.api.RGBColor;
import me.lanzhi.bluestargame.BluestarGamePlugin;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class setNickListener implements Listener
{
    private final List<Player>players;
    private final BluestarGamePlugin plugin;
    public setNickListener(@NotNull BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
        this.players=plugin.getBluestarGameManager().getSettingNickPlayer();
    }
    @EventHandler(priority=EventPriority.LOWEST)
    public void onPlayerMessage(@NotNull AsyncPlayerChatEvent event)
    {
        if (players.remove(event.getPlayer()))
        {
            event.setCancelled(true);
            if (!plugin.getEcon().has(event.getPlayer(),1000))
            {
                event.getPlayer().sendMessage(plugin.getErrorMessageHead()+"你的钱不足1000");
                return;
            }
            if (plugin.getEcon().withdrawPlayer(event.getPlayer(),1000).type!=EconomyResponse.ResponseType.SUCCESS)
            {
                event.getPlayer().sendMessage(plugin.getErrorMessageHead()+"出现错误,请重试,或通知腐竹");
                return;
            }
            String nick=GradientColor.setColor(event.getMessage());
            if (nick.indexOf(' ')>=0)
            {
                event.getPlayer().sendMessage(plugin.getErrorMessageHead()+"错误!,名称中不能饱和空格");
                return;
            }
            if (ChatColor.stripColor(nick).length()>10)
            {
                event.getPlayer().sendMessage(plugin.getErrorMessageHead()+"错误.昵称长度(去除颜色符号后)不应超过10");
                return;
            }
            plugin.getChat().setPlayerPrefix(event.getPlayer(),nick);
            event.getPlayer().sendMessage(plugin.getMessageHead()+"设置成功");
        }
    }
    @EventHandler(priority=EventPriority.MONITOR)
    public void onPlayerQuite(@NotNull PlayerQuitEvent event)
    {
        plugin.getBluestarGameManager().getSettingNickPlayer().remove(event.getPlayer());
    }
}
