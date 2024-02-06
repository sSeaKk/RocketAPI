package api.sseakk.rocketapi;

import api.sseakk.rocketapi.database.DatabaseManager;
import api.sseakk.rocketapi.util.MessageUtil;
import api.sseakk.rocketapi.util.TextUtil;
import org.bukkit.plugin.java.JavaPlugin;

public final class RocketAPI extends JavaPlugin {
    private DatabaseManager dbManager;
    private MessageUtil messages;
    private String pluginTag = TextUtil.colorText("&c&l[&7&lRocketAPI&c&l]&r ");

    @Override
    public void onEnable() {
        this.messages = new MessageUtil(this);
        this.dbManager = new DatabaseManager(this);
        this.getCommand("rocketapi").setExecutor(new ApiCommand(this));
        this.getCommand("rocketapi").setTabCompleter(new ApiCommand(this));
        this.messages.infoMessage("Initializing internal API.");
    }

    @Override
    public void onDisable() {
        this.messages.infoMessage("Disabling internal API.");
    }

    public static RocketAPI getInstance(){
        return getPlugin(RocketAPI.class);
    }

    public String getVersion(){
        return getDescription().getVersion();
    }

    public String getPluginTag() {
        return pluginTag;
    }

    public String getAuthor(){
        return "sSeaKk";
    }

    public MessageUtil getMessages() {
        return messages;
    }

    public DatabaseManager getDbManager() { return dbManager; }
}