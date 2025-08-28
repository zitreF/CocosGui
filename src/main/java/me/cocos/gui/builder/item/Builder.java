package me.cocos.gui.builder.item;

import me.cocos.gui.builder.item.impl.ItemBuilder;
import me.cocos.gui.data.GuiItem;
import me.cocos.gui.helper.ChatHelper;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Builder<M extends ItemMeta, T extends Builder<M, T>> {

    protected final M meta;
    private final ItemStack itemStack;

    @SuppressWarnings("unchecked")
    public Builder(Material material) {
        this.itemStack = new ItemStack(material);
        this.meta = (M) itemStack.getItemMeta();
    }

    public T lore(List<String> lore) {
        meta.lore(lore.stream().map(ChatHelper::colored).collect(Collectors.toList()));
        return this.self();
    }

    public T lore(String... lore) {
        meta.lore(Arrays.stream(lore).map(ChatHelper::colored).collect(Collectors.toList()));
        return this.self();
    }

    public T type(Material material) {
        itemStack.setType(material);
        return this.self();
    }

    public T addLore(String lore) {
        List<Component> lores = meta.lore() == null ? new ArrayList<>() : meta.lore();
        lores.add(ChatHelper.colored(lore));
        meta.lore(lores);
        return this.self();
    }

    public T name(String name) {
        meta.displayName(ChatHelper.colored(name));
        return this.self();
    }

    public T amount(int amount) {
        itemStack.setAmount(amount);
        return this.self();
    }

    public T enchantment(Enchantment enchantment, int level) {
        meta.addEnchant(enchantment, level, true);
        return this.self();

    }
    public T flag(ItemFlag... flags) {
        meta.addItemFlags(flags);
        return this.self();
    }

    public GuiItem asGuiItem() {
        return new GuiItem(this.build());
    }

    public ItemStack build() {
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public abstract T self();
}
