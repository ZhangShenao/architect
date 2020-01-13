package william.concurrent.status;

/**
 * @Author zhangshenao
 * @Date 2020-01-13
 * @Description 展示线程的状态
 */
public class ShowThreadStatus {
    public static void main(String[] args) {
        //调用Thread.sleep()方法,线程进入TIMED_WAITING状态
        new Thread(() -> {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "TIMED_WAITING").start();

        //线程等待进入synchronized代码块(等待同步锁),进入BLOCKED状态
        new Thread(new BlockTask(), "BLOCKED-1").start();
        new Thread(new BlockTask(), "BLOCKED-2").start();

        //线程等待Object#wait()方法,进入WAITING状态
        new Thread(new WaitingTask(), "WAITING").start();
    }

    private static class BlockTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (BlockTask.class) {
                    try {
                        Thread.sleep(Long.MAX_VALUE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static class WaitingTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (WaitingTask.class) {
                    try {
                        WaitingTask.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
