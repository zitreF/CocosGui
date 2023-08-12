package me.cocos.gui.builder.item;

import me.cocos.gui.builder.item.impl.ItemBuilder;
import me.cocos.gui.data.GuiItem;
import me.cocos.gui.helper.ChatHelper;
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

    public Builder(Material material) {
        this.itemStack = new ItemStack(material);
        this.meta = (M) itemStack.getItemMeta();
    }

    public T lore(List<String> lore) {
        this.meta.setLore(lore.stream().map(ChatHelper::colored).collect(Collectors.toList()));
        return this.self();
    }

    public T lore(String... lore) {
        this.meta.setLore(Arrays.stream(lore).map(ChatHelper::colored).collect(Collectors.toList()));
        return this.self();
    }

    public T type(Material material) {
        this.itemStack.setType(material);
        return this.self();
    }

    public T addLore(String lore) {
        List<String> lores = this.meta.getLore() == null ? new ArrayList<>() : this.meta.getLore();
        lores.add(ChatHelper.colored(lore));
        this.meta.setLore(lores);
        return this.self();
    }

    public T name(String name) {
        this.meta.setDisplayName(ChatHelper.colored(name));
        return this.self();
    }

    public T amount(int amount) {
        this.itemStack.setAmount(amount);
        return this.self();
    }

    public T enchantment(Enchantment enchantment, int level) {
        this.meta.addEnchant(enchantment, level, true);
        return this.self();

    }
    public T flag(ItemFlag... flags) {
        this.meta.addItemFlags(flags);
        return this.self();
    }

    public GuiItem asGuiItem() {
        return new GuiItem(this.build());
    }

    public ItemStack build() {
        this.itemStack.setItemMeta(meta);
        return itemStack;
    }

    public abstract T self();
}
