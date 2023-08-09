package me.cocos.gui.gui.impl;

import me.cocos.gui.builder.gui.GuiBuilder;
import me.cocos.gui.data.GuiItem;
import me.cocos.gui.gui.Gui;
import me.cocos.gui.pattern.Pattern;
import me.cocos.gui.structure.Structure;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.function.BiConsumer;

public final class NormalGui extends Gui {

    public NormalGui(String name, int rows) {
        super(name, rows);
    }

    @Override
    public void dispose() {
        this.actions.clear();
        this.inventory.clear();
        if (structure != null) this.structure.dispose();
    }

    public static class NormalGuiBuilder implements GuiBuilder<NormalGui, NormalGuiBuilder> {

        private final NormalGui normalGui;

        public NormalGuiBuilder(String name, int rows) {
            this.normalGui = new NormalGui(name, rows);
        }

        @Override
        public NormalGuiBuilder structure(Structure structure) {
            this.normalGui.applyStructure(structure);
            return this;
        }

        @Override
        public NormalGuiBuilder ingredient(char character, GuiItem guiItem) {
            this.normalGui.getStructure().ingredient(character, guiItem);
            return this;
        }

        @Override
        public NormalGuiBuilder item(int slot, GuiItem item) {
            this.normalGui.setItem(slot, item);
            return this;
        }

        @Override
        public NormalGuiBuilder blockPlayerInventory(boolean value) {
            this.normalGui.setBlockPlayerInventory(value);
            return this;
        }

        @Override
        public NormalGuiBuilder pattern(Pattern pattern, GuiItem guiItem) {
            this.normalGui.applyPattern(pattern, guiItem);
            return this;
        }

        @Override
        public NormalGuiBuilder addItem(GuiItem item, int times) {
            this.normalGui.addItem(item, times);
            return this;
        }

        @Override
        public NormalGuiBuilder addItems(GuiItem... items) {
            this.normalGui.addItems(items);
            return this;
        }

        @Override
        public NormalGuiBuilder onClick(BiConsumer<InventoryClickEvent, Player> onClick) {
            this.normalGui.setOnClick(onClick);
            return this;
        }

        @Override
        public NormalGuiBuilder onClose(BiConsumer<InventoryCloseEvent, Player> onClose) {
            this.normalGui.setOnClose(onClose);
            return this;
        }

        @Override
        public NormalGuiBuilder disposable(boolean disposable) {
            this.normalGui.setDisposable(disposable);
            return this;
        }

        @Override
        public NormalGui build() {
            return this.normalGui;
        }

        public static NormalGuiBuilder from(String name, int rows) {
            return new NormalGuiBuilder(name, rows);
        }
    }
}
