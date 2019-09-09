package william.zookeeper.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/5 14:55
 * @Description:
 */
public class SimpleWatcher implements Watcher{
    private final CountDownLatch connectedLatch;

    public SimpleWatcher(CountDownLatch connectedLatch) {
        this.connectedLatch = connectedLatch;
    }

    @Override
    public void process(WatchedEvent event) {
        System.err.println("Watched Event: " + event);
        if (Event.KeeperState.SyncConnected == event.getState()){
            connectedLatch.countDown();
        }
    }
}
