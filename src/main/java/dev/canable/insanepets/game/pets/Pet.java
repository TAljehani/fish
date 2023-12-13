package dev.canable.insanepets.game.pets;

import dev.canable.insanepets.InsanePetsPlugin;
import dev.canable.insanepets.utils.TextHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

@Getter
@Setter
@AllArgsConstructor
public abstract class Pet {
    private PetType petType;
    private String skullTextures;

    public abstract void applyEffects(Player player);
    public abstract void removeEffects(Player player);

    public static void choosePet(Player player, String displayname){
        PetImpl.getFleetfootPet().removeEffects(player);
        PetImpl.getMightyValorPet().removeEffects(player);
        PetImpl.getProjectaGuardPet().removeEffects(player);
        if(displayname.equals(TextHelper.format(PetImpl.getFleetfootPet().getPetType().getDisplayname()))){
            PetImpl.getFleetfootPet().applyEffects(player);
        }else if(displayname.equals(TextHelper.format(PetImpl.getMightyValorPet().getPetType().getDisplayname()))){
            PetImpl.getMightyValorPet().applyEffects(player);
        }else if(displayname.equals(TextHelper.format(PetImpl.getProjectaGuardPet().getPetType().getDisplayname()))){
            PetImpl.getProjectaGuardPet().applyEffects(player);
        }
    }

}
