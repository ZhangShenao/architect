package william.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author zhangshenao
 * @Date 2019-09-23
 * @Description 分布式计数器
 */
public class TestDistributedCounter {
    private CuratorFramework client;

    @Before
    public void createConnection() {
        //创建被配置CuratorFramework实例
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3, 60000);
        client = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .connectionTimeoutMs(3000)
                .sessionTimeoutMs(60000)
                .retryPolicy(retryPolicy)
                .build();

        client.start();
    }

    @Test
    public void testDistributedCounter() throws InterruptedException {
        String counterPath = "/distributed_counter";

        DistributedAtomicInteger counter = new DistributedAtomicInteger(client, counterPath, new RetryNTimes(3, 1000));
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                try {
                    counter.add(1);
                    System.err.println("success: " + counter.get().succeeded());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(Long.MAX_VALUE);

    }
}
