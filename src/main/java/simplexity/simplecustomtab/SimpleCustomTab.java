package simplexity.simplecustomtab;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;
import simplexity.simplecustomtab.commands.ReloadCommand;
import simplexity.simplecustomtab.config.ConfigHandler;
import simplexity.simplecustomtab.listeners.LoginListener;
import simplexity.simplecustomtab.scheduler.ScheduleManager;

public final class SimpleCustomTab extends JavaPlugin {

    private final static MiniMessage miniMessage = MiniMessage.miniMessage();

    private static boolean PAPI = false;
    private static SimpleCustomTab instance;

    public static boolean hasPAPI() {
        return PAPI;
    }

    public static SimpleCustomTab getInstance() {
        return instance;
    }


    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        ConfigHandler.getInstance().loadConfig();
        if (instance.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            PAPI = true;
        }
        instance.getServer().getPluginManager().registerEvents(new LoginListener(), instance);
        instance.getCommand("sctreload").setExecutor(new ReloadCommand());
        ScheduleManager.getInstance().startAnimation();
        ScheduleManager.getInstance().startTabListSchedule();
        // Plugin startup logic

    }
    public static MiniMessage getMiniMessage() {
        return miniMessage;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
