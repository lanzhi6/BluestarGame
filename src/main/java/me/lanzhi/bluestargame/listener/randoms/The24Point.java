package me.lanzhi.bluestargame.listener.randoms;

import me.lanzhi.bluestargame.BluestarGame;
import me.lanzhi.bluestargame.Ctrls.CTRL;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class The24Point implements Listener
{
    @EventHandler
    public void onChatFor24(AsyncPlayerChatEvent event)
    {
        String message = event.getMessage();
        if ((!CTRL.the24()) && (!CTRL.the24end))
        {
            return;
        }
        String[] ans = me.lanzhi.bluestargame.Ctrls.math.yunsuan(message);
        if (ans == null)
        {
            return;
        }
        try {
            if ((int) Double.parseDouble(ans[0]) != 24) {
                return;
            }
        } catch (NumberFormatException e)
        {
            return;
        }
        int[] a = CTRL.fourNum;
        boolean fflag = true;
        for (int i = 1; i <= 4; i++) {
            boolean flag = false;
            for (int j = 0; j < 4; j++) {
                if ((ans[i].equals(a[j] + "")) && (a[j] != -1)) {
                    a[j] = -1;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                fflag = false;
                break;
            }
        }
        if (fflag)
        {
            org.bukkit.Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + "[BluestarGame]" + event.getPlayer().getName() + "答案正确!");
            org.bukkit.Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + "[BluestarGame]获得1000!");
            CTRL.the24end = true;
            me.lanzhi.bluestarapi.Api.Bluestar.useCommand(org.bukkit.Bukkit.getConsoleSender(), "eco give "+event.getPlayer().getName()+" 1000", BluestarGame.plugin);
        }
    }
}
