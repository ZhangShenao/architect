package william.zookeeper.lock;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/7 20:35
 * @Description:
 */
public interface DistributedLock {
    boolean tryLock();

    void lock();

    void releaseLock();
}
