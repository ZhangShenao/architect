package william.concurrent.aqs;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/22 17:20
 * @Description:
 */
public class TestMutex {
    public static void main(String[] args) throws InterruptedException {
        final Mutex mutex = new Mutex();
        for (int i = 0;i < 10;i++){
            new Thread(() -> {
                try {
                    mutex.lock();
                    System.err.println(Thread.currentThread().getName() + "--> do something...");
                    Thread.sleep(2000L);
                }catch (Exception e){
                    e.printStackTrace();
                } finally {
                    mutex.unlock();
                }
            }).start();
        }

        Thread.sleep(Long.MAX_VALUE);
    }
}
