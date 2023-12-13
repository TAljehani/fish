package dev.canable.insanepets.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import dev.canable.insanepets.InsanePetsPlugin;
import dev.canable.insanepets.game.pets.PetImpl;
import dev.canable.insanepets.utils.ItemBuilder;
import dev.canable.insanepets.utils.TextHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

@CommandAlias("pet")
public class PetCommand extends BaseCommand {
    @Default
    public void onDefaultExecution(Player player){
        Inventory inventory = Bukkit.createInventory(null,9, TextHelper.format("&e&lPets"));
        inventory.setItem(0, InsanePetsPlugin.getInstance().getItems().getBlackGlass());
        inventory.setItem(1, InsanePetsPlugin.getInstance().getItems().getBlackGlass());
        inventory.setItem(3, InsanePetsPlugin.getInstance().getItems().getBlackGlass());
        inventory.setItem(5, InsanePetsPlugin.getInstance().getItems().getBlackGlass());
        inventory.setItem(7, InsanePetsPlugin.getInstance().getItems().getBlackGlass());
        inventory.setItem(8, InsanePetsPlugin.getInstance().getItems().getBlackGlass());
        inventory.setItem(2, new ItemBuilder(Material.SKULL_ITEM,1,(short) 3)
                .createSkull(PetImpl.getFleetfootPet().getSkullTextures())
                .setDisplayname(PetImpl.getFleetfootPet().getPetType().getDisplayname())
                .addUnsafeEnchant(Enchantment.DURABILITY,1)
                .hideEnchants().build());
        inventory.setItem(4, new ItemBuilder(Material.SKULL_ITEM,1,(short) 3)
                .createSkull(PetImpl.getProjectaGuardPet().getSkullTextures())
                .setDisplayname(PetImpl.getProjectaGuardPet().getPetType().getDisplayname())
                .addUnsafeEnchant(Enchantment.DURABILITY,1)
                .hideEnchants().build());
        inventory.setItem(6, new ItemBuilder(Material.SKULL_ITEM,1,(short) 3)
                .createSkull(PetImpl.getMightyValorPet().getSkullTextures())
                .setDisplayname(PetImpl.getMightyValorPet().getPetType().getDisplayname())
                .addUnsafeEnchant(Enchantment.DURABILITY,1)
                .hideEnchants().build());
        player.openInventory(inventory);
        player.playSound(player.getLocation(),Sound.NOTE_PLING,1,1);
    }
}
