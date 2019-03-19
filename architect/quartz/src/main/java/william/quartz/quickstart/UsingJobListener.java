package william.quartz.quickstart;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.impl.matchers.KeyMatcher;
import william.quartz.jobs.SimpleJob;
import william.quartz.listener.SimpleJobListener;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/17 20:08
 * @Description:使用Job监听器
 */
public class UsingJobListener {
    public static void main(String[] args) throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail job = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("SimpleJob1", "group1")
                .build();


        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1))
                .build();

        scheduler.scheduleJob(job, trigger);

        scheduler.start();

        //注册全局的JobListener
        scheduler.getListenerManager().addJobListener(new SimpleJobListener(), EverythingMatcher.allJobs());

        //注册局部的JobListener
        scheduler.getListenerManager().addJobListener(new SimpleJobListener(), KeyMatcher.keyEquals(JobKey.jobKey("SimpleJob2","group1")));
        Thread.sleep(Long.MAX_VALUE);

        scheduler.shutdown();
    }
}
