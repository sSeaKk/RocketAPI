package api.sseakk.rocketapi;

import api.sseakk.rocketapi.util.MessageUtil;
import api.sseakk.rocketapi.util.TextUtil;
import org.bukkit.plugin.java.JavaPlugin;

public final class RocketAPI extends JavaPlugin {
    private static RocketAPI instance;
    private String pluginTag = TextUtil.colorText("&c&l[&7&lRocketAPI&c&l]&r ");

    @Override
    public void onEnable() {
        instance = this;
        this.getCommand("rocketapi").setExecutor(new ApiCommand(this));
        this.getCommand("rocketapi").setTabCompleter(new ApiCommand(this));
        MessageUtil.infoMessage(this, "Initializing internal API.");
    }

    @Override
    public void onDisable() {
        MessageUtil.infoMessage(this, "Disabling internal API.");
    }

    public static RocketAPI getInstance(){
        return instance;
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
}