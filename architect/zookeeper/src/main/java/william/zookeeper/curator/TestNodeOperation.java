package william.zookeeper.curator;

import static org.apache.zookeeper.CreateMode.EPHEMERAL;
import static org.apache.zookeeper.CreateMode.PERSISTENT;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author zhangshenao
 * @Date 2019-09-23
 * @Description 节点操作
 */
public class TestNodeOperation {
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
    public void testCreateNode() throws Exception {
        //创建临时节点,并递归创建父节点
        client.create()
                .creatingParentsIfNeeded()
                .withMode(PERSISTENT)
                .forPath("/nodes/n1", "node1".getBytes());
        Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public void testDeleteNode() throws Exception {
        //创建节点
        String path = "/nodes/n2";
        client.create()
                .creatingParentsIfNeeded()
                .withMode(PERSISTENT)
                .forPath(path, "node2".getBytes());
        System.err.println("创建节点: " + path);

        //记录节点元信息
        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath(path);
        System.err.println("节点版本: " + stat.getVersion());

        //删除节点
        client.delete()
                .deletingChildrenIfNeeded()
                .withVersion(stat.getVersion())
                .forPath(path);
        System.err.println("删除节点: " + path);
    }

    @Test
    public void testGetData() throws Exception {
        //创建节点
        String path = "/nodes/n3";
        client.create()
                .creatingParentsIfNeeded()
                .withMode(EPHEMERAL)
                .forPath(path, "node3".getBytes());

        //获取节点数据并保存
        byte[] data = client.getData().storingStatIn(new Stat()).forPath(path);
        System.err.println("Data: " + new String(data));
    }

    @Test
    public void testSetData() throws Exception {
        //创建节点
        String path = "/nodes/n4";
        client.create()
                .creatingParentsIfNeeded()
                .withMode(PERSISTENT)
                .forPath(path, "node4".getBytes());
        Stat stat = new Stat();
        //获取节点数据并保存
        byte[] data = client.getData().storingStatIn(new Stat()).forPath(path);

        //基于最新的版本,修改节点数据,操作成功
        int version =
                client.setData().withVersion(stat.getVersion()).forPath(path, "new node4".getBytes()).getVersion();
        System.err.println("修改节点数据,最新版本: " + version);

        //基于旧的版本,修改节点数据,操作失败
        version = client.setData().withVersion(stat.getVersion()).forPath(path).getVersion();
        System.err.println("修改节点数据,最新版本: " + version);

    }

}
