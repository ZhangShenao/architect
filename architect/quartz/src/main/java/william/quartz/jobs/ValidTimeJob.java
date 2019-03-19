package william.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/17 14:10
 * @Description:在指定时间内执行的Job
 */
public class ValidTimeJob implements Job{
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        //获取触发器的开始和结束时间
        Date startTime = context.getTrigger().getStartTime();
        Date endTime = context.getTrigger().getEndTime();
        System.err.println("Execute ValidTimeJob,startTime: " + format.format(startTime) + ", endTime: " + format.format(endTime));
    }
}
