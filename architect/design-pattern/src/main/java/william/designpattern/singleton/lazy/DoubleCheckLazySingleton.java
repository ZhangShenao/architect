package william.designpattern.singleton.lazy;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 基于双重检查锁机制的延迟加载单例模式
 * 优点:使用volatile关键字避免了指令重排序,只有再实例为空时才进行同步阻塞,提高了效率
 * 缺点:实现方式较复杂
 */
public class DoubleCheckLazySingleton {
    private volatile static DoubleCheckLazySingleton INSTANCE;

    private DoubleCheckLazySingleton() {
    }

    public static DoubleCheckLazySingleton getInstance() {
        if (INSTANCE == null) {
            //双重检查锁机制
            synchronized (DoubleCheckLazySingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DoubleCheckLazySingleton();
                }
            }
        }
        return INSTANCE;
    }

}
