package william.quartz.quickstart;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import william.quartz.jobs.CronJob;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/17 14:48
 * @Description:使用CronTrigger
 */
public class CronTrigger {
    public static void main(String[] args) throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail job = JobBuilder.newJob(CronJob.class)
                .withIdentity("CronJob", "group1")
                .build();

        //使用CronTrigger
        org.quartz.CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                //每5秒执行一次
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();

        scheduler.scheduleJob(job, trigger);

        scheduler.start();

        Thread.sleep(Long.MAX_VALUE);

        scheduler.shutdown();
    }
}
