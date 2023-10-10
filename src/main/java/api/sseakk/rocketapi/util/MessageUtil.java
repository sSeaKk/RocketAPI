package api.sseakk.rocketapi.util;

import api.sseakk.rocketapi.RocketAPI;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MessageUtil {
    public static void infoMessage(JavaPlugin plugin, String message){
        plugin.getServer().getLogger().info("["+ plugin.getName() + "] " + message);
    }

    public static void playerMessage(Player player, String message){
        player.sendMessage(TextUtil.colorText(message));
    }

    public static void playerMessage(Player player, TextComponent... message){
        player.spigot().sendMessage(message);
    }

    public static void broadcastMessage(String message){
        Bukkit.getServer().broadcastMessage(message);
    }

    public static void broadcastMessage(TextComponent message){
        Bukkit.getServer().spigot().broadcast(message);
    }

    public static void warningMessage(JavaPlugin plugin, String message){
        plugin.getServer().getLogger().warning("[" + plugin.getName() + "] " + message);
    }
    public static void errorMessage(JavaPlugin plugin, String message){
        plugin.getServer().getLogger().severe("[" + plugin.getName() + "] " + message);
    }
    public static void delayedMessage(Player player, int secs, String message) {
        Bukkit.getServer().getScheduler().runTaskLater(RocketAPI.getInstance(), new Runnable() {
            public void run() {
                playerMessage(player, message);
            }
        }, (secs * 20L));
    }
    public static void delayedMessage(Player player, int secs, TextComponent... message) {
        Bukkit.getServer().getScheduler().runTaskLater(RocketAPI.getInstance(), new Runnable() {
            public void run() {
                playerMessage(player, message);
            }
        }, (secs * 20L));
    }
    public static void delayedMessage(Player player, Long mili, String message) {
        Bukkit.getServer().getScheduler().runTaskLater(RocketAPI.getInstance(), new Runnable() {
            public void run() {
                playerMessage(player, message);
            }
        }, mili);
    }
    public static void delayedMessage(Player player, Long mili, TextComponent... message) {
        Bukkit.getServer().getScheduler().runTaskLater(RocketAPI.getInstance(), new Runnable() {
            public void run() {
                playerMessage(player, message);
            }
        }, mili);
    }
}