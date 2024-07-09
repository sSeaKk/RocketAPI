package api.sseakk.rocketapi;

import api.sseakk.rocketapi.util.MessageUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class FileManager {
    private final JavaPlugin plugin;
    private final MessageUtil msg = RocketAPI.getInstance().getMessages();

    public FileManager(JavaPlugin plugin){
        this.plugin = plugin;
    }

    public File createFile(String path, String fileName){
        return new File(this.plugin.getDataFolder().getPath() + path, fileName);
    }

    public File loadFile(String path, String fileName){
        File file = new File(this.plugin.getDataFolder().getPath() + path, fileName);
        if(!file.exists()) {
            this.msg.errorMessage("A file is not found! (PATH: " + file.getPath() + ")");
            return null;
        }

        return file;
    }

    public void deleteFile(String path, String fileName){
        File fileToDelete = new File(this.plugin.getDataFolder().getPath() + path, fileName);

        if(!fileToDelete.delete()){
            this.msg.errorMessage("A file is not found! (PATH: " + fileToDelete.getPath() + ")");
        }
    }

    public File getFolder(String folder) {
        File path = new File(plugin.getDataFolder().getPath()+folder);
        if(!path.exists()){
            this.msg.errorMessage("Folder '"+path.getPath()+"' do not exist!");
        }
        return path;
    }
}