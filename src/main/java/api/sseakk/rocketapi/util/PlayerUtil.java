package api.sseakk.rocketapi.util;

import org.bukkit.entity.Player;

/**
 * PlayerUtil class utility for player related methods.
 * @author sSeaKk
 * @version 1.0
 * @since RocketAPI-1.5
 */
public class PlayerUtil {

    /**
     * Checks if the player can execute a command.
     * @since RocketAPI-1.5
     * @param player player to check
     * @param permission permission to check
     * @return true if the player can execute the command, false instead
     */
    public static boolean canExecute(Player player, String permission) {
        return player.isOp() || player.hasPermission(permission);
    }
}
