package api.sseakk.rocketapi.util;

import api.sseakk.rocketapi.RocketAPI;
import api.sseakk.rocketapi.database.Database;
import api.sseakk.rocketapi.database.Table;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class MessageUtil {
    private JavaPlugin plugin;
    private Logger rocketLog;

    public MessageUtil(JavaPlugin plugin){
        this.plugin = plugin;
        this.rocketLog = RocketAPI.getInstance().getLogger();
    }

    public void infoMessage(String message){
        this.plugin.getServer().getLogger().info("["+ this.plugin.getName() + "] " + message);
    }

    public void playerMessage(Player player, String message){
        player.sendMessage(TextUtil.colorText(message));
    }

    public void playerMessage(Player player, TextComponent... message){
        player.spigot().sendMessage(message);
    }

    public void broadcastMessage(String message){
        Bukkit.getServer().broadcastMessage(message);
    }

    public void broadcastMessage(TextComponent message){
        Bukkit.getServer().spigot().broadcast(message);
    }

    public void warningMessage(JavaPlugin plugin, String message){
        plugin.getServer().getLogger().warning("[" + plugin.getName() + "] " + message);
    }
    public void errorMessage(JavaPlugin plugin, String message){
        plugin.getServer().getLogger().severe("[" + plugin.getName() + "] " + message);
    }
    public void delayedMessage(Player player, int secs, String message) {
        Bukkit.getServer().getScheduler().runTaskLater(RocketAPI.getInstance(), new Runnable() {
            public void run() {
                playerMessage(player, message);
            }
        }, (secs * 20L));
    }
    public void delayedMessage(Player player, int secs, TextComponent... message) {
        Bukkit.getServer().getScheduler().runTaskLater(RocketAPI.getInstance(), new Runnable() {
            public void run() {
                playerMessage(player, message);
            }
        }, (secs * 20L));
    }
    public void delayedMessage(Player player, Long mili, String message) {
        Bukkit.getServer().getScheduler().runTaskLater(RocketAPI.getInstance(), new Runnable() {
            public void run() {
                playerMessage(player, message);
            }
        }, mili);
    }
    public void delayedMessage(Player player, Long mili, TextComponent... message) {
        Bukkit.getServer().getScheduler().runTaskLater(RocketAPI.getInstance(), new Runnable() {
            public void run() {
                playerMessage(player, message);
            }
        }, mili);
    }

    public void databaseLog(Database database, String message){
        this.rocketLog.info(TextUtil.databasePrefix(this.plugin, database) + message);
    }

    public void tableLog(Table table, String message){
        this.rocketLog.info(TextUtil.tablePrefix(this.plugin, table) + message);
    }

    public void databaseWarning(Database database, String message){
        this.rocketLog.warning(TextUtil.databasePrefix(this.plugin, database) + message);
    }

    public void databaseConnectionError(String dbName){
        this.rocketLog.warning(TextUtil.databasePrefix(this.plugin, dbName) + ": Database connection failed. Check the connection data again.");
    }
}