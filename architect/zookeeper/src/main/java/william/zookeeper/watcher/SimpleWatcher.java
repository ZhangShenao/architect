package william.zookeeper.watcher;

import static org.apache.zookeeper.Watcher.Event.KeeperState.SyncConnected;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/5 14:55
 * @Description:
 */
public class SimpleWatcher implements Watcher {
    private final CountDownLatch connectedLatch;

    public SimpleWatcher(CountDownLatch connectedLatch) {
        this.connectedLatch = connectedLatch;
    }

    @Override
    public void process(WatchedEvent event) {
        System.err.println("Watched Event: " + event);
        if (SyncConnected == event.getState()) {
            connectedLatch.countDown();
        }
    }
}
