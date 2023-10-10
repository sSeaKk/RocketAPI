package api.sseakk.rocketapi;

import api.sseakk.rocketapi.util.MessageUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.Scanner;

public class FileManager {
    private JavaPlugin plugin;
    private File file = null;
    private FileWriter fileWriter = null;
    private PrintWriter printWriter = null;
    private BufferedWriter bufferedWriter = null;
    private Scanner scanner = null;

    public FileManager(JavaPlugin plugin){
        this.plugin = plugin;
    }

    public File createFile(String path, String fileName){
        if(! new File(path).exists()){
            new File(this.plugin.getDataFolder().getPath()+ path).mkdirs();
        }

        try {
            this.file = new File(this.plugin.getDataFolder().getPath() + path, fileName);

            this.fileWriter = new FileWriter(this.file);
            this.printWriter = new PrintWriter(this.fileWriter);
            this.bufferedWriter = new BufferedWriter(this.printWriter);
            this.scanner = new Scanner(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public File loadFile(String path, String fileName){
        this.file = new File(this.plugin.getDataFolder().getPath() + path, fileName);
        if(!this.file.exists()) {
            MessageUtil.errorMessage(this.plugin, "A file is not found! (PATH: " + this.file.getPath() + ")");
        }

        try{
            this.fileWriter = new FileWriter(this.file);
            this.printWriter = new PrintWriter(this.fileWriter);
            this.bufferedWriter = new BufferedWriter(printWriter);
            this.scanner = new Scanner(this.file);

            return this.file;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteFile(String path, String fileName){
        File fileToDelete = new File(this.plugin.getDataFolder().getPath() + path, fileName);

        if(!fileToDelete.delete()){
            MessageUtil.errorMessage(this.plugin, "A file is not found! (PATH: " + this.file.getPath() + ")");
        }
    }

    public File getFolder(String folder) throws FileNotFoundException{
        File path = new File(plugin.getDataFolder().getPath()+folder);
        if(!path.exists()){
            throw new FileNotFoundException("Folder '"+path.getPath()+"' do not exist!");
        }
        return path;
    }

    public FileWriter getFileWriter(){
        return this.fileWriter;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public Scanner getScanner() {
        return scanner;
    }
}