package dev.canable.insanepets.tasks;

import dev.canable.insanepets.InsanePetsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;


import java.util.Map;
import java.util.UUID;

public class TeleportTask extends BukkitRunnable {
    @Override
    public void run() {
        for (Map.Entry<UUID, ArmorStand> entry : InsanePetsPlugin.getInstance().getPetManager().playerArmorstandMap.entrySet()) {
            Player player = Bukkit.getPlayer(entry.getKey());
            ArmorStand armorStand = entry.getValue();
            double time = (double) System.currentTimeMillis() / 1000.0;
            double yOffset = Math.sin(time) * 0.25;
            Location shoulderLocation = getShoulderLocation(player).add(0, yOffset, 0);
            armorStand.teleport(shoulderLocation);
        }
    }

    public static Location getShoulderLocation(Player player) {
        Location playerLocation = player.getLocation().add(0,1.5,0);
        playerLocation.setYaw(90);
        Vector vector = playerLocation.getDirection();
        playerLocation.add(vector.multiply(0.5));
        playerLocation.setYaw(player.getLocation().getYaw());
        vector = playerLocation.getDirection();
        playerLocation.add(vector.multiply(-0.2));
        return playerLocation;
    }


}
