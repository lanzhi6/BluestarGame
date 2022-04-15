package me.lanzhi.bluestargame;

import me.lanzhi.bluestargame.Ctrls.CTRL;
import me.lanzhi.bluestargame.Ctrls.CtrlSponge;
import me.lanzhi.bluestargame.Type.Public;
import me.lanzhi.bluestargame.Type.elevator;
import me.lanzhi.bluestargame.Type.superSponge;
import me.lanzhi.bluestargame.Type.muted;
import me.lanzhi.bluestargame.commands.*;
import me.lanzhi.bluestargame.register.RegisterListeners;
import me.lanzhi.bluestargame.register.RegisterRecipe;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import me.lanzhi.bluestarapi.Api.YamlFile;
import net.milkbowl.vault.economy.Economy;

import java.io.File;
import java.util.List;

public final class BluestarGame extends org.bukkit.plugin.java.JavaPlugin
{
    public static Plugin plugin;
    public static YamlFile config;
    public static File PlayerData;
    public static YamlFile PlayerMap;
    public static YamlFile Data;
    public static Economy econ=null;
    public static String messageHead=ChatColor.GOLD+"["+ChatColor.DARK_AQUA+"BluestarGame"+ChatColor.GOLD+"]";

    private BukkitTask task;

    @Override
    public void onEnable()
    {
        plugin = this;
        ConfigurationSerialization.registerClass(muted.class);
        ConfigurationSerialization.registerClass(superSponge.class);
        ConfigurationSerialization.registerClass(elevator.class);
        ConfigurationSerialization.registerClass(Public.class);

        saveDefaultConfig();

        config = new YamlFile(new File(plugin.getDataFolder(),"config.yml"));
        PlayerMap = new YamlFile(new File(plugin.getDataFolder(),"playerMap.yml"));
        Data = new YamlFile(new File(plugin.getDataFolder(),"data.yml"));
        PlayerData = new File(plugin.getDataFolder(),"PlayerData");
        PlayerData.mkdirs();

        int pluginId = 14294;
        new Metrics(this, pluginId);

        RegisterListeners.registerListeners();
        RegisterRecipe.registerRecipes();

        getCommand("bluestargame").setExecutor(new me.lanzhi.bluestargame.commands.maincommand());
        getCommand("bluestargamelist").setExecutor(new bsgamelist());
        getCommand("muted").setExecutor(new mutedCommand());
        getCommand("chat").setExecutor(new chat());
        getCommand("bluestaritem").setExecutor(new bluestaritem());
        getCommand("elevator").setExecutor(new elevatorCommand());
        getCommand("xiaomobank").setExecutor(new XiaoMoBank());

        if (config.getBoolean("auto"))
        {
            CTRL.runAuto(true);
        }

        CtrlSponge.set((List<superSponge>) Data.getList("superSponges"));
        this.task = CtrlSponge.ctrlsponge.runTaskTimer(plugin, 0L, 2L);
        econ=getServer().getServicesManager().getRegistration(Economy.class).getProvider();

        System.out.println("BluestarGame已加载");
    }

    @Override
    public void onDisable()
    {
        this.task.cancel();
        CTRL.theend();
        CTRL.all(false);
        Bukkit.clearRecipes();
        Data.set("superSponges",CtrlSponge.get());
        Data.save();
        PlayerMap.save();
        Data.save();
        System.out.println("BluestarGame已卸载");
    }
}


  