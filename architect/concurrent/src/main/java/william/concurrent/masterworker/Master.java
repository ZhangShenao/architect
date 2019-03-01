package william.concurrent.masterworker;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.*;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/28 22:47
 * @Description: Master-Slave模式之Master。 Master负责任务的接收与分配, 实际任务的执行交给Worker处理, Worker处理任务后将结果上报给Master, 由Master对结果进行汇总。
 */
public class Master<T> {
    private BlockingQueue<Callable<T>> taskQueue;

    private HashMap<String, Worker> workers;

    private ConcurrentHashMap<String, T> results;

    public Master(int workerNum, int maxTaskNum) {
        taskQueue = new ArrayBlockingQueue<>(maxTaskNum);
        workers = new HashMap<>(workerNum);
        results = new ConcurrentHashMap<>();

        for (int i = 0; i < workerNum; i++) {
            String workerKey = String.format("Worker-%s", i);
            workers.putIfAbsent(workerKey, new Worker(workerKey, this));
        }

        start();
    }

    public void submit(Callable<T> task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Optional<Callable<T>> fetchTask() {
        try {
            return Optional.ofNullable(taskQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public void recordResult(String workerKey, T result) {
        results.put(workerKey, result);
    }

    private void start() {
        workers.values().forEach(w -> new Thread(w).start());
    }
}
