package me.cocos.gui.helper;

import org.bukkit.ChatColor;

import java.util.List;

public final class ChatHelper {

    private ChatHelper() {
        throw new UnsupportedOperationException("You cannot do this!");
    }

    public static String colored(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> colored(List<String> lines) {
        return lines.stream().map(ChatHelper::colored).toList();
    }
}
