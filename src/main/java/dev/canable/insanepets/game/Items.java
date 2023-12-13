package dev.canable.insanepets.game;

import dev.canable.insanepets.utils.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Items {
    private @Getter ItemStack blackGlass = new ItemBuilder(Material.STAINED_GLASS_PANE,1,7).setDisplayname(" ").build();
}
