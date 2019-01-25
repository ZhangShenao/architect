package william.concurrent.aqs;

import org.springframework.util.Assert;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/22 18:39
 * @Description:
 */
public class TwinsLock implements Lock{
    private final Sync sync = new Sync(2);

    private static final class Sync extends AbstractQueuedSynchronizer{
        Sync(int count){
            Assert.isTrue(count > 0,"Count Must be Positive");
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int reduceCount) {
            for(;;){
                int current = getState();
                int updated = current - reduceCount;
                if (updated < 0 || compareAndSetState(current, updated)){
                    return updated;
                }

            }
        }

        @Override
        protected boolean tryReleaseShared(int returnCount) {
            for (;;){
                int current = getState();
                int updated = current + returnCount;
                if (compareAndSetState(current,updated)){
                    return true;
                }
            }
        }

        private Condition newCondition(){
            return new ConditionObject();
        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return (sync.tryAcquireShared(1) >= 0);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
