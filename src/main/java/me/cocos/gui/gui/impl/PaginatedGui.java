package me.cocos.gui.gui.impl;

import me.cocos.gui.builder.gui.GuiBuilder;
import me.cocos.gui.data.GuiItem;
import me.cocos.gui.gui.Gui;
import me.cocos.gui.pattern.Pattern;
import me.cocos.gui.structure.Structure;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public final class PaginatedGui extends Gui {

    private List<GuiItem> guiItems;

    private char key;
    private int currentPage;

    public PaginatedGui(String name, int rows) {
        super(name, rows);
        this.guiItems = new ArrayList<>();
        this.currentPage = 0;
    }

    @Override
    public void dispose() {
        this.actions.clear();
        this.inventory.clear();
        this.guiItems.clear();
        if (structure != null) this.structure.dispose();
    }

    public void applyStructure() {
        if (structure != null) {
            String pattern = structure.getPattern();

            int itemsPerPage = StringUtils.countMatches(pattern, key);
            int startIndex = currentPage * itemsPerPage;
            int endIndex = Math.min(startIndex + itemsPerPage, this.guiItems.size());

            int index = 0;

            for (int i = 0; i < this.inventory.getSize(); i++) {
                char character = pattern.charAt(i);

                if (character != key) {
                    continue;
                }

                if (index < endIndex - startIndex) {
                    GuiItem guiItem = this.guiItems.get(startIndex + index);
                    this.setItem(i, guiItem);
                    index++;
                    continue;
                }
                break;
            }
        }
    }

    public void nextPage() {
        int itemsPerPage = StringUtils.countMatches(structure.getPattern(), key);
        int maxPage = (int) Math.ceil((double) this.guiItems.size() / itemsPerPage);
        if (this.currentPage >= maxPage - 1) {
            return;
        }
        this.getInventory().clear();
        this.currentPage++;
        this.applyStructure();
        this.structure.apply(this);
    }

    public void previousPage() {
        if (this.currentPage > 0) {
            this.getInventory().clear();
            this.currentPage--;
            this.applyStructure();
            this.structure.apply(this);
        }
    }

    public static class PaginatedGuiBuilder implements GuiBuilder<PaginatedGui, PaginatedGuiBuilder> {

        private final PaginatedGui paginatedGui;

        public PaginatedGuiBuilder(String name, int rows) {
            this.paginatedGui = new PaginatedGui(name, rows);
        }

        @Override
        public PaginatedGuiBuilder structure(Structure structure) {
            this.paginatedGui.applyStructure(structure);
            return this;
        }

        @Override
        public PaginatedGuiBuilder ingredient(char character, GuiItem guiItem) {
            this.paginatedGui.getStructure().ingredient(character, guiItem);
            return this;
        }

        @Override
        public PaginatedGuiBuilder item(int slot, GuiItem item) {
            this.paginatedGui.setItem(slot, item);
            return this;
        }

        public PaginatedGuiBuilder setIngredients(char character, List<GuiItem> guiItems) {
            this.paginatedGui.key = character;
            this.paginatedGui.guiItems = new ArrayList<>(guiItems);
            return this;
        }

        @Override
        public PaginatedGuiBuilder blockPlayerInventory(boolean value) {
            this.paginatedGui.setBlockPlayerInventory(value);
            return this;
        }

        @Override
        public PaginatedGuiBuilder pattern(Pattern pattern, GuiItem guiItem) {
            this.paginatedGui.applyPattern(pattern, guiItem);
            return this;
        }

        @Override
        public PaginatedGuiBuilder addItem(GuiItem item, int times) {
            this.paginatedGui.addItem(item, times);
            return this;
        }

        @Override
        public PaginatedGuiBuilder addItems(GuiItem... items) {
            this.paginatedGui.addItems(items);
            return this;
        }

        @Override
        public PaginatedGuiBuilder onClick(BiConsumer<InventoryClickEvent, Player> onClick) {
            this.paginatedGui.setOnClick(onClick);
            return this;
        }

        @Override
        public PaginatedGuiBuilder onClose(BiConsumer<InventoryCloseEvent, Player> onClose) {
            this.paginatedGui.setOnClose(onClose);
            return this;
        }

        @Override
        public PaginatedGuiBuilder disposable(boolean disposable) {
            this.paginatedGui.setDisposable(disposable);
            return this;
        }

        @Override
        public PaginatedGui build() {
            paginatedGui.applyStructure();
            return this.paginatedGui;
        }

        public static PaginatedGuiBuilder from(String name, int rows) {
            return new PaginatedGuiBuilder(name, rows);
        }
    }
}
