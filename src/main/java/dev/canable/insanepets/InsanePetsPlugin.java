package dev.canable.insanepets;

import co.aikar.commands.BukkitCommandManager;
import dev.canable.insanepets.commands.PetCommand;
import dev.canable.insanepets.game.Items;
import dev.canable.insanepets.game.pets.PetImpl;
import dev.canable.insanepets.listeners.GlobalEvents;
import dev.canable.insanepets.managers.PetManager;
import dev.canable.insanepets.tasks.TeleportTask;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class InsanePetsPlugin extends JavaPlugin {
    private static @Getter InsanePetsPlugin instance;
    private @Getter PetManager petManager = new PetManager();
    private @Getter Items items = new Items();
    private @Getter BukkitCommandManager bukkitCommandManager;

    @Override
    public void onEnable() {
        instance=this;
        bukkitCommandManager = new BukkitCommandManager(this);
        bukkitCommandManager.registerCommand(new PetCommand());
        Bukkit.getPluginManager().registerEvents(new GlobalEvents(),this);
        new TeleportTask().runTaskTimer(this,5*20,1);
    }

    @Override
    public void onDisable() {
        petManager.playerArmorstandMap.forEach((uuid, armorStand) -> armorStand.remove());
    }
}
