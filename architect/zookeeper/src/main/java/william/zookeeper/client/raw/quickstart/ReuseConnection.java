package william.zookeeper.client.raw.quickstart;

import org.apache.zookeeper.ZooKeeper;
import william.zookeeper.constant.ZookeeperConstant;
import william.zookeeper.watcher.SimpleWatcher;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/5 14:03
 * @Description:复用Session信息创建会话
 */
public class ReuseConnection {
    private static final CountDownLatch connectedLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        //构造ZooKeeper实例,同时建立会话
        ZooKeeper zooKeeper = new ZooKeeper(ZookeeperConstant.ZOOKEEPER_URL, ZookeeperConstant.CONNECTION_TIMEOUT_MILLIS, new SimpleWatcher(connectedLatch));
        connectedLatch.await();

        //获取会话的Session信息
        long sessionId = zooKeeper.getSessionId();
        byte[] sessionPasswd = zooKeeper.getSessionPasswd();

        //使用错误的Session信息创建会话,创建失败
        zooKeeper = new ZooKeeper(ZookeeperConstant.ZOOKEEPER_URL, ZookeeperConstant.CONNECTION_TIMEOUT_MILLIS, new SimpleWatcher(connectedLatch), 1L, "test".getBytes());

        //复用Session信息创建会话,创建成功
        zooKeeper = new ZooKeeper(ZookeeperConstant.ZOOKEEPER_URL, ZookeeperConstant.CONNECTION_TIMEOUT_MILLIS, new SimpleWatcher(connectedLatch), sessionId, sessionPasswd);
        Thread.sleep(Long.MAX_VALUE);
    }
}
