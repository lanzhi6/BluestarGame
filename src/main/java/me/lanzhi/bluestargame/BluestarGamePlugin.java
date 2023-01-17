package me.lanzhi.bluestargame;

import me.lanzhi.bluestargame.Type.CompressedCoal;
import me.lanzhi.bluestargame.Type.Elevator;
import me.lanzhi.bluestargame.Type.SuperSponge;
import me.lanzhi.bluestargame.bluestarapi.Bluestar;
import me.lanzhi.bluestargame.bluestarapi.config.AutoSerialize;
import me.lanzhi.bluestargame.bluestarapi.config.YamlFile;
import me.lanzhi.bluestargame.listener.breakBedrockListener;
import me.lanzhi.bluestargame.managers.BluestarGameManager;
import me.lanzhi.bluestargame.register.CommandRegister;
import me.lanzhi.bluestargame.register.ListenersRegister;
import me.lanzhi.bluestargame.register.RecipeRegister;
import me.lanzhi.bluestargameapi.IBluestarGamePlugin;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public final class BluestarGamePlugin extends IBluestarGamePlugin
{
    private final YamlFile config;
    private final File PlayerData;
    private final YamlFile PlayerMap;
    private final YamlFile Data;
    private final YamlFile Lang;
    private final String messageHead=ChatColor.GOLD+"["+ChatColor.DARK_AQUA+"BluestarGame"+ChatColor.GOLD+"]";
    private final String errorMessageHead=messageHead+ChatColor.RED;
    private final SimpleDateFormat BluestarDateFormat;
    private final NumberFormat BluestarNF;
    private Economy econ;
    private Chat chat;
    private BluestarGameManager bluestarGameManager;
    private CommandRegister commandRegister;
    private ListenersRegister listenersRegister;
    private RecipeRegister recipeRegister;
    private BukkitTask spongeTask;
    private BukkitTask bedrockTask;

    public BluestarGamePlugin()
    {
        AutoSerialize.registerClass(Elevator.class);
        AutoSerialize.registerClass(SuperSponge.class);
        ConfigurationSerialization.registerClass(CompressedCoal.class);
        config=new YamlFile(new File(this.getDataFolder(),"config.yml"));
        PlayerMap=new YamlFile(new File(this.getDataFolder(),"playerMap.yml"));
        Data=new YamlFile(new File(this.getDataFolder(),"data.yml"));
        Lang=new YamlFile(new File(this.getDataFolder(),"lang.yml"));
        PlayerData=new File(this.getDataFolder(),"PlayerData");
        PlayerData.mkdirs();
        BluestarDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BluestarNF=NumberFormat.getInstance();
        BluestarNF.setGroupingUsed(false);

        PluginCommand pluginCommand=Bluestar.getCommandManager().newPluginCommand("test",this);
        Bluestar.getCommandManager().registerPluginCommand(pluginCommand);
    }

    @Override
    public void onEnable()
    {
        config.reload();
        PlayerMap.reload();
        Data.reload();
        Lang.reload();
        bluestarGameManager=new BluestarGameManager(this);
        commandRegister=new CommandRegister(this);
        listenersRegister=new ListenersRegister(this);
        recipeRegister=new RecipeRegister(this);
        econ=getServer().getServicesManager().load(Economy.class);
        chat=getServer().getServicesManager().load(Chat.class);

        new Metrics(this,14294);

        listenersRegister.registerListeners();
        recipeRegister.registerRecipes();
        commandRegister.registerCommands();

        if (config.getBoolean("auto"))
        {
            getBluestarGameManager().getRandomEventManger().runAuto(true);
        }
        getBluestarGameManager().getRandomEventManger().randchat(true);

        getBluestarGameManager().getSuperSpongeManager().set((List<SuperSponge>) Data.getList("superSponges"));
        this.spongeTask=getBluestarGameManager().getSuperSpongeManager().ctrlsponge.runTaskTimer(this,0L,2L);
        this.bedrockTask=breakBedrockListener.breakBedrock.runTaskTimer(this,0L,20L);

        Bukkit.getServicesManager().register(IBluestarGamePlugin.class,this,this,ServicePriority.Normal);

        System.out.println("BluestarGame已加载");
    }

    @Override
    public void onDisable()
    {
        Bukkit.getScheduler().cancelTasks(this);
        this.spongeTask.cancel();
        this.bedrockTask.cancel();
        getBluestarGameManager().stop();
        recipeRegister.cancellationRecipes();
        Data.set("superSponges",getBluestarGameManager().getSuperSpongeManager().getSponges());
        Data.save();
        PlayerMap.save();
        config.save();
        Lang.save();
        Bukkit.getServicesManager().unregisterAll(this);
        System.out.println("BluestarGame已卸载");
    }

    public Economy getEcon()
    {
        return econ;
    }

    public Chat getChat()
    {
        return chat;
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
    public YamlFile getLang()
    {
        return Lang;
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
}


  