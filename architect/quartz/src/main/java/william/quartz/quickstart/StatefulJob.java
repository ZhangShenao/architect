package william.quartz.quickstart;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import william.quartz.jobs.CounterJob;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/17 13:43
 * @Description:执行有状态的Job
 */
public class StatefulJob {
    public static void main(String[] args) throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail job = JobBuilder.newJob(CounterJob.class)
                .withIdentity("CountJob", "group1")
                .usingJobData("count",0)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever())
                .build();

        scheduler.scheduleJob(job, trigger);

        scheduler.start();

        Thread.sleep(Long.MAX_VALUE);

        scheduler.shutdown();
    }
}
