package william.kafka.rebalance;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;

/**
 * @Author: ZhangShenao
 * @Date: 2019/5/28 18:00
 * @Description:自定义Rebalance监听器
 */
public class SimpleRebalanceListener implements ConsumerRebalanceListener{
    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        //当开启新一轮Rebalance前调用
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        //当Rebalance完成后调用
    }
}
