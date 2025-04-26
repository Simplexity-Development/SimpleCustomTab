package simplexity.simplecustomtab.util;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import simplexity.simplecustomtab.SimpleCustomTab;

public class ParseUtils {
    private static final MiniMessage miniMessage = SimpleCustomTab.getMiniMessage();
    //Credit: https://docs.advntr.dev/faq.html#how-can-i-use-bukkits-placeholderapi-in-minimessage-messages

    /**
     * Creates a tag resolver capable of resolving PlaceholderAPI tags for a given player.
     *
     * @param player the player
     *
     * @return the tag resolver
     */
    public static TagResolver papiTag(Player player) {
        return TagResolver.resolver("papi", (argumentQueue, context) -> {
            final String papiPlaceholder = argumentQueue.popOr("-error, PAPIParse line 25").value();
            final String parsedPlaceholder = PlaceholderAPI.setPlaceholders(player, '%' + papiPlaceholder + '%');
            Component componentPlaceholder;
            if (parsedPlaceholder.contains("§")) {
                componentPlaceholder = LegacyComponentSerializer.legacySection().deserialize(parsedPlaceholder);
            } else {
                componentPlaceholder = miniMessage.deserialize(parsedPlaceholder);
            }
            return Tag.selfClosingInserting(componentPlaceholder);
        });
    }

    public static TagResolver defaultTags(@NotNull Player player) {
        return TagResolver.resolver(
                Placeholder.component("displayname", player.displayName()),
                Placeholder.unparsed("username", player.getName()));
    }
}
