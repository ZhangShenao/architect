package william.quartz.jobs;

import lombok.Getter;
import lombok.Setter;
import org.quartz.*;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/17 13:32
 * @Description:有状态的Job,记录Job总共执行的次数
 */

//有状态的Job:每次Job执行结束后,Scheduler都会将JobDataMap中数据的修改重新记录下来,而不是每次都创建一job个新的JobDataMap
@PersistJobDataAfterExecution

//对于标记了@PersistJobDataAfterExecution注解的Job,需要考虑加@DisallowConcurrentExecution注解,避免并发修改JobDataMap中的数据
@DisallowConcurrentExecution
@Getter
@Setter
public class CounterJob implements Job {
    private int count;

    public CounterJob() {
        System.err.println("New CounterJob");
    }

    @Override
    public void execute(JobExecutionContext context) {
        //将执行次数+1,并且保存到JobDetail的JobDataMap中
        context.getJobDetail().getJobDataMap().put("count", ++count);

        System.err.println("执行CounterJob,执行次数: " + count);

    }
}
