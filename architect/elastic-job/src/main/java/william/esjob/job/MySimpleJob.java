package william.esjob.job;

import com.cxytiandi.elasticjob.annotation.ElasticJobConf;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/18 13:07
 * @Description:简单任务
 */
@ElasticJobConf(name = "MySimpleJob", cron = "0/1 * * * * ?",
        shardingItemParameters = "0=0,1=1", description = "简单任务")
public class MySimpleJob implements SimpleJob {

    public void execute(ShardingContext context) {
        System.err.println("执行MySimpleJob,分片参数：" + context.getShardingParameter());
    }

}
