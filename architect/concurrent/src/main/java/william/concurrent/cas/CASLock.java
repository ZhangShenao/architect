package william.concurrent.cas;

import sun.misc.Unsafe;
import william.concurrent.unsafe.UnsafeFactory;

import java.lang.reflect.Field;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/14 下午2:04
 * <p>
 * 基于CAS实现锁
 */
public class CASLock {
    private volatile int state;  //标识当前锁状态,使用volatile保证可见性
    private final Unsafe UNSAFE; //Unsafe对象
    private final long OFFSET;   //state字段的偏移量

    public CASLock() {
        try {
            this.state = 0;
            this.UNSAFE = UnsafeFactory.getInstance();
            Field field = CASLock.class.getDeclaredField("state");
            this.OFFSET = UNSAFE.objectFieldOffset(field);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //尝试加CAS锁
    public boolean tryLock() {
        return UNSAFE.compareAndSwapInt(this, OFFSET, 0, 1);
    }

    public void unlock() {
        setState(0);
    }

    //获取当前锁状态
    public int getState() {
        return state;
    }

    //设置锁状态
    public void setState(int state) {
        this.state = state;
    }

    public static void main(String[] args) throws InterruptedException {
        CASLock lock = new CASLock();

        Thread t1 = new Thread(new Task(lock), "Thread-1");
        Thread t2 = new Thread(new Task(lock), "Thread-2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }

    static class Task implements Runnable {
        private CASLock lock;

        public Task(CASLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            //自旋获取CAS锁
            for (; ; ) {
                if (lock.getState() == 0 && lock.tryLock()) {
                    System.out.println(Thread.currentThread().getName() + " 获取到CAS锁,开始执行业务流程");
                    try {
                        Thread.sleep(2000L);

                        //获取锁成功,退出自旋
                        break;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
    }


}
