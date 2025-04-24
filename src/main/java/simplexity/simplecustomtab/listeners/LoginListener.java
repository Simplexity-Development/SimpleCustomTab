package simplexity.simplecustomtab.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import simplexity.simplecustomtab.SimpleCustomTab;
import simplexity.simplecustomtab.util.UpdateTabList;

public class LoginListener implements Listener {
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        SimpleCustomTab.getInstance()
                .getServer()
                .getScheduler()
                .runTaskLater(SimpleCustomTab.getInstance(), UpdateTabList::updatePlayerList, 20);

    }
}
