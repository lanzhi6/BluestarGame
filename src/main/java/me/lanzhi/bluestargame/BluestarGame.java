package me.lanzhi.bluestargame;


import me.lanzhi.bluestargame.Ctrls.CTRL;
import me.lanzhi.bluestargame.Ctrls.CtrlSponge;
import me.lanzhi.bluestargame.Type.Public;
import me.lanzhi.bluestargame.Type.elevator;
import me.lanzhi.bluestargame.Type.superSponge;
import me.lanzhi.bluestargame.Type.muted;
import me.lanzhi.bluestargame.commands.*;
import me.lanzhi.bluestargame.listener.HealthFix;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import me.lanzhi.bluestarapi.Api.YamlFile;

import java.io.File;
import java.util.List;

public final class BluestarGame extends org.bukkit.plugin.java.JavaPlugin
{
    public static Plugin plugin;
    public static YamlFile config;
    public static File PlayerData;
    public static YamlFile PlayerMap;
    public static YamlFile Data;
    BukkitTask task;

    @Override
    public void onEnable()
    {
        ConfigurationSerialization.registerClass(muted.class);
        ConfigurationSerialization.registerClass(superSponge.class);
        ConfigurationSerialization.registerClass(elevator.class);
        ConfigurationSerialization.registerClass(Public.class);

        saveDefaultConfig();

        plugin = this;
        config = new YamlFile(new File(plugin.getDataFolder(),"config.yml"));
        PlayerMap = new YamlFile(new File(plugin.getDataFolder(),"playerMap.yml"));
        Data = new YamlFile(new File(plugin.getDataFolder(),"data.yml"));
        PlayerData = new File(plugin.getDataFolder(),"PlayerData");
        PlayerData.mkdirs();

        int pluginId = 14294;
        Metrics metrics = new Metrics(this, pluginId);

        RegisterListeners.registerListeners();

        getCommand("bluestargame").setExecutor(new me.lanzhi.bluestargame.commands.maincommand());
        getCommand("bluestargamelist").setExecutor(new bsgamelist());
        getCommand("muted").setExecutor(new mutedCommand());
        getCommand("chat").setExecutor(new chat());
        getCommand("bluestaritem").setExecutor(new bluestaritem());
        getCommand("elevator").setExecutor(new elevatorCommand());

        if (config.getBoolean("auto"))
        {
            CTRL.runAuto(true);
        }

        ShapelessRecipe bluestarsponge = new ShapelessRecipe(new org.bukkit.NamespacedKey(this, "supersponge"), superSponge.getSuperSponge().getItem());
        bluestarsponge = bluestarsponge.addIngredient(9, Material.SPONGE);
        Bukkit.addRecipe(bluestarsponge);

        ShapedRecipe supersponge = new ShapedRecipe(new org.bukkit.NamespacedKey(this, "watersponge"), superSponge.getSuperSponge().getItem());
        supersponge = supersponge.shape("a");
        supersponge = supersponge.setIngredient('a', new RecipeChoice.ExactChoice(superSponge.getlavaSponge().getItem()));
        Bukkit.addRecipe(supersponge);

        ShapedRecipe lavasponge = new ShapedRecipe(new org.bukkit.NamespacedKey(this, "lavasponge"), superSponge.getlavaSponge().getItem());
        lavasponge = lavasponge.shape("a");
        lavasponge = lavasponge.setIngredient('a', new RecipeChoice.ExactChoice(superSponge.getSuperSponge().getItem()));
        Bukkit.addRecipe(lavasponge);
        CtrlSponge.set((List<superSponge>) config.getList("superSponges"));
        this.task = CtrlSponge.ctrlsponge.runTaskTimer(plugin, 0L, 2L);

        System.out.println("BluestarGame已加载");
    }

    @Override
    public void onDisable()
    {
        this.task.cancel();
        CTRL.theend();
        CTRL.all(false);
        Bukkit.clearRecipes();
        config.set("superSponges",CtrlSponge.get());
        config.save();
        PlayerMap.save();
        Data.save();
        System.out.println("BluestarGame已卸载");
    }
}


  