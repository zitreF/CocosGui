package menu;

import me.cocos.gui.builder.gui.GuiBuilder;
import me.cocos.gui.builder.item.impl.ItemBuilder;
import me.cocos.gui.data.GuiItem;
import me.cocos.gui.structure.Structure;
import me.cocos.gui.gui.Gui;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class TestMenu {

    public void openGui(Player player) {
        Gui gui = GuiBuilder.normal("&7Test", 3)
                .onClick((event, p) -> event.setCancelled(true))
                .disposable(true)
                .structure(Structure.of(
                                "# # # # # # # # #",
                                "# # & # & # & # #",
                                "# # # # # # # # #"
                        )
                        .ingredient('&', GuiItem.of(Material.DIAMOND).onClick((inventoryClickEvent, p) -> {
                            p.sendMessage("diament");
                        }))
                        .ingredient('#', GuiItem.of(Material.BLACK_STAINED_GLASS_PANE).onClick(((inventoryClickEvent, p) -> {
                            p.sendMessage("glass");
                        }))))
                .build();
        gui.open(player);
    }

    public void openSecondGui(Player player) {
        List<GuiItem> randomList = Arrays.stream(Material.values())
                .limit(10)
                .filter(material -> material != Material.AIR)
                .map(GuiItem::of)
                .toList();
        Gui gui = GuiBuilder.normal("&7Test 2", 3)
                .onClick((event, p) -> event.setCancelled(true))
                .disposable(true)
                .structure(Structure.of(
                        "& & & & & & & & &",
                        "& # # # # # # # &",
                        "& & & & & & & & &"
                        )
                        .ingredient('&', GuiItem.of(Material.BLACK_STAINED_GLASS))
                )
                .build();
        gui.open(player);
    }

    public void openThirdGui(Player player) {
        List<GuiItem> randomList = Arrays.stream(Material.values())
                .filter(material -> material != Material.AIR)
                .limit(10)
                .map(GuiItem::of)
                .toList();
        Gui gui = GuiBuilder.normal("&7Test 2", 3)
                .onClick((event, p) -> event.setCancelled(true))
                .disposable(true)
                .build();
        gui.setItem(0, ItemBuilder.from(Material.TERRACOTTA).asGuiItem());
        gui.open(player);
    }
}
