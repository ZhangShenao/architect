package william.kafka.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/25 13:48
 * @Description:自定义Partitioner
 */
public class AuditPartitioner implements Partitioner {
    public static final String AUDIT_KEY = "audit";

    private static Random random;

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        String k = (String) key;
        //获取所有可用的分区
        List<PartitionInfo> partitions = cluster.availablePartitionsForTopic(topic);
        int partitionSize = partitions.size();
        int auditPartition = partitionSize - 1;
        //将包含audit字符串的key发往最后一个分区,其他的key按照随机策略分配分区
        return (!StringUtils.isEmpty(k) && k.contains(AUDIT_KEY)) ? auditPartition : random.nextInt(partitionSize > 1 ? partitionSize - 1 : partitionSize);
    }

    @Override
    public void close() {
        //TODO 该方法执行资源的释放操作
    }

    @Override
    public void configure(Map<String, ?> configs) {
        random = new Random();
        //TODO 该方法执行必要资源的初始化工作
    }
}
