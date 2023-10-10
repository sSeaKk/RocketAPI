package api.sseakk.rocketapi;

import api.sseakk.rocketapi.util.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ApiCommand implements TabExecutor{
    private RocketAPI plugin;

    public ApiCommand(RocketAPI plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length > 0){
            if(args[0].equalsIgnoreCase("version")){
                if(sender instanceof Player){
                    MessageUtil.playerMessage((Player) sender, this.plugin.getPluginTag() + "version "+ this.plugin.getVersion());
                } else{
                    MessageUtil.infoMessage(this.plugin, "version: " + this.plugin.getVersion());
                }
                return true;
            }

            if(args[0].equalsIgnoreCase("author")){
                if(sender instanceof Player){
                    MessageUtil.playerMessage(((Player) sender).getPlayer(), this.plugin.getPluginTag() + "author: " + this.plugin.getAuthor());
                } else{
                    MessageUtil.infoMessage(this.plugin, "author: " + this.plugin.getAuthor());
                }
                return true;
            }
        }

        if(sender instanceof Player){
            MessageUtil.playerMessage((((Player) sender).getPlayer()), this.plugin.getPluginTag() + "Commands: " +
                    "\n&6/rocketapi version &f: shows api version" +
                    "\n&6/rocketapi author &f: shows api author");
        } else {
            MessageUtil.infoMessage(this.plugin,"Commands:" +
                    "\n/rocketapi version &f: shows api version" +
                    "\n/rocketapi author &f: shows api author");
        }
        return true;
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