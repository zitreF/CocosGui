package me.cocos.gui.listener;

import me.cocos.gui.gui.Gui;
import me.cocos.gui.gui.holder.GuiHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public final class InventoryCloseListener implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getInventory().getHolder() instanceof GuiHolder guiHolder)) return;

        Gui gui = guiHolder.gui();

        Player player = (Player) event.getPlayer();

        if (gui.onClose() != null) {
            gui.onClose().accept(event, player);
        }

        if (gui.isDisposable()) {
            gui.dispose();
        }
    }
}
