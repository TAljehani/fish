package dev.canable.insanepets.utils;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

import java.lang.reflect.Field;
import java.util.*;

@SuppressWarnings({"FieldMayBeFinal", "deprecation", "unused"})
public class ItemBuilder {

    private ItemMeta itemMeta;
    private ItemStack itemStack;
    public static String skullTexturePrefix = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUv";

    public ItemBuilder(Material mat, int size, int theByte) {
        itemStack = new ItemStack(mat, size, (byte) theByte);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder setDisplayname(String s) {
        itemMeta.setDisplayName(s.replace("&", "§"));
        return this;
    }

    public ItemBuilder setLore(String... s) {
        List<String> lores = Lists.newArrayList();
        for(String lore : s){
            lores.add(lore.replace("&","§"));
        }
        itemMeta.setLore(lores);
        return this;
    }


    public ItemBuilder addItemFlags(ItemFlag... s) {
        itemMeta.addItemFlags(s);
        return this;
    }

    public ItemBuilder hideEnchants() {
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean flag) {
        itemMeta.spigot().setUnbreakable(flag);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchant, int level) {
        itemMeta.addEnchant(enchant, level, true);
        return this;
    }

    public ItemBuilder setSkullOwner(String name) {
        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
        Objects.requireNonNull(meta).setOwner(name);
        itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addUnsafeEnchant(Enchantment enchant, int level) {
        itemStack.addUnsafeEnchantment(enchant, level);
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack setArmorColor(ItemStack itemStack, Color color) {
        LeatherArmorMeta meta = (LeatherArmorMeta) itemStack.getItemMeta();
        meta.setColor(color);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemBuilder createSkull(String textures){
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(),null);
        gameProfile.getProperties().put("textures", new Property("textures",textures));
        try{
            Field profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta,gameProfile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        itemStack.setItemMeta(skullMeta);
        return this;
    }

    public static Object serialize(ItemStack itemStack){
        return itemStack.serialize();
    }
    public static String getEnchants(ItemStack i) {
        List<String> e = new ArrayList<>();
        Map<Enchantment, Integer> en = i.getEnchantments();
        for (Enchantment t : en.keySet()) {
            e.add(t.getName() + "-" + en.get(t));
        }
        return StringUtils.join(e, "~");
    }

    public static String toString(ItemStack i) {
        String[] parts = new String[6];
        parts[0] = i.getType().name();
        parts[1] = Integer.toString(i.getAmount());
        parts[2] = String.valueOf(i.getDurability());
        parts[3] = i.getItemMeta().getDisplayName();
        parts[4] = String.valueOf(i.getData().getData());
        parts[5] = getEnchants(i);
        return StringUtils.join(parts, "&");
    }


    public static ItemStack fromString(String p) {
        String[] a = p.split("&");
        ItemStack i = new ItemStack(Material.getMaterial(a[0]), Integer.parseInt(a[1]));
        i.setDurability((short) Integer.parseInt(a[2]));
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(a[3]);
        i.setItemMeta(meta);
        MaterialData data = i.getData();
        data.setData((byte) Integer.parseInt(a[4]));
        i.setData(data);
        if (a.length > 5) {
            String[] parts = a[5].split("~");
            for (String s : parts) {
                String label = s.split("-")[0];
                String amplifier = s.split("-")[1];
                Enchantment type = Enchantment.getByName(label);
                if (type == null)
                    continue;
                int f;
                try {
                    f = Integer.parseInt(amplifier);
                } catch (Exception ex) {
                    continue;
                }
                i.addEnchantment(type, f);
            }
        }
        return i;
    }

}
