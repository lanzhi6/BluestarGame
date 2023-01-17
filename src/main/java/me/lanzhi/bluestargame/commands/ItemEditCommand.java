package me.lanzhi.bluestargame.commands;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.bluestarapi.nbt.NBTItem;
import me.lanzhi.bluestargame.bluestarapi.player.gui.ChestGui;
import me.lanzhi.bluestargame.bluestarapi.player.gui.GuiItem;
import me.lanzhi.bluestargame.bluestarapi.player.input.PlayerAnvilInput;
import me.lanzhi.bluestargame.bluestarapi.reflect.ConstructorAccessor;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class ItemEditCommand implements CommandExecutor, TabExecutor
{
    private final BluestarGamePlugin plugin;

    public ItemEditCommand(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,
                             @NotNull String[] args)
    {
        if (!(sender instanceof Player player))
        {
            sender.sendMessage(plugin.getErrorMessageHead()+"player only");
            return true;
        }
        Gson gson=new Gson();

        JsonObject jsonObject=gson.fromJson(new NBTItem(player.getInventory().getItemInMainHand()).toString(),
                                            JsonObject.class);

        Book.Builder builder=Book.builder();
        builder.addPage(Component.text(gson.toJsonTree(jsonObject).getAsString()))
               .title(Component.text("ItemEdit"))
               .author(Component.text("Lanzhi"));

        player.openBook(builder);

        if (true)
        {
            return true;
        }

        ItemStack itemStack=player.getInventory().getItemInMainHand().clone();

        if (itemStack==null||itemStack.getType().isAir())
        {
            sender.sendMessage(plugin.getErrorMessageHead()+"你必须手持任意物品");
            return true;
        }
        ChestGui gui;
        try
        {
            gui=ConstructorAccessor.getDeclaredConstructor(ChestGui.Builder.class,Plugin.class)
                                   .invoke(plugin)
                                   .setSize(2)
                                   .title("物品编辑器")
                                   .open(player);
        }
        catch (Throwable e)
        {
            throw new RuntimeException(e);
        }
        ItemMeta itemMeta=itemStack.getItemMeta();
        assert itemMeta!=null;
        PlayerAnvilInput.Builder input=PlayerAnvilInput.builder();
        input.title("输入新名称").onComplete((playerAnvilInput,s)->
                                             {
                                                 itemMeta.setDisplayName(s);
                                                 return PlayerAnvilInput.Response.close();
                                             });
        GuiItem name=new GuiItem().setItem(new ItemStack(Material.GOLD_BLOCK))
                                  .setOnClick((gui1,clickType)->GuiItem.Response.input(input.text(itemMeta.getDisplayName())));
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender,@NotNull Command command,
                                                @NotNull String label,@NotNull String[] args)
    {
        return null;
    }
}
