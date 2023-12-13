package dev.canable.insanepets.game.pets;

import dev.canable.insanepets.InsanePetsPlugin;
import dev.canable.insanepets.utils.ItemBuilder;
import dev.canable.insanepets.utils.TextHelper;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ProjectaGuardPet extends Pet {
    public ProjectaGuardPet(PetType petType) {
        super(PetType.PROJECTAGAURD, ItemBuilder.skullTexturePrefix+"MjExMTY5M2UxODhkNjZlZGFjMzcxOGJmYTA4MDY0ZTA5ZWNlYmJhNzEwMGI2NzRiZjk5ODhiNGM3MTRlZTM1ZCJ9fX0=");
    }

    @Override
    public void applyEffects(Player player) {
        InsanePetsPlugin.getInstance().getPetManager().projectaGuardOwnerSet.add(player.getUniqueId());
        ArmorStand armorStand = player.getWorld().spawn(player.getLocation(),ArmorStand.class);
        armorStand.setSmall(true);
        armorStand.setBasePlate(false);
        armorStand.setHelmet(new ItemBuilder(Material.SKULL_ITEM,1,(short) 3).createSkull(this.getSkullTextures()).build());
        armorStand.setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        armorStand.setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        armorStand.setBoots(new ItemStack(Material.IRON_BOOTS));
        armorStand.setGravity(false);
        InsanePetsPlugin.getInstance().getPetManager().playerArmorstandMap.put(player.getUniqueId(),armorStand);
        player.sendMessage(TextHelper.format("&e&lPets &8┃ &7you have chosen the pet "+this.getPetType().getDisplayname()));
        player.sendMessage(TextHelper.format("&e&lPets &8┃ &e\"Now i will protect you from all projectiles!\""));
        player.playSound(player.getLocation(), Sound.LEVEL_UP,1,1);
    }

    @Override
    public void removeEffects(Player player) {
        InsanePetsPlugin.getInstance().getPetManager().projectaGuardOwnerSet.remove(player.getUniqueId());
        if(!InsanePetsPlugin.getInstance().getPetManager().playerArmorstandMap.containsKey(player.getUniqueId())) return;
        InsanePetsPlugin.getInstance().getPetManager().playerArmorstandMap.get(player.getUniqueId()).remove();
        InsanePetsPlugin.getInstance().getPetManager().playerArmorstandMap.remove(player.getUniqueId());
    }
}
