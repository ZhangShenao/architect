package william.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author zhangshenao
 * @Date 2019-09-23
 * @Description Leader选举
 */
public class TestLeaderSelector {
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
    public void testLeaderSelection() throws InterruptedException {
        String leaderPath = "/leader_selection";
        LeaderSelector selector = new LeaderSelector(client, leaderPath, new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                System.err.println("Leader选举成功");
                //竞争到Leader后,可以执行相应的业务逻辑
                Thread.sleep(Long.MAX_VALUE);

                //该方法执行完成后,Curator会自动释放Leader权利,并开启新一轮Leader选举
            }
        });

        selector.start();
        selector.autoRequeue();

        Thread.sleep(Long.MAX_VALUE);
    }
}
