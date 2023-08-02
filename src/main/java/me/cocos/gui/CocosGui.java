package me.cocos.gui;

import me.cocos.gui.builder.gui.GuiBuilder;
import me.cocos.gui.listener.InventoryClickListener;
import me.cocos.gui.listener.InventoryCloseListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Stream;

public final class CocosGui {

    public static final Plugin PLUGIN = JavaPlugin.getProvidingPlugin(CocosGui.class);

    public static void initialize() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        Stream.of(
                new InventoryClickListener(),
                new InventoryCloseListener()
        ).forEach(listener -> pluginManager.registerEvents(listener, PLUGIN));
    }
}
