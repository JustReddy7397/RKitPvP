package ga.justreddy.wiki.rkitpvp.util;

import com.cryptomorin.xseries.XMaterial;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import lombok.experimental.NonFinal;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItemUtil implements Builder<ItemStack> {

    ItemStack itemStack;
    ItemMeta meta;
    @NonFinal List<String> lore;

    public ItemUtil(XMaterial material) {
        this(material.parseItem());
    }

    public ItemUtil(ItemStack itemStack) {
        this.itemStack = itemStack;
        if (itemStack == null) throw new IllegalStateException("ITEMSTACK IS NULL!");
        this.meta = itemStack.getItemMeta();
        lore = new ArrayList<>();
    }

    public ItemUtil setName(String name) {
        meta.setDisplayName(ChatUtil.format(name));
        return this;
    }

    public ItemUtil setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemUtil addLore(String lore) {
        this.lore.add(lore);
        return this;
    }

    public ItemUtil setFlags(ItemFlag... flags) {
        meta.addItemFlags(flags);
        return this;
    }

    public ItemUtil setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemUtil withPotion(PotionType type, boolean splash, PotionEffect effect) {
        Material material = itemStack.getType();
        if (material != Material.POTION) {
            throw new IllegalStateException("You need a potion material to create potions!");
        }

        // TODO
        return this;
    }


    @Override
    public ItemStack build() {
        meta.setLore(ChatUtil.format(lore));
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
