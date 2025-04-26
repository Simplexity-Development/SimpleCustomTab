package simplexity.simplecustomtab.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import simplexity.simplecustomtab.SimpleCustomTab;
import simplexity.simplecustomtab.config.ConfigHandler;
import simplexity.simplecustomtab.objects.TabFormat;

import java.util.HashSet;

public class UpdateTabList {

    private static final MiniMessage miniMessage = SimpleCustomTab.getMiniMessage();

    public static void updatePlayerList() {
        if (SimpleCustomTab.hasPAPI()) {
            SimpleCustomTab.getInstance().getServer().getOnlinePlayers().forEach(player -> {
                Component playerTab = miniMessage.deserialize(
                        getFormatString(player),
                        ParseUtils.papiTag(player),
                        ParseUtils.defaultTags(player));
                player.playerListName(playerTab);
            });
        } else {
            SimpleCustomTab.getInstance().getServer().getOnlinePlayers().forEach(player -> {
                Component playerTab = miniMessage.deserialize(
                        getFormatString(player),
                        ParseUtils.defaultTags(player));
                player.playerListName(playerTab);
            });
        }
    }

    public static void updateHeader(String string) {
        Component header;
        if (SimpleCustomTab.hasPAPI()) {
            header = miniMessage.deserialize(string, ParseUtils.papiTag(null));
        } else {
            header = miniMessage.deserialize(string);
        }
        SimpleCustomTab.getInstance().getServer().sendPlayerListHeader(header);
    }

    public static void updateFooter(String string) {
        Component footer;
        if (SimpleCustomTab.hasPAPI()) {
            footer = miniMessage.deserialize(string, ParseUtils.papiTag(null));
        } else {
            footer = miniMessage.deserialize(string);
        }
        SimpleCustomTab.getInstance().getServer().sendPlayerListFooter(footer);
    }

    private static String getFormatString(Player player){
        String defaultFormat = ConfigHandler.getInstance().getPlayerString();
        if (!ConfigHandler.getInstance().isPermissionFormatting()) {
            return defaultFormat;
        }
        int currentPriority = 0;
        String currentFormat = defaultFormat;
        HashSet<TabFormat> tabFormats = ConfigHandler.getInstance().getTabFormats();
        for (TabFormat format : tabFormats) {
            if (!player.hasPermission(format.permission())) continue;
            if (currentPriority < format.priority()) {
                currentFormat = format.format();
                currentPriority = format.priority();
            }
        }
        return currentFormat;
    }
}
