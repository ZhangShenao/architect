package william.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/17 17:07
 * @Description:cron定时任务
 */
public class CronJob implements Job{
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.err.println("Execute Cron Job...");
    }
}
