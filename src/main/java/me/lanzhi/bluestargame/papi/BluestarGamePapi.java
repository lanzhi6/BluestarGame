package me.lanzhi.bluestargame.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import me.lanzhi.bluestartpscontrol.BluestarTpsControlApi;

public class BluestarGamePapi extends PlaceholderExpansion
{
    public BluestarGamePapi()
    {
        this.register();
    }

    @Override
    public @NotNull String getIdentifier()
    {
        return "BluestarGame";
    }

    @Override
    public @NotNull String getName()
    {
        return "BluestarGame-Papi";
    }

    @Override
    public @NotNull String getAuthor()
    {
        return "lanzhi";
    }

    @Override
    public @NotNull String getVersion()
    {
        return "2.5.1";
    }

    @Override
    public boolean persist()
    {
        return true;
    }

    @Override
    @NotNull
    public String onRequest(@Nullable OfflinePlayer player,@Nullable String params)
    {
        return BluestarTpsControlApi.tpsFormat(MinecraftServer.getServer().recentTps[0]);
    }
}
