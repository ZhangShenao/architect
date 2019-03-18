package william.quartz.jobs;

import org.quartz.*;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/19 15:10
 * @Description:
 */
public class SimpleJob implements Job{
    @Override
    public void execute(JobExecutionContext context) {
        System.err.println("Execute SimpleJob");

        //可以根据JobExecutionContext获取Job执行时的上下文信息
        Trigger trigger = context.getTrigger();
        JobDetail jobDetail = context.getJobDetail();
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        System.err.println("TriggerName: " + trigger.getKey().getName());
        System.err.println("JobName: " + jobDetail.getKey().getName());
        System.err.println("Data: " + jobDataMap.getString("city"));
    }
}
