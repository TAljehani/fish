package dev.canable.insanepets.game.pets;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PetType {
    PROJECTAGAURD("&e&lProjectaGuard"), MIGHTYVALOR("&4&LMighty Valor"),FLEETFOOT("&b&lFleetfoot");

    private String displayname;
}
