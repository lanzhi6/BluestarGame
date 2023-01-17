package me.lanzhi.bluestargame.listener;

import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargame.bluestarapi.config.YamlFile;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.function.Consumer;

public final class FixListener implements Listener
{
    private final BluestarGamePlugin plugin;

    public FixListener(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
    }

    @EventHandler(priority=EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player=event.getPlayer();
        player.setHealthScaled(false);
        YamlFile playerData=YamlFile.loadYamlFile(new File(plugin.getPlayerData(),
                                                           event.getPlayer().getUniqueId()+".yml"));
        double health=playerData.getDouble("health");
        double maxHealth=playerData.getDouble("maxhealth");
        if (maxHealth!=0)
        {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
        }
        if (health!=0)
        {
            player.setHealth(health);
        }
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                player.setHealthScaled(false);
                if (maxHealth!=0)
                {
                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
                }
            }
        }.runTaskLater(plugin,70);

        addRecipe(player);
    }

    private void addRecipe(Player player)
    {
        player.getDiscoveredRecipes().forEach(player::undiscoverRecipe);
        Bukkit.getScheduler().runTaskAsynchronously(plugin,()->
        {
            Consumer<Recipe> consumer=recipe->
            {
                if (recipe instanceof ShapedRecipe)
                {
                    addRecipe(player,((ShapedRecipe) recipe).getKey());
                }
                else if (recipe instanceof ShapelessRecipe)
                {
                    addRecipe(player,((ShapelessRecipe) recipe).getKey());
                }
            };
            Bukkit.recipeIterator().forEachRemaining(consumer);
        });
    }

    private void addRecipe(Player player,NamespacedKey recipe)
    {
        Bukkit.getScheduler().runTask(plugin,()->player.discoverRecipe(recipe));
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        YamlFile playerData=YamlFile.loadYamlFile(new File(plugin.getPlayerData(),
                                                           event.getPlayer().getUniqueId()+".yml"));
        double maxhealth=event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
        if (plugin.getBluestarGameManager().getRandomEventManger().oneHealth())
        {
            maxhealth=plugin.getBluestarGameManager()
                            .getRandomEventManger().oneHealth_playerHealth.get(event.getPlayer());
        }
        playerData.set("health",Math.min(event.getPlayer().getHealth(),maxhealth));
        playerData.set("maxhealth",maxhealth);
        playerData.save();
        plugin.getPlayerMap().set(event.getPlayer().getName(),event.getPlayer().getUniqueId()+"");
    }
}