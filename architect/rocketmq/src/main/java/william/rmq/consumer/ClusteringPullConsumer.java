package william.rmq.consumer;

import org.apache.rocketmq.client.consumer.*;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import william.rmq.common.RocketMQConstants;
import java.util.List;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/4 09:54
 * @Description:集群模式的消费者,采用Pull方式拉取消息
 */
public class ClusteringPullConsumer {
    private static final int PULL_MESSAGE_MAX_SIZE_EACH_TIME = 32;
    private static final int PULL_MESSAGE_INTERVAL_MILLIS = 3000;

    public static void main(String[] args) throws Exception {
        //创建MQPullConsumerScheduleService,拉取消息的定时任务
        MQPullConsumerScheduleService pullConsumerScheduleService = new MQPullConsumerScheduleService(RocketMQConstants.PULL_CONSUMER_GROUP);

        //设置NameServer地址
        pullConsumerScheduleService.getDefaultMQPullConsumer().setNamesrvAddr(RocketMQConstants.NAMESRV_ADDR);

        //设置为集群消费模式
        pullConsumerScheduleService.setMessageModel(MessageModel.CLUSTERING);

        //注册拉取消息回调
        pullConsumerScheduleService.registerPullTaskCallback(RocketMQConstants.TOPIC_NAME, (mq, context) -> {
            MQPullConsumer consumer = context.getPullConsumer();
            try {
                //获取当前拉取到的offset
                long offset = consumer.fetchConsumeOffset(mq, false);
                if (offset < 0) {
                    offset = 0;
                }
                //尝试拉取消息,返回拉取的结果
                PullResult pullResult = consumer.pull(mq, "*", offset, PULL_MESSAGE_MAX_SIZE_EACH_TIME);
                switch (pullResult.getPullStatus()) {
                    //拉取到消息
                    case FOUND:
                        List<MessageExt> msgs = pullResult.getMsgFoundList();
                        for (MessageExt msg : msgs) {
                            //消费消息
                            System.err.println("拉取到消息: " + msg);
                        }
                        break;

                    //没有匹配的消息
                    case NO_MATCHED_MSG:
                        System.err.println("没有匹配的消息");
                        break;

                    //没有新的消息
                    case NO_NEW_MSG:
                        System.err.println("没有新的消息");
                        break;

                    //offset非法
                    case OFFSET_ILLEGAL:
                        System.err.println("offset非法");
                        break;
                    default:
                        break;
                }

                //向Broker更新拉取消息的offset
                consumer.updateConsumeOffset(mq, pullResult.getNextBeginOffset());

                //设置再过3000ms后重新拉取
                context.setPullNextDelayTimeMillis(PULL_MESSAGE_INTERVAL_MILLIS);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //启动MQPullConsumerScheduleService
        pullConsumerScheduleService.start();
    }
}
