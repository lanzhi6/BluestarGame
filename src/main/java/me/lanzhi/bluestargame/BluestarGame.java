package me.lanzhi.bluestargame;


import me.lanzhi.bluestargame.Ctrls.CTRL;
import me.lanzhi.bluestargame.Ctrls.CtrlSponge;
import me.lanzhi.bluestargame.Type.superSponge;
import me.lanzhi.bluestargame.commands.bsgamelist;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public final class BluestarGame extends org.bukkit.plugin.java.JavaPlugin
{
    public static Plugin config;
    BukkitTask task;

    @Override
    public void onEnable()
    {
        config = getProvidingPlugin(BluestarGame.class);
        Bukkit.getPluginManager().registerEvents(new listener(), this);
        if (getCommand("bluestargame") != null)
        {
            getCommand("bluestargame").setExecutor(new me.lanzhi.bluestargame.commands.maincommand());
        }
        else
        {
            System.out.println(ChatColor.RED + "错误!");
        }
        if (getCommand("bluestargamelist") != null)
        {
            getCommand("bluestargamelist").setExecutor(new bsgamelist());
        }
        else
        {
            System.out.println(ChatColor.RED + "错误!");
        }
        if (config.getConfig().getBoolean("auto"))
        {
            CTRL.runAuto(true);
        }
        ShapedRecipe recipe = new ShapedRecipe(new org.bukkit.NamespacedKey(this, "supersponge"), superSponge.getSuperSponge().getItem());
        recipe = recipe.shape("aaa", "aaa", "aaa");
        recipe = recipe.setIngredient('a', Material.SPONGE);
        Bukkit.addRecipe(recipe);
        this.task = new CtrlSponge().ctrlsponge.runTaskTimer(getPlugin(BluestarGame.class), 0L, 2L);
        System.out.println("BluestarGame已加载");
    }

    @Override
    public void onDisable()
    {
        this.task.cancel();
        CTRL.theend();
        CTRL.all(false);
        Bukkit.clearRecipes();
        System.out.println("BluestarGame已卸载");
    }
}


  