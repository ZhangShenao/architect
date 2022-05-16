package william.concurrent.sync;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/16 下午1:59
 * <p>
 * synchronized是JVM内置锁,基于操作系统的Monitor(监视器/管程)模型实现
 * synchronized通过调用操作系统的互斥原语Mutex实现
 * 被阻塞的线程会被挂起,等待重新调度,涉及到用户态到内核态的切换,是一个重量级操作,性能较差
 * <p>
 * Monitor:管程（监视器）模型，管理共享变量及对共享变量的操作过程,让它们支持并发：
 * state共享状态
 * 入口等待队列
 * 多个条件变量Condition
 * 每个条件变量对应一个条件变量等待队列
 * <p>
 * synchronized对Monitor模型进行了简化,仅有一个条件变量（一个条件变量等待队列）
 */
public class SynchronizedState {
    //synchronized方法
    //JVM对方法增加ACC_SYNCHRONIZED标志
    public synchronized void synchronizedMethod() {
        System.out.println("Enter Synchronized Method");
    }

    //synchronized代码块
    //JVM通过monitorenter和monitorexit指定实现
    public void synchronizedBlock() {
        Object lock = new Object();
        synchronized (lock) {
            System.out.println("Enter Synchronized Block");
        }
    }

    public static void main(String[] args) {

    }
}
