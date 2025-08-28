package me.cocos.gui.helper;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;

import java.util.Collection;
import java.util.List;

public final class ChatHelper {

    public static final LegacyComponentSerializer SERIALIZER =
            LegacyComponentSerializer
                    .builder()
                    .hexColors()
                    .extractUrls()
                    .hexCharacter('#')
                    .character('&')
                    .useUnusualXRepeatedCharacterHexFormat()
                    .build();

    private ChatHelper() {
        throw new UnsupportedOperationException("You cannot do this!");
    }

    public static String coloredLegacy(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static Component colored(String text) {
        return SERIALIZER.deserialize(text);
    }

    public static List<Component> colored(Collection<String> lines) {
        return lines.stream().map(ChatHelper::colored).toList();
    }

    public static List<String> coloredLegacy(Collection<String> lines) {
        return lines.stream().map(ChatHelper::coloredLegacy).toList();
    }
}
