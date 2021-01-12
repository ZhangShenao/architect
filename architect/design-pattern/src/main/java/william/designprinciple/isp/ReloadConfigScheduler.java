package william.designprinciple.isp;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangshenao
 * @Date 2021-01-12
 * @Description 配置热更新定时器
 */
public class ReloadConfigScheduler {
    private long initDelay;
    private long fixedDelay;
    private ReloadableResource config;
    private ScheduledExecutorService scheduler;

    public ReloadConfigScheduler(long initDelay, long fixedDelay, ReloadableResource config) {
        this.initDelay = initDelay;
        this.fixedDelay = fixedDelay;
        this.config = config;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    public void run() {
        scheduler.scheduleWithFixedDelay(config::reload, initDelay, fixedDelay, TimeUnit.MILLISECONDS);
    }
}
