package me.cocos.gui.data;

import me.cocos.gui.gui.Gui;
import me.cocos.gui.gui.impl.NormalGui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Structure {

    private static final Map<Character, GuiItem> globalIngredients = new HashMap<>();

    private final Map<Character, GuiItem> ingredients;
    private final String pattern;

    public Structure(String... pattern) {
        this.pattern = Arrays.stream(pattern).map(string -> string.replace(" ", "")).collect(Collectors.joining());
        this.ingredients = new HashMap<>();
    }

    public Structure ingredient(char character, GuiItem guiItem) {
        this.ingredients.put(character, guiItem);
        return this;
    }

    public void apply(Gui gui) {
        IntStream.range(0, this.pattern.length())
                .forEach(slot -> {
                    char character = this.pattern.charAt(slot);
                    GuiItem guiItem = this.ingredients.get(character);
                    if (guiItem != null) {
                        gui.setItem(slot, guiItem);
                        return;
                    }
                    GuiItem globalIngredient = globalIngredients.get(character);
                    if (globalIngredient != null) {
                        gui.setItem(slot, globalIngredient);
                    }
                });
    }

    public static void globalIngredient(char character, GuiItem guiItem) {
        globalIngredients.put(character, guiItem);
    }

    public static Structure of(String... pattern) {
        return new Structure(pattern);
    }
}
