package william.concurrent.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/30 11:57
 * @Description:
 */
public class PendingTaskErrorHandler implements RejectedExecutionHandler{
    private PendingTaskErrorHandler(){}

    private static class SingletonHolder{
        private static final PendingTaskErrorHandler INSTANCE = new PendingTaskErrorHandler();
    }

    public static PendingTaskErrorHandler getInstance(){
        return PendingTaskErrorHandler.SingletonHolder.INSTANCE;
    }

    private static final Logger logger = LoggerFactory.getLogger(PendingTaskErrorHandler.class);

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        logger.error("Task Rejected!! " + r);
    }

    public void handleTaskExecutionError(String taskKey,Throwable throwable){
        logger.error("Task Error!! taskKey: " + taskKey,throwable);
    }

}
