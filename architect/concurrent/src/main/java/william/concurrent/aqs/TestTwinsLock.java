package william.concurrent.aqs;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/22 18:52
 * @Description:
 */
public class TestTwinsLock {
    public static void main(String[] args) throws InterruptedException {
        final TwinsLock lock = new TwinsLock();
        for (int i = 0;i < 10;i++){
            new Thread(() -> {
                try {
                    lock.lock();
                    System.err.println(Thread.currentThread().getName() + "--> do something...");
                    Thread.sleep(2000L);
                }catch (Exception e){
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }).start();
        }

        Thread.sleep(Long.MAX_VALUE);
    }
}
