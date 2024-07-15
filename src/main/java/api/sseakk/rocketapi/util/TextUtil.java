package api.sseakk.rocketapi.util;

import api.sseakk.rocketapi.database.Database;
import api.sseakk.rocketapi.database.Table;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * TextUtil class utility for colorizing text and formatting it.
 * @author sSeaKk
 * @version 1.3
 * @since RocketAPI-1.0
 */
public class TextUtil {

    /**
     * Colorizes the text.
     * @since RocketAPI-1.0
     * @param text text to colorize
     * @return colorized text
     */
    public static String colorText(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    /**
     * Formats the decimal number to 2 decimal places.
     * @since RocketAPI-1.0
     * @param decimal decimal number to format
     * @return formatted decimal number
     */
    public static String decimalFormat(Double decimal) {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", dfs);

        return  df.format(decimal);
    }

    /**
     * Converts a simple text string to a TextComponent.
     * @since RocketAPI-1.0
     * @param message text to convert
     * @return converted TextComponent
     */
    public static TextComponent toTextComponent(String message){
        return new TextComponent(colorText(message));
    }

    /**
     * Removes the color from the text.
     * @since RocketAPI-1.0
     * @param text text to remove color
     * @return text without color
     */
    public static String removeColor(String text){
        return ChatColor.stripColor(text);
    }

    /**
     * Returns the prefix for the database.
     * @since RocketAPI-1.3
     * @param plugin plugin's database
     * @param database database to get prefix
     * @return database prefix
     */
    protected static String databasePrefix(JavaPlugin plugin, Database database){
        return "[" + plugin.getName() + ":" + database.getName()+"]";
    }

    /**
     * Returns the prefix for the database.
     * @since RocketAPI-1.3
     * @param plugin plugin's database
     * @param dbName database name to get prefix
     * @return database prefix
     */
    protected static String databasePrefix(JavaPlugin plugin, String dbName){
        return "[" + plugin.getName() + ":" + dbName+"]";
    }

    /**
     * Returns the prefix for the table.
     * @since RocketAPI-1.3
     * @param plugin plugin's table
     * @param table table to get prefix
     * @return table prefix
     */
    protected static String tablePrefix(JavaPlugin plugin, Table table){
        return "[" + plugin.getName() + ":" + table.getDatabaseName()+"/"+table.getName()+"]";
    }
}