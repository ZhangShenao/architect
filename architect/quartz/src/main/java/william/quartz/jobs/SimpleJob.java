package william.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/19 15:10
 * @Description:
 */
public class SimpleJob implements Job{
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.err.println("Execute SimpleJob");
    }
}
