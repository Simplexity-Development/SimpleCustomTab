package simplexity.simplecustomtab.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import simplexity.simplecustomtab.SimpleCustomTab;
import simplexity.simplecustomtab.config.ConfigHandler;

public class UpdateTabList {

    private static final MiniMessage miniMessage = SimpleCustomTab.getMiniMessage();

    public static void updatePlayerList() {
        if (SimpleCustomTab.hasPAPI()) {
            SimpleCustomTab.getInstance().getServer().getOnlinePlayers().forEach(player -> {
                Component playerTab = miniMessage.deserialize(ConfigHandler.getInstance().getPlayerString(), PAPIParse.papiTag(player));
                player.playerListName(playerTab);
            });
        } else {
            SimpleCustomTab.getInstance().getServer().getOnlinePlayers().forEach(player -> {
                Component playerTab = miniMessage.deserialize(ConfigHandler.getInstance().getPlayerString());
                player.playerListName(playerTab);
            });
        }
    }

    public static void updateHeader(String string) {
        Component header;
        if (SimpleCustomTab.hasPAPI()) {
            header = miniMessage.deserialize(string, PAPIParse.papiTag(null));
        } else {
            header = miniMessage.deserialize(string);
        }
        SimpleCustomTab.getInstance().getServer().sendPlayerListHeader(header);
    }

    public static void updateFooter(String string) {
        Component footer;
        if (SimpleCustomTab.hasPAPI()) {
            footer = miniMessage.deserialize(string, PAPIParse.papiTag(null));
        } else {
            footer = miniMessage.deserialize(string);
        }
        SimpleCustomTab.getInstance().getServer().sendPlayerListFooter(footer);
    }
}
