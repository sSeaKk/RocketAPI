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

public abstract class Menu {
    protected Component menuName;
    protected int slots;
    protected Inventory menu;
    protected static ArrayList<Player> openedGui = new ArrayList<Player>();
    protected static Map<Player, Menu> playerMenuOpened = new HashMap<Player, Menu>();

    protected ItemStack item;
    protected SkullMeta skmeta;

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

    public Menu(Player player, String name, InventoryType type){
        this.menuName = Component.text(name);
        this.slots = type.getDefaultSize();

        this.menu = RocketAPI.getInstance().getServer().createInventory(null, type, this.menuName);

        if(!playerMenuOpened.containsKey(player)) playerMenuOpened.put(player, this);

        openedGui.add(player);
        player.openInventory(menu);
    }

    public Inventory getMenu() {
        return this.menu;
    }

    public static boolean isGuiOpen(Player player) {
        if(openedGui.contains(player)) return true;
        return false;
    }

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
            SkullMeta skmeta = (SkullMeta) item.getItemMeta();
            skmeta.setOwningPlayer(Bukkit.getOfflinePlayer(name));
            item.setItemMeta(skmeta);
        }

        this.menu.setItem(slot, item);
    }

    protected void putOpenedMenuToPlayer(Player player, Menu menu) {
        if(playerMenuOpened.containsKey(player)) {
            playerMenuOpened.remove(player);
        }

        playerMenuOpened.put(player, menu);
    }

    public static Menu getPlayerOpenMenu(Player player) {
        return playerMenuOpened.get(player);
    }

    public abstract void openPrevMenu(Player player, String menuName);
}