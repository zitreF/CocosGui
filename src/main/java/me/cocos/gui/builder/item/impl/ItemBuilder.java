package me.cocos.gui.builder.item.impl;

import me.cocos.gui.builder.item.Builder;
import me.cocos.gui.data.GuiItem;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public final class ItemBuilder extends Builder<ItemMeta> {

    public ItemBuilder(Material material) {
        super(material);
    }

    public ItemBuilder color(Color color) {
        if (!(meta instanceof LeatherArmorMeta)) {
            throw new IllegalArgumentException("Item isn't armor!");
        }
        ((LeatherArmorMeta) this.meta).setColor(color);
        return this;
    }

    public static ItemBuilder from(Material material) {
        return new ItemBuilder(material);
    }
}
