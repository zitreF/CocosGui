package me.cocos.gui.builder.item.impl;

import me.cocos.gui.builder.item.Builder;
import org.bukkit.Material;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public final class PotionBuilder extends Builder<PotionMeta> {

    public PotionBuilder(Type type) {
        super(type.material);
    }

    public PotionBuilder potionType(PotionType potionType) {
        this.meta.setBasePotionData(new PotionData(potionType));
        return this;
    }

    public enum Type {
        POTION(Material.POTION),
        LINGERING(Material.LINGERING_POTION),
        SPLASH(Material.SPLASH_POTION);

        private final Material material;

        Type(Material material) {
            this.material = material;
        }
    }
}
