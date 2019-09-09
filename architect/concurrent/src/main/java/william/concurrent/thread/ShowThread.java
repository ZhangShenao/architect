package william.concurrent.thread;


import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/16 23:17
 * @Description: 打印当前JVM线程堆栈
 */
public class ShowThread {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        if (threadInfos != null && threadInfos.length > 0){
            Arrays.stream(threadInfos)
                    .forEach(threadInfo -> System.err.println("Thread-Id: " + threadInfo.getThreadId() + ",threadName: " + threadInfo.getThreadName()));
        }
    }
}
