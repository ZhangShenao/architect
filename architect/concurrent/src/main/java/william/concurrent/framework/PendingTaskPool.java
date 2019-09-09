package william.concurrent.framework;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/30 11:51
 * @Description:
 */
public class PendingTaskPool {
    private PendingTaskPool(){}

    private static class SingletonHolder{
        private static final PendingTaskPool INSTANCE = new PendingTaskPool();
    }

    public static PendingTaskPool getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private static final long THREAD_EXPIRE_TIME_MILLIS = 1000L * 10;
    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private static final int MAX_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;
    private static final int TASK_QUEUE_SIZE = 500;
    private static final BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(TASK_QUEUE_SIZE);
    private static final ExecutorService pool = new ThreadPoolExecutor(CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            THREAD_EXPIRE_TIME_MILLIS,
            TimeUnit.MILLISECONDS,
            taskQueue,
            PendingTaskErrorHandler.getInstance());
    private static final Map<String,TaskInstance<?,?>> registeredTasks = new ConcurrentHashMap<>();

    public void registerTask(TaskInstance<?,?> taskInstance){
        TaskInstance<?, ?> updated = registeredTasks.putIfAbsent(taskInstance.getKey(), taskInstance);
        if (updated != null){
            throw new RuntimeException("Duplicated Task Registered!! taskKey: " + taskInstance.getKey());
        }
    }

    public <T,R> void executeTask(String taskKey,T taskData){
        TaskInstance<T, R> taskInstance = (TaskInstance<T, R>) registeredTasks.get(taskKey);
        if (taskInstance == null){
            throw new RuntimeException("Task not Registered!! taskKey: " + taskInstance.getKey());
        }
        PendingTask<T, R> pendingTask = new PendingTask<T, R>(taskInstance, taskData);
        pool.submit(pendingTask);
    }

    public <T,R> TaskInstance<T,R> getTaskByKey(String taskKey){
        return (TaskInstance<T, R>) registeredTasks.get(taskKey);
    }

    public void removeTaskByKey(String taskKey){
        registeredTasks.remove(taskKey);
    }


    private static class PendingTask<T,R> implements Runnable{
        private TaskInstance<T,R> taskInstance;
        private T taskMetadata;

        public PendingTask(TaskInstance<T, R> taskInstance, T taskMetadata) {
            this.taskInstance = taskInstance;
            this.taskMetadata = taskMetadata;
        }

        @Override
        public void run() {
            TaskResultInfo<R> resultInfo = null;
            try {
                TaskExecutor<T, R> taskExecutor = taskInstance.getTaskExecutor();
                R taskResult = taskExecutor.executeTask(taskMetadata);
                resultInfo = TaskResultInfo.success(taskResult);
            }catch (Exception e){
                PendingTaskErrorHandler.getInstance().handleTaskExecutionError(taskInstance.getKey(),e);
                resultInfo = TaskResultInfo.fail(e);
            }finally {
                if (resultInfo == null){
                    resultInfo = TaskResultInfo.fail("Task Execute Fail");
                }
                taskInstance.recordTaskResult(resultInfo);
            }

        }
    }
}
