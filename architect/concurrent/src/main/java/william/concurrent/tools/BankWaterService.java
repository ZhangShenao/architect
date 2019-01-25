package william.concurrent.tools;

import org.springframework.util.CollectionUtils;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/19 14:13
 * @Description:
 */
public class BankWaterService implements Runnable{
    private static final int THREAD_NUM = 5;
    private static Map<String,Long> bankWaters = new ConcurrentHashMap<>();
    private static Executor executor = Executors.newCachedThreadPool();
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_NUM,this);

    public void countAllWater(){
        for (int i = 0;i < THREAD_NUM;i++){
            executor.execute(() -> {
                bankWaters.put(Thread.currentThread().getName(), ThreadLocalRandom.current().nextLong(1,99999));
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public void run() {
        if (CollectionUtils.isEmpty(bankWaters)){
            return;
        }
        Optional<Long> result = bankWaters.values().stream().reduce((a, b) -> Long.sum(a, b));
        if (result.isPresent()){
            System.err.println("Total Water: " + result.get());
        }
        System.err.println("isBroker: " + cyclicBarrier.isBroken());
    }

    public static void main(String[] args) {
        new BankWaterService().countAllWater();
    }
}
