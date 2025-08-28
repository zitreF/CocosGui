package me.cocos.gui.builder.item.impl;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.cocos.gui.builder.item.Builder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public final class SkullBuilder extends Builder<SkullMeta, SkullBuilder> {

    public SkullBuilder() {
        super(Material.PLAYER_HEAD);
    }

    public SkullBuilder owner(String name) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayerIfCached(name);
        if (offlinePlayer != null) {
            meta.setOwningPlayer(offlinePlayer);
        }
        return this;
    }

    public SkullBuilder owner(UUID uuid) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
        meta.setOwningPlayer(offlinePlayer);
        return this;
    }

    public SkullBuilder texture(String base64texture) {

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", base64texture));

        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return this;
    }

    public static SkullBuilder fromTexture(String base64texture) {
        SkullBuilder skullBuilder = new SkullBuilder();
        skullBuilder.texture(base64texture);
        return skullBuilder;
    }

    public static SkullBuilder fromOwner(String owner) {
        SkullBuilder skullBuilder = new SkullBuilder();
        skullBuilder.owner(owner);
        return skullBuilder;
    }

    @Override
    public SkullBuilder self() {
        return this;
    }
}
