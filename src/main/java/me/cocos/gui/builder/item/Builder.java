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

public abstract class Builder<M extends ItemMeta> {

    protected final M meta;
    private final ItemStack itemStack;

    public Builder(Material material) {
        this.itemStack = new ItemStack(material);
        this.meta = (M) itemStack.getItemMeta();
    }

    public Builder<M> lore(List<String> lore) {
        this.meta.setLore(lore.stream().map(ChatHelper::colored).collect(Collectors.toList()));
        return this;
    }

    public Builder<M> lore(String... lore) {
        this.meta.setLore(Arrays.stream(lore).map(ChatHelper::colored).collect(Collectors.toList()));
        return this;
    }

    public Builder<M> addLore(String lore) {
        List<String> lores = this.meta.getLore() == null ? new ArrayList<>() : this.meta.getLore();
        lores.add(ChatHelper.colored(lore));
        this.meta.setLore(lores);
        return this;
    }

    public Builder<M> name(String name) {
        this.meta.setDisplayName(ChatHelper.colored(name));
        return this;
    }

    public Builder<M> amount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public Builder<M> enchantment(Enchantment enchantment, int level) {
        this.meta.addEnchant(enchantment, level, true);
        return this;

    }
    public Builder<M> flag(ItemFlag... flags) {
        this.meta.addItemFlags(flags);
        return this;
    }

    public GuiItem asGuiItem() {
        return new GuiItem(this.build());
    }

    public ItemStack build() {
        this.itemStack.setItemMeta(meta);
        return itemStack;
    }

}
