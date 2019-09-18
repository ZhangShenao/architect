package william.designpattern.singleton.lazy;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 延迟加载的单例模式
 * 优点:需要使用时才被实例化,节省内存空间
 * 缺点:线程不安全,需要额外的机制保证线程安全
 */
public class LazySingleton {
    private static LazySingleton INSTANCE;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazySingleton();
        }
        return INSTANCE;
    }

}
