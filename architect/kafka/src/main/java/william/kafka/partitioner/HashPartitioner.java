package william.kafka.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.Map;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/7 09:29
 * @Description: 基于Hash算法的Partitioner
 */
public class HashPartitioner implements Partitioner{
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int idx = 0;
        if (!CollectionUtils.isEmpty(partitions)){
            idx = Math.abs(key.hashCode() % partitions.size());
        }
        return idx;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
