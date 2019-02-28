package william.concurrent.delayqueue;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/27 18:47
 * @Description:
 */
public class TestScheduledCache {
    public static void main(String[] args) throws InterruptedException {
        ScheduledCache<String,String> cache = new ScheduledCache<>(16);
        cache.put("1","a",1000L);
        cache.put("2","b",5000L);
        cache.put("3","c",10000L);

        Thread.sleep(15000L);
        cache.evict();
    }
}
