package william.concurrent.completionservice;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author zhangshenao
 * @Date 2021-03-18
 * @Description 使用CompletionService，实现异步任务执行结果的有序化
 */
@Slf4j
public class CompletionServiceDemo {
    public static void main(String[] args) throws Exception {
        //服务商数量
        int providerNum = 3;

        //创建业务线程池
        ExecutorService executor = Executors.newFixedThreadPool(providerNum);

        //创建CompletionService
        CompletionService<String> cs = new ExecutorCompletionService<>(executor);

        //使用阻塞队列保存执行结果
        BlockingQueue<Future<String>> futures = new ArrayBlockingQueue<>(providerNum);

        //执行任务
        futures.put(cs.submit(CompletionServiceDemo::geoCoderS1));
        futures.put(cs.submit(CompletionServiceDemo::geoCoderS2));
        futures.put(cs.submit(CompletionServiceDemo::geoCoderS3));

        //只要有一个任务成功返回即可，同时取消其他任务
        String code = null;
        try {
            for (int i = 0; i < providerNum; i++) {
                String s = cs.take().get();
                if (StringUtils.hasText(s)) {
                    code = s;
                    break;
                }
            }
            System.err.println("GEO Code: " + code);
        } finally {
            futures.forEach(x -> x.cancel(true));
        }
    }


    //模拟从3个不同的地图服务商获取GEOCode场景
    private static String geoCoderS1() {
        sleepInSeconds(ThreadLocalRandom.current().nextLong(1, 5));
        return "S1";
    }

    private static String geoCoderS2() {
        sleepInSeconds(ThreadLocalRandom.current().nextLong(1, 5));
        return "S2";
    }

    private static String geoCoderS3() {
        sleepInSeconds(ThreadLocalRandom.current().nextLong(1, 5));
        return "S3";
    }

    private static void sleepInSeconds(long secs) {
        try {
            Thread.sleep(secs * 1000L);
        } catch (InterruptedException e) {
            log.error("Thread Sleep Error!", e);
        }
    }


}
