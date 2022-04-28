package me.lanzhi.bluestargame;

import me.lanzhi.bluestarapi.Api.YamlFile;
import me.lanzhi.bluestargame.Ctrls.CTRL;
import me.lanzhi.bluestargame.Ctrls.CtrlSponge;
import me.lanzhi.bluestargame.Type.CompressedCoal;
import me.lanzhi.bluestargame.Type.Elevator;
import me.lanzhi.bluestargame.Type.SuperSponge;
import me.lanzhi.bluestargame.register.*;
import me.lanzhi.bluestargame.listener.breakBedrockListener;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public final class BluestarGame extends org.bukkit.plugin.java.JavaPlugin
{
    public static JavaPlugin plugin;
    public static YamlFile config;
    public static File PlayerData;
    public static YamlFile PlayerMap;
    public static YamlFile Data;
    public static Economy econ=null;
    public static String messageHead=ChatColor.GOLD+"["+ChatColor.DARK_AQUA+"BluestarGame"+ChatColor.GOLD+"]";
    public static String errorMessageHead=messageHead+ChatColor.RED;
    public static SimpleDateFormat BluestarDataFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static NumberFormat BluestarNF=NumberFormat.getInstance();

    private BukkitTask spongeTask;
    private BukkitTask bedrockTask;

    @Override
    public void onEnable()
    {
        BluestarNF.setGroupingUsed(false);
        plugin=this;
        ConfigurationSerialization.registerClass(SuperSponge.class);
        ConfigurationSerialization.registerClass(Elevator.class);
        ConfigurationSerialization.registerClass(CompressedCoal.class);

        saveDefaultConfig();

        config=new YamlFile(new File(plugin.getDataFolder(),"config.yml"));
        PlayerMap=new YamlFile(new File(plugin.getDataFolder(),"playerMap.yml"));
        Data=new YamlFile(new File(plugin.getDataFolder(),"data.yml"));
        PlayerData=new File(plugin.getDataFolder(),"PlayerData");
        PlayerData.mkdirs();

        new Metrics(this,14294);

        registerListeners.registerListeners();
        registerRecipe.registerRecipes();
        registerCommand.registerCommands();
        registerConfigurationSection.registerConfigurationSections();

        if (config.getBoolean("auto"))
        {
            CTRL.runAuto(true);
        }

        CtrlSponge.set((List<SuperSponge>) Data.getList("superSponges"));
        this.spongeTask=CtrlSponge.ctrlsponge.runTaskTimer(plugin,0L,2L);
        this.bedrockTask=breakBedrockListener.breakBedrock.runTaskTimer(plugin,0L,20L);
        econ=getServer().getServicesManager().getRegistration(Economy.class).getProvider();

        System.out.println("BluestarGame已加载");
    }

    @Override
    public void onDisable()
    {
        this.spongeTask.cancel();
        this.bedrockTask.cancel();
        CTRL.theend();
        CTRL.all(false);
        registerRecipe.cancellationRecipes();
        Data.set("superSponges",CtrlSponge.get());
        Data.save();
        PlayerMap.save();
        Data.save();
        config.save();
        System.out.println("BluestarGame已卸载");
    }
}


  