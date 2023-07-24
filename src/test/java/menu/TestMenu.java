package menu;

import me.cocos.gui.builder.gui.GuiBuilder;
import me.cocos.gui.data.GuiItem;
import me.cocos.gui.data.Structure;
import me.cocos.gui.gui.Gui;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class TestMenu {

    public void openGui(Player player) {
        Gui gui = GuiBuilder.normal("&7Test", 3)
                .onClick((event, p) -> event.setCancelled(true))
                .disposable(true)
                .build();
        Structure structure = Structure.of(
                "# # # # # # # # #",
                "# # & # & # & # #",
                "# # # # # # # # #"
        ).ingredient('&', GuiItem.of(Material.DIAMOND).onClick((inventoryClickEvent, player1) -> player1.sendMessage("diament")))
                .ingredient('#', GuiItem.of(Material.BLACK_STAINED_GLASS_PANE).onClick(((inventoryClickEvent, player1) -> player1.sendMessage("glass"))));
        structure.apply(gui);
        player.openInventory(gui.getInventory());
    }
}
