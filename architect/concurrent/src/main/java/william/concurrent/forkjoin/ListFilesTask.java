package william.concurrent.forkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import org.springframework.util.CollectionUtils;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/18 12:21
 * @Description:
 */
public class ListFilesTask extends RecursiveAction {
    private String path;

    private Predicate<File> filePredicate;

    public ListFilesTask(String path, Predicate<File> filePredicate) {
        this.path = path;
        this.filePredicate = filePredicate;
    }

    @Override
    protected void compute() {
        File file = new File(path);
        File[] files = file.listFiles();
        if (files == null || files.length <= 0) {
            return;
        }
        List<ListFilesTask> tasks = new ArrayList<>();
        for (File f : files) {
            if (f.isFile()) {
                if (filePredicate.test(f)) {
                    System.err.println(f.getName());
                }
            } else if (f.isDirectory()) {
                tasks.add(new ListFilesTask(f.getPath(), filePredicate));
            }
        }
        if (!CollectionUtils.isEmpty(tasks)) {
            invokeAll(tasks);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ListFilesTask listFilesTask = new ListFilesTask("/", (file -> file.getName().endsWith(".java")));
        forkJoinPool.submit(listFilesTask);
        forkJoinPool.awaitTermination(5, TimeUnit.SECONDS);
        forkJoinPool.shutdown();
    }
}
