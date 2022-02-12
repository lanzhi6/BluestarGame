package me.lanzhi.bluestargame;


import me.lanzhi.bluestargame.Ctrls.CTRL;
import me.lanzhi.bluestargame.Ctrls.CtrlSponge;
import me.lanzhi.bluestargame.Type.superSponge;
import me.lanzhi.bluestargame.commands.bsgamelist;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.RecipeChoice;
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

        ShapedRecipe bluestarsponge = new ShapedRecipe(new org.bukkit.NamespacedKey(this, "bluestarsponge"), superSponge.getSuperSponge().getItem());
        bluestarsponge = bluestarsponge.shape("aaa", "aaa", "aaa");
        bluestarsponge = bluestarsponge.setIngredient('a', Material.SPONGE);
        Bukkit.addRecipe(bluestarsponge);

        ShapedRecipe supersponge = new ShapedRecipe(new org.bukkit.NamespacedKey(this, "supersponge"), superSponge.getSuperSponge().getItem());
        supersponge = supersponge.shape("a");
        supersponge = supersponge.setIngredient('a', new RecipeChoice.ExactChoice(superSponge.getlavaSponge().getItem()));
        Bukkit.addRecipe(supersponge);

        ShapedRecipe lavasponge = new ShapedRecipe(new org.bukkit.NamespacedKey(this, "lavasponge"), superSponge.getlavaSponge().getItem());
        lavasponge = lavasponge.shape("a");
        lavasponge = lavasponge.setIngredient('a', new RecipeChoice.ExactChoice(superSponge.getSuperSponge().getItem()));
        Bukkit.addRecipe(lavasponge);
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


  