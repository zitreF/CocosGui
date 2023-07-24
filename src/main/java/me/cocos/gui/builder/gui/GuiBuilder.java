package me.cocos.gui.builder.gui;

import me.cocos.gui.data.GuiItem;
import me.cocos.gui.data.Structure;
import me.cocos.gui.gui.Gui;
import me.cocos.gui.gui.impl.NormalGui;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.function.BiConsumer;

public interface GuiBuilder<G extends Gui, B extends GuiBuilder<G, B>> {

    B structure(Structure structure);

    B ingredient(char character, GuiItem guiItem);

    B item(int slot, GuiItem item);

    B addItems(GuiItem... items);

    B onClick(BiConsumer<InventoryClickEvent, Player> onClick);

    B onClose(BiConsumer<InventoryCloseEvent, Player> onClose);

    B disposable(boolean disposable);

    G build();

    static NormalGui.NormalGuiBuilder normal(String name, int rows) {
        return new NormalGui.NormalGuiBuilder(name, rows);
    }
}
