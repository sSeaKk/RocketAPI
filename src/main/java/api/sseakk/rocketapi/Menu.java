package api.sseakk.rocketapi;

import api.sseakk.rocketapi.util.TextUtil;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public abstract class Menu {
    protected String menuName;
    protected int slots;
    protected Inventory menu;
    protected static ArrayList<Player> openedGui = new ArrayList<Player>();
    protected static HashMap<Player, Menu> lastMenuOpened = new HashMap<Player, Menu>();
    protected ItemStack item;
    protected SkullMeta skullMeta;

    protected Menu(Player player, String name, int slots) {
        this.menuName = name;
        this.slots = slots;

        this.menu = RocketAPI.getInstance().getServer().createInventory(null, this.slots, this.menuName);

        if(!lastMenuOpened.containsKey(player)) {
            lastMenuOpened.put(player, this);
        }

        openedGui.add(player);
        player.openInventory(menu);
    }

    public void createIcon(String name, int slot, ItemStack item, Object... args) {
        this.menu.setItem(slot, new ItemStack(Material.AIR));
        ArrayList<String> lore = new ArrayList<String>();
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(TextUtil.colorText(name));
        for(Object obj : args) {
            if(obj instanceof String) {
                lore.add(TextUtil.colorText(obj.toString()));
            }
            if(obj instanceof ItemFlag) {
                meta.addItemFlags((ItemFlag) obj);
            }
            if(obj instanceof Enchantment) {
                meta.addEnchant((Enchantment) obj, 1, true);
            }
        }

        meta.setLore(lore);
        item.setItemMeta(meta);

        if(item.getType() == Material.PLAYER_HEAD) {
            this.skullMeta = (SkullMeta) item.getItemMeta();
            this.skullMeta.setOwningPlayer(RocketAPI.getInstance().getServer().getOfflinePlayer(name));
            for(Object obj : args){
                if(obj instanceof String) {
                    this.skullMeta.setLore(Arrays.asList(TextUtil.colorText(obj.toString())));
                }
            }
            item.setDurability((short) 3);
            item.setItemMeta(skullMeta);
        }

        this.menu.setItem(slot, item);
    }


}
