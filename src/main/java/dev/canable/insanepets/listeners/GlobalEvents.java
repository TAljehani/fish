package dev.canable.insanepets.listeners;

import dev.canable.insanepets.game.pets.Pet;
import dev.canable.insanepets.utils.TextHelper;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GlobalEvents implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event){
        ItemStack item = event.getCurrentItem();
        Player player = (Player) event.getView().getPlayer();
        if(item==null) return;
        if(event.getClickedInventory().getTitle().equals(TextHelper.format("&e&lPets"))){
            if(item.getType()!= Material.STAINED_GLASS_PANE){
                Pet.choosePet(player,item.getItemMeta().getDisplayName());
                player.closeInventory();
            }
        }
    }

}
