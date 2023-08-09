package me.cocos.gui.pattern;

import me.cocos.gui.data.GuiItem;
import me.cocos.gui.gui.Gui;

import java.util.function.BiConsumer;

public enum Pattern {

    FILL((gui, guiItem) -> {
        gui.addItem(guiItem, gui.getSize());
    }),
    BORDER((gui, guiItem) -> {
        int inventorySize = gui.getSize();
        int rows = inventorySize / 9;

        for (int slot = 0; slot < inventorySize; slot++) {
            int row = slot / 9;
            int col = slot % 9;

            if (row == 0 || row == rows - 1 || col == 0 || col == 8) {
                gui.setItem(slot, guiItem);
            }
        }
    });

    private final BiConsumer<Gui, GuiItem> action;

    Pattern(BiConsumer<Gui, GuiItem> action) {
        this.action = action;
    }

    public BiConsumer<Gui, GuiItem> getAction() {
        return action;
    }
}
