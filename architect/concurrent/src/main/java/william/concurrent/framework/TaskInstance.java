package william.concurrent.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/30 11:28
 * @Description:
 */
public class TaskInstance<T,R> implements Delayed {
    private static final long DEFAULT_CLEAN_UP_DELAY_MILLIS = 1000L * 30;

    private String key;
    private String name;
    private int totalTaskCount;
    private TaskExecutor<T,R> taskExecutor;
    private AtomicInteger completeCount;
    private AtomicInteger successCount;
    private BlockingDeque<TaskResultInfo<R>> taskResults;
    private long cleanUpDelayMillis = DEFAULT_CLEAN_UP_DELAY_MILLIS;

    private long cleanUpTimestampNanos;

    public TaskInstance(String key,String name,int totalTaskCount,TaskExecutor<T,R> taskExecutor){
        this.key = key;
        this.name = name;
        this.totalTaskCount = totalTaskCount;
        this.taskExecutor = taskExecutor;

        completeCount = new AtomicInteger(0);
        successCount = new AtomicInteger(0);
        taskResults = new LinkedBlockingDeque<>(totalTaskCount);
        cleanUpTimestampNanos = TimeUnit.MICROSECONDS.toNanos(System.currentTimeMillis() + cleanUpDelayMillis);
    }

    public int getSuccessCount(){
        return successCount.get();
    }

    public int getCompleteCount(){
        return completeCount.get();
    }

    public int getFailCount(){
        return (getSuccessCount() - getCompleteCount());
    }

    public TaskExecutor<T,R> getTaskExecutor(){
        return taskExecutor;
    }

    public String showProgress() {
        return "Success[" + successCount.get() + "]/Current[" + completeCount.get()+"] Total[" + totalTaskCount + "]";
    }

    public List<TaskResultInfo<R>> getTaskResults(){
        List<TaskResultInfo<R>> taskResultInfos = new ArrayList<>(totalTaskCount);
        TaskResultInfo<R> taskResultInfo = null;
        while ((taskResultInfo = taskResults.pollFirst()) != null){
            taskResultInfos.add(taskResultInfo);
        }
        return taskResultInfos;
    }

    public void recordTaskResult(TaskResultInfo<R> taskResultInfo){
        taskResults.offerLast(taskResultInfo);
        completeCount.incrementAndGet();

        if (taskResultInfo.isSuccess()){
            successCount.incrementAndGet();
        }

        if (completeCount.get() >= totalTaskCount){
            TaskFinalizer.getInstance().addTask(this);
        }
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return (cleanUpTimestampNanos - TimeUnit.MICROSECONDS.toNanos(System.currentTimeMillis()));
    }

    @Override
    public int compareTo(Delayed o) {
        return (int)(getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS));
    }
}
