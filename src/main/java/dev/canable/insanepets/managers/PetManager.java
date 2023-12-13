package dev.canable.insanepets.managers;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class PetManager {
    public HashSet<UUID> projectaGuardOwnerSet = Sets.newHashSet();
    public HashSet<UUID> mightyValorOwnerSet = Sets.newHashSet();
    public HashSet<UUID> fleetfootOwnerSet = Sets.newHashSet();
    public HashMap<UUID, ArmorStand> playerArmorstandMap = Maps.newHashMap();
}
