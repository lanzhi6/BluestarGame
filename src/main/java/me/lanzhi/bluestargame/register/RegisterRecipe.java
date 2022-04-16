package me.lanzhi.bluestargame.register;

import me.lanzhi.bluestargame.BluestarGame;
import me.lanzhi.bluestargame.Type.superSponge;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.ArrayList;
import java.util.List;

public class RegisterRecipe
{
    final private static List<NamespacedKey>recipes=new ArrayList<>();
    public static void registerRecipes()
    {
        /* 9海绵->超级海绵 */
        recipes.add(new NamespacedKey(BluestarGame.plugin, "supersponge"));
        ShapelessRecipe bluestarsponge = new ShapelessRecipe(new NamespacedKey(BluestarGame.plugin, "supersponge"), superSponge.getWaterSponge().getItem());
        bluestarsponge = bluestarsponge.addIngredient(9, Material.SPONGE);
        Bukkit.addRecipe(bluestarsponge);

        /* 岩浆海绵->水海绵 */
        recipes.add(new NamespacedKey(BluestarGame.plugin, "watersponge"));
        ShapedRecipe supersponge = new ShapedRecipe(new NamespacedKey(BluestarGame.plugin, "watersponge"), superSponge.getWaterSponge().getItem());
        supersponge = supersponge.shape("a");
        supersponge = supersponge.setIngredient('a', new RecipeChoice.ExactChoice(superSponge.getlavaSponge().getItem()));
        Bukkit.addRecipe(supersponge);

        /* 水海绵->岩浆海绵 */
        recipes.add(new NamespacedKey(BluestarGame.plugin, "lavasponge"));
        ShapedRecipe lavasponge = new ShapedRecipe(new NamespacedKey(BluestarGame.plugin, "lavasponge"), superSponge.getlavaSponge().getItem());
        lavasponge = lavasponge.shape("a");
        lavasponge = lavasponge.setIngredient('a', new RecipeChoice.ExactChoice(superSponge.getWaterSponge().getItem()));
        Bukkit.addRecipe(lavasponge);

        /* 使用过的水海绵烧制 */
        recipes.add(new NamespacedKey(BluestarGame.plugin, "usedwatersponge"));
        FurnaceRecipe usedWatersponge=new FurnaceRecipe(
                new NamespacedKey(BluestarGame.plugin,"usedwatersponge"),
                superSponge.getWaterSponge().getItem(),
                new RecipeChoice.ExactChoice(superSponge.getUsedWaterSponge().getItem()),
                5,
                200);
        Bukkit.addRecipe(usedWatersponge);

        /* 使用过的岩浆海绵烧制 */
        recipes.add(new NamespacedKey(BluestarGame.plugin, "usedlavasponge"));
        FurnaceRecipe usedlavasponge=new FurnaceRecipe(
                new NamespacedKey(BluestarGame.plugin,"usedlavasponge"),
                superSponge.getlavaSponge().getItem(),
                new RecipeChoice.ExactChoice(superSponge.getUsedLavaSponge().getItem()),
                5,
                200);
        Bukkit.addRecipe(usedlavasponge);
    }

    public static void cancellationRecipes()
    {
        for (NamespacedKey i:recipes)
        {
            Bukkit.removeRecipe(i);
        }
    }
}
