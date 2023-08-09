package me.cocos.gui.helper;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ChatHelper {

    private static final Pattern HEX_PATTERN = Pattern.compile("&#(\\w{5}[0-9a-f])");


    private ChatHelper() {
        throw new UnsupportedOperationException("You cannot do this!");
    }

    public static String colored(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String hex(String text) {
        Matcher matcher = HEX_PATTERN.matcher(text);

        StringBuilder builder = new StringBuilder();

        while (matcher.find()) {
            matcher.appendReplacement(builder, net.md_5.bungee.api.ChatColor.of("#" + matcher.group(1)).toString());
        }

        return ChatColor.translateAlternateColorCodes('&', matcher.appendTail(builder).toString());

    }

    public static List<String> colored(List<String> lines) {
        return lines.stream().map(ChatHelper::colored).toList();
    }

    public static List<String> hex(List<String> lines) {
        return lines.stream().map(ChatHelper::hex).toList();
    }
}
