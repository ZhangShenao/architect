package william.concurrent.delayqueue;

import java.util.concurrent.DelayQueue;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/25 22:06
 * @Description:网吧
 */
public class InternetBar implements Runnable {
    private final DelayQueue<InternetAddict> addicts = new DelayQueue<>();
    private boolean inBusiness = true;

    public void newCustomer(String id, String name, int fee) {
        InternetAddict addict = new InternetAddict(id, name, calculateInternetTimeMillis(fee));
        addicts.put(addict);
        System.err.println(String.format("网瘾少年来上网了!! id: %s, name: %s, fee: %s", id, name, fee));
    }

    public void quit(InternetAddict addict) {
        addicts.remove(addict);
        System.err.println(String.format("网瘾少年下线了!! id: %s, name: %s", addict.getId(), addict.getName()));
    }

    private long calculateInternetTimeMillis(int fee) {
        return System.currentTimeMillis() + fee * 1000L;
    }

    @Override
    public void run() {
        while (inBusiness) {
            try {
                InternetAddict addict = addicts.take();
                quit(addict);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
