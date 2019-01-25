package william.concurrent.delayqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/30 14:20
 * @Description:
 */
public class DelayTask implements Runnable,Delayed{
    private static final long DEFAULT_DELAY_MILLIS = 1000L * 5;

    private long expireTime;

    public DelayTask(){
        expireTime = TimeUnit.MICROSECONDS.toNanos(System.currentTimeMillis() + DEFAULT_DELAY_MILLIS);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return (expireTime - TimeUnit.MICROSECONDS.toNanos(System.currentTimeMillis()));
    }

    @Override
    public int compareTo(Delayed o) {
        return (int)(getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS));
    }

    @Override
    public void run() {
        System.err.println("Run Delay Task");
    }
}
