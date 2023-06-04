package ga.justreddy.wiki.rkitpvp.model.kit.layout;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class KitLayout implements ConfigurationSerializable {

    private final String kitName;
    private Map<Integer, ItemStack> itemsLayout;

    public KitLayout(String kitName, Map<Integer, ItemStack> itemsLayout) {
        this.kitName = kitName;
        this.itemsLayout = itemsLayout;
    }

    public KitLayout(String kitName) {
        this.kitName = kitName;
        this.itemsLayout = new HashMap<>();
    }

    public String getKitName() {
        return kitName;
    }

    public Map<Integer, ItemStack> getItemsLayout() {
        return itemsLayout;
    }

    public void setItemsLayout(Map<Integer, ItemStack> itemsLayout) {
        this.itemsLayout = itemsLayout;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("kitName", kitName);
        map.put("itemsLayout", itemsLayout);
        return map;
    }
}
