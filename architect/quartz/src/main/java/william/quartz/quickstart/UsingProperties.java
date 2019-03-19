package william.quartz.quickstart;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.util.ResourceUtils;
import william.quartz.jobs.SimpleJob;

import java.util.Properties;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/17 19:39
 * @Description:使用自定义Properties文件初始化Shceduler
 */
public class UsingProperties {
    public static void main(String[] args) throws Exception {
        //加载quartz.properties配置文件
        Properties props = new Properties();
        props.load(ResourceUtils.getURL("classpath:quartz.properties").openStream());

        //使用指定的配置文件,创建Scheduler
        StdSchedulerFactory factory = new StdSchedulerFactory();
        factory.initialize(props);
        Scheduler scheduler = factory.getScheduler();
        System.err.println("SchedulerName: " + scheduler.getSchedulerName());

        JobDetail job = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("SimpleJob1", "group1")
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
