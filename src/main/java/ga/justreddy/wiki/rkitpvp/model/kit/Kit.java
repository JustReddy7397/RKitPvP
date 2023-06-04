package ga.justreddy.wiki.rkitpvp.model.kit;

import ga.justreddy.wiki.rkitpvp.model.entity.KitPlayer;
import ga.justreddy.wiki.rkitpvp.model.kit.layout.KitLayout;
import ga.justreddy.wiki.rkitpvp.util.ChatUtil;
import ga.justreddy.wiki.rkitpvp.util.ItemUtil;
import ga.justreddy.wiki.rkitpvp.util.NumberUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Kit {

    String name;
    List<ItemStack> kitItems;
    List<ItemStack> armorItems;
    ItemStack guiKitItem;
    boolean isDefault;

    private Kit(String name) {
        this.name = name;
    }

    public Kit(String name, List<ItemStack> kitItems, List<ItemStack> armorItems, Material kitItemType) {
        this(name, kitItems, armorItems, kitItemType, false);
    }

    public Kit(String name, List<ItemStack> kitItems, List<ItemStack> armorItems, Material kitItemType, boolean isDefault) {
        this.name = name;
        this.kitItems = kitItems;
        this.armorItems = armorItems;
        this.isDefault = isDefault;
        setGuiKitItem(kitItemType);
    }

    public void equipKit(KitPlayer kitPlayer) {
        KitLayout layout = kitPlayer.getKits().getKitLayout(this);

        PlayerInventory inventory = kitPlayer.getPlayer().getInventory();
        inventory.clear();

        if (!armorItems.isEmpty()) {
            for (ItemStack item : armorItems) {
                String type = item.getType().name();
                if (type.endsWith("HELMET")) inventory.setHelmet(item);
                if (type.endsWith("CHESTPLATE")) inventory.setChestplate(item);
                if (type.endsWith("LEGGINGS")) inventory.setLeggings(item);
                if (type.endsWith("BOOTS")) inventory.setBoots(item);
            }
        }
        if (!kitItems.isEmpty()) {
            if (layout != null) {
                for (Map.Entry<Integer, ItemStack> entry : layout.getItemsLayout().entrySet()) {
                    inventory.setItem(entry.getKey(), entry.getValue());
                }
            } else {
                for (ItemStack item : kitItems) {
                    inventory.addItem(item);
                }
            }
        }
    }

    public static Kit fromPlayerInventory(KitPlayer player, Material kitItem, String kitName) {
        Kit kit = new Kit(kitName);
        PlayerInventory inventory = player.getPlayer().getInventory();
        kit.setArmorItems(Arrays.asList(inventory.getArmorContents()));
        List<ItemStack> kitItems = new ArrayList<>();
        for (int i = 0; i <= 35; i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null) {
                if (item.getType() != Material.AIR) {
                    kitItems.add(item);
                }
            }
        }
        kit.setKitItems(kitItems);
        kit.setGuiKitItem(kitItem);
        return kit;
    }

    public void setGuiKitItem(Material kitItem) {
        ItemUtil guiItem = new ItemUtil(new ItemStack(kitItem));
        guiItem.setFlags(ItemFlag.HIDE_ATTRIBUTES);
        guiItem.setName("&b" + ChatUtil.uppercaseFirstLetter(name));
        List<String> lore = new ArrayList<>();
        if (!armorItems.isEmpty()) {
            for (ItemStack itemStack : armorItems) {
                List<String> itemNameList = new ArrayList<>();
                itemNameList.add(ChatUtil.nameOf(itemStack.getType()));
                if (itemStack.hasItemMeta()) {
                    ItemMeta meta = itemStack.getItemMeta();
                    if (meta.hasEnchants()) {
                        meta.getEnchants().forEach((enchantment, integer) -> itemNameList.add(
                                "● " + ChatUtil.nameOf(enchantment) + " " + integer));
                    }
                }
                itemNameList.forEach(string -> lore.add("&7" + string));
            }
            if (name.equalsIgnoreCase("Blacksmith")) {
                guiItem.addLore("&7Random Diamond Armor Piece");
            }
        }

        if (!kitItems.isEmpty())
            for (ItemStack item : kitItems) {
                int amount = item.getAmount();
                List<String> itemNameList = new ArrayList<>();
                String itemName = amount > 1 ?
                        ChatUtil.nameOf(item.getType()) + " x" + amount : ChatUtil.nameOf(item.getType());

                itemNameList.add(itemName);
                if (item.hasItemMeta()) {
                    ItemMeta meta = item.getItemMeta();
                    if (meta.hasEnchants()) {
                        meta.getEnchants().forEach((enchantment, integer) ->
                                itemNameList.add("● " + ChatUtil.nameOf(enchantment) + " " + integer));
                    }
                }

                if (item.getType() == Material.ENCHANTED_BOOK) {
                    if (item.hasItemMeta()) {
                        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
                        if (meta.hasStoredEnchants()) {
                            meta.getStoredEnchants().forEach((enchantment, integer) ->
                                    itemNameList.add("● " + ChatUtil.nameOf(enchantment) + " " + integer));
                        }
                    }
                }

                if (item.getType() == Material.POTION && item.hasItemMeta()) {
                    PotionMeta meta = (PotionMeta) item.getItemMeta();
                    if (meta.hasCustomEffects()) {
                        meta.getCustomEffects().forEach(potionEffect -> {
                            itemNameList.add(
                                    "● " +
                                            ChatUtil.uppercaseFirstLetter(
                                                    potionEffect.getType().getName()
                                            )
                            );
                            itemNameList.add("● Level: " + (potionEffect.getAmplifier() + 1));
                            itemNameList.add("● Duration: " + NumberUtil.toFormat(potionEffect.getDuration() / 20));
                        });
                    }
                }
                itemNameList.forEach(string -> lore.add("&7" + string));
            }

        guiItem.setLore(lore);
        this.guiKitItem = guiItem.build();
    }

    public void onKill(KitPlayer killer, KitPlayer victim) {}

}
