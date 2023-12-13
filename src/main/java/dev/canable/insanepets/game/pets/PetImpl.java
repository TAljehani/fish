package dev.canable.insanepets.game.pets;

import lombok.Getter;

public class PetImpl {
    private static @Getter FleetfootPet fleetfootPet = new FleetfootPet(PetType.FLEETFOOT);
    private static @Getter MightyValorPet mightyValorPet = new MightyValorPet(PetType.MIGHTYVALOR);
    private static @Getter ProjectaGuardPet projectaGuardPet  = new ProjectaGuardPet(PetType.PROJECTAGAURD);
}
