package api.sseakk.rocketapi;

import api.sseakk.rocketapi.database.DatabaseManager;
import api.sseakk.rocketapi.util.MessageUtil;
import api.sseakk.rocketapi.util.TextUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

/**
 * RocketAPI is an API/Library that provides methods for developers to simplify development of Bukkit fork plugins.
 * @author sSeaKk
 * @version RocketAPI-2.0.3
 */
public final class RocketAPI extends JavaPlugin {
    private DatabaseManager dbManager;
    private MessageUtil messages;
    private final String pluginTag = TextUtil.colorText("&c&l[&7&lRocketAPI&c&l]&r ");

    @Override
    public void onEnable() {
        this.messages = new MessageUtil(this);
        this.dbManager = new DatabaseManager(this);
        Objects.requireNonNull(this.getCommand("rocketapi")).setExecutor(new ApiCommand(this));
        Objects.requireNonNull(this.getCommand("rocketapi")).setTabCompleter(new ApiCommand(this));
        this.messages.infoMessage("Initializing internal API.");
    }

    @Override
    public void onDisable() {
        this.messages.infoMessage("Disabling internal API.");
    }

    /**
     * Returns the instance of the RocketAPI.
     * @since RocketAPI-1.0
     * @return instance of the RocketAPI
     */
    public static RocketAPI getInstance(){
        return getPlugin(RocketAPI.class);
    }

    /**
     * Returns the version of the RocketAPI.
     * @since RocketAPI-1.0
     * @return version of the RocketAPI
     */
    public String getVersion(){
        return this.getPluginMeta().getVersion();
    }

    /**
     * Returns the tag of the RocketAPI.
     * @since RocketAPI-1.0
     * @return tag of the RocketAPI
     */
    public String getPluginTag() {
        return pluginTag;
    }

    /**
     * Returns the author of the RocketAPI.
     * @since RocketAPI-1.0
     * @return author of the RocketAPI
     */
    public String getAuthor(){
        return "sSeaKk";
    }

    /**
     * Returns the MessageUtil instance.
     * @since RocketAPI-1.3
     * @return MessageUtil instance of the RocketAPI instance
     */
    public MessageUtil getMessages() {
        return messages;
    }

    /**
     * Returns the DatabaseManager instance.
     * @since RocketAPI-1.3
     * @return DatabaseManager instance of the RocketAPI instance
     */
    public DatabaseManager getDbManager() {
        return dbManager;
    }
}