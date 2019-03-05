package william.zookeeper.client.raw.quickstart;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import william.zookeeper.constant.ZookeeperConstant;
import william.zookeeper.watcher.SimpleWatcher;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/5 14:55
 * @Description:同步创建Zookeeper节点
 */
public class CreateNodeSync {
    private static final CountDownLatch connectedLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper(ZookeeperConstant.ZOOKEEPER_URL, ZookeeperConstant.CONNECTION_TIMEOUT_MILLIS, new SimpleWatcher(connectedLatch));
        connectedLatch.await();

        //创建临时节点
        String path = "/zk-test-ephemeral-";
        String result = zooKeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.err.println("创建临时节点,result: " + result);

        //创建临时顺序节点,会在路径后面追加一个底层的数字
        result = zooKeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.err.println("创建临时顺序节点,result: " + result);

    }
}
