package william.zookeeper.client.curator;


import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;
import william.zookeeper.constant.ZookeeperConstant;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/5 15:38
 * @Description:Curator接口同步操作
 */
public class CuratorSyncOperations {
    private CuratorFramework client;

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
    public void createNode() throws Exception {
        String path = "/zk-book";
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath(path, "aaa".getBytes());
    }

    @Test
    public void deleteNode() throws Exception {
        String path = "/zk-book";
        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath(path);
        client.delete().guaranteed().deletingChildrenIfNeeded().withVersion(stat.getVersion()).forPath(path);
    }

    @Test
    public void getData() throws Exception {
        String path = "/zk-book";
        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath(path);

        //使用最新的Stat更新数据,操作成功
        client.setData().withVersion(stat.getVersion()).forPath(path,"bbb".getBytes());

        //使用过期的Stat更新数据,操作失败。withVersion会根据当前version进行CAS操作。
        client.setData().withVersion(stat.getVersion()).forPath(path,"ccc".getBytes());
    }

}
