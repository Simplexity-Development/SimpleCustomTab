package simplexity.simplecustomtab.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import simplexity.simplecustomtab.SimpleCustomTab;
import simplexity.simplecustomtab.util.UpdateTabList;
import simplexity.simplecustomtab.config.ConfigHandler;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        ConfigHandler.getInstance().loadConfig();
        UpdateTabList.updatePlayerList();
        commandSender.sendMessage(SimpleCustomTab.getMiniMessage().deserialize("<green>Reloaded config!"));
        return true;
    }
}
