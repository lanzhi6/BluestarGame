package me.lanzhi.bluestargame.listener;

import me.lanzhi.bluestargame.Type.elevator;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.Map;
import java.util.Set;

import static me.lanzhi.bluestargame.BluestarGame.Data;

public class Elevator implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerMove(PlayerMoveEvent event)
    {
        if (event.getPlayer().getVelocity().getY()<=0||event.getPlayer().isFlying()||event.isCancelled())
        {
            return;
        }
        Location loc = event.getPlayer().getLocation();
        elevator ele=elevator.getElevator(loc);
        if (ele!=null)
        {
            for (long y = loc.getBlockY()+1;y<=ele.getMaxY();y++)
            {
                Location locc=loc.clone();
                locc.setY(y);
                Location loccc=loc.clone();
                loccc.setY(y-1);
                if ((!locc.getBlock().getType().isSolid()||locc.getBlock().getType().name().endsWith("SIGN"))&&(loccc.getBlock().getType().isSolid()&&!loccc.getBlock().getType().name().endsWith("SIGN")))
                {
                    event.getPlayer().teleport(locc);
                    event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN+"UP UPP UPPP!"));
                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                    return;
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerSneak(PlayerToggleSneakEvent event)
    {
        if (event.isCancelled()||!event.isSneaking()||!event.getPlayer().isOnGround())
        {
            return;
        }
        Location loc = event.getPlayer().getLocation();
        elevator ele=elevator.getElevator(loc);
        if (ele!=null)
        {
            for (long y = loc.getBlockY()-1;y>=ele.getMinY();y--)
            {
                Location locc=loc.clone();
                locc.setY(y);
                Location loccc=loc.clone();
                loccc.setY(y-1);
                if ((!locc.getBlock().getType().isSolid()||locc.getBlock().getType().name().endsWith("SIGN"))&&(loccc.getBlock().getType().isSolid()&&!loccc.getBlock().getType().name().endsWith("SIGN")))
                {
                    event.getPlayer().teleport(locc);
                    event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN+"DOWN DOWWN DOWWWN!"));
                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                    return;
                }
            }
        }
    }

}
