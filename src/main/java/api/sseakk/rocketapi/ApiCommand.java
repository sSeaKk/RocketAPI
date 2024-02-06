package api.sseakk.rocketapi;

import api.sseakk.rocketapi.database.Database;
import api.sseakk.rocketapi.database.TestDatabase;
import api.sseakk.rocketapi.util.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ApiCommand implements TabExecutor {
    private RocketAPI plugin;
    private MessageUtil messages;

    public ApiCommand(RocketAPI plugin){
        this.plugin = plugin;
        this.messages = this.plugin.getMessages();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length > 0){
            if(args[0].equalsIgnoreCase("version")){
                if(sender instanceof Player){
                    this.messages.playerMessage((Player) sender, this.plugin.getPluginTag() + "version "+ this.plugin.getVersion());
                } else{
                    this.messages.infoMessage("version: " + this.plugin.getVersion());
                }
                return true;
            }

            if(args[0].equalsIgnoreCase("author")){
                if(sender instanceof Player){
                    this.messages.playerMessage(((Player) sender).getPlayer(), this.plugin.getPluginTag() + "author: " + this.plugin.getAuthor());
                } else{
                    this.messages.infoMessage("author: " + this.plugin.getAuthor());
                }
                return true;
            }

            if(args[0].equalsIgnoreCase("testDatabase") || args[0].equalsIgnoreCase("td")){
               Database database = this.plugin.getDbManager().connectDatabase("localhost:3306", "RocketAPI");
               TestDatabase td = new TestDatabase(database.getName(), database.getConnection());

               td.testStatement();
               return true;
            }
        }

        /*if(sender instanceof Player){
            this.messages.playerMessage((((Player) sender).getPlayer()), this.plugin.getPluginTag() + "Commands: " +
                    "\n&6/rocketapi version &f: shows api version" +
                    "\n&6/rocketapi author &f: shows api author");
        } else {
            this.messages.infoMessage("Commands:" +
                    "\n/rocketapi version &f: shows api version" +
                    "\n/rocketapi author &f: shows api author");
        }*/
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1) {
            List<String> tabList = Arrays.asList("version", "author");
            String input = args[0].toLowerCase();
            List<String> completions = null;

            for (String s : tabList) {
                if (s.startsWith(input)) {
                    if (completions == null) {
                        completions = new ArrayList<>();
                    }
                    completions.add(s);
                }
            }

            if (completions != null) {
                Collections.sort(completions);
            }
            return completions;
        }

        return null;
    }
}