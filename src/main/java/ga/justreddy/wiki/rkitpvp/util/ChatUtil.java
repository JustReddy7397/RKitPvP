package ga.justreddy.wiki.rkitpvp.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class ChatUtil {

    public static String format(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static List<String> format(List<String> input) {
        List<String> list = new ArrayList<>();
        input.forEach(s -> list.add(format(s)));
        return list;
    }

}
