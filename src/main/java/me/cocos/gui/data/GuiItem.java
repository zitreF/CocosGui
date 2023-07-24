package me.cocos.gui.data;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;

public final class GuiItem {

    private BiConsumer<InventoryClickEvent, Player> onInventoryClick;
    private final ItemStack itemStack;

    public GuiItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public BiConsumer<InventoryClickEvent, Player> getOnInventoryClick() {
        return onInventoryClick;
    }

    public GuiItem onClick(BiConsumer<InventoryClickEvent, Player> onInventoryClick) {
        this.onInventoryClick = onInventoryClick;
        return this;
    }

    public static GuiItem of(ItemStack itemStack) {
        return new GuiItem(itemStack);
    }
    public static GuiItem of(Material material) {
        return new GuiItem(new ItemStack(material));
    }
}
