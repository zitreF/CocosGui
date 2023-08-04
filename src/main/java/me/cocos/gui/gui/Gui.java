package me.cocos.gui.gui;

import me.cocos.gui.CocosGui;
import me.cocos.gui.data.GuiItem;
import me.cocos.gui.structure.Structure;
import me.cocos.gui.gui.holder.GuiHolder;
import me.cocos.gui.helper.ChatHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public abstract class Gui {

    protected final Map<Integer, GuiItem> actions;
    private BiConsumer<InventoryClickEvent, Player> onClick;
    private BiConsumer<InventoryCloseEvent, Player> onClose;
    protected final Inventory inventory;
    private boolean disposable;
    private boolean blockPlayerInventory;
    protected Structure structure;

    public Gui(String name, int rows) {
        this.inventory = Bukkit.createInventory(new GuiHolder(this), rows*9, ChatHelper.colored(name));
        this.actions = new HashMap<>();
    }

    public int getSize() {
        return this.inventory.getSize();
    }

    public void setItem(int slot, GuiItem guiItem) {
        this.actions.put(slot, guiItem);
        this.inventory.setItem(slot, guiItem.getItemStack());
    }

    public void addItems(GuiItem... guiItems) {
        for (GuiItem guiItem : guiItems) {
            int firstEmpty = this.inventory.firstEmpty();
            this.actions.put(firstEmpty, guiItem);
            this.getInventory().setItem(firstEmpty, guiItem.getItemStack());
        }
    }

    public void addItem(GuiItem item, int times) {
        for (int i = 0; i < times; i++) {
            this.setItem(this.inventory.firstEmpty(), item);
        }
    }

    public GuiItem getGuiItem(int slot) {
        return this.actions.get(slot);
    }

    public ItemStack getItem(int slot) {
        return this.inventory.getItem(slot);
    }


    public Inventory getInventory() {
        return this.inventory;
    }

    public void setBlockPlayerInventory(boolean blockPlayerInventory) {
        this.blockPlayerInventory = blockPlayerInventory;
    }

    public boolean blockPlayerInventory() {
        return this.blockPlayerInventory;
    }

    public void setDisposable(boolean disposable) {
        this.disposable = disposable;
    }

    public boolean isDisposable() {
        return this.disposable;
    }

    public void applyStructure(Structure structure) {
        this.structure = structure;
        structure.apply(this);
    }

    public Structure getStructure() {
        return this.structure;
    }

    public void setOnClick(BiConsumer<InventoryClickEvent, Player> onClick) {
        this.onClick = onClick;
    }

    public void setOnClose(BiConsumer<InventoryCloseEvent, Player> onClose) {
        this.onClose = onClose;
    }

    public BiConsumer<InventoryClickEvent, Player> onClick() {
        return this.onClick;
    }
    public BiConsumer<InventoryCloseEvent, Player> onClose() {
        return this.onClose;
    }

    public void open(Player player) {
        player.openInventory(this.getInventory());
    }

    public abstract void dispose();
}
