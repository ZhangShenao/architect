package william.concurrent.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.DelayQueue;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/30 12:53
 * @Description:
 */
public class TaskFinalizer {
    static {
        Thread finalizer = new Thread(new FinalizerRunnable());
        finalizer.setDaemon(true);
        finalizer.start();
    }

    private TaskFinalizer(){}

    private static class SingletonHolder{
        private static final TaskFinalizer INSTANCE = new TaskFinalizer();
    }

    public static TaskFinalizer getInstance(){
        return TaskFinalizer.SingletonHolder.INSTANCE;
    }

    private static final Logger logger = LoggerFactory.getLogger(TaskFinalizer.class);

    private static final DelayQueue<TaskInstance<?,?>> taskInstances = new DelayQueue<>();

    public void addTask(TaskInstance<?,?> taskInstance){
        taskInstances.put(taskInstance);
    }

    private static class FinalizerRunnable implements Runnable{

        @Override
        public void run() {
            try {
                while (true){
                    TaskInstance<?, ?> taskInstance = taskInstances.take();
                    if (taskInstance != null){
                        System.err.println("Remove Task after Complement: " + taskInstance.getKey());
                        PendingTaskPool.getInstance().removeTaskByKey(taskInstance.getKey());
                    }
                }
            }catch (Exception e){
                logger.error("Task Task from TaskFinalizer Error!! ",e);
            }
        }
    }
}
