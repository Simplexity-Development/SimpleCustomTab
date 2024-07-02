package simplexity.simplecustomtab.config;

import org.bukkit.configuration.file.FileConfiguration;
import simplexity.simplecustomtab.SimpleCustomTab;

import java.util.ArrayList;

public class ConfigHandler {
    private static ConfigHandler instance;

    public ConfigHandler() {}
    public static ConfigHandler getInstance() {
        if (instance == null) {
            instance = new ConfigHandler();
        }
        return instance;
    }
    private String header, footer, playerString;
    private boolean animated;
    private int delayInTicks;
    private ArrayList<String> animatedHeader = new ArrayList<>(), animatedFooter = new ArrayList<>();
    public void loadConfig() {
        SimpleCustomTab.getInstance().reloadConfig();
        FileConfiguration config =  SimpleCustomTab.getInstance().getConfig();
        header = config.getString("header", "<red>Header");
        footer = config.getString("footer", "<gradient:red:gold>-=-=-=-=-=-=-=-=-=-</gradient>");
        playerString = config.getString("player", "<papi:luckperms_prefix> <papi:player_displayname>");
        animated = config.getBoolean("animated.enabled", false);
        delayInTicks = config.getInt("animated.delay", 5);
        animatedHeader = (ArrayList<String>) config.getStringList("animated.header");
        animatedFooter = (ArrayList<String>) config.getStringList("animated.footer");
    }
    public String getHeader() {
        return header;
    }
    public String getFooter() {
        return footer;
    }
    public String getPlayerString() {
        return playerString;
    }

    public boolean isAnimated() {
        return animated;
    }

    public int getDelayInTicks() {
        return delayInTicks;
    }

    public ArrayList<String> getAnimatedHeader() {
        return animatedHeader;
    }

    public ArrayList<String> getAnimatedFooter() {
        return animatedFooter;
    }
}
