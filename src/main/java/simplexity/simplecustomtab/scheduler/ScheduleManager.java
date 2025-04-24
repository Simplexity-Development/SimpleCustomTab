package simplexity.simplecustomtab.scheduler;

import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import simplexity.simplecustomtab.SimpleCustomTab;
import simplexity.simplecustomtab.util.UpdateTabList;
import simplexity.simplecustomtab.config.ConfigHandler;

public class ScheduleManager {
    private BukkitTask animationTask;
    private BukkitTask reloadTablistTask;
    private int currentHeaderFrame = 0;
    private int currentFooterFrame = 0;

    private ScheduleManager() {}
    private static ScheduleManager instance;
    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void startAnimation(){
        BukkitScheduler scheduler = SimpleCustomTab.getInstance().getServer().getScheduler();
        if (animationTask != null && !animationTask.isCancelled()) {
            animationTask.cancel();
        }
        animationTask = scheduler.runTaskTimer(SimpleCustomTab.getInstance(),
                this::updateTabListHeaderAndFooter, 0,
                ConfigHandler.getInstance().getDelayInTicks());
    }

    public void startTabListSchedule(){
        BukkitScheduler scheduler = SimpleCustomTab.getInstance().getServer().getScheduler();
        if (reloadTablistTask != null && !reloadTablistTask.isCancelled()) {
            reloadTablistTask.cancel();
        }
        reloadTablistTask = scheduler.runTaskTimer(SimpleCustomTab.getInstance(),
                this::reloadTabList, 0,
                ConfigHandler.getInstance().getUpdateTicks());

    }

    private void updateTabListHeaderAndFooter() {
        incrementFrame();
        String currentHeader;
        String currentFooter;
        if (ConfigHandler.getInstance().isAnimated()) {
            currentHeader = ConfigHandler.getInstance().getAnimatedHeader().get(currentHeaderFrame);
            currentFooter = ConfigHandler.getInstance().getAnimatedFooter().get(currentFooterFrame);
        } else {
            currentHeader = ConfigHandler.getInstance().getHeader();
            currentFooter = ConfigHandler.getInstance().getFooter();
        }
        UpdateTabList.updateHeader(currentHeader);
        UpdateTabList.updateFooter(currentFooter);
    }

    public void incrementFrame() {
        if (currentHeaderFrame + 1 >= ConfigHandler.getInstance().getAnimatedHeader().size()) {
            currentHeaderFrame = 0;
        } else {
            currentHeaderFrame++;
        }
        if (currentFooterFrame + 1 >= ConfigHandler.getInstance().getAnimatedFooter().size()) {
            currentFooterFrame = 0;
        } else {
            currentFooterFrame++;
        }
    }

    public void reloadTabList(){
        UpdateTabList.updatePlayerList();
    }


}
