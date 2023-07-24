package me.cocos.gui.listener;

import me.cocos.gui.data.GuiItem;
import me.cocos.gui.gui.Gui;
import me.cocos.gui.gui.holder.GuiHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.PlayerInventory;

public final class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getView().getTopInventory().getHolder() instanceof GuiHolder guiHolder)) return;

        Gui gui = guiHolder.gui();

        if (event.getClickedInventory() instanceof PlayerInventory && gui.blockPlayerInventory()) {
            event.setCancelled(true);
            return;
        }

        Player player = (Player) event.getWhoClicked();

        if (gui.onClick() != null) {
            gui.onClick().accept(event, player);
        }

        GuiItem guiItem = gui.getGuiItem(event.getSlot());

        if (guiItem != null && guiItem.getOnInventoryClick() != null) {
            guiItem.getOnInventoryClick().accept(event, player);
        }
    }
}
