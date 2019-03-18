package william.quartz.quickstart;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import william.quartz.jobs.ValidTimeJob;
import java.util.Date;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/17 14:09
 * @Description:指定Job的开启和结束时间
 */
public class AssignJobStartAndEndTime {
    public static void main(String[] args) throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail job = JobBuilder.newJob(ValidTimeJob.class)
                .withIdentity("CountJob", "group1")
                .build();

        long now = System.currentTimeMillis();
        Date startTime = new Date(now + 3000L);
        Date endTime = new Date(now + 10000L);

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")

                //指定触发的开始和结束时间
                .startAt(startTime)
                .endAt(endTime)

                //设置每秒触发1次,且最多重复触发3次(一共4次)
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1).withRepeatCount(3))
                .build();

        scheduler.scheduleJob(job, trigger);

        scheduler.start();

        Thread.sleep(Long.MAX_VALUE);

        scheduler.shutdown();
    }
}
