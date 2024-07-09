package api.sseakk.rocketapi.util;

import org.bukkit.entity.Player;

public class PlayerUtil {
    public static boolean canExecute(Player player, String permission) {
        return player.isOp() || player.hasPermission(permission);
    }
}
