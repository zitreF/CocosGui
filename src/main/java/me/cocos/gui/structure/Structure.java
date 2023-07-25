package me.cocos.gui.structure;

import me.cocos.gui.data.GuiItem;
import me.cocos.gui.gui.Gui;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Structure {

    private static final Map<Character, GuiItem> globalIngredients = new HashMap<>();

    private final Map<Character, GuiItem> ingredients;
    private final Map<Character, List<GuiItem>> ingredientList;
    private final String pattern;

    public Structure(String... pattern) {
        this.pattern = Arrays.stream(pattern).map(string -> string.replace(" ", "")).collect(Collectors.joining());
        this.ingredients = new HashMap<>();
        this.ingredientList = new HashMap<>();
    }

    public Structure ingredient(char character, GuiItem guiItem) {
        this.ingredients.put(character, guiItem);
        return this;
    }

    public void apply(Gui gui) {
        for (int i = 0; i < pattern.length(); i++) {
            char character = this.pattern.charAt(i);
            GuiItem guiItem = this.getGuiItem(character);
            if (guiItem != null) {
                gui.setItem(i, guiItem);
            }
        }
    }


    private GuiItem getGuiItem(char character) {
        return Optional.ofNullable(ingredients.get(character))
                .orElseGet(() -> {
                    List<GuiItem> guiItems = ingredientList.get(character);
                    if (guiItems != null) {
                        Iterator<GuiItem> guiItemIterator = guiItems.iterator();
                        if (guiItemIterator.hasNext()) {
                            GuiItem nextGuiItem = guiItemIterator.next();
                            guiItemIterator.remove();
                            return nextGuiItem;
                        }
                    }
                    return globalIngredients.get(character);
                });
    }

    public Structure contents(char character, List<GuiItem> guiItems) {
        ingredientList.put(character, new ArrayList<>(guiItems));
        return this;
    }

    public void dispose() {
        this.ingredients.clear();
        this.ingredientList.clear();
    }

    public static void globalIngredient(char character, GuiItem guiItem) {
        globalIngredients.put(character, guiItem);
    }

    public static Structure of(String... pattern) {
        return new Structure(pattern);
    }
}
