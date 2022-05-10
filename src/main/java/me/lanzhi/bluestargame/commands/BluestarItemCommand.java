package me.lanzhi.bluestargame.commands;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.lanzhi.bluestarapi.Api.RGBChat;
import me.lanzhi.bluestargame.BluestarGamePlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static me.lanzhi.bluestargame.listener.effectListener.effectEventType;

import java.util.*;

public final class BluestarItemCommand implements CommandExecutor, TabExecutor
{
    private final List<String> entityType=new ArrayList<>();
    private final List<String> potionEffectType=new ArrayList<>();
    private final Map<String,effectEventType> effectEvents=new HashMap<>();
    private final BluestarGamePlugin plugin;
    private ConfigurationSection effectLang;
    private ConfigurationSection entityLang;

    public BluestarItemCommand(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
        effectLang=plugin.getLang().getConfigurationSection("effect");
        entityLang=plugin.getLang().getConfigurationSection("entity");
        if (effectLang==null)
        {
            effectLang=plugin.getLang().createSection("effect");
        }
        if (entityLang==null)
        {
            entityLang=plugin.getLang().createSection("entity");
        }
        for (EntityType i: EntityType.values())
        {
            if (i.getName()==null)
            {
                continue;
            }
            if (entityLang.getString(i.getName(),"").isEmpty())
            {
                entityLang.set(i.getName(),i.getName());
            }
            entityType.add(entityLang.getString(i.getName(),i.getName()));
        }
        for (PotionEffectType i: PotionEffectType.values())
        {
            if (effectLang.getString(i.getName().toLowerCase(),"").isEmpty())
            {
                effectLang.set(i.getName().toLowerCase(),i.getName().toLowerCase());
            }
            potionEffectType.add(effectLang.getString(i.getName().toLowerCase(),i.getName().toLowerCase()));
        }
        effectEvents.put("非生物导致受伤给予自己",effectEventType.DamageToUs);
        effectEvents.put("被击中给予自己",effectEventType.DamageByEntityToUs);
        effectEvents.put("击中生物时给予自己",effectEventType.DamageEntityToUs);
        effectEvents.put("击中生物时给予对方",effectEventType.DamageEntityToIt);
        effectEvents.put("被生物击中给予对方",effectEventType.DamageByEntityToIt);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,@NotNull String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.RED+"此命令仅允许玩家输入!");
            return false;
        }
        if (args.length<1)
        {
            sender.sendMessage(ChatColor.RED+"格式错误!");
            return false;
        }
        Player player=(Player) sender;
        switch (args[0])
        {
            case "watersponge":
            {
                int cnt=1;
                if (args.length>1)
                {
                    try
                    {
                        cnt=Integer.parseInt(args[1]);
                    }
                    catch (NumberFormatException e)
                    {
                    }
                }
                ItemStack itemStack=plugin.getBluestarGameManager().getSuperSpongeManager().getWaterSponge().getItem().clone();
                itemStack.setAmount(cnt);
                player.getInventory().addItem(itemStack);
                player.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已为给予您 \"超级海绵\" ×"+cnt);
                return true;
            }
            case "lavasponge":
            {
                int cnt=1;
                if (args.length>1)
                {
                    try
                    {
                        cnt=Integer.parseInt(args[1]);
                    }
                    catch (NumberFormatException e)
                    {
                    }
                }
                ItemStack itemStack=plugin.getBluestarGameManager().getSuperSpongeManager().getlavaSponge().getItem().clone();
                itemStack.setAmount(cnt);
                player.getInventory().addItem(itemStack);
                player.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已为给予您 \"岩浆海绵\" ×"+cnt);
                return true;
            }
            case "usedwatersponge":
            {
                int cnt=1;
                if (args.length>1)
                {
                    try
                    {
                        cnt=Integer.parseInt(args[1]);
                    }
                    catch (NumberFormatException e)
                    {
                    }
                }
                ItemStack itemStack=plugin.getBluestarGameManager().getSuperSpongeManager().getUsedWaterSponge().getItem().clone();
                itemStack.setAmount(cnt);
                player.getInventory().addItem(setLore(itemStack));
                player.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已为给予您 \"湿超级海绵\" ×"+cnt);
                return true;
            }
            case "usedlavasponge":
            {
                int cnt=1;
                if (args.length>1)
                {
                    try
                    {
                        cnt=Integer.parseInt(args[1]);
                    }
                    catch (NumberFormatException e)
                    {
                    }
                }
                ItemStack itemStack=plugin.getBluestarGameManager().getSuperSpongeManager().getUsedLavaSponge().getItem().clone();
                itemStack.setAmount(cnt);
                player.getInventory().addItem(itemStack);
                player.sendMessage(plugin.getMessageHead()+ChatColor.GREEN+"已为给予您 \"湿岩浆海绵\" ×"+cnt);
                return true;
            }
            case "sword":
            {
                if (player.getInventory().getItemInMainHand().getType().isAir())
                {
                    player.sendMessage(plugin.getMessageHead()+ChatColor.RED+"请手持任意物品");
                    return false;
                }
                NBTItem item=new NBTItem(player.getInventory().getItemInMainHand());
                item.addCompound("BluestarGame").setBoolean("sword",true);
                player.getInventory().setItemInMainHand(setLore(item.getItem()));
                return true;
            }
            case "bow":
            {
                if (args.length<2)
                {
                    sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"请输入生物类型");
                }
                EntityType entityType=null;
                for (String i: entityLang.getKeys(false))
                {
                    if (args[1].equals(entityLang.getString(i)))
                    {
                        entityType=EntityType.fromName(i);
                        break;
                    }
                }
                if (entityType==null)
                {
                    sender.sendMessage(plugin.getErrorMessageHead()+"未知生物类型");
                    return true;
                }
                int cnt=1;
                double v=1;
                if (args.length>=3)
                {
                    try
                    {
                        v=Double.parseDouble(args[2]);
                    }
                    catch (NumberFormatException e)
                    {
                        sender.sendMessage(plugin.getErrorMessageHead()+"错误,力量不合法");
                        return true;
                    }
                    if (v<=0D)
                    {
                        sender.sendMessage(plugin.getErrorMessageHead()+"错误,力量不合法");
                        return true;
                    }
                    if (args.length>=4)
                    {
                        try
                        {
                            cnt=Integer.parseInt(args[3]);
                        }
                        catch (NumberFormatException e)
                        {
                            sender.sendMessage(plugin.getErrorMessageHead()+"错误,数量不合法");
                            return true;
                        }
                        if (cnt<=0)
                        {
                            sender.sendMessage(plugin.getErrorMessageHead()+"错误,数量不合法");
                            return true;
                        }
                    }
                }
                NBTItem bow=new NBTItem(new ItemStack(Material.BOW,cnt));
                NBTCompound bluestarGame=bow.addCompound("BluestarGame");
                bluestarGame.setString("arrow",entityType.getName());
                bluestarGame.setDouble("arrowSpeed",v);
                player.getInventory().addItem(setLore(bow.getItem()));
                return true;
            }
            case "effect":
            {
                if (args.length<2)
                {
                    sender.sendMessage(plugin.getErrorMessageHead()+"错误,请输入效果");
                    return true;
                }
                if (player.getInventory().getItemInMainHand().getType().isAir())
                {
                    sender.sendMessage(plugin.getErrorMessageHead()+"请先手持任意物品");
                    return true;
                }
                PotionEffectType type=null;
                for (String i: effectLang.getKeys(false))
                {
                    if (args[1].equals(effectLang.getString(i)))
                    {
                        type=PotionEffectType.getByName(i);
                        break;
                    }
                }
                if (type==null)
                {
                    sender.sendMessage(plugin.getErrorMessageHead()+"错误,未知效果");
                    return true;
                }
                int l;
                try
                {
                    l=Integer.parseInt(args[2]);
                }
                catch (Throwable e)
                {
                    l=1;
                }
                NBTItem item=new NBTItem(player.getInventory().getItemInMainHand());
                if (l==0)
                {
                    item.addCompound("BluestarGame").addCompound("effect").removeKey(type.getName());
                }
                else
                {
                    item.addCompound("BluestarGame").addCompound("effect").setInteger(type.getName(),l);
                }
                player.getInventory().setItemInMainHand(setLore(item.getItem()));
                return true;
            }
            case "effectEvent":
            {
                if (args.length<2)
                {
                    sender.sendMessage(plugin.getErrorMessageHead()+"错误,请输入效果");
                    return true;
                }
                if (player.getInventory().getItemInMainHand().getType().isAir())
                {
                    sender.sendMessage(plugin.getErrorMessageHead()+"请先手持任意物品");
                    return true;
                }
                PotionEffectType type=null;
                for (String i: effectLang.getKeys(false))
                {
                    if (args[1].equals(effectLang.getString(i)))
                    {
                        type=PotionEffectType.getByName(i);
                        break;
                    }
                }
                if (type==null)
                {
                    sender.sendMessage(plugin.getErrorMessageHead()+"错误,未知效果");
                    return true;
                }
                int l;
                try
                {
                    l=Integer.parseInt(args[2]);
                }
                catch (Throwable e)
                {
                    l=1;
                }
                effectEventType mod;
                try
                {
                    mod=effectEvents.get(args[3]);
                }
                catch (Throwable e)
                {
                    mod=null;
                }
                if (mod==null)
                {
                    sender.sendMessage(plugin.getErrorMessageHead()+"触发条件错误!");
                    return true;
                }
                int time;
                try
                {
                    time=Integer.parseInt(args[4]);
                    if (time==0)
                    {
                        throw new NullPointerException();
                    }
                }
                catch (Throwable e)
                {
                    time=5;
                }
                NBTItem item=new NBTItem(player.getInventory().getItemInMainHand());
                if (l==0)
                {
                    item.addCompound("BluestarGame").addCompound(mod.name()).removeKey(type.getName());
                }
                else
                {
                    NBTCompound nbtCompound=item.addCompound("BluestarGame").addCompound(mod.name()).addCompound(type.getName());
                    nbtCompound.setInteger("s",l);
                    nbtCompound.setInteger("time",time*20);
                }
                player.getInventory().setItemInMainHand(setLore(item.getItem()));
                return true;
            }
            case "setname":
            {
                ItemStack itemStack=player.getInventory().getItemInMainHand();
                if (itemStack==null||itemStack.getType().isAir())
                {
                    player.sendMessage(plugin.getErrorMessageHead()+"请手持任意物品");
                    return true;
                }
                if (args.length<2)
                {
                    player.sendMessage(plugin.getErrorMessageHead()+"请输入名字");
                    return true;
                }
                StringBuilder builder=new StringBuilder(args[1]);
                for (int i=2;i<args.length;i++)
                {
                    builder.append(' ');
                    builder.append(args[i]);
                }
                ItemMeta itemMeta=itemStack.getItemMeta();
                itemMeta.setDisplayName(RGBChat.setColor(builder.toString().replaceAll("\"\""," ")));
                itemStack.setItemMeta(itemMeta);
                player.getInventory().setItemInMainHand(itemStack);
                return true;
            }
            default:
            {
                player.sendMessage(plugin.getErrorMessageHead()+"格式错误");
                return true;
            }
        }
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,@NotNull String[] args)
    {
        if (args.length==1)
        {
            return Arrays.asList("watersponge","lavasponge","usedwatersponge","usedlavasponge","effect","effectEvent","sword","bow","setname");
        }
        if ("bow".equals(args[0]))
        {
            if (args.length==2)
            {
                return entityType;
            }
            if (args.length==3)
            {
                return Arrays.asList("力量","发射速度=原速度*力量");
            }
            if (args.length==4)
            {
                return Collections.singletonList("数量");
            }
            return Collections.emptyList();
        }
        if (args[0].startsWith("effect"))
        {
            if (args.length==2)
            {
                return potionEffectType;
            }
            if (args.length==3)
            {
                return Collections.singletonList("等级");
            }
            if (args.length==4&&"effectEvent".equals(args[0]))
            {
                return new ArrayList<>(effectEvents.keySet());
            }
            if (args.length==5&&"effectEvent".equals(args[0]))
            {
                return Collections.singletonList("时间");
            }
            return Collections.emptyList();
        }
        if ("setname".equals(args[0]))
        {
            return Collections.singletonList("名字");
        }
        if (args.length==2)
        {
            return Collections.singletonList("数量");
        }
        return Collections.emptyList();
    }

    private ItemStack setLore(ItemStack itemStack)
    {
        NBTCompound bluestarGame=new NBTItem(itemStack).addCompound("BluestarGame");
        ItemMeta meta=itemStack.getItemMeta();
        List<String> lore=new ArrayList<>();
        if (bluestarGame==null)
        {
            return itemStack;
        }
        String c=RGBChat.toColorCode("888888");
        if (bluestarGame.getCompound("effect")!=null)
        {
            lore.add(c+"<===--- "+RGBChat.toColorCode("abcdef")+"手持或佩戴时效果"+c+" ---===>");
            NBTCompound compound=bluestarGame.getCompound("effect");
            for (String i: compound.getKeys())
            {
                lore.add(ChatColor.GRAY+effectLang.getString(i.toLowerCase(),i.toLowerCase())+" "+compound.getInteger(i));
            }
        }
        for (effectEventType i:effectEventType.values())
        {
            if (bluestarGame.getCompound(i.name())!=null)
            {
                lore.add(c+"<===--- "+i.getName()+c+" ---===>");
                NBTCompound compound=bluestarGame.getCompound(i.name());
                for (String j: compound.getKeys())
                {
                    lore.add(ChatColor.GRAY+effectLang.getString(j.toLowerCase(),j.toLowerCase())+" "+compound.addCompound(j).getInteger("s")+"  时间: "+(compound.addCompound(j).getInteger("time")/20));
                }
            }
        }
        if (bluestarGame.getString("arrow")!=null&&!bluestarGame.getString("arrow").isEmpty())
        {
            lore.add(ChatColor.AQUA+"箭: "+entityLang.getString(bluestarGame.getString("arrow")));
        }
        if (bluestarGame.getDouble("arrowSpeed")!=null&&bluestarGame.getInteger("arrowSpeed")!=0)
        {
            lore.add(ChatColor.LIGHT_PURPLE+"力量: "+bluestarGame.getDouble("arrowSpeed"));
        }
        if (bluestarGame.getInteger("waterSponge")==1)
        {
            lore.add(ChatColor.GOLD+"超级海绵");
        }
        if (bluestarGame.getInteger("waterSponge")==-1)
        {
            lore.add(ChatColor.GOLD+"湿超级海绵");
        }
        if (bluestarGame.getInteger("lavaSponge")==1)
        {
            lore.add(ChatColor.GOLD+"岩浆海绵");
        }
        if (bluestarGame.getInteger("lavaSponge")==-1)
        {
            lore.add(ChatColor.GOLD+"湿岩浆海绵");
        }
        if (bluestarGame.getBoolean("sword"))
        {
            lore.add(ChatColor.GOLD+"无敌OP剑");
        }
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
