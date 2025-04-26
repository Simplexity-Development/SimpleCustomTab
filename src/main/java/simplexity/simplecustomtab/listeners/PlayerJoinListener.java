package simplexity.simplecustomtab.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.Permission;
import simplexity.simplecustomtab.config.ConfigHandler;

import java.util.HashMap;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent joinEvent){
        Player player = joinEvent.getPlayer();
        HashMap<Permission, Integer> permMap = ConfigHandler.getInstance().getSortOrder();
        for (Permission perm : permMap.keySet()) {
            int permOrder = permMap.get(perm);
            if (player.getPlayerListOrder() > permOrder) continue;
            if (player.hasPermission(perm)) {
                player.setPlayerListOrder(permMap.get(perm));
            }
        }
    }
}
