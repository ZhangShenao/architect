package william.zookeeper.client.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.Before;
import org.junit.Test;
import william.zookeeper.constant.ZookeeperConstant;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/5 16:37
 * @Description:监听器 Curator提供了两种类型的监听：NodeCache和PathChildrenCache
 * NodeCache用于监听节点本身的数据变化
 * PathChildrenCache用于指定数据节点的子节点的变化
 */
public class ListenerOperation {
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
    public void nodeCacheListener() throws Exception {
        String path = "/zk-book";

        //创建节点
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, "aaa".getBytes());

        //创建NodeCache。NodeCache可以理解为一个Zookeeper节点的本地缓存视图,可以通过与远程节点的对比来监听节点的变化
        NodeCache nodeCache = new NodeCache(client, path);

        //启动NodeCache
        nodeCache.start(true);

        //注册监听器
        nodeCache.getListenable().addListener(() -> {
            ChildData data = nodeCache.getCurrentData();
            System.err.println("Node Data Changed. path: " + data.getPath() + ", new data: " + new String(data.getData()));
        });

        //修改节点数据
        client.setData().forPath(path, "bbb".getBytes());
        Thread.sleep(1000L);

        //删除节点
        client.delete().guaranteed().deletingChildrenIfNeeded().forPath(path);
        Thread.sleep(Long.MAX_VALUE);

    }

    @Test
    public void pathChildrenCacheListener() throws Exception {
        String path = "/zk-book";

        //创建PathChildrenCache。PathChildrenCache可以理解为指定节点的所有子节点信息的本地缓存视图,它会监听子节点的变更,但是并不监听节点本身的变化,且无法监听二级子节点的变化。
        PathChildrenCache cache = new PathChildrenCache(client, path, true);

        //启动PathChildrenCache
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);

        //注册监听器
        cache.getListenable().addListener((client, event) -> {
            ChildData data = event.getData();
            System.err.println("PathChildrenCache: New Event [ type: " + event.getType() + ", path: " + data.getPath() + ", data: " + new String(data.getData()) + " ]");
        });

        //创建父节点
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, "p".getBytes());
        Thread.sleep(1000L);

        //创建子节点
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path + "/c1", "c1".getBytes());
        Thread.sleep(1000L);

        //修改子节点数据
        client.setData().forPath(path + "/c1", "c1111".getBytes());
        Thread.sleep(1000L);

        //删除子节点
        client.delete().guaranteed().deletingChildrenIfNeeded().forPath(path + "/c1");
        Thread.sleep(1000L);

        //删除父节点
        client.delete().guaranteed().deletingChildrenIfNeeded().forPath(path);

        Thread.sleep(Long.MAX_VALUE);
    }
}
