package william.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/17 20:03
 * @Description:简单的Job监听器
 */
public class SimpleJobListener implements JobListener {
    @Override
    public String getName() {
        return "SimpleJobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        String jobName = context.getJobDetail().getKey().getName();
        System.err.println("Job To Be Executed,jobName: " + jobName);
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        String jobName = context.getJobDetail().getKey().getName();
        System.err.println("Job Execution Vetoed,jobName: " + jobName);
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        String jobName = context.getJobDetail().getKey().getName();
        System.err.println("Job Was Executed,jobName: " + jobName);
    }
}
