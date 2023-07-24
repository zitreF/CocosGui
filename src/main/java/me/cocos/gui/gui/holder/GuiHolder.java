package me.cocos.gui.gui.holder;

import me.cocos.gui.gui.Gui;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public record GuiHolder(Gui gui) implements InventoryHolder {

    @Override
    public Inventory getInventory() {
        return gui.getInventory();
    }
}
