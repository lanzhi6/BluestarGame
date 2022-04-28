package me.lanzhi.bluestargame.register;

import me.lanzhi.bluestargame.BluestarGame;
import me.lanzhi.bluestargame.Type.CompressedCoal;
import me.lanzhi.bluestargame.Type.SuperSponge;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;

import java.util.ArrayList;
import java.util.List;

public class registerRecipe
{
    final private static List<NamespacedKey> recipes=new ArrayList<>();

    public static void registerRecipes()
    {
        /* 9海绵->超级海绵 */
        NamespacedKey super_sponge_key=new NamespacedKey(BluestarGame.plugin,"super_sponge");
        recipes.add(super_sponge_key);
        ShapelessRecipe super_sponge=new ShapelessRecipe(super_sponge_key,SuperSponge.getWaterSponge().getItem());
        super_sponge=super_sponge.addIngredient(9,Material.SPONGE);
        Bukkit.addRecipe(super_sponge);

        /* 岩浆海绵->水海绵 */
        NamespacedKey water_sponge_key=new NamespacedKey(BluestarGame.plugin,"water_sponge");
        recipes.add(water_sponge_key);
        ShapedRecipe water_sponge=new ShapedRecipe(water_sponge_key,SuperSponge.getWaterSponge().getItem());
        water_sponge=water_sponge.shape("a");
        water_sponge=water_sponge.setIngredient('a',new RecipeChoice.ExactChoice(SuperSponge.getlavaSponge().getItem()));
        Bukkit.addRecipe(water_sponge);

        /* 水海绵->岩浆海绵 */
        NamespacedKey lava_sponge_key=new NamespacedKey(BluestarGame.plugin,"lava_sponge");
        recipes.add(lava_sponge_key);
        ShapedRecipe lava_sponge=new ShapedRecipe(lava_sponge_key,SuperSponge.getlavaSponge().getItem());
        lava_sponge=lava_sponge.shape("a");
        lava_sponge=lava_sponge.setIngredient('a',new RecipeChoice.ExactChoice(SuperSponge.getWaterSponge().getItem()));
        Bukkit.addRecipe(lava_sponge);

        /* 使用过的水海绵烧制 */
        NamespacedKey used_water_sponge_key=new NamespacedKey(BluestarGame.plugin,"used_water_sponge");
        recipes.add(used_water_sponge_key);
        FurnaceRecipe used_water_sponge=new FurnaceRecipe(used_water_sponge_key,SuperSponge.getWaterSponge().getItem(),new RecipeChoice.ExactChoice(SuperSponge.getUsedWaterSponge().getItem()),5,200);
        Bukkit.addRecipe(used_water_sponge);

        /* 使用过的岩浆海绵烧制 */
        NamespacedKey used_lava_sponge_key=new NamespacedKey(BluestarGame.plugin,"used_lava_sponge");
        recipes.add(used_lava_sponge_key);
        FurnaceRecipe used_lava_sponge=new FurnaceRecipe(used_lava_sponge_key,SuperSponge.getlavaSponge().getItem(),new RecipeChoice.ExactChoice(SuperSponge.getUsedLavaSponge().getItem()),5,200);
        Bukkit.addRecipe(used_lava_sponge);

        /*烧制煤炭块*/
        NamespacedKey fired_coal_block_key=new NamespacedKey(BluestarGame.plugin,"fired_coal_block");
        recipes.add(fired_coal_block_key);
        BlastingRecipe fired_coal_block=new BlastingRecipe(fired_coal_block_key,CompressedCoal.FIRED_COAL_BLOCK.getItem(),new RecipeChoice.ExactChoice(new ItemStack(Material.COAL_BLOCK)),2,200);
        Bukkit.addRecipe(fired_coal_block);

        /*压缩煤炭*/
        NamespacedKey compressed_coal_key=new NamespacedKey(BluestarGame.plugin,"compressed_coal");
        recipes.add(compressed_coal_key);
        ShapedRecipe compressed_coal=new ShapedRecipe(compressed_coal_key,CompressedCoal.COMPRESSED_COAL.getItem());
        compressed_coal.shape("aaa","aaa","aaa");
        compressed_coal.setIngredient('a',new RecipeChoice.ExactChoice(CompressedCoal.FIRED_COAL_BLOCK.getItem()));
        Bukkit.addRecipe(compressed_coal);

        /*烧制压缩煤炭*/
        NamespacedKey fired_compressed_coal_key=new NamespacedKey(BluestarGame.plugin,"fired_compressed_coal");
        recipes.add(fired_compressed_coal_key);
        BlastingRecipe fired_compressed_coal=new BlastingRecipe(
                fired_compressed_coal_key,
                CompressedCoal.FIRED_COMPRESSED_COAL.getItem(),
                new RecipeChoice.ExactChoice(CompressedCoal.COMPRESSED_COAL.getItem()),
                2,200
        );
        Bukkit.addRecipe(fired_compressed_coal);

        /*合成钻石*/
        NamespacedKey coal_diamond_key=new NamespacedKey(BluestarGame.plugin,"coal_diamond");
        recipes.add(coal_diamond_key);
        ShapedRecipe coal_diamond=new ShapedRecipe(coal_diamond_key,new ItemStack(Material.DIAMOND));
        coal_diamond.shape("aaa","aaa","aaa");
        coal_diamond.setIngredient('a',new RecipeChoice.ExactChoice(CompressedCoal.FIRED_COMPRESSED_COAL.getItem()));
        Bukkit.addRecipe(coal_diamond);

        /*覆盖煤炭块合成煤炭*/
        Bukkit.removeRecipe(NamespacedKey.minecraft("coal"));
        ShapedRecipe minecraft_coal=new ShapedRecipe(NamespacedKey.minecraft("coal"),new ItemStack(Material.COAL,9));
        minecraft_coal.shape("a").setIngredient('a',new RecipeChoice.ExactChoice(new ItemStack(Material.COAL_BLOCK)));
        Bukkit.addRecipe(minecraft_coal);
    }

    public static void cancellationRecipes()
    {
        for (NamespacedKey i: recipes)
        {
            Bukkit.removeRecipe(i);
        }
    }
}
