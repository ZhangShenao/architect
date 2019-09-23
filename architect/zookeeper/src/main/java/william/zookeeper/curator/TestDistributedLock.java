package william.zookeeper.curator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author zhangshenao
 * @Date 2019-09-23
 * @Description 分布式锁
 */
public class TestDistributedLock {
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
    public void testDistributedLock() throws InterruptedException {
        //使用
        String lockPath = "/distributed_lock";
        InterProcessMutex lock = new InterProcessMutex(client, lockPath);

        //模拟时间戳生成订单号
        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                try {
                    latch.await();
                    lock.acquire();
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderId = format.format(new Date());
                    System.err.println("生成订单号: " + orderId);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        lock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        latch.countDown();
        Thread.sleep(Long.MAX_VALUE);
    }
}
