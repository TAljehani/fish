package dev.canable.insanepets.utils;


import lombok.Getter;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
public class TextHelper {
    private final static @Getter char barBox = '▉';
    public static String[] format(String... strings){
        return Arrays.stream(strings).map(TextHelper::format).toArray(String[]::new);
    }
    public static String format(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static List<String> format(List<String> strings){
        return strings.stream().map(TextHelper::format).collect(Collectors.toList());
    }
    public static String middle(String string){
        StringBuilder message = new StringBuilder();
        for (int i = 0; i <(80-string.length())/2; i++) {
            message.append(" ");
        }
        return message.append(format(string)).toString();
    }
    public static void sendActionBar(Player player, String text){
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + format(text) + "\"}"), (byte) 2);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
    }
    public static String barOf(int filled, int max){
        StringBuilder string = new StringBuilder("&a");
        for (int i = 0; i < filled; i++) {
            string.append(getBarBox());
        }
        string.append("&c");
        for (int i = 0; i < max-filled; i++) {
            string.append(getBarBox());
        }
        return TextHelper.format(string.toString());
//        return "&a" + String.valueOf(getBarBox()).repeat(Math.max(0, filled)) +
//                "&c" +
//                String.valueOf(getBarBox()).repeat(Math.max(0, max - filled));
        // it's java 8, so I can't use String.repeat
    }

}
