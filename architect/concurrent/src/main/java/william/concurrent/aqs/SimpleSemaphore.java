package william.concurrent.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/3 11:15
 * @Description:自定义Semaphore,内部通过Sync同步器继承了AQS,采用共享的方式获取资源
 */
public class SimpleSemaphore {
    private Sync sync;

    public SimpleSemaphore(int permits){
        sync = new Sync(permits);
    }


    public void acquire(){
        sync.acquireShared(1);
    }

    public void release(){
        sync.releaseShared(1);
    }

    static class Sync extends AbstractQueuedSynchronizer {
        public Sync(int permits) {
            if (permits <= 0) {
                throw new IllegalArgumentException("permits must be positive!!");
            }
            setState(permits);
        }

        @Override
        protected int tryAcquireShared(int reduce) {
            for (; ; ) {
                int current = getState();
                int remains = current - reduce;
                if (remains < 0 || compareAndSetState(current, remains)) {
                    return remains;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int released) {
            for (; ; ) {
                int current = getState();
                int updated = current + released;
                if (updated < current){
                    throw new RuntimeException("Maximum permit count exceeded!!");
                }
                if (compareAndSetState(current, updated)) {
                    return true;
                }
            }
        }


    }
}
