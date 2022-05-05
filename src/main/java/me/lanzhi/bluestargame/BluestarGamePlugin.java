package me.lanzhi.bluestargame;

import me.lanzhi.bluestarapi.Api.config.AutoSerialize;
import me.lanzhi.bluestarapi.Api.config.YamlFile;
import me.lanzhi.bluestargame.Type.CompressedCoal;
import me.lanzhi.bluestargame.Type.Elevator;
import me.lanzhi.bluestargame.Type.SuperSponge;
import me.lanzhi.bluestargame.managers.BluestarGameManager;
import me.lanzhi.bluestargame.listener.breakBedrockListener;
import me.lanzhi.bluestargame.register.CommandRegister;
import me.lanzhi.bluestargame.register.ListenersRegister;
import me.lanzhi.bluestargame.register.RecipeRegister;
import me.lanzhi.bluestargameapi.BluestarGamePluginInterface;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public final class BluestarGamePlugin extends JavaPlugin implements BluestarGamePluginInterface
{
    private final YamlFile config;
    private final File PlayerData;
    private final YamlFile PlayerMap;
    private final YamlFile Data;
    private Economy econ;
    private final String messageHead=ChatColor.GOLD+"["+ChatColor.DARK_AQUA+"BluestarGame"+ChatColor.GOLD+"]";
    private final String errorMessageHead=messageHead+ChatColor.RED;
    private final SimpleDateFormat BluestarDateFormat;
    private final NumberFormat BluestarNF;
    private BluestarGameManager bluestarGameManager;
    private CommandRegister commandRegister;
    private ListenersRegister listenersRegister;
    private RecipeRegister recipeRegister;
    private BukkitTask spongeTask;
    private BukkitTask bedrockTask;
    private boolean isEnable=false;

    public BluestarGamePlugin()
    {
        AutoSerialize.registerClass(Elevator.class);
        AutoSerialize.registerClass(SuperSponge.class);
        ConfigurationSerialization.registerClass(CompressedCoal.class);
        config=new YamlFile(new File(this.getDataFolder(),"config.yml"));
        PlayerMap=new YamlFile(new File(this.getDataFolder(),"playerMap.yml"));
        Data=new YamlFile(new File(this.getDataFolder(),"data.yml"));
        PlayerData=new File(this.getDataFolder(),"PlayerData");
        PlayerData.mkdirs();
        BluestarDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BluestarNF=NumberFormat.getInstance();
        BluestarNF.setGroupingUsed(false);
    }

    @Override
    public void onEnable()
    {
        config.reload();
        PlayerMap.reload();
        Data.reload();
        bluestarGameManager=new BluestarGameManager(this);
        commandRegister=new CommandRegister(this);
        listenersRegister=new ListenersRegister(this);
        recipeRegister=new RecipeRegister(this);
        econ=getServer().getServicesManager().load(Economy.class);

        new Metrics(this,14294);

        listenersRegister.registerListeners();
        recipeRegister.registerRecipes();
        commandRegister.registerCommands();

        if (config.getBoolean("auto"))
        {
            getBluestarGameManager().getRandomEventManger().runAuto(true);
        }

        getBluestarGameManager().getSuperSpongeManager().set((List<SuperSponge>) Data.getList("superSponges"));
        this.spongeTask=getBluestarGameManager().getSuperSpongeManager().ctrlsponge.runTaskTimer(this,0L,2L);
        this.bedrockTask=breakBedrockListener.breakBedrock.runTaskTimer(this,0L,20L);

        Bukkit.getServicesManager().register(BluestarGamePluginInterface.class,this,this,ServicePriority.Normal);

        isEnable=true;
        System.out.println("BluestarGame已加载");
    }

    @Override
    public void onDisable()
    {
        this.spongeTask.cancel();
        this.bedrockTask.cancel();
        getBluestarGameManager().getRandomEventManger().end();
        getBluestarGameManager().getRandomEventManger().all(false);
        recipeRegister.cancellationRecipes();
        Data.set("superSponges",getBluestarGameManager().getSuperSpongeManager().getSponges());
        Data.save();
        PlayerMap.save();
        config.save();
        Bukkit.getServicesManager().unregisterAll(this);
        isEnable=false;
        System.out.println("BluestarGame已卸载");
    }

    public Economy getEcon()
    {
        return econ;
    }

    @Override
    public File getPlayerData()
    {
        return PlayerData;
    }

    @Override
    public NumberFormat getNumberFormat()
    {
        return BluestarNF;
    }

    @Override
    public SimpleDateFormat getDateFormat()
    {
        return BluestarDateFormat;
    }

    @Override
    public String getErrorMessageHead()
    {
        return errorMessageHead;
    }

    @Override
    public String getMessageHead()
    {
        return messageHead;
    }

    @NotNull
    @Override
    public YamlFile getConfig()
    {
        return config;
    }
    @Override
    public void reloadConfig()
    {
        config.reload();
    }
    @Override
    public void saveConfig()
    {
        config.save();
    }

    @Override
    public YamlFile getData()
    {
        return Data;
    }

    @Override
    public YamlFile getPlayerMap()
    {
        return PlayerMap;
    }

    @Override
    public BluestarGameManager getBluestarGameManager()
    {
        return bluestarGameManager;
    }

    @Override
    public NumberFormat getBluestarNF()
    {
        return BluestarNF;
    }

    @Override
    public SimpleDateFormat getBluestarDateFormat()
    {
        return BluestarDateFormat;
    }

    @Override
    public BluestarGamePlugin getPlugin()
    {
        return this;
    }

    public boolean isEnable()
    {
        return this.isEnable;
    }
}


  