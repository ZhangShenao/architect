package william.zookeeper.connection;

import org.apache.zookeeper.*;
import william.zookeeper.constant.ZookeeperConstant;

import java.util.concurrent.CountDownLatch;


/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/7 15:06
 * @Description:
 */
public class ZKConnection {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper(ZookeeperConstant.ZOOKEEPER_URL, ZookeeperConstant.CONNECTION_TIMEOUT_MILLIS, new InitConnectionWatcher());
        countDownLatch.await();
        System.err.println("state: " + zooKeeper.getState());
        zooKeeper.create("/test-persist", "test-persist-node".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.err.println("节点创建成功");
        zooKeeper.close();

    }

    private static class InitConnectionWatcher implements Watcher {

        @Override
        public void process(WatchedEvent event) {
            Event.KeeperState keeperState = event.getState();
            Event.EventType eventType = event.getType();
            if (Event.KeeperState.SyncConnected == keeperState && Event.EventType.None == eventType) {
                countDownLatch.countDown();
                System.err.println("Zookeeper Connection Success...");
            }
        }
    }
}
