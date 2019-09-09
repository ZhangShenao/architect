package william.concurrent.masterworker;


/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/28 22:48
 * @Description: Master-Slave模式之Master。
 * Worker是实际处理任务的角色,处理后将结果上报给Master
 */
public class Worker<T> implements Runnable {
    private String key;

    private Master<T> master;

    public Worker(String key, Master<T> master) {
        this.key = key;
        this.master = master;
    }

    @Override
    public void run() {
        while (true) {
            master.fetchTask().ifPresent(t -> {
                try {
                    T result = t.call();
                    System.err.println("Worker执行完毕,key: " + key + ",result: " + result);
                    master.recordResult(key, result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
