package william.concurrent.pattern.balking;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangshenao
 * @Date 2021-03-21
 * @Description 具有自动保存功能的编辑器
 * 采用Balking模式实现，本质上是多线程版本的if——在多线程场景下下判断一个条件是否满足
 */
public class AutoSavedEditor {
    //条件变量
    private boolean edited;

    //自动保存时间间隔
    private long saveIntervalInSecs;

    //自动保存执行器
    private ScheduledExecutorService executor;

    public AutoSavedEditor(long saveIntervalInSecs) {
        this.saveIntervalInSecs = saveIntervalInSecs;
        this.executor = Executors.newSingleThreadScheduledExecutor();

        //开启自动保存任务
        executor.scheduleWithFixedDelay(new AutoSaveTask(this), 0L, saveIntervalInSecs, TimeUnit.SECONDS);
    }

    //自动保存
    //Balking模式经典实现——加锁
    private synchronized void autoSave() {
        if (!edited) {
            return;
        }

        System.err.println("自动保存文件...");
        edited = false;
    }

    public void edit() {
        System.err.println("编辑文本...");

        changed();
    }

    //条件改变的方法
    //Balking模式经典实现——加锁
    private synchronized void changed() {
        edited = true;
    }

    //自动保存定时任务
    private class AutoSaveTask implements Runnable {
        private final AutoSavedEditor editor;

        public AutoSaveTask(AutoSavedEditor editor) {
            this.editor = editor;
        }

        @Override
        public void run() {
            editor.autoSave();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AutoSavedEditor editor = new AutoSavedEditor(3L);
        editor.edit();

        Thread.sleep(5000L);
        editor.edit();

        Thread.sleep(Long.MAX_VALUE);
    }


}
