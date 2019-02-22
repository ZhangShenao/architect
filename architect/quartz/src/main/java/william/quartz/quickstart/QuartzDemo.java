package william.quartz.quickstart;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import william.quartz.jobs.SimpleJob;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/19 15:09
 * @Description:
 */
public class QuartzDemo {
    public static void main(String[] args) throws Exception {
        // Grab the Scheduler instance from the Factory
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // define the job and tie it to our MyJob class
        JobDetail job = newJob(SimpleJob.class)
                .withIdentity("SimpleJob1", "group1")
                .build();

        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever())
                .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);

        // and start it off
        scheduler.start();

        Thread.sleep(Long.MAX_VALUE);

        scheduler.shutdown();
    }
}
