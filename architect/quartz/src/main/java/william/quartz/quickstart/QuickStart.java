package william.quartz.quickstart;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import william.quartz.jobs.SimpleJob;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/19 15:09
 * @Description:
 */
public class QuickStart {
    public static void main(String[] args) throws Exception {
        //通过工厂获取Scheduler实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //通过JobBuilder创建Job实例JobDetail
        //自定义的Job类定义了任务的执行逻辑,而JobDetail代表一个运行中的唯一的Job实例
        JobDetail job = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("SimpleJob1", "group1")
                .usingJobData("city","BeiJing")
                .build();

        //通过TriggerBuilder创建Trigger,Trigger定义了指定Job的触发方式
        //一个JobDetail可以指定多种Trigger方式,但是一个Trigger只能被指派给一个JobDetail
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever())
                .build();

        //将Scheduler与Trigger关联起来,通过Scheduler去调度任务
        scheduler.scheduleJob(job, trigger);

        //启动Scheduler
        scheduler.start();

        Thread.sleep(Long.MAX_VALUE);

        //关闭Scheduler
        scheduler.shutdown();
    }
}
