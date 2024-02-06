package api.sseakk.rocketapi.util;

import api.sseakk.rocketapi.database.Database;
import api.sseakk.rocketapi.database.Table;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class TextUtil {
    public static String colorText(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String decimalFormat(Double decimal) {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", dfs);

        return  df.format(decimal);
    }

    public static TextComponent toTextComponent(String message){
        return new TextComponent(colorText(message));
    }

    public static String removeColor(String text){
        return ChatColor.stripColor(text);
    }

    protected static String databasePrefix(JavaPlugin plugin, Database database){
        return "[" + plugin.getName() + ":" + database.getName()+"]";
    }

    protected static String databasePrefix(JavaPlugin plugin, String dbName){
        return "[" + plugin.getName() + ":" + dbName+"]";
    }

    protected static String tablePrefix(JavaPlugin plugin, Table table){
        return "[" + plugin.getName() + ":" + table.getDatabaseName()+"/"+table.getName()+"]";
    }
}