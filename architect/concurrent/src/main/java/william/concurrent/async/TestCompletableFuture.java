package william.concurrent.async;

import org.junit.Test;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import static java.util.concurrent.TimeUnit.*;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/19 16:04
 * @Description:CompletableFuture的使用 默认情况下, CompletableFuture使用公共的ForkJoinPool线程池, 这个线程池默认创建的线程数=CPU核数
 * 也可以使用自定义线程池
 */
public class TestCompletableFuture {
    //任务间的串行关系,后一个操作依赖于前一个操作的结果
    @Test
    public void testSerial() {

        CompletableFuture<String> f = CompletableFuture.supplyAsync(() -> "Hello World")
                .thenApply(s -> s + " HaHa")
                .thenApply(String::toUpperCase);
        System.err.println(f.join());
    }

    //任务间的And汇聚关系,在所有前置任务执行完成后,再执行后置任务,但是前置任务之间可以并发执行
    @Test
    public void testAnd() {
        //异步执行任务1,无返回值
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            System.err.println("T1: 洗水壶");
            sleep(1L, SECONDS);

            System.err.println("T1: 烧开水");
            sleep(15L, SECONDS);
        });

        //异步执行任务2,有返回值
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.err.println("T2: 洗茶壶");
            sleep(1L, SECONDS);

            System.err.println("T2: 洗茶杯");
            sleep(2L, SECONDS);

            System.err.println("T2: 拿茶叶");
            sleep(1L, SECONDS);

            return "铁观音";
        });

        //待任务1和任务2都执行完成后,执行任务3
        CompletableFuture<String> f3 = f1.thenCombine(f2, (aVoid, s) -> {
            System.err.println("T3: 拿到茶叶: " + s);
            System.err.println("T3: 泡茶");

            return "上茶: " + s;
        });

        //等待任务3的执行结果
        System.err.println(f3.join());
    }

    //任务间的OR汇聚关系,指后置任务所依赖的前置任务中,只要有一个执行完成,就可以执行后置任务
    @Test
    public void testOr() {
        Supplier<Integer> s = () -> {
            int r = ThreadLocalRandom.current().nextInt(5, 10);
            sleep(r, SECONDS);
            return r;
        };

        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(s);
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(s);

        CompletableFuture<Integer> f3 = f1.applyToEither(f2, i -> i);
        System.err.println(f3.join());
    }

    //异常处理
    @Test
    public void testException(){
        CompletableFuture<Integer> f = CompletableFuture.supplyAsync(() -> 7 / 0)
                .thenApply(r -> r * 10)
                .exceptionally(e -> 0);
        System.err.println(f.join());
    }

    private void sleep(long time, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
