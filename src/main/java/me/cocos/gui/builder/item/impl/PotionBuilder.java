package me.cocos.gui.builder.item.impl;

import me.cocos.gui.builder.item.Builder;
import org.bukkit.Material;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public final class PotionBuilder extends Builder<PotionMeta, PotionBuilder> {

    public PotionBuilder(Type type) {
        super(type.material);
    }

    public PotionBuilder potionType(PotionType potionType) {
        meta.setBasePotionData(new PotionData(potionType));
        return this;
    }

    @Override
    public PotionBuilder self() {
        return this;
    }

    public static PotionBuilder from(Type type) {
        return new PotionBuilder(type);
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
