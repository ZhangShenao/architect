package william.concurrent.cas;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/12/17 17:38
 * @Description:
 */
public class Counter {
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        List<Thread> threads = new LinkedList<>();
        for (int j = 0;j < 100;j++){
            Thread thread = new Thread(() -> {
                for (int k = 0;k < 100000;k++){
                    counter.safeAdd();
                    counter.unsafeCount();
                }
            });
            threads.add(thread);
        }

        for (Thread thread : threads){
            thread.start();
        }

        for (Thread thread : threads){
            thread.join();
        }

        System.err.println("Safe Result: " + counter.atomicInteger.get());
        System.err.println("Unsafe Result: " + counter.i);
    }

    //安全操作
    public void safeAdd(){
        //使用CAS操作
        for (;;){
            int value = atomicInteger.get();
            if (atomicInteger.compareAndSet(value,++value)){
                break;
            }
        }
    }

    //非安全操作
    public void unsafeCount(){
        ++i;
    }
}
