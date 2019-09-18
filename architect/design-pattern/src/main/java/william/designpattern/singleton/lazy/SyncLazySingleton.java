package william.designpattern.singleton.lazy;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 基于同步机制的延迟加载的单例模式, 使用synchronized保证线程安全
 * 缺点:效率低
 */
public class SyncLazySingleton {
    private static SyncLazySingleton INSTANCE;

    private SyncLazySingleton() {
    }

    public synchronized static SyncLazySingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SyncLazySingleton();
        }
        return INSTANCE;
    }

}
