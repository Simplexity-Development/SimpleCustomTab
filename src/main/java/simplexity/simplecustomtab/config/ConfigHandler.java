package simplexity.simplecustomtab.config;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.Permission;
import simplexity.simplecustomtab.SimpleCustomTab;
import simplexity.simplecustomtab.objects.TabFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Logger;

public class ConfigHandler {
    private static ConfigHandler instance;

    public ConfigHandler() {}
    public static ConfigHandler getInstance() {
        if (instance == null) {
            instance = new ConfigHandler();
        }
        return instance;
    }
    private final Logger logger = SimpleCustomTab.getInstance().getLogger();
    private String header, footer, playerString;
    private boolean animated, sortUsers, permissionFormatting;
    private int delayInTicks, updateTicks;
    private ArrayList<String> animatedHeader = new ArrayList<>();
    private ArrayList<String> animatedFooter = new ArrayList<>();
    private final HashMap<Permission, Integer> sortOrder = new HashMap<>();
    private final HashSet<TabFormat> tabFormats = new HashSet<>();

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
        if (sortUsers) populateSortOrder(config);
        if (permissionFormatting) populatePermissionFormats(config);
    }

    private ArrayList<String> populateStringArrayLists(FileConfiguration config, String path) {
        return (ArrayList<String>) config.getStringList(path);
    }

    private void populateSortOrder(FileConfiguration config) {
        sortOrder.clear();
        ConfigurationSection section = config.getConfigurationSection("sort-users.sort-order");
        if (section == null) {
            logger.warning("No 'sort order' section found, skipping config section. Please check that the syntax is correct and that SPACE was used and not TAB");
            return;
        }
        for (String key : section.getKeys(false)) {
            int order;
            try {
                order = Integer.parseInt(key);
            } catch (NumberFormatException e) {
                logger.warning("The sort order of '" + key + "' is not valid. The key must be a valid integer. Skipping");
                continue;
            }
            String permissionString = section.getString(key);
            if (permissionString == null) {
                logger.warning("No permission provided for '" + key + "', skipping");
                continue;
            }
            Permission permission = new Permission(permissionString);
            sortOrder.put(permission, order);
        }
    }

    private void populatePermissionFormats(FileConfiguration config){
        tabFormats.clear();
        ConfigurationSection section = config.getConfigurationSection("permission-formatting.formats");
        if (section == null) {
            logger.warning("No configuration section found for permission based formats. Please check that your syntax is correct and SPACE was used and not TAB");
            return;
        }
        for (String format : section.getKeys(false)) {
            String permissionString = section.getString(format + ".permission");
            String formatString = section.getString(format + ".format");
            int priority = section.getInt(format + ".priority");
            if (permissionString == null || permissionString.isEmpty()) {
                logger.warning("No permission found for permission format: " + format);
                continue;
            }
            if (formatString == null || formatString.isEmpty()) {
                logger.warning("No format string found for format: " + format);
                continue;
            }
            Permission permission = new Permission(permissionString);
            TabFormat tabFormat = new TabFormat(permission, formatString, priority);
            tabFormats.add(tabFormat);
        }
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

    public boolean isSortUsers() {
        return sortUsers;
    }

    public boolean isPermissionFormatting() {
        return permissionFormatting;
    }

    public HashMap<Permission, Integer> getSortOrder(){
        return sortOrder;
    }

    public HashSet<TabFormat> getTabFormats(){
        return tabFormats;
    }
}
