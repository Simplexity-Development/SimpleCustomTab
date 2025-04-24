package simplexity.simplecustomtab.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.Permission;
import simplexity.simplecustomtab.SimpleCustomTab;

import java.util.ArrayList;
import java.util.HashMap;

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
    private boolean animated, sortUsers, permissionFormatting;
    private int delayInTicks, updateTicks;
    private ArrayList<String> animatedHeader = new ArrayList<>();
    private ArrayList<String> animatedFooter = new ArrayList<>();
    private final HashMap<Integer, Permission> sortOrder = new HashMap<>();
    private final HashMap<Permission, String> permissionFormats = new HashMap<>();

    public void loadConfig() {
        SimpleCustomTab.getInstance().reloadConfig();
        FileConfiguration config =  SimpleCustomTab.getInstance().getConfig();
        header = config.getString("header", "<red>Header");
        footer = config.getString("footer", "<gradient:red:gold>-=-=-=-=-=-=-=-=-=-</gradient>");
        playerString = config.getString("player", "<papi:luckperms_prefix> <papi:player_displayname>");
        animated = config.getBoolean("animated.enabled", false);
        delayInTicks = config.getInt("animated.delay", 5);
        updateTicks = config.getInt("update-ticks", 20);
        sortUsers = config.getBoolean("sort-users.enabled", false);
        permissionFormatting = config.getBoolean("permission-formatting.enabled", false);
        animatedHeader = populateStringArrayLists(config,"animated.header");
        animatedFooter = populateStringArrayLists(config,"animated.footer");
    }

    private ArrayList<String> populateStringArrayLists(FileConfiguration config, String path) {
        return (ArrayList<String>) config.getStringList(path);
    }

    private HashMap<Integer, Permission> populateSortOrder(FileConfiguration config, String path) {

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

    public int getUpdateTicks() {
        return updateTicks;
    }
}
