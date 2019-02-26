package william.concurrent.delayqueue;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/25 22:06
 * @Description:网瘾少年
 */
@Getter
@Setter
public class InternetAddict implements Delayed {
    private final String id;
    private final String name;
    private final long endTimeMillis;

    public InternetAddict(String id, String name, long endTimeMillis) {
        this.id = id;
        this.name = name;
        this.endTimeMillis = endTimeMillis;
    }

    //上网的剩余时间
    @Override
    public long getDelay(TimeUnit unit) {
        return (endTimeMillis - System.currentTimeMillis());
    }

    @Override
    public int compareTo(Delayed o) {
        InternetAddict addict = (InternetAddict) o;
        return (endTimeMillis > addict.endTimeMillis ? 1 : 0);
    }
}
