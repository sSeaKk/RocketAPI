package api.sseakk.rocketapi;

import api.sseakk.rocketapi.util.MessageUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * FileManager is a class that allows you to manage files in your plugin, in a simple way.
 * @author sSeaKk
 * @version 2.0.3
 * @since RocketAPI-1.0
 * */
public abstract class FileManager {
    private final JavaPlugin plugin;
    private final MessageUtil msg;

    /**
     * FileManager constructor
     * @since RocketAPI-1.0
     * @param plugin Plugin instance.
     * */
    public FileManager(JavaPlugin plugin){
        this.plugin = plugin;
        this.msg = RocketAPI.getInstance().getMessages();
    }

    /**
     * Create a file in the plugin folder.
     * @since RocketAPI-1.0
     * @param path Path to the file.
     * @param fileName Name of the file.
     * @return File created.
     * */
    public File createFile(String path, String fileName){
        File file = new File(this.plugin.getDataFolder().getPath() + path, fileName);
        if (file.getParentFile().mkdir()) this.msg.infoMessage("Directorio creado.");

        return new File(this.plugin.getDataFolder().getPath() + path, fileName);
    }

    /**
     * Load a file from the plugin folder.
     * @since RocketAPI-1.0
     * @param path Path to the file.
     * @param fileName Name of the file.
     * @return File loaded, null if the file does not exist.
     * */
    public File loadFile(String path, String fileName){
        File file = new File(this.plugin.getDataFolder().getPath() + path, fileName);
        if(!file.exists()) {
            this.msg.errorMessage("A file is not found! (PATH: " + file.getPath() + ")");
            return null;
        }

        return file;
    }

    /**
     * Delete a file from the plugin folder.
     * @since RocketAPI-1.0
     * @param path Path to the file.
     * @param fileName Name of the file.
     * */
    public void deleteFile(String path, String fileName){
        File fileToDelete = new File(this.plugin.getDataFolder().getPath() + path, fileName);

        if(!fileToDelete.delete()){
            this.msg.errorMessage("A file is not found! (PATH: " + fileToDelete.getPath() + ")");
        }
    }

    /**
     * Get a folder from the plugin folder.
     * @since RocketAPI-1.0
     * @param folder Path to the folder.
     * @return Folder.
     * */
    public File getFolder(String folder) {
        File path = new File(plugin.getDataFolder().getPath()+folder);
        if(!path.exists()){
            this.msg.errorMessage("Folder '"+path.getPath()+"' do not exist!");
        }
        return path;
    }
}