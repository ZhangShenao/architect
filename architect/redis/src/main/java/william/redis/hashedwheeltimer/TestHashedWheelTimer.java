package william.redis.hashedwheeltimer;

import io.netty.util.HashedWheelTimer;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: ZhangShenao
 * @Date 2022/8/11 1:57 PM
 * @Description: HashedWheelTimer时间轮
 */
public class TestHashedWheelTimer {
    public static void main(String[] args) {
        //创建HashedWheelTimer时间轮
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();

        //提交延时任务
        hashedWheelTimer.newTimeout(timeout -> System.out.println("5s之后执行"), 5, TimeUnit.SECONDS);
        hashedWheelTimer.newTimeout(timeout -> System.out.println("10s之后执行"), 10, TimeUnit.SECONDS);
        hashedWheelTimer.newTimeout(timeout -> System.out.println("20s之后执行"), 20, TimeUnit.SECONDS);
        
        System.out.println("主线程结束");
    }
}
