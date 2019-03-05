package william.zookeeper.client.curator;


import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;
import william.zookeeper.constant.ZookeeperConstant;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/5 15:38
 * @Description:Curator接口异步操作
 */
public class CuratorAsyncOperations {
    private CuratorFramework client;

    private CountDownLatch latch = new CountDownLatch(2);
    private ExecutorService executor = Executors.newFixedThreadPool(2);


    @Before
    public void init() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder()
                .connectString(ZookeeperConstant.ZOOKEEPER_URL)
                .connectionTimeoutMs(ZookeeperConstant.CONNECTION_TIMEOUT_MILLIS)
                .sessionTimeoutMs(ZookeeperConstant.SESSION_TIMEOUT_MILLIS)
                .retryPolicy(retryPolicy)
                .build();
        client.start();
    }

    @Test
    public void operate() throws Exception {
        String path = "/zk-book";
        System.err.println("Thread: " + Thread.currentThread().getName());

        //使用自定义的线程池
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground((client, event) -> {
            System.err.println("Thread: " + Thread.currentThread().getName());
            System.err.println("Event:[code: " + event.getResultCode() + ", type: " + event.getType() + "]");
            latch.countDown();
        }, executor).forPath(path, "aaa".getBytes());

        //使用默认的线程池
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground((client, event) -> {
            System.err.println("Thread: " + Thread.currentThread().getName());
            System.err.println("Event:[code: " + event.getResultCode() + ", type: " + event.getType() + "]");
            latch.countDown();
        }).forPath(path, "bbb".getBytes());

        latch.await();

    }
}
