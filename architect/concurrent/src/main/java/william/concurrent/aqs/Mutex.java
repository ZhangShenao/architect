package william.concurrent.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/22 17:04
 * @Description:自定义独占锁
 */
public class Mutex implements Lock{
    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(Sync.LOCK_STATE);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(Sync.LOCK_STATE);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(Sync.LOCK_STATE);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        sync.tryAcquireNanos(Sync.LOCK_STATE,unit.toNanos(time));
        return false;
    }

    @Override
    public void unlock() {
        sync.release(Sync.NOT_LOCK_STATE);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    /**
     * 独占锁的同步器,继承AbstractQueuedSynchronizer,同一时刻只有一个线程可以获取到同步状态
     */
    private static final class Sync extends AbstractQueuedSynchronizer{
        private static final int NOT_LOCK_STATE = 0;
        private static final int LOCK_STATE = 1;

        @Override
        protected boolean tryAcquire(int arg) {
            //当state为0时获取锁,将state设置为 1
            //因为当前线程在请求锁时并没有获取同步状态,因此需要进行CAS操作
            if (compareAndSetState(NOT_LOCK_STATE,LOCK_STATE)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }

            //获取锁失败
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            //当state为1时,释放锁,并将state置为0
            int state = getState();
            if (state == NOT_LOCK_STATE){
                throw new IllegalMonitorStateException();
            }

            //因为调用tryRelease()方法时,线程已经获取到了同步状态,因此直接setState即可
            setState(NOT_LOCK_STATE);
            setExclusiveOwnerThread(null);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            //判断锁是否被当前线程独占
            return (getState() == LOCK_STATE && Thread.currentThread() == getExclusiveOwnerThread());
        }

        private Condition newCondition(){
            return new ConditionObject();
        }
    }
}
