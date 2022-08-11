package me.lanzhi.bluestargame.listener;

import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.Type.Elevator;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.jetbrains.annotations.NotNull;

public final class elevatorListener implements Listener
{
    private final BluestarGamePlugin plugin;

    public elevatorListener(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
    }

    @EventHandler(priority=EventPriority.MONITOR)
    public void onPlayerMove(PlayerMoveEvent event)
    {
        if (event.getPlayer().getVelocity().getY()<=0||event.getPlayer().isFlying()||event.isCancelled())
        {
            return;
        }
        Location loc=event.getPlayer().getLocation();
        Player player=event.getPlayer();
        Location to=getTeleportLocation(loc,1);
        if (to!=null)
        {
            player.teleport(to,PlayerTeleportEvent.TeleportCause.CHORUS_FRUIT);
            event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent(ChatColor.GREEN+"UP UPP UPPP!"));
            event.getPlayer().playSound(event.getPlayer().getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
        }
    }

    @EventHandler(priority=EventPriority.MONITOR)
    public void onPlayerSneak(PlayerToggleSneakEvent event)
    {
        if (event.isCancelled()||!event.isSneaking()||!event.getPlayer().isOnGround())
        {
            return;
        }
        Location loc=event.getPlayer().getLocation();
        Player player=event.getPlayer();
        Location to=getTeleportLocation(loc,-1);
        if (to!=null)
        {
            player.teleport(to,PlayerTeleportEvent.TeleportCause.CHORUS_FRUIT);
            event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent(ChatColor.GREEN+"DOWN DOWWN DOWWWN!"));
            event.getPlayer().playSound(event.getPlayer().getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
        }
    }

    private Location getTeleportLocation(Location loc,long cnt)
    {
        Elevator ele=plugin.getBluestarGameManager().getElevator(loc);
        if (ele!=null)
        {
            for (long y=loc.getBlockY()+cnt;y<=ele.getMaxY()&&y>=ele.getMinY();y+=cnt)
            {
                Location locc=loc.clone();
                locc.setY(y);
                Location loccc=loc.clone();
                loccc.setY(y-1);
                if ((!locc.getBlock().getType().isSolid()||locc.getBlock().getType().name().endsWith("SIGN"))&&(loccc.getBlock().getType().isSolid()&&!loccc.getBlock().getType().name().endsWith(
                        "SIGN")))
                {
                    return locc;
                }
            }
        }
        return null;
    }
}
