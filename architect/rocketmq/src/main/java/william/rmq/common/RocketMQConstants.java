package william.rmq.common;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/12/6 23:42
 * @Description:
 */
public interface RocketMQConstants {
    String TOPIC_NAME = "Test-Topic";
    String NAMESRV_ADDR = "127.0.0.1:9876";

    String PRODUCER_GROUP = "Test-Producer-Group";
    String CONSUMER_GROUP = "Test-Consumer-Group";
    String PULL_CONSUMER_GROUP = "Test-Pull-Consumer-Group";

    int RETRY_TIMES_WHEN_SEND_FAILED = 5;

    int SNED_TIME_OUT_MILLIS = 5 * 1000;
}
