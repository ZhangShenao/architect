package william.zookeeper.curator;

import static org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode.POST_INITIALIZED_EVENT;
import static org.apache.zookeeper.CreateMode.EPHEMERAL;
import static org.apache.zookeeper.CreateMode.PERSISTENT;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author zhangshenao
 * @Date 2019-09-23
 * @Description 事件监听
 */
public class TestEventListening {
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

    //NodeCache:监听节点数据变化
    @Test
    public void testNodeCache() throws Exception {
        //创建节点
        String path = "/nodes/n1";
        client.create().creatingParentsIfNeeded().withMode(EPHEMERAL).forPath(path, "node1".getBytes());

        //创建事件监听并启动
        NodeCache cache = new NodeCache(client, path, false);
        cache.start(true);

        //注册监听器
        cache.getListenable().addListener(
                () -> System.err.println("Node Data Updated: " + new String(cache.getCurrentData().getData())));

        //修改节点数据
        client.setData().forPath(path, "new node1".getBytes());
        Thread.sleep(2000L);

        //删除节点
        //        client.delete().deletingChildrenIfNeeded().forPath(path);
        Thread.sleep(Long.MAX_VALUE);
    }

    //PathChildrenCache:监听路径下子节点变化
    //注:PathChildrenCache无法监听二级子节点
    @Test
    public void testPathChildrenCache() throws Exception {
        //创建PathChildrenCache并启动
        String path = "/nodes";
        PathChildrenCache cache = new PathChildrenCache(client, path, true);
        cache.start(POST_INITIALIZED_EVENT);

        //注册监听器,监听路径下的子节点变化
        cache.getListenable().addListener((cli, event) -> {
            switch (event.getType()) {
                case CHILD_ADDED:
                    System.err.println("CHILD_ADDED, " + event.getData());
                case CHILD_UPDATED:
                    System.err.println("CHILD_UPDATED, " + event.getData());
                case CHILD_REMOVED:
                    System.err.println("CHILD_REMOVED, " + event.getData());
            }
        });

        //创建父节点
        client.create().creatingParentsIfNeeded().withMode(PERSISTENT).forPath(path);
        Thread.sleep(2000L);

        //创建子节点
        client.create().creatingParentsIfNeeded().forPath(path + "/n1", "node1".getBytes());
        Thread.sleep(2000L);

        //修改子节点数据
        client.setData().forPath(path + "/n1", "new node1".getBytes());
        Thread.sleep(2000L);

        //删除子节点
        client.delete().deletingChildrenIfNeeded().forPath(path + "/n1");
        Thread.sleep(2000L);

        //删除父节点,监听器不会监听该事件
        client.delete().deletingChildrenIfNeeded().forPath(path);
        Thread.sleep(Long.MAX_VALUE);
    }
}
