package william.concurrent.framework;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/30 11:21
 * @Description:
 */
@FunctionalInterface
public interface TaskExecutor<T,R> {
    R executeTask(T taskMetadata);
}
