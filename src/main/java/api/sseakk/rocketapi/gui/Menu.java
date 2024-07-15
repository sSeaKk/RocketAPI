package api.sseakk.rocketapi.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import api.sseakk.rocketapi.RocketAPI;
import api.sseakk.rocketapi.util.TextUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * Menu class for creating GUIs.
 * @author sSeaKk
 * @version 1.3
 * @since RocketAPI-1.0
 */
public abstract class Menu {
    protected Component menuName;
    protected int slots;
    protected Inventory menu;
    protected static ArrayList<Player> openedGui = new ArrayList<Player>();
    protected static Map<Player, Menu> playerMenuOpened = new HashMap<Player, Menu>();

    protected ItemStack item;

    /**
     * Creates a new menu for the player.
     * @since RocketAPI-1.0
     * @param player player to open menu
     * @param name name of menu
     * @param slots number of slots of inventory menu
     */
    public Menu(Player player, String name, int slots) {
        this.menuName = Component.text(name);
        this.slots = slots;

        this.menu = RocketAPI.getInstance().getServer().createInventory(null, this.slots, this.menuName);

        if(!playerMenuOpened.containsKey(player)) {
            playerMenuOpened.put(player, this);
        }

        openedGui.add(player);
        player.openInventory(menu);
    }

    /**
     * Creates a new menu for the player.
     * @since RocketAPI-1.3
     * @param player player to open menu
     * @param name name of menu
     * @param type type of inventory menu
     */
    public Menu(Player player, String name, InventoryType type){
        this.menuName = Component.text(name);
        this.slots = type.getDefaultSize();

        this.menu = RocketAPI.getInstance().getServer().createInventory(null, type, this.menuName);

        if(!playerMenuOpened.containsKey(player)) playerMenuOpened.put(player, this);

        openedGui.add(player);
        player.openInventory(menu);
    }

    /**
     * Get the menu inventory window.
     * @since RocketAPI-1.3
     * @return Inventory
     */
    public Inventory getMenu() {
        return this.menu;
    }

    /**
     * Check if the player has the menu open.
     * @since RocketAPI-1.3
     * @param player player
     * @return boolean
     */
    public static boolean isGuiOpen(Player player) {
        return openedGui.contains(player);
    }

    /**
     * Create icon in the menu.
     * @since RocketAPI-1.0
     * @param name icon name
     * @param slot icon slot
     * @param item icon item
     * @param args icon extra arguments:
     *             - String: icon lore
     *             - ItemFlag: icon item flag
     *             - Enchantment: icon item enchantment
     */
    public void createIcon(String name, int slot, ItemStack item, Object... args) {
        ArrayList<Component> lore = new ArrayList<>();
        ItemMeta meta = item.getItemMeta();

        meta.displayName(Component.text(TextUtil.colorText(name)));
        for(Object obj : args) {
            if(obj instanceof String) {
                lore.add(Component.text(TextUtil.colorText(obj.toString())));
            }
            if(obj instanceof ItemFlag) {
                meta.addItemFlags((ItemFlag) obj);
            }
            if(obj instanceof Enchantment) {
                item.addUnsafeEnchantment((Enchantment) obj, 1);
            }
        }

        meta.lore(lore);
        item.setItemMeta(meta);

        if(item.getType() == Material.PLAYER_HEAD) {
            SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
            skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(name));
            item.setItemMeta(skullMeta);
        }

        this.menu.setItem(slot, item);
    }

    /**
     * Put the previous menu to the player key.
     * @since RocketAPI-1.3
     * @param player player
     * @param menu menu
     */
    protected void putOpenedMenuToPlayer(Player player, Menu menu) {
        playerMenuOpened.remove(player);
        playerMenuOpened.put(player, menu);
    }

    /**
     * Get the player's open menu.
     * @since RocketAPI-1.3
     * @param player player
     * @return get previous menu
     */
    public static Menu getPlayerOpenMenu(Player player) {
        return playerMenuOpened.get(player);
    }

    /**
     * Open the previous menu.
     * @since RocketAPI-1.3
     * @param player player
     * @param menuName menu name of previous menu
     */
    public abstract void openPrevMenu(Player player, String menuName);
}