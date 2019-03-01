package william.concurrent.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/1 23:40
 * @Description:
 */
public class TestLockSupport {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            System.err.println("线程A执行");
            System.err.println("线程A被阻塞");

            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
            }
            //线程阻塞
            LockSupport.park();

            System.err.println("线程A被唤醒");
        });
        t.start();

        //unpark方法可以先于park调用,不关心线程的执行先后顺序
        LockSupport.unpark(t);
        Thread.sleep(5000L);

    }
}
