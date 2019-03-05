package william.zookeeper.client.raw.quickstart;

import org.apache.zookeeper.ZooKeeper;
import william.zookeeper.constant.ZookeeperConstant;
import william.zookeeper.watcher.SimpleWatcher;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/5 14:03
 * @Description:创建一个基本的Zookeeper会话实例
 */
public class CreateConnectionSync {
    private static final CountDownLatch connectedLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        //构造ZooKeeper实例,同时建立会话
        ZooKeeper zooKeeper = new ZooKeeper(ZookeeperConstant.ZOOKEEPER_URL, ZookeeperConstant.CONNECTION_TIMEOUT_MILLIS, new SimpleWatcher(connectedLatch));
        connectedLatch.await();
        System.err.println("Connection Created. State: " + zooKeeper.getState());
    }
}
