package api.sseakk.rocketapi.util;

import api.sseakk.rocketapi.RocketAPI;
import api.sseakk.rocketapi.database.Database;
import api.sseakk.rocketapi.database.Table;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * MessageUtil class utility if for sending messages to players and console.
 * @author sSeaKk
 * @version 1.6
 * @since RocketAPI-1.0
 */
public class MessageUtil {
    private final JavaPlugin plugin;
    private final Logger rocketLog;

    /**
     * Creates a new MessageUtil instance.
     * @since RocketAPI-1.0
     * @param plugin plugin instance
     */
    public MessageUtil(JavaPlugin plugin){
        this.plugin = plugin;
        this.rocketLog = RocketAPI.getInstance().getLogger();
    }

    /**
     * Sends an info message to the console.
     * @since RocketAPI-1.0
     * @param message message to send
     */
    public void infoMessage(String message){
        this.plugin.getServer().getLogger().info("["+ this.plugin.getName() + "] " + message);
    }

    /**
     * Sends a message to the player.
     * @since RocketAPI-1.0
     * @param player player to send the message
     * @param message message to send
     */
    public void playerMessage(Player player, String message){
        player.sendMessage(TextUtil.colorText(message));
    }

    /**
     * Sends a message to the player.
     * @since RocketAPI-1.0
     * @param player player to send the message
     * @param message message to send
     */
    public void playerMessage(Player player, TextComponent... message){
        player.spigot().sendMessage(message);
    }

    /**
     * Broadcasts a message to all players.
     * @since RocketAPI-1.0
     * @param message message to broadcast
     */
    public void broadcastMessage(String message){
        this.plugin.getServer().broadcastMessage(message);
    }

    /**
     * Broadcasts a message to all players.
     * @since RocketAPI-1.0
     * @param message message to broadcast
     */
    public void broadcastMessage(TextComponent message){
        this.plugin.getServer().spigot().broadcast(message);
    }

    /**
     * Sends a warning message to the console.
     * @since RocketAPI-1.0
     * @param message message to send
     */
    public void warningMessage(String message){
        plugin.getServer().getLogger().warning("[" + plugin.getName() + "] " + message);
    }

    /**
     * Sends an error message to the console.
     * @since RocketAPI-1.0
     * @param message message to send
     */
    public void errorMessage(String message){
        plugin.getServer().getLogger().severe("[" + plugin.getName() + "] " + message);
    }

    /**
     * Sends a message to the player after a certain delay.
     * @since RocketAPI-1.0
     * @param player player to send the message
     * @param secs delay in seconds
     * @param message message to send
     */
    public void delayedMessage(Player player, int secs, String message) {
        Bukkit.getServer().getScheduler().runTaskLater(RocketAPI.getInstance(), () -> playerMessage(player, message), (secs * 20L));
    }

    /**
     * Sends a message to the player after a certain delay.
     * @since RocketAPI-1.0
     * @param player player to send the message
     * @param secs delay in seconds
     * @param message message to send
     */
    public void delayedMessage(Player player, int secs, TextComponent... message) {
        Bukkit.getServer().getScheduler().runTaskLater(RocketAPI.getInstance(), () -> playerMessage(player, message), (secs * 20L));
    }

    /**
     * Sends a message to the player after a certain delay.
     * @since RocketAPI-1.0
     * @param player player to send the message
     * @param milli delay in milliseconds
     * @param message message to send
     */
    public void delayedMessage(Player player, Long milli, String message) {
        Bukkit.getServer().getScheduler().runTaskLater(RocketAPI.getInstance(), () -> playerMessage(player, message), milli);
    }

    /**
     * Sends a message to the player after a certain delay.
     * @since RocketAPI-1.0
     * @param player player to send the message
     * @param milli delay in milliseconds
     * @param message message to send
     */
    public void delayedMessage(Player player, Long milli, TextComponent... message) {
        Bukkit.getServer().getScheduler().runTaskLater(RocketAPI.getInstance(), () -> playerMessage(player, message), milli);
    }

    /**
     * Sends an info message to the console coming from a database.
     * @since RocketAPI-1.3
     * @param database database that the message is coming from
     * @param message message to send
     */
    public void databaseLog(Database database, String message){
        this.rocketLog.info(TextUtil.databasePrefix(this.plugin, database) + message);
    }

    /**
     * Sends an info message to the console coming from a table.
     * @since RocketAPI-1.3
     * @param table table that the message is coming from
     * @param message message to send
     */
    public void tableLog(Table table, String message){
        this.rocketLog.info(TextUtil.tablePrefix(this.plugin, table) + message);
    }

    /**
     * Sends a warning message to the console coming from a database.
     * @since RocketAPI-1.3
     * @param database database that the message is coming from
     * @param message message to send
     */
    public void databaseWarning(Database database, String message){
        this.rocketLog.warning(TextUtil.databasePrefix(this.plugin, database) + message);
    }

    /**
     * Sends connection error message to the console.
     * @param dbName name of the database
     */
    public void databaseConnectionError(String dbName){
        this.rocketLog.warning(TextUtil.databasePrefix(this.plugin, dbName) + ": Database connection failed. Check the connection data again.");
    }

    /**
     * Sends a message to the command executer. No mather who executed the command.
     * @since RocketAPI-1.4.1
     * @param sender command sender to send the message
     * @param message message to send
     */
    public void messageSender(CommandSender sender, String message){
        if(sender instanceof Player) playerMessage((Player) sender, message);
        if(sender instanceof ConsoleCommandSender) infoMessage(message);
    }
}