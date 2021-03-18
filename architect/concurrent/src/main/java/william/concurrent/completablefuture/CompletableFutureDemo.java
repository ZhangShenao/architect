package william.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author zhangshenao
 * @Date 2021-03-18
 * @Description 使用CompletableFuture进行异步编程
 */
@Slf4j
public class CompletableFutureDemo {
    public static void main(String[] args) {
        //异步执行T1,不关注返回值
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            System.err.println("T1:洗水壶");
            sleepInSeconds(1);
            System.err.println("T1:烧开水");
            sleepInSeconds(15);
        });

        //异步执行T2,需要获取返回值
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.err.println("T2:洗茶壶");
            sleepInSeconds(1);
            System.err.println("T2:洗茶杯");
            sleepInSeconds(2);
            System.err.println("T2:拿茶叶");
            sleepInSeconds(1);
            return "铁观音";
        });

        //异步执行T3,需要等到T1和T2同时执行完成
        //thenCombine描述了AND类型的汇聚关系
        CompletableFuture<String> f3 = f1.thenCombine(f2, (v, s) -> {
            System.err.println("T3:拿到茶叶" + s);
            System.err.println("T3:泡茶");
            sleepInSeconds(5);
            return "上茶:" + s;
        });

        //等待T3执行完成
        System.err.println(f3.join());

    }

    private static void sleepInSeconds(long secs) {
        try {
            Thread.sleep(secs * 1000L);
        } catch (InterruptedException e) {
            log.error("Thread Sleep Error!", e);
        }
    }
}
