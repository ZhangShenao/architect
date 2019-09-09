package william.concurrent.framework;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/30 13:46
 * @Description:
 */
public class DemoTask implements TaskExecutor<String,String> {
    @Override
    public String executeTask(String taskMetadata) {
        System.err.println("Exec Task: " + taskMetadata);
        try {
            Thread.sleep(50L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return taskMetadata;
    }

    public static void main(String[] args) throws InterruptedException {
        int totalTaskCount = 200;
        String taskKey = "Task-Key";
        String taskName = "TaskName";
        TaskInstance<String, String> taskInstance = new TaskInstance<>(taskKey, taskName, totalTaskCount, new DemoTask());
        PendingTaskPool.getInstance().registerTask(taskInstance);
        for (int i = 0;i < totalTaskCount;i++){
            String taskMetadata = "metadata-" + (i + 1);
            PendingTaskPool.getInstance().executeTask(taskKey,taskMetadata);
        }

        for (int i = 0;i < 100;i++){
            new Thread(new QueryResultRunnable(taskKey)).start();
            Thread.sleep(1000L);
        }
    }

    private static class QueryResultRunnable implements Runnable{
        private String taskKey;

        public QueryResultRunnable(String taskKey) {
            this.taskKey = taskKey;
        }

        @Override
        public void run() {
            TaskInstance<?, ?> taskInstance = PendingTaskPool.getInstance().getTaskByKey(taskKey);
            if (taskInstance != null){
                System.err.println(taskInstance.showProgress());
            }
        }
    }
}
